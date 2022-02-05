package pers.kulujian.coursework.coursework_2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
 * 
 * 功能:
 * 1. 建立 Person 資料
 * 		-> 輸入 name, birth
 * 2. 取得 Person 全部資料
 * 		-> 不用輸入參數
 * 3. 根據姓名取得 Person
 * 		-> 輸入 name
 * 4. 取得今天生日的 Person
 * 		-> 輸入今天日期
 * 5. 取得某一歲數以上的 Person
 * 		-> 輸入 age
 * 6. 根據姓名來修改Person的生日
 * 		-> 輸入 name, birth
 * 7. 根據姓名來刪除Person
 * 		-> 輸入 name
 * */

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;

	// 1. 建立 Person 資料
	public void addPerson(String name, int yyyy, int mm, int dd) {
		// 1. 判斷 name, yyyy, mm 與 dd 是否有資料? JsonDB判斷回傳 true or false
		// 2. 將 yyyy/mm/dd 轉日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			addPerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addPerson(String name, Date birth) {
		// 1. 判斷 name 與 birth 是否有資料? JsonDB判斷回傳 true or false
		// 2. 建立 Person 資料
		boolean check = personService.append(name, birth);
		System.out.println("+--------------+---------+--------------+");
		System.out.printf("|          add person:   %-6b         |\n", check);
		System.out.println("+--------------+---------+--------------+");
	}

	// 2. 取得 Person 全部資料
	public void printAllPersons() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		for(Person p : people) {
//			String birthday = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth()));
			System.out.println("+--------------+---------+--------------+");
		}
	}

	// 3. 根據姓名取得 Person
	public void getPersonByName(String name) {
		// 1. 判斷 name ? JsonDB判斷回傳 true or false
		// 2. 根據姓名取得 Person
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		Person person = personService.getPerson(name);
		if(person == null) {
			System.out.println("+--------------+---------+--------------+");
			System.out.printf("|   Name : %-10s Not found         |\n", name); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
		}else {
			List<Person> pp2 = Stream.of(person).collect(Collectors.toList());
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			for(Person p : pp2) {
//				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth()));
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	
	// 4. 取得今天生日的 Person
	public void getPersonByBirth() {
		// 1. 判斷 有無今日 birth ? JsonDB判斷回傳 true or false
		// 2. 依據目前日期取得當日生日 person
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM-dd");
		Date today = new Date();
//		 資料呈現
		List<Person> person = personService.getPerson(today);
		if(person.isEmpty()) {
			System.out.println("+--------------+---------+--------------+");
			System.out.printf("|   Birthday in (  %-5s  ) Not found   |\n", new SimpleDateFormat("MM-dd").format(today)); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			
		}else {
//			List<Person> pp2 = Stream.of(person).collect(Collectors.toList());
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			for(Person p : person) {
//				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth()));
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	
	// 5. 取得某一歲數以上的 Person
	public void getPersonByAge(int age) {
		// 1. 判斷大於age person ? JsonDB判斷回傳 true or false
		// 2. 依據輸入age取得大於age person
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		 資料呈現
		List<Person> person = personService.getPerson(age);
		if(person.isEmpty()) {
			System.out.println("+--------------+---------+--------------+");
			System.out.printf("|     Older than (%2d) age Not found     |\n", age); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			
		}else {
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			for(Person p : person) {
//				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth()));
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	
	// 6. 根據姓名來修改Person的生日
	public void updatePerson(String name, int yyyy, int mm, int dd) {
		// 1. 將 yyyy/mm/dd 轉日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			updatePerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updatePerson(String name, Date birth) {
		// 1. 判斷 name 是否有資料? JsonDB判斷回傳 true or false
		// 2. 修改 Person birth 資料
		boolean check = personService.modify(name, birth);
		System.out.println("+--------------+---------+--------------+");
		System.out.printf("|       update person:   %-6b         |\n", check);
		System.out.println("+--------------+---------+--------------+");
	}
	
	// 7. 根據姓名來刪除Person
	public void deletePerson (String name) {
		// 1. 判斷 name 是否有資料? JsonDB判斷回傳 true or false
		// 2. 建立 Person 資料
		boolean check = personService.remove(name);
		System.out.println("+--------------+---------+--------------+");
		System.out.printf("|       delete person:   %-6b         |\n", check);
		System.out.println("+--------------+---------+--------------+");
	}
}
