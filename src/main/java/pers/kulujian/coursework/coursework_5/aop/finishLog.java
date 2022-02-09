package pers.kulujian.coursework.coursework_5.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class finishLog {

	//PointCut
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_5.controller.BookController.buyBook(..))")
	public void pt() {}
	
	@Before(value = "pt()")
	public void before(JoinPoint joinPoint) {
		System.out.println("前置通知");
	}
	
	@After(value = "pt()")
	public void after(JoinPoint joinPoint) {
		System.out.println("後置通知");
	}
}
