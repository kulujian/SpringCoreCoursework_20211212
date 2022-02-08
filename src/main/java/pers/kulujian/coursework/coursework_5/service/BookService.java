package pers.kulujian.coursework.coursework_5.service;

public interface BookService {
	/*
	 * 注意：
	 * 	若使用變動語法(新增、修改、刪除)時，建議語法後都要拋出Exception
	 * 	已利實作時異常處理配置(其實就是提醒會有錯誤異常發生) 
	 *  ex. void buyOne(Integer wid, Integer bid) throws Exception;
	 *  
	 *  若未先設定Impl實作時只能拋出 oncheck 的 exception
	 *  ex. RuntimeException
	 */
	
	void buyOne(Integer wid, Integer bid);
	// 注意：點點點 要放對地方，這代表可以放陣列
	void buyMany(Integer wid, Integer... bid);

}
