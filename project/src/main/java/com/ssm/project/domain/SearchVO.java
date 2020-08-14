package com.ssm.project.domain;

import lombok.Data;

@Data
public class SearchVO {
	
	private String fld;
	private Object value; 
	private boolean isLike; 
	private int page;
	private int limit;
	private String expr;

}