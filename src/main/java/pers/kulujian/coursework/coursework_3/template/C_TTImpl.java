package pers.kulujian.coursework.coursework_3.template;

import org.springframework.stereotype.Component;

@Component
public class C_TTImpl implements C_TT{

	@Override
	public Integer add(Integer x, Integer y) {

		Integer sum = x + y;
		
		return sum;
	}

}
