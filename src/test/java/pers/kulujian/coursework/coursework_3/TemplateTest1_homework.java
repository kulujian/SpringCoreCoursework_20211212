package pers.kulujian.coursework.coursework_3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.kulujian.coursework.coursework_3.template.EmpDao_homework;

public class TemplateTest1_homework {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
	private EmpDao_homework empDao_homework = ctx.getBean("empDao_homework", EmpDao_homework.class);
	private boolean stop;
	
	
	// 又來一個選單
	private void menu() {
		System.out.println("=================================================");
		System.out.println(" 1. 取得 Emp 全部資料");
		System.out.println(" 2. 根據 eid 取得 ename");
		System.out.println(" 3. 查詢 log 紀錄");
		System.out.println(" 0. 離開");
		System.out.println("=================================================");
		System.out.print("請選擇: ");
		
		Scanner sc = new Scanner(System.in);
		String pause;
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				printEmp();
				break;
			case 2:
				searchPersonByName();
				break;	
			case 3:
				viewLog();
				break;
			case 0:
				System.out.println("離開系統");
				stop = true;
				break;
		}
	}
//	 1. 取得 Emp 全部資料
	private void printEmp() {
		List<Map<String, Object>> stream = empDao_homework.queryAll();
		System.out.println("+----+------------+-----+-----------------------+");
		System.out.println("| id |    name    | age |       createtime      |");
		System.out.println("+----+------------+-----+-----------------------+");
		for ( Map<String, Object> emps : stream ) {
			System.out.printf("| %2d | %10s | %3d | %s |\n",emps.get("eid"),emps.get("ename"),emps.get("age"),emps.get("createtime"));
			System.out.println("+----+------------+-----+-----------------------+");
		}
		System.out.println("請按【enter鍵】繼續 : ");
		String pause = new Scanner(System.in).nextLine();
	}
//	 2. 根據 eid 取得 Ename
	private void searchPersonByName() {
		System.out.print("請輸入Eid: ");
		String eid = new Scanner(System.in).next();
		String ename = empDao_homework.searchNameById(eid);
		System.out.println("+-----------------------------------------------+"); // 47
		System.out.printf("|     根據  eid (%3s) 取得的 Ename 為 : %8s    |\n", eid, ename); // 47-13-30
		System.out.println("+----+------------+-----+-----------------------+");
		
		System.out.println("請按【enter鍵】繼續 : ");
		String pause = new Scanner(System.in).nextLine();
	}
//	 3. 查詢 log 紀錄
	private void viewLog() {
		List<Map<String, Object>> stream = empDao_homework.showLog();
		System.out.println("+------+----------------+-----------------------+");
		System.out.println("| lid  |   method_name  |     log_timestamp     |");
		System.out.println("+------+----------------+-----------------------+");
		for ( Map<String, Object> logs : stream ) {
			System.out.printf("| %3d  | %14s | %s |\n",logs.get("lid"),logs.get("method_name"),logs.get("log_timestamp"));
			System.out.println("+------+----------------+-----------------------+");
		}
		System.out.println("請按【enter鍵】繼續 : ");
		String pause = new Scanner(System.in).nextLine();
	}
	
	public void start() {
		while (!stop) {
			menu();
		}
	}
	
	
	
	
	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
//		EmpDao_homework empDao = ctx.getBean("empDao_homework", EmpDao_homework.class);
//		System.out.println(empDao.queryAll());
		
		// 如何取得 eid=2 的員工姓名？ (請使用 java 8 stream)
//		String ename = empDao.queryAll().stream()
//				.filter(e->(e.get("eid")+"").equals("2"))	//Map取得資料要使用get()。Map是Object要轉字串
//				.findFirst()
//				.get()		// 取得所有資料(eid、ename、age、createtime)之後還是 Map(Object)
//				.get("ename")+"";	// Map取得資料要使用get()。取得後要在轉成字串，才會正常顯示
//		System.out.println(ename);
		
		/*
		 *  HomeWork 利用 AOP 
		 *  在每次的 查詢中 都可以將查詢時間的 Log 記錄下來
		 *  +
		*/
		new TemplateTest1_homework().start();
		
		// 多筆查詢 1
//		System.out.println(empDao.queryListEmps());
		// 多筆查詢 2
//		System.out.println(empDao.qreryListEmps2());
	}
}
