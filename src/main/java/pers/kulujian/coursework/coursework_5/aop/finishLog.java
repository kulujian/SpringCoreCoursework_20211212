package pers.kulujian.coursework.coursework_5.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;

import pers.kulujian.coursework.coursework_5.dao.BookDao;

@Component
@Aspect
public class finishLog {

	@Autowired
	private BookDao bookdao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//PointCut 切點1
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_5.controller.BookController.buyBook(..))")
	public void pt1() {}

	//PointCut 切點2
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_5.controller.BookController.buyBooks(..))")
	public void pt2() {}
	
	
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
	@Around(value = "pt1() || pt2()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object result = null;
		// 1. 前置通知
		
//		int wid = (Integer)joinPoint.getArgs()[0];
//		List<Integer> a = Stream.of((Integer[])joinPoint.getArgs()[1]).collect(Collectors.toList());
//		Iterator<Integer> b = a.iterator();
//		List<Map> tt = new ArrayList();
//		while (b.hasNext()) {
//			Map<String, Object> xx = new LinkedHashMap<String, Object>();
//			int bid = b.next();
//			xx.put("bid", bid);
//			String sql1 = "SELECT bname FROM book WHERE bid = ?";
//			String bname = jdbcTemplate.queryForObject(sql1, String.class, bid);
//			xx.put("bname", bname);
//			String sql2 = "SELECT wname FROM wallet WHERE wid = ?";
//			String wname = jdbcTemplate.queryForObject(sql2, String.class, wid);
//			xx.put("wname", wname);
//			int price = bookdao.getPrice(bid);
//			xx.put("price", price);
//			tt.add(xx);
//		}
//		tt.forEach(System.out::println);
//		System.out.println(tt.size());
//
//		String sql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
//				+ " values (?, ?, ?, ?, ?, ?)";
//		
//		System.out.printf("");
//		for(int i=0; i<tt.size(); i++) {
//			jdbcTemplate.update(sql, wid, tt.get(i).get("wname"), tt.get(i).get("bid"), tt.get(i).get("bname"), 1, 1*(int)(tt.get(i).get("price")));
//			
//		}
		
		
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
				System.out.println(salseLog(joinPoint)>0 ? "紀錄完成" : "紀錄失敗");
			}
		}
		return result;
	}
	
	
	public Integer salseLog(ProceedingJoinPoint joinPoint) {
		Integer result = 0;
		int wid = joinPoint.getArgs()[0].getClass().getSimpleName().equals("Integer") ? (Integer)joinPoint.getArgs()[0] : 0;

		String sql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		if(joinPoint.getArgs()[1].getClass().getSimpleName().equals("Integer")) {
			
			int bid = (Integer)joinPoint.getArgs()[1];
			String sql1 = "SELECT bname FROM book WHERE bid = ?";
			String bname = jdbcTemplate.queryForObject(sql1, String.class, bid);
			String sql2 = "SELECT wname FROM wallet WHERE wid = ?";
			String wname = jdbcTemplate.queryForObject(sql2, String.class, wid);
			int price = bookdao.getPrice(bid);
			return jdbcTemplate.update(sql, wid, wname, bid, bname, 1, 1*price);
			
		} else {
			
			List<Integer> bids_t = Stream.of((Integer[])joinPoint.getArgs()[1]).collect(Collectors.toList());
			Iterator<Integer> bids = bids_t.iterator();
			List<Map> order_log = new ArrayList();
			while (bids.hasNext()) {
				Map<String, Object> temp = new LinkedHashMap<String, Object>();
				int bid = bids.next();
				temp.put("bid", bid);
				String sql1 = "SELECT bname FROM book WHERE bid = ?";
				String bname = jdbcTemplate.queryForObject(sql1, String.class, bid);
				temp.put("bname", bname);
				String sql2 = "SELECT wname FROM wallet WHERE wid = ?";
				String wname = jdbcTemplate.queryForObject(sql2, String.class, wid);
				temp.put("wname", wname);
				int price = bookdao.getPrice(bid);
				temp.put("price", price);
				order_log.add(temp);
			}
			for(int i=0; i<order_log.size(); i++) {
				result = jdbcTemplate.update(sql, 
					wid, 
					order_log.get(i).get("wname"), 
					order_log.get(i).get("bid"), 
					order_log.get(i).get("bname"), 
					1, 
					1*(int)(order_log.get(i).get("price")));
			}
			return result;
			
		}
	}
}
