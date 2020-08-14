package com.ssm.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.project.dao.MemberDAO;
import com.ssm.project.domain.MemberVO;
import com.ssm.project.domain.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional(readOnly = true)
	@Override
	public boolean hasUsername(String username) {
		log.info("Service hasUsername");
		return memberDAO.hasUsername(username);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void insertMember(MemberVO memberVO) {
		
		log.debug("svc insertMember");
		memberDAO.insertMember(memberVO);
		
		// 패쓰워드 암호화
		BCryptPasswordEncoder passwordEncoder 
			= new BCryptPasswordEncoder();
		String hashedPassword 
			= passwordEncoder.encode(memberVO.getMemberPassword());
		
		Users users = new Users();
		users.setUsername(memberVO.getMemberId());
		users.setPassword(hashedPassword);
		users.setEnabled(1);
		
		// 암호화 패쓰워드 및 롤(Role) 정보 저장
		memberDAO.insertUsers(users, "ROLE_USER");
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isMember(String id) {
		
		log.debug("svc isMember");
		return memberDAO.isMember(id);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnableEmail(String email) {
		
		log.debug("svc isEnableEmail");
		return memberDAO.isEnableEmail(email);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnablePhone(String phone) {

		log.debug("svc isEnablePhone");
		return memberDAO.isEnablePhone(phone);
	}

	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String memberId) {
		
		log.debug("svc getMember");
		return memberDAO.getMember(memberId);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updateMember(MemberVO member) {
		
		log.debug("svc updateMember");
		memberDAO.updateMember(member);
		
		log.info("### 회원정보 :"+ member);
		log.info("### 패쓰워드 :"+ member.getMemberPassword());
				
		// 패쓰워드 없으면(공백) 패쓰워드 변경 구문 통과(pass)
		if (!member.getMemberPassword().trim().contentEquals("")) {
			
			log.info("############### 패쓰워드 변경 ############");
			// 패쓰워드 암호화
			log.info("--------------------------------------");
			BCryptPasswordEncoder passwordEncoder 
				= new BCryptPasswordEncoder();
			String hashedPassword 
				= passwordEncoder.encode(member.getMemberPassword());
			
			Users users = new Users();
			users.setUsername(member.getMemberId());
			users.setPassword(hashedPassword);
			users.setEnabled(1);
			
			log.info("###### 신규 패쓰워드 : " + users.getPassword());
			
			// 암호화 패쓰워드 및 롤(Role) 정보 저장
			memberDAO.updateUsers(users);
		} else  {
			log.info("##### 패쓰워드 불변 ######");	
		} //		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void deleteMember(String memberId) {

		log.debug("svc deleteMember");
		memberDAO.deleteMember(memberId);
		memberDAO.deleteUsers(memberId);
	}

	@Transactional(readOnly=true)
	@Override
	public List<MemberVO> getMembersByPaging(int page, int limit) {

		log.debug("svc getMembersByPaging");
		return memberDAO.getMembersByPaging(page, limit);
	}

	@Transactional(readOnly=true)
	@Override
	public List<MemberVO> getAllMembers() {

		log.debug("svc getAllMembers");
		return memberDAO.getAllMembers();
	}

	@Transactional(readOnly=true)
	@Override
	public List<MemberVO> getMembersByFieldAndPaging(String fld, Object value, boolean isLike, int page, int limit) {
		
		log.debug("svc getMembersByFieldAndPaging");
		return memberDAO.getMembersByFieldAndPaging(fld, value, isLike, page, limit);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isEnableEmail(String memberId, String email) {
		log.debug("svc isEnableEmail(update)");
		return memberDAO.isEnableEmail(memberId, email);
	}

	@Override
	public boolean isEnablePhone(String memberId, String phone) {
		log.debug("svc isEnablePhone(update)");
		return memberDAO.isEnablePhone(memberId, phone);
	}

}
