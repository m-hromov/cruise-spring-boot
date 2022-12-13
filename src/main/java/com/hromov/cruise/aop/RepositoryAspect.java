package com.hromov.cruise.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Aspect
@Component
public class RepositoryAspect {
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
            log.error("Error while proceeding " + className + "#" + methodName);
            throw new RuntimeException();
        } finally {
            stopWatch.stop();
            log.debug("Execution of " + className + "#" + methodName + " took " + stopWatch.getTotalTimeSeconds() + "s");
        }
    }

    @AfterThrowing(pointcut = "execution(public * com.hromov.cruise.repository.*.*(..))",
            throwing = "ex")
    public void logRepositoryExceptions(Exception ex) {
        log.error(ex);
    }
}
