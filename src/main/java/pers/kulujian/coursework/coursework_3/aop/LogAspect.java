package pers.kulujian.coursework.coursework_3.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	
	
	@Before(value = "execution(* pers.kulujian.coursework.coursework_3.template.C_TTImpl.add(..))")
	public void before(JoinPoint joinPoint) {
		System.out.printf("%s %s %s\n", "前置通知", 
										joinPoint.getSignature().getName(), 
										Arrays.toString(joinPoint.getArgs()));
	}

}
