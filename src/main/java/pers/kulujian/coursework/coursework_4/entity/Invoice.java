package pers.kulujian.coursework.coursework_4.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Invoice {
	
	// 發票,欄位
	private Integer id;
	private Date invdate;
	
	//關係
	private List<Item> items; // 一個 Invoice.id 對應多個 Item.invid
	private List<ItemProduct> itemProducts; // 一個 Invoice.id 對應多個 Item.ipid

	public Invoice() {
//		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<ItemProduct> getItemProducts() {
		return itemProducts;
	}
	public void setItemProducts(List<ItemProduct> itemProducts) {
		this.itemProducts = itemProducts;
	}

	@Override
	public String toString() {
		return  "發票內容 " + 
				( id == null ? "" : "[發票序號=" + id) + 
				(invdate == null ? "" : ", 發票日期=" + invdate) + 
				", 品項=" + 
				(items == null ? "null" : items.stream().map(i -> i.toString()).collect(Collectors.toList())) + 
				", 產品項目=" + 
				(itemProducts == null ? "null" : itemProducts.stream().map(i -> i.toString()).collect(Collectors.toList())) + 
				"]";
	}

	
}
