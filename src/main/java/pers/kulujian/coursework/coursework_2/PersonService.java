package pers.kulujian.coursework.coursework_2;

import java.util.Date;
import java.util.List;

public interface PersonService {
	boolean append(String name, Date birth);
	boolean append(Person person);
	List<Person> findAllPersons();
	Person getPerson(String name);
	List<Person> getPerson(Date birth);
	List<Person> getPerson(int age);
	boolean modify(String name, Date birth);
	boolean modify(Person person);
	boolean remove(String name);
	
}
