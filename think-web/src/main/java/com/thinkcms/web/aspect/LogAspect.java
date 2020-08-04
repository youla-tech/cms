package com.thinkcms.web.aspect;

import com.thinkcms.system.api.log.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
class LogAspect {
	  
	  @Autowired
	  private LogService logService;
	
	  @Around("execution(* com.thinkcms.web.controller..*.*(..)) && @annotation(com.thinkcms.core.annotation.Logs)")
      public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		    Long starTime = System.currentTimeMillis();
			Object result = pjp.proceed();
		    Long time = System.currentTimeMillis()-starTime;
			logService.saveLog(pjp, time.intValue());//单位毫秒
			return result;
      }
	  
}
