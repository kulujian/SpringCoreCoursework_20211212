package pers.kulujian.coursework.coursework_3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.kulujian.coursework.coursework_3.template.C_TT;

public class testAAA {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-config.xml");
		C_TT c_TT = ctx.getBean("c_TTImpl", C_TT.class);
		
		System.out.println(c_TT.add(10, 20));

	}

}
