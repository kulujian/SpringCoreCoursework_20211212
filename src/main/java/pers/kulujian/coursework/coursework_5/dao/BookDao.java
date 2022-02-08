package pers.kulujian.coursework.coursework_5.dao;

public interface BookDao {
	/*
	 * 注意：
	 * 	若使用變動語法(新增、修改、刪除)時，建議語法後都要拋出Exception
	 * 	已利實作時異常處理配置(其實就是提醒會有錯誤異常發生) 
	 *  ex. Integer updateStock(Integer bid, Integer amount) throws Exception
	 *  
	 *  若未先設定Impl實作時只能拋出 oncheck 的 exception
	 *  ex. RuntimeException
	 */
	Integer getPrice(Integer bid); // 透過 bid 取得 price
	Integer getStockAmount(Integer bid); // 透過 bid 取得 stock amount
	Integer getWalletMoney(Integer wid); // 透過 wid 取得 wallent money
	Integer updateStock(Integer bid, Integer amount); // 透過 bid 更新 stock amount
	Integer updateWallet(Integer wid, Integer money); // 透過 wid 更新 wallet money
}
