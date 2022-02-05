package pers.kulujian.coursework.coursework_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonController {
	
	/*	下面main方法為測試controller與service連線是否正常
	 *  目前只做了兩種方法，剩下五種還沒有做
	 * 
	 **/
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonController personController = ctx.getBean("personController", PersonController.class);
		
		//personContolller.printAllPersons();
		//personContolller.addPerson("Bob", 2013, 12, 26);
		//personContolller.printAllPersons();
		
//		System.out.println(personController.getPersonByName("Randy"));
//		System.out.println(personController.getPersonByName("Tom"));
		
		personController.getPersonByName("Randy");
		personController.getPersonByName("kulujain");
	}

}
