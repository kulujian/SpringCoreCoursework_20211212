package pers.kulujian.coursework.coursework_5.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kulujian.coursework.coursework_5.dao.BookDao;

@Component
@Aspect
public class finishLog {

	@Autowired
	private BookDao bookdao;
	
	//PointCut 切點
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_5.controller.BookController.buyBook(..))")
	public void pt() {}
	
//	@Before(value = "pt()")
	public void before(JoinPoint joinPoint) {
		System.out.printf("%s, %s, %s\n", 
			"前置通知", 
			joinPoint.getSignature().getName(), 
			Arrays.toString(joinPoint.getArgs()));
//		System.out.println("getKind       : "+joinPoint);
//		System.out.println("getTarget     : "+joinPoint.getTarget());
//		System.out.println("getThis       : "+joinPoint.getThis());
//		System.out.println("getClass      : "+joinPoint.getClass());
//		System.out.println("getStaticPart : "+joinPoint.getStaticPart());
//		System.out.println("=====================================================================================");
//		System.out.println("getSignature().getDeclaringTypeName() : "+joinPoint.getSignature().getDeclaringTypeName());
//		System.out.println("getSignature().getModifiers()         : "+joinPoint.getSignature().getModifiers());
//		System.out.println("getSignature().getDeclaringType()     : "+joinPoint.getSignature().getDeclaringType());
//		System.out.println("getSignature().hashCode()             : "+joinPoint.getSignature().hashCode());
//		System.out.println("getSignature().toString()             : "+joinPoint.getSignature().toString());
//		System.out.println("getSignature().toShortString()        : "+joinPoint.getSignature().toShortString());
//		System.out.println("getSignature().toLongString()         : "+joinPoint.getSignature().toLongString());
//		System.out.println("getSignature().getClass()             : "+joinPoint.getSignature().getClass().get);
		
		
	}
	
//	@After(value = "pt()")
	public void after(JoinPoint joinPoint) {
		System.out.printf("%s, %s, %s\n", 
				"後置通知",
				joinPoint.getSignature().getName(), 
				Arrays.toString(joinPoint.getArgs()));
		
//		System.out.println(bookdao.getPrice((Integer)joinPoint.getArgs()[1]));
	}
	
//	@After(value = "pt()")
//	@AfterReturning(value = "pt()", returning = "result")
	public void afterReturning (Object result) {
		System.out.println("返回通知 - result = " + result);
	}
	

	// 環繞通知
	@Around(value = "pt()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object result = null;
		// 1. 前置通知
//		System.out.println("環繞通知：前置通知");
		try {
			// 2. 執行業務邏輯方法
			result = joinPoint.proceed(); 
			// 3. 返回通知
			System.out.println("環繞通知：返回通知 result = " + result);
		} catch (Throwable ex) {
			// 4. 異常通知
			System.out.println("環繞通知：異常通知 e = " + ex);
		} finally {
			// 5. 後置通知
			System.out.println("環繞通知：後置通知");
			if(result.equals(true)) {
				System.out.println(bookdao.getPrice((Integer)joinPoint.getArgs()[1]));
				System.out.println(bookdao.getStockAmount((Integer)joinPoint.getArgs()[1]));
				System.out.println(bookdao.getWalletMoney((Integer)joinPoint.getArgs()[0]));
			}
			
		}
		return result;
	}
	
}
