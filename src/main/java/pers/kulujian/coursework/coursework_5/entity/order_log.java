package pers.kulujian.coursework.coursework_5.entity;

import java.util.Date;

public class order_log {

	// order_log 欄位
	private Integer lid;
	private Date logTime;
	private Integer customerNum;
	private String customerName;
	private Integer productCode;
	private String productName;
	private Integer productAmount;
	private Integer productSumMoney;
	
	
	public order_log() {
//		super();
	}
	
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public Integer getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getProductCode() {
		return productCode;
	}
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	public Integer getProductSumMoney() {
		return productSumMoney;
	}
	public void setProductSumMoney(Integer productSumMoney) {
		this.productSumMoney = productSumMoney;
	}
	@Override
	public String toString() {
		return "order_log [lid=" + lid + ", logTime=" + logTime + ", customerNum=" + customerNum + ", customerName="
				+ customerName + ", productCode=" + productCode + ", productName=" + productName + ", productAmount="
				+ productAmount + ", productSumMoney=" + productSumMoney + "]";
	}
	
}
