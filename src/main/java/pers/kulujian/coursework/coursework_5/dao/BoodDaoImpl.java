package pers.kulujian.coursework.coursework_5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pers.kulujian.coursework.coursework_5.exception.InsufficientAmount;
import pers.kulujian.coursework.coursework_5.exception.InsufficientQuantity;

@Repository
public class BoodDaoImpl implements BookDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 *  由產品代碼查詢產品價格
	 * 	@param bid 產品代碼
	 *  @param sql 資料庫語法
	 *  @return Integer 產品價格
	 */
	@Override
	public Integer getPrice(Integer bid) {
		String sql = "select price from book where bid = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	/**
	 *  由產品代碼查詢庫存數量
	 * 	@param bid 產品代碼
	 *  @param sql 資料庫語法
	 *  @return Integer 庫存數量
	 */
	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	/**
	 *  由錢包代碼查詢錢包餘額
	 * 	@param wid 錢包代碼
	 *  @param sql 資料庫語法
	 *  @return Integer 錢包餘額
	 */
	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity{
		// 確認該商品最後庫存數量是否大於零，並且最後庫存數量大於購買量。
		Integer new_Amount = getStockAmount(bid);
		if(new_Amount <= 0) {
			throw new InsufficientQuantity(String.format("此書號：%d 目前沒有庫存，目前數量為：%d", bid, new_Amount));
		}else if(new_Amount < amount) {
			throw new InsufficientQuantity(String.format("此書號：%d 目前庫存不跑，目前數量為：%d ， 欲購買數量：%d", bid, new_Amount, amount));
		}
		// 修改庫存
		String sql = "update stock set amount = amount - ? where bid = ?";
		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount{
		// 確認雲端錢包最後餘額是否大於零，並且最後餘額大於購量商品總金額。
		Integer new_Money = getWalletMoney(wid);
		if(new_Money <= 0) {
			throw new InsufficientAmount(String.format("錢包號碼：%d 目前餘額不足，目前餘額為：%d", wid, new_Money));
		}else if(new_Money < money) {
			throw new InsufficientAmount(String.format("錢包號碼：%d 目前餘額不足，目前餘額為：%d ， 需支出金額為：%d", wid, new_Money, money));
		}
		// 修改餘額
		String sql = "update wallet set money = money - ? where wid = ?";
		return jdbcTemplate.update(sql, money, wid);
	}
	
}
