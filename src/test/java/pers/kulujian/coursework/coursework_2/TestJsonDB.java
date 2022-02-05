package pers.kulujian.coursework.coursework_2;

import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJsonDB {
	
	/*	下面main方法為測試jsondb驅動與person.json資料庫交互是正常
	 *  目前只做了兩種方法，剩下五種還沒有做
	 * 
	 **/
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		JsonDB jsonDB = ctx.getBean("jsonDB", JsonDB.class);
		
		
//		System.out.println(jsonDB.queryAll());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		boolean check = jsonDB.add(new Person("John", 0, sdf.parse("2000/1/1")));
		System.out.println(check);
		
//		System.out.println(jsonDB.queryAll());
	}

}
