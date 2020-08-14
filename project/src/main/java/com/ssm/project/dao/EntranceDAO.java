package com.ssm.project.dao;

import java.util.Date;
import java.util.List;

import com.ssm.project.domain.CustomerEntranceVO;

public interface EntranceDAO {
	
	/**
	 * 내방 고객 현황 조회
	 * @return 내방 고객 현황
	 */
	List<CustomerEntranceVO> getEntrances();
	
	/**
	 * 내방고객 현황 삽입
	 * @param customerEntranceVO
	 */
	void insertEntrance(CustomerEntranceVO customerEntranceVO);

	/**
	 * 내방 고객 시간대 별 현황 조회
	 * @param entranceDate 내방 일자
	 * @param entranceTimeBand 내방 시간대
	 * @return 내방 고객 시간대 별 현황
	 */
	List<CustomerEntranceVO> getEntrancesByDate(Date entranceDate, String entranceTimeBand);
	
	/**
	 * 내방 고객 시간대 별 현황 조회 : 특정 시간 이내
	 * @param entranceDate 내방 일자
	 * @param entranceTimeBand 내방 시간대
	 * @return 특정 시간 이내 내방 고객 시간대별 현황
	 */
	List<CustomerEntranceVO> getEntrancesTilTimeBand(Date entranceDate, String entranceTimeBand);
	
	/**
	 * 내방 고객 시간대 별 현황 조회 : 특정 시간대
	 * @param entranceDate 내방 일자
	 * @param entranceTimeBand 내방 시간대
	 * @return 특정 시간 이내 내방 고객 시간대별 현황
	 */
	List<CustomerEntranceVO> getEntrancesByTimeBand(Date entranceDate, String entranceTimeBand);
	
}
