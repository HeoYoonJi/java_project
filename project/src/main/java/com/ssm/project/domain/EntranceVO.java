package com.ssm.project.domain;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntranceVO {

	private int entranceNum;
	private List<String> books;
	
	@Override
	public String toString() {
		return "entranceNum=" + entranceNum + ", books=" + books;
	}
	
}
