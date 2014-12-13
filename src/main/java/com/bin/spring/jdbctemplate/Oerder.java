package com.bin.spring.jdbctemplate;

public class Oerder {
	private int orderId ;
	private String orderName ;
	private Customer customer ;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Oerder [orderId=" + orderId + ", orderName=" + orderName + ", customer=" + customer + "]";
	}
	
}
