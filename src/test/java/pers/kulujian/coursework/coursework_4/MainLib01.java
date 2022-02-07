package pers.kulujian.coursework.coursework_4;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext; //ApplicationContext 關閉方法寫在這裡...

import pers.kulujian.coursework.coursework_4.controllers.ItemInvidIpidController;
import pers.kulujian.coursework.coursework_4.template.ItemInvidIpidDao;

public class MainLib01 {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
	private ItemInvidIpidController itemInvidIpidController = ctx.getBean("itemInvidIpidController", ItemInvidIpidController.class);
	private boolean stop;
	
	public void menu() {
		System.out.println("=========================================");
		System.out.println("1. 查詢 全部【發票】資料");
		System.out.println("2. 查詢 每一張發票有賣哪些商品");
		System.out.println("3. 統計 每一張發票有幾件商品");
		System.out.println("4. 統計 每一張發票價值多少");
		System.out.println("5. 統計 每一樣商品各賣了多少數量");
		System.out.println("6. 分析 哪一件商品銷售總金額最高");
		System.out.println("7. 分析 哪一張發票價值最高");
		System.out.println("0. 離開");
		System.out.println("=========================================");
		System.out.print("請選擇: ");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				itemInvidIpidController.showAllStream();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 2:
				itemInvidIpidController.findProductInInvoice();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;	
			case 3:
				itemInvidIpidController.countProductInInvoice();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 4:
				itemInvidIpidController.countMorthOfInvoice();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 5:
				itemInvidIpidController.countAmountOfText();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 6:
				itemInvidIpidController.getMaxPriceOfText();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 7:
				itemInvidIpidController.getMaxMorthOfInvoice();
				System.out.println("請按【Enter鍵】繼續");
				new Scanner(System.in).nextLine();
				break;
			case 0:
				System.out.println("離開系統");
				sc.close();
				stop = true;
				break;
		}
	}
	
	public void start() {
		while (!stop) {
			menu();
		}
	}
	
	public static void main(String[] args) {
		
		new MainLib01().start();
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
		ItemInvidIpidDao itemInvidIpidDao = ctx.getBean("itemInvidIpidDao", ItemInvidIpidDao.class);
		
		((ConfigurableApplicationContext) ctx).close(); // 關閉ApplicationContext用，需要import
		
//		itemInvIdIpidDao.queryInvoice().forEach(System.out::println);
				
//		itemInvIdIpidDao.queryItem().forEach(System.out::println);
		
//		/* 回家作業 start
			System.out.println("每一張發票有哪些商品?");
			itemInvidIpidDao.getProductByInvoice().forEach(System.out::println);
			System.out.println("==========================================================");
			System.out.println("每一張發票有幾件商品?");
			itemInvidIpidDao.getAmountByInvoice().forEach(System.out::println);
			System.out.println("==========================================================");
			System.out.println("每一張發票價值多少?");
			itemInvidIpidDao.getPriceByInvoice().forEach(System.out::println);
			System.out.println("==========================================================");
			System.out.println("每一樣商品各賣了多少數量？");
			itemInvidIpidDao.getAmountByText().forEach(System.out::println);
			System.out.println("==========================================================");
			System.out.println("哪一件商品賣得錢最多(銷售總金額最高)？");
			itemInvidIpidDao.getMaxPriceByText().forEach(System.out::println);
			System.out.println("==========================================================");
			System.out.println("哪一張發票價值最高(發票金額最高)？");
			itemInvidIpidDao.getMaxPriceByInvoice().forEach(System.out::println);
//		*/// 回家作業 end
		
	}

}
