package pers.kulujian.coursework.coursework_5.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
	
	// 環繞通知
	@Around(value = "pt1() || pt2()")
	public Object around(ProceedingJoinPoint joinPoint) {
		Object result = null;
		// 1. 前置通知
		
/*  練習如何分類統計
		Object[] a = joinPoint.getArgs();
		List<Object> b = Arrays.asList(a);
		Iterator<Integer> bids =  Arrays.stream((Integer[])a[1])
										.distinct().collect(Collectors.toList()).iterator();
		while (bids.hasNext()) {
			int bid = bids.next();
			if (b.get(1) instanceof Integer[]) {
				List<Integer> e = Arrays.asList((Integer[])b.get(1));
				System.out.printf("書號 : %d , 數量 : %d\n",bid,Collections.frequency(e, bid)));
			}
		}
*/
		
/*	完成版定稿，原預想最終insert時，值要在迴圈外使用，所設定很多區域變數
		List<Integer> allAmount = Arrays.stream((Integer[])joinPoint.getArgs()[1])
										.collect(Collectors.toList());
		Iterator<Integer> bids =  allAmount.stream().distinct().collect(Collectors.toList()).iterator();
		List<Integer> bid = new ArrayList<Integer>(); 
		List<Integer >buyAmount = new ArrayList<Integer>();
		List<Map<String, Object>> bookStream = new LinkedList<Map<String,Object>>();
		String searchSql1 = "SELECT wid, wname FROM wallet WHERE wid = ?";
		List<Map<String, Object>> walletStream = jdbcTemplate.queryForList(searchSql1,(Integer)joinPoint.getArgs()[0]);
		String searchSql2 = "SELECT bid, bname, price FROM book WHERE bid = ?";
		String insertLogSql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
				+ " values (?, ?, ?, ?, ?, ?)";
		int i = 0;
		while (bids.hasNext()) {
			bid.add(bids.next());
			buyAmount.add(Collections.frequency(allAmount, bid.get(i)));
			bookStream.addAll(jdbcTemplate.queryForList(searchSql2,bid.get(i)));
			
			jdbcTemplate.update(insertLogSql, 
					walletStream.get(0).get("wid"), 
					walletStream.get(0).get("wname"), 
					bookStream.get(i).get("bid"), 
					bookStream.get(i).get("bname"), 
					buyAmount.get(i), 
					buyAmount.get(i)*(int)(bookStream.get(i).get("price")));
			i++;
		}
		walletStream.forEach(System.out::println);
		buyAmount.forEach(System.out::println);
		bookStream.forEach(System.out::println);
*/
		
		
		
		
/*	練習 .add()  與  .addAll()	
		String searchSql = "SELECT bname, price FROM book";// WHERE bid = ?";
		List<Map<String, Object>> logs = new ArrayList();logs.addAll(jdbcTemplate.queryForList(searchSql));//(Integer)joinPoint.getArgs()[1]);
		logs.forEach(System.out::println);
		System.out.println("0 : "+logs.get(0));
		System.out.println(logs.get(0).get("price"));
		System.out.println(logs.get(0).get("bname"));
*/
	
/*	初代版本，定稿
		int wid = (Integer)joinPoint.getArgs()[0];
		List<Integer> a = Stream.of((Integer[])joinPoint.getArgs()[1]).collect(Collectors.toList());
		Iterator<Integer> b = a.iterator();
		List<Map> tt = new ArrayList();
		while (b.hasNext()) {
			Map<String, Object> xx = new LinkedHashMap<String, Object>();
			int bid = b.next();
			xx.put("bid", bid);
			String sql1 = "SELECT bname FROM book WHERE bid = ?";
			String bname = jdbcTemplate.queryForObject(sql1, String.class, bid);
			xx.put("bname", bname);
			String sql2 = "SELECT wname FROM wallet WHERE wid = ?";
			String wname = jdbcTemplate.queryForObject(sql2, String.class, wid);
			xx.put("wname", wname);
			int price = bookdao.getPrice(bid);
			xx.put("price", price);
			tt.add(xx);
		}
		tt.forEach(System.out::println);
		System.out.println(tt.size());

		String sql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		System.out.printf("");
		for(int i=0; i<tt.size(); i++) {
			jdbcTemplate.update(sql, wid, tt.get(i).get("wname"), tt.get(i).get("bid"), tt.get(i).get("bname"), 1, 1*(int)(tt.get(i).get("price")));
			
		}
*/		
		
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
/* 初代版本，買一本記一次			
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
*/		

			/**
			 * 	買多本LOG記錄，book價格改變不影響LOG金額，原先預想的是方法要可以共用，但目前還寫不出來
			 * 	1.將多餘的宣告拿掉
			 *  2.紀錄方式為一個品項無論數量紀一筆，不同品項同時間COMMIT，紀錄時間會一致。
			 */
			List<Integer> allAmount = Arrays.stream((Integer[])joinPoint.getArgs()[1])
											.collect(Collectors.toList());
			Iterator<Integer> bids =  allAmount.stream().distinct().collect(Collectors.toList()).iterator();
//			List<Integer> bid = new ArrayList<Integer>(); 
//			List<Integer >buyAmount = new ArrayList<Integer>();
//			List<Map<String, Object>> bookStream = new LinkedList<Map<String,Object>>();
			String searchSql1 = "SELECT wid, wname FROM wallet WHERE wid = ?";
			List<Map<String, Object>> walletStream = jdbcTemplate.queryForList(searchSql1,wid);
			String searchSql2 = "SELECT bid, bname, price FROM book WHERE bid = ?";
//			String insertLogSql = "insert into order_log (customerNum, customerName, productCode, productName, productAmount, productSumMoney)"
//					+ " values (?, ?, ?, ?, ?, ?)";
//			int i = 0;
			while (bids.hasNext()) {
//				bid.add(bids.next());
				int bid = bids.next();
//				buyAmount.add(Collections.frequency(allAmount, bid.get(i)));
				List<Map<String, Object>> bookStream = jdbcTemplate.queryForList(searchSql2,bid);
				
				result = jdbcTemplate.update(sql, 
						walletStream.get(0).get("wid"), 
						walletStream.get(0).get("wname"), 
						bookStream.get(0).get("bid"), 
						bookStream.get(0).get("bname"), 
						Collections.frequency(allAmount, bid), 
						Collections.frequency(allAmount, bid) * (int)(bookStream.get(0).get("price")));
//				i++;
			}
			
			
			return result;
			
		}
	}
}
