package pers.kulujian.coursework.coursework_5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.kulujian.coursework.coursework_5.controller.BookController;
import pers.kulujian.coursework.coursework_5.controller.Order_logController;

public class TestBook {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");
		BookController bookController = ctx.getBean(BookController.class);
		Order_logController order_logController = ctx.getBean(Order_logController.class);
//		System.out.println(bookController);
		// case 1 買單本
//		Integer wid = 1;
//		Integer bid = 1;
//		bookController.buyBook(wid, bid);

		// case 2 買多本
		Integer wid = 1;
		bookController.buyBooks(wid, 1,2,2,2,1,1,2,2,2,1);
		
		order_logController.printAllOrder_log();
		
	}
}
