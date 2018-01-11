package com.fesco.saashr.core.aop;

import com.fesco.saashr.core.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Controller层切点
     */
    @Pointcut("execution (* com.fesco.saashr.web.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     *
     * @param jp 切点
     */
    @Before("controllerAspect()")
    public void before(JoinPoint jp) {
        if (logger.isInfoEnabled()) {
            logger.info("[前置通知]");
        }
    }

    /**
     * 环绕通知
     *
     * @param pjp 切点
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        if (logger.isInfoEnabled()) {
            logger.info("[开始环绕通知]");
        }
        long start = System.currentTimeMillis();
        try {
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

    /**
     * 后置通知
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            logger.info("[后置通知]");
        }
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpSession session = request.getSession();
        //读取session中的用户
        //User user = (User) session.getAttribute(SessionAttr.Current_USER.getValue());
        //请求的IP
        //String ip = request.getRemoteAddr();
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String type = "";
            String description = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        Annotation annotation = method.getAnnotation(Log.class);
                        if (annotation != null) {
                            type = method.getAnnotation(Log.class).type();
                            description = method.getAnnotation(Log.class).description();
                        }
                        break;
                    }
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + type);
                logger.info("方法描述:" + description);
                logger.info("请求人:" + "user");
                logger.info("请求IP:" + "localhost");
            }
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage());
            }
        }
    }

    /**
     * 异常通知
     *
     * @param jp 切点
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void afterThrowing(JoinPoint jp, ArithmeticException e) {
        if (logger.isInfoEnabled()) {
            logger.info("[异常通知]");
            logger.info(e.getMessage());
        }
    }
}