package com.ssm.project.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.project.domain.CustomerEntranceVO;
import com.ssm.project.domain.EntranceDateVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EntranceDAOImpl implements EntranceDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CustomerEntranceVO> getEntrances() {
		log.debug("dao getEntrances");
		return sqlSession.selectList("mapper.BookMapper.getEntrances");
	}

	@Override
	public void insertEntrance(CustomerEntranceVO customerEntranceVO) {
		log.debug("dao insertEntrance");
		sqlSession.insert("mapper.BookMapper.insertEntrance", customerEntranceVO);
	}

	@Override
	public List<CustomerEntranceVO> getEntrancesByDate(Date entranceDate, String entranceTimeBand) {
		log.debug("dao getEntrancesByDate");
		Map<String, Object> map = new HashMap<>();
		log.info("-------------날짜:"+entranceDate.toString());
		map.put("entranceDate", entranceDate);
		
		// 버그 패치 : 22:00의 타임밴드(21:30 ~ 22:30)가 유입되면 21:00 타임밴드(20:30 ~ 21:30)로 치환
		entranceTimeBand = entranceTimeBand.equals("21:30 ~ 22:30") ? "20:30 ~ 21:30" : entranceTimeBand; 
				
		map.put("entranceTimeBand", entranceTimeBand);
		return sqlSession.selectList("mapper.BookMapper.getEntrancesByDate", map);
	}

	@Override
	public List<CustomerEntranceVO> getEntrancesTilTimeBand(Date entranceDate, String entranceTimeBand) {
		log.debug("dao getentrancesTilTimeBand");
		EntranceDateVO enDateVO = new EntranceDateVO();
		enDateVO.setEntranceDate(entranceDate);
		enDateVO.setEntranceTimeBand(entranceTimeBand.trim());
		log.info("**entVO : "+enDateVO);
		return sqlSession.selectList("mapper.BookMapper.getEntrancesTilTimeBand", enDateVO);
	}

	@Override
	public List<CustomerEntranceVO> getEntrancesByTimeBand(Date entranceDate, String entranceTimeBand) {
		log.debug("dao getentrancesByTimeBand");
		EntranceDateVO enDateVO = new EntranceDateVO();
		enDateVO.setEntranceDate(entranceDate);
		enDateVO.setEntranceTimeBand(entranceTimeBand.trim());
		log.info("**entVO : "+enDateVO);
		return sqlSession.selectList("mapper.BookMapper.getEntrancesByTimeBand", enDateVO);
	}

}
