package com.ssm.project.domain;

public class PageVO {
	
	private int page; // 현재 페이지
	private int maxPage; // 총 페이지
	private int startPage; // 시작 페이지
	private int endPage; // 마지막 페이지
	private int listCount; // 페이지당 게시글수
	
	public PageVO() {}
	
	public PageVO(int page, 
				  int maxPage, 
				  int startPage, 
				  int endPage, 
				  int listCount) {
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.listCount = listCount;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", listCount=" + listCount + "]";
	}
	
}