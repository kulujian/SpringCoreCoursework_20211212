package pers.kulujian.coursework.coursework_5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.kulujian.coursework.coursework_5.dao.Order_logDao;
import pers.kulujian.coursework.coursework_5.entity.Order_log;

@Service
public class Order_logServiceImpl implements Order_logService{

	@Autowired
	private Order_logDao order_logDao;
	
	@Override
	public List<Order_log> findAllOrder_logs() {
		return order_logDao.readAll();
	}

}
