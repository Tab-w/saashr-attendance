package com.fesco.saashr.web.aop;

import com.fesco.saashr.web.annotation.Log;
import com.fesco.saashr.web.common.SessionAttr;
import com.fesco.saashr.web.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            logger.info("===========执行前置通知===========");
        }
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 切点
     */
    @Around("controllerAspect()")
    public void around(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            logger.info("===========开始执行环绕通知===========");
        }
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("===========结束执行环绕通知===========");
                logger.info("===========用时:" + (end - start) + "秒===========");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (logger.isErrorEnabled()) {
                logger.error("===========环绕通知异常===========");
                logger.error("===========用时:" + (end - start) + "秒===========");
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 后置通知
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            logger.info("===========执行后置通知===========");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(SessionAttr.Current_USER.getValue());
        //请求的IP
        String ip = request.getRemoteAddr();
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
                        type = method.getAnnotation(Log.class).type();
                        description = method.getAnnotation(Log.class).description();
                        break;
                    }
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + type);
                logger.info("方法描述:" + description);
                logger.info("请求人:" + user.getUsername());
                logger.info("请求IP:" + ip);
            }
            // TODO: 2018-01-10 存档到数据库
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("error:" + e.getMessage());
            }
        }
    }
}