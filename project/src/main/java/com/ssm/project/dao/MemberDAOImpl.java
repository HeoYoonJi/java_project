/**
 * 
 */
package com.ssm.project.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.project.domain.MemberVO;
import com.ssm.project.domain.PageDTO;
import com.ssm.project.domain.SearchVO;
import com.ssm.project.domain.Users;
import com.ssm.project.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean hasUsername(String username) {

		log.info("hasUsername");
		
		return sqlSession.getMapper(MemberMapper.class)
						 .hasUsername(username) == 1 ? true : false;
	} //

	@Override
	public void insertUsers(Users users, String role) {

		log.info("insertUsers");
		
		sqlSession.getMapper(MemberMapper.class)
				  .insertUser(users);
		
		sqlSession.getMapper(MemberMapper.class)
		  		  .insertUserRoles(users.getUsername(), role);
	} //
	
	@Override
	public void updateUsers(Users users) {

		log.info("updateUsers");
		
		sqlSession.getMapper(MemberMapper.class).updateUsers(users);		
	}

	@Override
	public void deleteUsers(String username) {

		log.info("deleteUsers");
		
		sqlSession.getMapper(MemberMapper.class).deleteUsers(username);
	}
	
	@Override
	public void insertMember(MemberVO member) {
		
		log.info("생일 : "+member.getMemberBirth());

		log.debug("dao insertMember");
		
		// Date -> String -> Date
		//java.sql.Date temp = java.sql.Date.valueOf("2020-07-16");
		//String time = temp.toString();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Date date1 = dateFormat.parse(time); // Date
		// 일반 회원정보 저장
		sqlSession.insert("com.ssm.project.mapper.MemberMapper.insertMember", member);
	}

	@Override
	public boolean isMember(String id) {

		log.debug("dao isMember");
		return (int)sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.isMember", id) == 1 ? true : false;
	}

	@Override
	public boolean isEnableEmail(String email) {
		
		log.debug("dao isEnableEmail");
		return (int)sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.isEnableEmail", email) == 1 ? true : false; 
	}

	@Override
	public boolean isEnablePhone(String phone) {

		log.debug("dao isEnablePhone");
		return (int)sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.isEnablePhone", phone) == 1 ? true : false;
	}

	@Override
	public MemberVO getMember(String memberId) {

		log.debug("dao getMember");
		return sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.getMember", memberId);
	}
	
	@Override
	public void updateMember(MemberVO member) {

		log.debug("dao updateMember");
		sqlSession.update("com.ssm.project.mapper.MemberMapper.updateMember", member);
	}

	@Override
	public void deleteMember(String memberId) {

		log.debug("dao deleteMember");
		sqlSession.delete("com.ssm.project.mapper.MemberMapper.deleteMember", memberId);
	}

	@Override
	public List<MemberVO> getMembersByPaging(int page, int limit) {

		log.debug("dao getMembersByPaging");
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(page);
		pageDTO.setLimit(limit);
		return sqlSession.selectList("com.ssm.project.mapper.MemberMapper.getMembersByPaging", pageDTO);
	}

	@Override
	public List<MemberVO> getAllMembers() {

		log.debug("dao getAllMembers");
		return sqlSession.selectList("com.ssm.project.mapper.MemberMapper.getAllMembers");
	}

	@Override
	public List<MemberVO> getMembersByFieldAndPaging(String fld, Object value, boolean isLike, int page, int limit) {

		log.debug("dao getMembersByFieldAndPaging");
		
		// 유사 검색 여부에 따른 연산자 및 필드값 부분 처리, SQL 구문
		String expr = isLike == true ? " like '%"+value+"%'" : " = '"+value+"'";				
							
		SearchVO searchVO = new SearchVO();
		searchVO.setFld(fld);
		searchVO.setLike(isLike);
		searchVO.setPage(page);
		searchVO.setLimit(limit);
		searchVO.setExpr(expr);
		
		return sqlSession.selectList("com.ssm.project.mapper.MemberMapper.getMembersByFieldAndPaging", searchVO);
	}

	@Override
	public boolean isEnableEmail(String memberId, String email) {
		
		log.debug("dao isEnableEmail");
		
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("email", email);
		
		return (int)sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.isEnableEmailUpdate", map) == 1 ? true : false;
	}

	@Override
	public boolean isEnablePhone(String memberId, String phone) {
		
		log.debug("dao isEnablePhone");
		
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("phone", phone);
		
		return (int)sqlSession.selectOne("com.ssm.project.mapper.MemberMapper.isEnablePhoneUpdate", map) == 1 ? true : false;
	}

	
} //