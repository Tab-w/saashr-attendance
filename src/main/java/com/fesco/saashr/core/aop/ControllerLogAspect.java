package com.fesco.saashr.core.aop;

import com.fesco.saashr.core.annotation.ControllerLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author WangXingYu
 * @date 2018-01-10
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution (* com.fesco.saashr.web.controller..*.*(..)) && @annotation(com.fesco.saashr.core.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        if (logger.isInfoEnabled()) {
            logger.info("[开始环绕通知]");
        }
        long start = System.currentTimeMillis();
        try {
            getInfo(pjp);
            result = pjp.proceed();
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("[结束环绕通知:用时:" + (end - start) + "ms]");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("[环绕通知异常:用时:" + (end - start) + "ms]");
                logger.info(e.getMessage());
            }
            return result;
        }
        return result;
    }

    private void getInfo(ProceedingJoinPoint pjp) {
        try {
            //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //HttpSession session = request.getSession();
            //读取session中的用户
            //User user = (User) session.getAttribute(SessionAttr.Current_USER.getValue());
            //请求的IP
            //String ip = request.getRemoteAddr();
            String targetName = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            Object[] arguments = pjp.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String module = "";
            String operation = "";
            String description = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        Annotation annotation = method.getAnnotation(ControllerLog.class);
                        if (annotation != null) {
                            module = method.getAnnotation(ControllerLog.class).module();
                            operation = method.getAnnotation(ControllerLog.class).operation();
                            description = method.getAnnotation(ControllerLog.class).description();
                        }
                        break;
                    }
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("模块:" + module);
                logger.info("操作:" + operation);
                logger.info("描述:" + description);
            }
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage());
            }
        }
    }
}