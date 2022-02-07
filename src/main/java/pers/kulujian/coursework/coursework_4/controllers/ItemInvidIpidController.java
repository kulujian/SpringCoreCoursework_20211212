package pers.kulujian.coursework.coursework_4.controllers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.kulujian.coursework.coursework_4.entity.Invoice;
import pers.kulujian.coursework.coursework_4.entity.Item;
import pers.kulujian.coursework.coursework_4.entity.ItemProduct;
import pers.kulujian.coursework.coursework_4.template.ItemInvidIpidDao;

@Controller
public class ItemInvidIpidController {

	@Autowired
	private ItemInvidIpidDao itemInvIdIpidDao;
	
	// 取得 全部發票資料
	public void showAllStream () {
		Iterator<Invoice> s = itemInvIdIpidDao.queryInvoice().iterator();
		while (s.hasNext()) {
			Invoice a = (Invoice) s.next();
			Integer sum = 0;
			System.out.printf("發票序號:%3d  發票日期:%11s\n", a.getId(), a.getInvdate());
			Iterator<Item> b = a.getItems().iterator();
			Iterator<ItemProduct> c = a.getItemProducts().iterator();
			while (b.hasNext() && c.hasNext()) {
				Item d = (Item) b.next();
				ItemProduct e = (ItemProduct) c.next();
				System.out.printf("    品項:%3d  品名:%6s  數量:%3d  單價:%4d  小計:%4d\n", 
						d.getId(), e.getText(), d.getAmount(), e.getPrice(), (d.getAmount() * e.getPrice()));
				sum += d.getAmount() * e.getPrice();
			}
			System.out.printf("%44s發票金額:%4d\n\n", "", sum);
		}
	}
	
	// 查詢 每一張發票有賣哪些商品
	public void findProductInInvoice() {
		Iterator<Invoice> s = itemInvIdIpidDao.findProductInInvoice().iterator();
		while (s.hasNext()) {
			Invoice a = (Invoice) s.next();
			System.out.printf("發票序號:%3d  發票日期:%11s\n", a.getId(), a.getInvdate());
			Iterator<ItemProduct> c = a.getItemProducts().iterator();
			while (c.hasNext()) {
				ItemProduct e = (ItemProduct) c.next();
				System.out.printf("    品名:  %-6s\n", e.getText());
			}
			System.out.println("");
		}
	}
	
	// 統計 每一張發票有幾件商品(例如：項目xxx 數量2 , 項目xxx 數量 5。總共7件)
	public void countProductInInvoice () {
		Iterator<Invoice> s = itemInvIdIpidDao.countProductInInvoice().iterator();
		while (s.hasNext()) {
			Invoice a = (Invoice) s.next();
			System.out.printf("發票序號:%3d  發票日期:%11s", a.getId(), a.getInvdate());
			Iterator<Item> b = a.getItems().iterator();
			while (b.hasNext()) {
				Item d = (Item) b.next();
				System.out.printf("  所有產品共%3d 件。\n", d.getAmount());
			}
			System.out.println("");
		}
	}
	
	// 統計 每一張發票價值多少
	public void countMorthOfInvoice() {
		Iterator<Invoice> s = itemInvIdIpidDao.countMorthOfInvoice().iterator();
		while (s.hasNext()) {
			Invoice a = (Invoice) s.next();
			System.out.printf("發票序號:%3d  發票日期:%11s", a.getId(), a.getInvdate());
			Iterator<ItemProduct> c = a.getItemProducts().iterator();
			while (c.hasNext()) {
				ItemProduct e = (ItemProduct) c.next();
				System.out.printf("  發票金額:%5d 元。\n", e.getPrice());
			}
			System.out.println("");
		}
	}
	
	// 統計 每一樣商品各賣了多少數量
	public void countAmountOfText() {
		Iterator<ItemProduct> s = itemInvIdIpidDao.countAmountOfText().iterator();
		while (s.hasNext()) {
			ItemProduct a = (ItemProduct) s.next();
			System.out.printf("產品品項:%3d  產品名稱: %-6s", a.getId(), a.getText());
			Iterator<Item> c = a.getItems().iterator();
			while (c.hasNext()) {
				Item e = (Item) c.next();
				System.out.printf("  銷售數量:%3d 件。\n", e.getAmount());
			}
			System.out.println("");
		}
	}
	
	// 分析 哪一件商品銷售總金額最高
	public void getMaxPriceOfText() {
		Iterator<ItemProduct> s = itemInvIdIpidDao.getMaxPriceOfText().iterator();
		while (s.hasNext()) {
			ItemProduct a = (ItemProduct) s.next();
			System.out.printf("銷售金額最高的產品: %-6s  銷售金額: %3d 元\n", a.getText(), a.getPrice());
		}
		System.out.println("");
	}
	
	// 分析 哪一張發票價值最高
	public void getMaxMorthOfInvoice() {
		Iterator<Invoice> s = itemInvIdIpidDao.getMaxMorthOfInvoice().iterator();
		System.out.println("價值最高的發票：");
		while (s.hasNext()) {
			Invoice a = (Invoice) s.next();
			System.out.printf("發票序號:%3d  發票日期:%11s", a.getId(), a.getInvdate());
			Iterator<ItemProduct> c = a.getItemProducts().iterator();
			while (c.hasNext()) {
				ItemProduct e = (ItemProduct) c.next();
				System.out.printf("  發票金額:%5d 元。\n", e.getPrice());
			}
			System.out.println("");
		}
		
	}
	
}
