package com.ssm.project.domain;

import java.sql.Date;

public class BookVO {
	
	private int id;
	private String name;
	private String genre;
	private String bookImg;
	private String isbn10;
	private String isbn13;
	private Date pubdate;
	private String author;
	private String pubs;
	private int price;
	private int bsPrie;
	private int bsRate;
	private int pageNum;
	private int weight;
	private String bookSize;
	private float evalPoint;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	public String getIsbn10() {
		return isbn10;
	}
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBsPrie() {
		return bsPrie;
	}
	public void setBsPrie(int bsPrie) {
		this.bsPrie = bsPrie;
	}
	public int getBsRate() {
		return bsRate;
	}
	public void setBsRate(int bsRate) {
		this.bsRate = bsRate;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getBookSize() {
		return bookSize;
	}
	public void setBookSize(String bookSize) {
		this.bookSize = bookSize;
	}
	public float getEvalPoint() {
		return evalPoint;
	}
	public void setEvalPoint(float evalPoint) {
		this.evalPoint = evalPoint;
	}
	
	@Override
	public String toString() {
		return String.format(
				"BookVO [id=%s, name=%s, genre=%s, bookImg=%s, isbn10=%s, isbn13=%s, pubdate=%s, author=%s, pubs=%s, price=%s, bsPrie=%s, bsRate=%s, pageNum=%s, weight=%s, bookSize=%s, evalPoint=%s]",
				id, name, genre, bookImg, isbn10, isbn13, pubdate, author, pubs, price, bsPrie, bsRate, pageNum, weight,
				bookSize, evalPoint);
	}
	
}
