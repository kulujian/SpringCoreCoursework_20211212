package pers.kulujian.coursework.coursework_2;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
	private PersonController personController = ctx.getBean("personController", PersonController.class);
	private boolean stop;
	
	private void menu() {
		System.out.println("=========================================");
		System.out.println("1. 建立 Person 資料");
		System.out.println("2. 取得 Person 全部資料");
		System.out.println("3. 根據姓名取得 Person");
		System.out.println("4. 取得今天生日的 Person");
		System.out.println("5. 取得某一歲數以上的 Person");
		System.out.println("6. 根據姓名來修改Person的生日");
		System.out.println("7. 根據姓名來刪除Person");
		System.out.println("0. 離開");
		System.out.println("=========================================");
		System.out.print("請選擇: ");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				createPerson();
				break;
			case 2:
				printPersons();
				break;	
			case 3:
				searchPersonByName();
				break;
			case 4:
				searchBirchByToday();
				break;
			case 5:
				searchPersonByAge();
				break;
			case 6:
				updateBirthByName();
				break;
			case 7:
				deletePersonByName();
				break;
			case 0:
				System.out.println("離開系統");
				stop = true;
				break;
		}
	}
	// 1. 建立 Person 資料
	private void createPerson() {
		System.out.print("請輸入姓名 生日年 月 日: ");
		// Ex: Jack 1999 4 5
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		personController.addPerson(name, yyyy, mm, dd);
	}
	// 2. 取得 Person 全部資料
	private void printPersons() {
		personController.printAllPersons();
	}
	// 3. 根據姓名取得 Person
	private void searchPersonByName() {
		System.out.print("請輸入姓名: ");
		// Ex: Jack
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		personController.getPersonByName(name);
	}
	// 4. 取得今天生日的 Person
	private void searchBirchByToday() {
		personController.getPersonByBirth();
	}
	// 5. 取得某一歲數以上的 Person
	private void searchPersonByAge() {
		System.out.print("請輸入年齡: ");
		// Ex: 18
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		personController.getPersonByAge(age);
	}
	// 6. 根據姓名來修改Person的生日
	private void updateBirthByName() {
		System.out.print("請輸入姓名及要修改的生日(yyyy mm dd)\n請使用空格分開： ");
		// Ex: Jack 1999 1 1
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		personController.updatePerson(name, yyyy, mm, dd);
		
	}
	// 7. 根據姓名來刪除Person
	private void deletePersonByName() {
		System.out.print("請輸入姓名: ");
		// Ex: Jack
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		personController.deletePerson(name);
	}
	
	
	public void start() {
		while (!stop) {
			menu();
		}
	}
	
	
	public static void main(String[] args) {
		new PersonSystem().start();
	}

}
