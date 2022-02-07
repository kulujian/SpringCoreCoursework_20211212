package pers.kulujian.coursework.coursework_4.entity;

public class Item {
	
	// 發票項目,欄位
	private Integer id;
	private Integer amount;
	private Integer ipid;
	private Integer invid;
	
	//關係
	private Invoice invoice;  // 一個 Item.invid 對應一個 Invoice.id
	private ItemProduct itemProduct;  // 一個 Item.ipid 對應一個 ItemProduct.id
	
	public Item() {
//		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getIpid() {
		return ipid;
	}

	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}

	public Integer getInvid() {
		return invid;
	}

	public void setInvid(Integer invid) {
		this.invid = invid;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public ItemProduct getItemProduct() {
		return itemProduct;
	}

	public void setItemProduct(ItemProduct itemProduct) {
		this.itemProduct = itemProduct;
	}

	@Override
	public String toString() {
		return "查詢結果" + 
				(id == null ? "" : "[品項序號=" + id) + 
				(ipid == null ? "" : ", 產品序號=" + ipid) + 
				(invid == null ? "" : ", 發票序號=" + invid) + 
				(invoice == null ? "" :", 發票日期=" + invoice.getInvdate()) + 
				(itemProduct == null ? "" : (itemProduct.getText() == null ? "" : ", 產品名稱=" + itemProduct.getText()) +
											(itemProduct.getPrice() == null ? "" : ", 價錢=" + itemProduct.getPrice())) +
				(amount == null ? "" : ", 數量=" + amount) +  
				" 。";
	}
	
	
}
