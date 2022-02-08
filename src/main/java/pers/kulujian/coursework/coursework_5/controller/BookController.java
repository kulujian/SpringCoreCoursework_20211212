package pers.kulujian.coursework.coursework_5.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.kulujian.coursework.coursework_5.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Integer bid) {
		bookService.buyOne(wid, bid);
		System.out.println("單筆購買 buyBook OK ！");
	}

	public void buyBooks(Integer wid, Integer... bids) {
		bookService.buyMany(wid, bids);
		System.out.println("多筆購買 buyBooks OK ！");
	}
}
