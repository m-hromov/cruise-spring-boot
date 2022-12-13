package com.hromov.cruise.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class RepositoryAspect {

    private static final Logger LOGGER = LogManager.getLogger(RepositoryAspect.class);

    @Around("execution(public * com.hromov.cruise.repository.*.*(..))")
    public Object printExecutionTimeOfGetListMethods(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start(pjp.toShortString());
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            LOGGER.error("Error while proceeding " + className + "#" + methodName);
            throw new RuntimeException();
        } finally {
            stopWatch.stop();
            LOGGER.debug("Execution of " + className + "#" + methodName + " took " + stopWatch.getTotalTimeSeconds() + "s");
        }
    }

    @AfterThrowing(pointcut = "execution(public * com.hromov.cruise.repository.*.*(..))",
            throwing = "ex")
    public void logRepositoryExceptions(Exception ex) {
        LOGGER.error(ex);
    }
}
