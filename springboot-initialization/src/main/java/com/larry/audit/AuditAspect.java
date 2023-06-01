package com.larry.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuditAspect {

    @Pointcut("@annotation(com.larry.audit.Audited)")
    public void doAudit(){
    }

    @Around("doAudit()")
    public Object executeWithAuditContext(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("@@@@ Auditted@@@@" + method.getName());
        Audited audited = method.getAnnotation(Audited.class);
        System.out.println(audited.action());
        return joinPoint.proceed();
    }
}
