package pers.kulujian.coursework.coursework_2;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB { // Json 資料庫
	
	@Autowired
	private Gson gson;
	
	// Json file 資料庫存放地
	private static final Path PATH = Paths.get("src/main/java/pers/kulujian/coursework/coursework_2/person.json");
	
	// 取得 Person 全部資料
	public List<Person> queryAll() throws Exception {
		String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		// TypeToken類是用來解決java運行時泛型類型被擦除的問題。暫時記起來就好。
		Type type = new TypeToken<ArrayList<Person>>() {}.getType();
		List<Person> people = gson.fromJson(jsonStr, type);
		// 請將 age 變為最新年齡
		/*
		Date today = new Date();
		LocalDate todayLocalDate = today.toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate();
		for(Person person : people) {
			LocalDate birthLocalDate = person.getBirth().toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate();
			int age = todayLocalDate.getYear() - birthLocalDate.getYear();
			person.setAge(age);
		}
		*/
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int todayYear = calendar.get(Calendar.YEAR);
		for(Person person : people) {
			calendar.setTime(person.getBirth());
			int biythYear = calendar.get(Calendar.YEAR);
			int age = todayYear - biythYear;
			person.setAge(age);
		}
		return people;
	}
	// 建立 Person 資料
	public boolean add(Person person) throws Exception {
		List<Person> people = queryAll();
		// 作業 1:
		LocalDate iptBirth = person.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Optional<Person> check =
							people.stream()
								.filter(user -> 
										user.getBirth()
										.toInstant()
										.atZone(ZoneId.systemDefault())
										.toLocalDate()
										.equals(iptBirth)
									&&
										user.getName()
										.equals(person.getName()))
								.findFirst();
			
		// 如果 person 已存在則 return false
		// name, age, birth 皆與目前資料庫某一 person 資料相同
		if(!check.isPresent()) {
			people.add(person);
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
//			System.out.println("新增完成");
		}//else {
//			System.out.println("資料重複，請重新輸入");
//			
//		}
		return !check.isPresent() ? true : false ;
	}
	// 更新Person的生日
	public boolean update(Person person) throws Exception {
		List<Person> people = queryAll();
		Optional<Person> check = people.stream()
								.filter(user -> user.getName()
								.equals(person.getName()))
								.findFirst();
		if(check.isPresent()) {
			for(Person newPerson : people) {
				if(newPerson.getName().equals(person.getName())) {
					newPerson.setBirth(person.getBirth());
				}
			}
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
		}
		return check.isPresent() ? true : false ;
	}
	// 刪除Person
	public boolean delete(Person person) throws Exception {
		List<Person> people = queryAll();
		Optional<Person> check = people.stream()
								.filter(user -> user.getName()
								.equals(person.getName()))
								.findFirst();
		if(check.isPresent()) {
			people = people.stream().filter(p->!(p.getName().equals(person.getName()))).collect(Collectors.toList());
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
		}
		return check.isPresent() ? true : false ;
	}
}
