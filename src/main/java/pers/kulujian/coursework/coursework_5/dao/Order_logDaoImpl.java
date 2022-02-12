package pers.kulujian.coursework.coursework_5.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pers.kulujian.coursework.coursework_5.entity.Order_log;

@Repository
public class Order_logDaoImpl implements Order_logDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Order_log> readAll() {
		String sql = "select ol.customerName, \r\n"
				+ "	   ol.logTime, \r\n"
				+ "	   ol.productName, \r\n"
				+ "	   ol.productAmount, \r\n"
				+ "	   ol.productSumMoney\r\n"
				+ "from order_log ol";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order_log>(Order_log.class));
	}

}
