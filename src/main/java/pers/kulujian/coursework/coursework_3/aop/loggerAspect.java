package pers.kulujian.coursework.coursework_3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pers.kulujian.coursework.coursework_3.template.EmpDao_homework;

@Component	// 宣告接受管理
@Aspect		// 宣告為 切面程式
@Order(1)	// 數字越小越先執行(預設是 int 的最大值，【21億多...】)
public class loggerAspect {
	
	@Autowired
	private EmpDao_homework empDao;

	// PointCut 切入點，用來定義 Joinpoint 連接點 
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_3.template.EmpDao_homework.queryAll(..))")
	public void pt1() {}

	// PointCut 切入點，用來定義 Joinpoint 連接點
	@Pointcut(value = "execution(* pers.kulujian.coursework.coursework_3.template.EmpDao_homework.searchNameById(..))")
	public void pt2() {}

	// PointCut 切入點，用來定義 Joinpoint 連接點
//	@Pointcut(value = "execution(* com.study.springcore.jdbc.template.EmpDao.showLog(..))")
//	public void pt3() {}


	// 前置通知：運行在目標方法執行之前，所以與例外發生沒有關係
	@Before(value = "pt1() || pt2()") // 軔入點表達式支援： &&、||、! ， 例如："pt() && !pt2()"
	public void befor(JoinPoint joinPoint) {
		// joinpoint
//		String method_name = joinPoint.getSignature().getName().toString();
//		System.out.println(method_name);
		System.out.println("測試看看，是否成功");
		
//		int rowcount = empDao.addLog(method_name);
//			System.out.println("結果: " + (rowcount > 0 ? "紀錄完成" : "紀綠異常"));
	}

	// 返回通知：可以在切面程式中得到目標方法的回傳值
//	@AfterReturning(value = "pt()", returning = "result")
//	public void afterReturning (Object result) {
//		System.out.println("返回通知 - result = " + result);
//	}
	


}
