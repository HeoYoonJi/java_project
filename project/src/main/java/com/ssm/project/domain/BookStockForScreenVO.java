package com.ssm.project.domain;

import lombok.Data;

@Data
public class BookStockForScreenVO {

	private int id;
	private String name;
	private String genre;
	private String bookImg;
	private String isbn10;
	private String author;
	private int quantity;
	private int limit;
	private String standLocation;
}
