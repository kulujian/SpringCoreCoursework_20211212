package pers.kulujian.coursework.coursework_5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.kulujian.coursework.coursework_5.entity.Order_log;
import pers.kulujian.coursework.coursework_5.service.Order_logService;

@Controller
public class Order_logController {

	@Autowired
	private Order_logService order_logService;
	
	public void printAllOrder_log() {
		List<Order_log> logs = order_logService.findAllOrder_logs();
		System.out.println("+--------------------------------------------------------------+");
		System.out.printf("|%25s客戶交易紀錄%25s|\n","",""); // /64
		System.out.println("+----------+------------------------+--------+--------+--------+");
		System.out.println("| 客戶姓名 |        交易時間        |購買品項|購買數量|交易金額|"); // 6 + 10(9)+24(23)+8+8(4)+8(6)=64
		System.out.println("+----------+------------------------+--------+--------+--------+");
		for(Order_log log : logs) {
			System.out.printf("|%9s | %22s | %6s |  %4d  | %6d |\n", 
					log.getCustomerName(), 
					log.getLogTime(), 
					log.getProductName(), 
					log.getProductAmount(), 
					log.getProductSumMoney());//new SimpleDateFormat("yyyy/MM/dd").format(p.getBirth()));
			System.out.println("+----------+------------------------+--------+--------+--------+");
		}
	}
}
