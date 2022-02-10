package pers.kulujian.coursework.coursework_5.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pers.kulujian.coursework.coursework_5.dao.BookDao;

@Component
@Aspect
public class finishLog {

	@Autowired
	private BookDao bookdao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//PointCut 切點
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_5.controller.BookController.buyBooks(..))")
	public void pt() {}
	
//	@Before(value = "pt()")
	public void before(JoinPoint joinPoint) {
		System.out.printf("%s, %s, %s\n", 
			"前置通知", 
			joinPoint.getSignature().getName(), 
			Arrays.toString(joinPoint.getArgs()));
	}
	
//	@After(value = "pt()")
	public void after(JoinPoint joinPoint) {
		System.out.printf("%s, %s, %s\n", 
				"後置通知",
				joinPoint.getSignature().getName(), 
				Arrays.toString(joinPoint.getArgs()));
		
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
		System.out.println(Stream.of(joinPoint.getArgs()).getClass());
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
//			System.out.println("環繞通知：後置通知");
			if(result.equals(true)) {
//				salseLog(joinPoint);
			}
		}
		return result;
	}
	
	
	public Integer salseLog(ProceedingJoinPoint joinPoint) {
		int wid = (int)joinPoint.getArgs()[0];
		int bid = (int)joinPoint.getArgs()[1];
		
		String sql1 = "SELECT bname FROM book WHERE bid = ?";
		String bname = jdbcTemplate.queryForObject(sql1, String.class, bid);
		String sql2 = "SELECT wname FROM wallet WHERE wid = ?";
		String wname = jdbcTemplate.queryForObject(sql2, String.class, wid);
		int price = bookdao.getPrice(bid);
		
		String sql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		System.out.printf("");
		return jdbcTemplate.update(sql, wid, wname, bid, bname, 1, 1*price);
		
	}
	
}
