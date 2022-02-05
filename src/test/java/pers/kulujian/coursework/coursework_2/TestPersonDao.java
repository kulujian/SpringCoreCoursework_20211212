package pers.kulujian.coursework.coursework_2;

import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDao {

	/*	下面main方法為測試dao與jsondb連線是否正常
	 * 	目前只做了兩種方法，剩下五種還沒有做
	 * 
	 **/
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonDao personDao = ctx.getBean("personDaoImpl", PersonDaoImpl.class);
			
		
		System.out.println(personDao.readAll());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		boolean check = personDao.create(new Person("Mary", 0, sdf.parse("2010/1/1")));
		System.out.println(check);
		
		System.out.println(personDao.readAll());
	}

}
