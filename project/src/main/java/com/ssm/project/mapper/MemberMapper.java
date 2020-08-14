/**
 * 
 */
package com.ssm.project.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.project.domain.MemberVO;
import com.ssm.project.domain.PageDTO;
import com.ssm.project.domain.Role;
import com.ssm.project.domain.SearchVO;
import com.ssm.project.domain.Users;

public interface MemberMapper {
	
	/**
	 * 회원 정보 확인
	 * @param userName 회원 아이디
	 * @return 회원 정보
	 */
	Users getUserByUsername(String userName);
	
	/**
	 * 회원 등급 확인
	 * @param userName 회원 아이디
	 * @return 회원 등급
	 */
	Role getUserRolesByUsername(String userName);
	
	/**
	 * 회원 존재 여부 점검
	 * @param username 회원 아이디
	 * @return 존재 여부
	 */
	int hasUsername(String username);
	
	/**
	 * 회원 등록(회원 정보 삽입)
	 * @param users 회원 정보
	 */
	void insertUser(@Param("users") Users users);
	
	/**
	 * 회원 등급 등록
	 * @param username 회원 아이디
	 * @param role 회원 등급
	 */
	void insertUserRoles(@Param("username") String username, 
						 @Param("role") String role);
	
	/**
	 * 회원 정보 패쓰워드 갱신
	 * @param users 회원 정보
	 */
	void updateUsers(Users users);
	
	/**
	 * 회원 정보 패쓰워드 삭제
	 * @param username 회원 아이디
	 */
	void deleteUsers(String username);
	
	///////////////////////////////////////////////////////////
	
	/**
	 * (회원 가입 할 때)아이디 중복 점검
	 * @param memberId 회원 아이디
	 * @return 중복 여부
	 */
	int isMember(String memberId);
	
	/**
	 * (회원 가입 할 때)이메일 중복 점검
	 * @param memberEmail 회원 이메일
	 * @return 중복 여부
	 */
	int isEnableEmail(String memberEmail);
	
	/**
	 * (회원 가입 할 때)전화 번호 중복 점검
	 * @param memberPhone 회원 전화번호
	 * @return 중복 여부
	 */
	int isEnablePhone(String memberPhone);
	
	/**
	 * 회원 등록(회원 정보 삽입)
	 * @param memberVO
	 */
	void insertMember(MemberVO memberVO);
	
	/**
	 * 회원 정보 조회
	 * @param memberId 회원 아이디
	 * @return 회원 정보
	 */
	MemberVO getMember(String memberId);
	
	/**
	 * 회원 정보 업데이트
	 * @param memberVO
	 */
	void updateMember(MemberVO memberVO);
	
	/**
	 * 회원 정보 삭제
	 * @param memberId 회원 아이디
	 */
	void deleteMember(String memberId);
	
	/**
	 * 전체 회원 정보 조회
	 * @return 전체 회원 정보
	 */
	List<MemberVO> getAllMembers();
	
	/**
	 * 전체 회원 정보 조회(페이징)
	 * @param pageDTO 페이징 객체
	 * @return 전체 회원 정보
	 */
	List<MemberVO> getMembersByPaging(PageDTO pageDTO);
	
	/**
	 * 전체 회원 정보 검색 조회(검색 필드/페이징)
	 * @param searchVO 검색 객체
	 * @return 전체 회원 정보
	 */
	List<MemberVO> getMembersByFieldAndPaging(SearchVO searchVO);
	
	/**
	 * 이메일 중복 점검
	 * @param map 이메일 정보 포함 map
	 * @return 중복 점검 결과
	 */
	List<Integer> isEnableEmailUpdate(HashMap map);
	
	/**
	 * 전화번호 중복 점검
	 * @param map 전화번호 정보 포함 map
	 * @return 중복 점검 결과
	 */
	List<Integer> isEnablePhoneUpdate(HashMap map);

}