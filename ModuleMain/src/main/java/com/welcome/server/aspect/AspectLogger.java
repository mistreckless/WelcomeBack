package com.welcome.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by @mistreckless on 12.03.2017.!
 */
@Aspect
@Component
public class AspectLogger {
    private Logger logger= LoggerFactory.getLogger("application");

    @Before("execution(public * com.welcome.server.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.debug("Before "+ joinPoint.getSignature().getName()+ " called with args "+ Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution( public * com.welcome.server.service.impl.*.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        logger.debug("After "+ joinPoint.getSignature().getName()+ " return "+result.toString());
    }
}
