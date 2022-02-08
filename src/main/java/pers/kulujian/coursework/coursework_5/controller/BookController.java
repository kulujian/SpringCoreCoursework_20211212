package pers.kulujian.coursework.coursework_5.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.kulujian.coursework.coursework_5.exception.InsufficientAmount;
import pers.kulujian.coursework.coursework_5.exception.InsufficientQuantity;
import pers.kulujian.coursework.coursework_5.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Integer bid) {
		try {
			bookService.buyOne(wid, bid);
			System.out.println("單筆購買 buyBook OK ！");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足：" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足：" + e);
		}
	}

	public void buyBooks(Integer wid, Integer... bids) {
		try {
			bookService.buyMany(wid, bids);
			System.out.println("多筆購買 buyBooks OK ！");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足：" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足：" + e);
		}
	}
}
