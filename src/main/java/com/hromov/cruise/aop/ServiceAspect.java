package com.hromov.cruise.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Aspect
@Component
public class ServiceAspect {
    @Around("execution(public * *(..)) && within(com.hromov.cruise.service..*)")
    public Object printExecutionTimeOfGetListMethods(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start(pjp.toShortString());
        Object obj = pjp.proceed();
        stopWatch.stop();
        log.debug("Execution of " + className + "#" + methodName + " took " + stopWatch.getTotalTimeSeconds() + "s");
        return obj;
    }
}
