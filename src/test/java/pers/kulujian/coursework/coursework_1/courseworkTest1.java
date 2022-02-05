package pers.kulujian.coursework.coursework_1;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.kulujian.coursework.coursework_1.Clazz;
import pers.kulujian.coursework.coursework_1.Student;
import pers.kulujian.coursework.coursework_1.Teacher;

public class courseworkTest1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
		
		Student john = ctx.getBean("s1", Student.class);
//		System.out.println(john);
		
		Student mary = ctx.getBean("s2", Student.class);
//		System.out.println(mary);
		Teacher t1 = ctx.getBean("t1", Teacher.class);
		 
		// 請問 mary 的老師有誰 ? 印出 name
//		System.out.println(mary.getName() + "的課程：" + mary.getClazzs());
		Teacher[] teachers = {ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class)};
//		Set<Teacher> teachers2 = new LinkedHashSet<>();
//		for(Teacher teacher : teachers) {
//			clazz_loop:
//			for(Clazz cla1 : teacher.getClazzs()) {
//				for(Clazz cla2 : mary.getClazzs()) {
//					System.out.println(cla1+"1");
//					if(cla1.getId() == cla2.getId()) {
//						System.out.println(cla2.getId()+"2");
//						teachers2.add(teacher);
//						break clazz_loop;
//					}
//				}
//			}
//		}
//		System.out.println(mary.getName() + "的老師(物件)：" + teachers2);
//		System.out.println(mary.getName() + "的老師(名字)：" + teachers2.stream()
//														.map(Teacher::getName)
//														.collect(Collectors.toSet()));
		
		/*	以下是參考 陳政皓 同學的寫法
		 * 	之前不知道 往下 抓到ID及比較之後如何跳回上層在抓取老師的NAME
		 *  現在知道可以再MAP一次放入Teacher(Object) getName 方法
		 *  最後沒有直接foreach而是收集後在另外print出來，但是順序怪怪的
		 *  
		 *  PS:anyMatch的用法還在理解中..
		 */

		Set<String> teachers2 = Stream.of(teachers).filter(
				t->t.getClazzs().stream().map(Clazz::getId).anyMatch(
						id->mary.getClazzs().stream().map(Clazz::getId).anyMatch(id2->id2==id)
						)
				).map(Teacher::getName).collect(Collectors.toSet());
		System.out.println(mary.getName() + "的老師(名字)：" + teachers2);
	}
}
