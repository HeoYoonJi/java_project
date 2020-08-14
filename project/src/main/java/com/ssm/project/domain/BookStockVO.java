package com.ssm.project.domain;

public class BookStockVO {
	
	private int id;
	private int bookId;
	private int quantity;
	private int limit;
	private String standLocation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getStandLocation() {
		return standLocation;
	}
	public void setStandLocation(String standLocation) {
		this.standLocation = standLocation;
	}

}
