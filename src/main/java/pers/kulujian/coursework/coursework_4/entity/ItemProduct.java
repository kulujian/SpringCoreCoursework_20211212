package pers.kulujian.coursework.coursework_4.entity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemProduct {
	
	// 商品項目,欄位
	private Integer id;
	private String text;
	private Integer price;
	private Integer inventory;
	
	// 關係
	private List<Item> items;  // 一個 ItemProduct.id 對應多個 Item.ipid
	private List<Invoice> invoices;  // 一個 ItemProduct.id 對應多個 Item.invid
	
	
	public ItemProduct() {
//		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "產品項目 [產品序號=" + id + ", 產品名稱=" + text + ", 產品價格=" + price + ", 庫存數量=" + inventory + 
				(items == null ? "" : ", 品項=" + items.stream().map(i -> i.toString()).collect(Collectors.toList())) + 
				(invoices == null ? "" : ", 發票=" + invoices.stream().map(i -> i.toString()).collect(Collectors.toList())) + 
				"]";
	}

}
