package pers.kulujian.coursework.coursework_5.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.kulujian.coursework.coursework_5.dao.BookDao;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookdao;

	/*
	 *  交易註解 Transactional 配置後	
	 *	一、會讓資料庫管理下列方法： 
	 * 		getConnection()：     連線機制
	 * 		setAutocommit(false)：取消自動提交機制
	 * 		commit()：            確認所有交易成功後統一提交\
	 *  二、屬性：
	 * 		一、傳播模式 propagation：
	 * 			Propagation.REQUIRED     ：支持當前TX，若當前沒有TX，就新建TX (default)
	 * 			Propagation.SUPPORTS     ：支持當前TX，若當前沒有TX，就以非TX執行
	 * 			Propagation.MANDATORY    ：支持當前TX，若當前沒有TX，就拋出異常
	 * 			Propagation.REQUIRED_NEW ：新建TX，若當前存在TX，將當前TX掛起，以新建TX傳播
	 * 			Propagation.NOT_SUPPORTED：以非TX執行，若當前存在TX，將當前TX掛起
	 * 			Propagation.NEVER        ：以非TX執行，若當前存在TX，就拋出異常
	 * 			Propagation.NESTED       ：新建TX，若當前存在TX，將當前TX掛起，不傳播建立TX
	 * 		二、隔離級別 isolation：
	 *	 		Isolation.DEFAULT         ：使用底層資料庫預設的隔離層級(設計時需要詢問DB管理師預設等級，再判斷使用)
	 *	 		Isolation.READ_UNCOMMITTED：不做設定。會發生(Dirty read)((Un)Nonrepeatable read)(Phantom read)並可能會導致更新的資料遺失
	 *	 		Isolation.READ_COMMITTED  ：可以防止(Dirty read)。
	 *	 		Isolation.REPEATABLE_READ ：可以防止(Dirty read)((Un)Nonrepeatable read)
	 *	 		Isolation.SERIALIZABLE    ：鎖定資料表。可以防止(Dirty read)((Un)Nonrepeatable read)(Phantom read)。需考慮效能 
	 *	 		名詞註解：
	 *	 		Dirty read (髒讀)                      ：兩交易同時進行，其中一交易更新資料，另一交易讀取尚未commit資料。
	 *	 		(Un)Nonrepeatable read (無法重複的讀取)：同次交易，讀取兩次同欄位，但資料並不一致。
	 *	 		Phantom read(幻讀)                     ：交易A進行兩次查詢，兩次查詢中有B交易插入或刪除一筆資料，第二次查詢時就會多或少資料。
	 *	 	三、超時 timeout：
	 *	 		對資料庫若超過 timeout 所設定的時間就會進行回滾 (Roll back)，以秒為單位。
	 *	 	四、銷定資料庫 readOnly
	 *	 		false (default)：已鎖定的方式進行存取。
	 *	 		true           ：已不鎖定的方式進行存取增加效能，用於查詢方案。
	 *  	五、根據指定發生事件回滾或不回滾 rollbackFor / noRollbackFor
	 *		 	Ex:
	 *		  		rollbackFor = Exception.class (讓checked exception例外也回滾)
	 *		  		notRollbackFor = RunTimeException.class (讓unchecked exception例外不回滾)
	 *		  	注意：
	 *		 		1、Springt框架的事務基礎架構程式碼預設，只有在丟擲執行時期例外和unchecked exception時才會標識事務回滾。
	 *		 		   也就是說，當丟擲RunTimeException或其子類的例項時會進行回滾(Error也一樣)；從事務方法中丟擲的Checked exceptions不進行回滾。
	 *				2、被try catch包住的異常，不回滾。
	 */
	@Transactional(propagation =  Propagation.REQUIRED)
	/*
	 * 	若未配置 Transaction 交易註解
	 * 	下面執行之後會各自交易修改，導致有的方法已交易完成有的方法交易失敗
	 *  例如：同筆交易內存庫量夠就先扣庫存，遇到金額不足就跳失敗，但庫存卻不會回加
	 */
	@Override
	public void buyOne(Integer wid, Integer bid) {
		// 減去一本庫存
		bookdao.updateStock(bid, 1);
		// 取得書籍價格
		Integer price = bookdao.getPrice(bid);
		// 減去錢包裡的金額
		bookdao.updateWallet(wid, price);
	}

	/*
	 *  交易註解 Transactional 配置後	
	 *	一、會讓資料庫管理下列方法： 
	 * 		getConnection()：     連線機制
	 * 		setAutocommit(false)：取消自動提交機制
	 * 		commit()：            確認所有交易成功後統一提交\
	 *  二、屬性：
	 * 		一、傳播模式 propagation：
	 * 			Propagation.REQUIRED     ：支持當前TX，若當前沒有TX，就新建TX (default)
	 * 			Propagation.SUPPORTS     ：支持當前TX，若當前沒有TX，就以非TX執行
	 * 			Propagation.MANDATORY    ：支持當前TX，若當前沒有TX，就拋出異常
	 * 			Propagation.REQUIRED_NEW ：新建TX，若當前存在TX，將當前TX掛起，以新建TX傳播
	 * 			Propagation.NOT_SUPPORTED：以非TX執行，若當前存在TX，將當前TX掛起
	 * 			Propagation.NEVER        ：以非TX執行，若當前存在TX，就拋出異常
	 * 			Propagation.NESTED       ：新建TX，若當前存在TX，將當前TX掛起，不傳播建立TX
	 * 		二、隔離級別 isolation：
	 *	 		Isolation.DEFAULT         ：使用底層資料庫預設的隔離層級(設計時需要詢問DB管理師預設等級，再判斷使用)
	 *	 		Isolation.READ_UNCOMMITTED：不做設定。會發生(Dirty read)((Un)Nonrepeatable read)(Phantom read)並可能會導致更新的資料遺失
	 *	 		Isolation.READ_COMMITTED  ：可以防止(Dirty read)。
	 *	 		Isolation.REPEATABLE_READ ：可以防止(Dirty read)((Un)Nonrepeatable read)
	 *	 		Isolation.SERIALIZABLE    ：鎖定資料表。可以防止(Dirty read)((Un)Nonrepeatable read)(Phantom read)。需考慮效能 
	 *	 		名詞註解：
	 *	 		Dirty read (髒讀)                      ：兩交易同時進行，其中一交易更新資料，另一交易讀取尚未commit資料。
	 *	 		(Un)Nonrepeatable read (無法重複的讀取)：同次交易，讀取兩次同欄位，但資料並不一致。
	 *	 		Phantom read(幻讀)                     ：交易A進行兩次查詢，兩次查詢中有B交易插入或刪除一筆資料，第二次查詢時就會多或少資料。
	 *	 	三、超時 timeout：
	 *	 		對資料庫若超過 timeout 所設定的時間就會進行回滾 (Roll back)，以秒為單位。
	 *	 	四、銷定資料庫 readOnly
	 *	 		false (default)：已鎖定的方式進行存取。
	 *	 		true           ：已不鎖定的方式進行存取增加效能，用於查詢方案。
	 *  	五、根據指定發生事件回滾或不回滾 rollbackFor / noRollbackFor
	 *		 	Ex:
	 *		  		rollbackFor = Exception.class (讓checked exception例外也回滾)
	 *		  		notRollbackFor = RunTimeException.class (讓unchecked exception例外不回滾)
	 *		  	注意：
	 *		 		1、Springt框架的事務基礎架構程式碼預設，只有在丟擲執行時期例外和unchecked exception時才會標識事務回滾。
	 *		 		   也就是說，當丟擲RunTimeException或其子類的例項時會進行回滾(Error也一樣)；從事務方法中丟擲的Checked exceptions不進行回滾。
	 *				2、被try catch包住的異常，不回滾。
	 */
	@Transactional(propagation =  Propagation.REQUIRED)
	/*
	 * 	若未配置 Transaction 交易註解
	 * 	下面執行之後會各自交易修改，導致有的方法已交易完成有的方法交易失敗
	 *  例如：同筆交易內存庫量夠就先扣庫存，遇到金額不足就跳失敗，但庫存卻不會回加
	 */
	@Override
	public void buyMany(Integer wid, Integer... bids) {
		/* 重覆執行 buyOne 容內
		 *  1、減去庫存
		 *	2、取得書籍價格
		 *	3、減去錢包裡的金額  
		 * 
		 * 這邊會有log問題(紀錄五筆buyOne還是紀錄一筆buyMany)，這是HomeWork
		 */
		Stream.of(bids).forEach(bid -> buyOne(wid, bid));
		
		
	}
	
}
