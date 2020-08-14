/**
 * 
 */
package com.ssm.project.domain;

import java.util.Date;
import java.text.ParseException;

import lombok.Data;

/**
 * 회원정보 VO(값 객체)
 * 
 * @author javateam
 * @version 1.0
 */
@Data
public class MemberVO {
	
	/** 아이디 */
	private String memberId;
	/** 패쓰워드*/
	private String memberPassword;
	/** 별명 */
	private String memberNickname;
	/** 이름 */
	private String memberName;
	/** 성별 */
	private String memberGender;
	/** 이메일 */
	private String memberEmail;
	/** 연락처 */
	private String memberPhone;
	/** 생년월일 */
	private Date memberBirth;
	/** 우편번호 */
	private String memberZip;
	/** 주소 */
	private String memberAddress;
	/** 가입일 */
	private Date memberJoinDate;
	
	public MemberVO() {}
	
	/**
	 * DTO -> VO 치환
	 * @param memberDTO
	 * @throws ParseException 
	 */
	public MemberVO(MemberDTO memberDTO){
		this.memberId = memberDTO.getMemberId();
		this.memberPassword = memberDTO.getMemberPassword();
		this.memberNickname = memberDTO.getMemberNickname();
		this.memberName = memberDTO.getMemberName();
		this.memberGender = memberDTO.getMemberGender();
		this.memberEmail = memberDTO.getMemberEmail();
		this.memberPhone = memberDTO.getMemberPhone();
		this.memberBirth = new Date(memberDTO.getMemberBirth().getTime());
		this.memberZip = memberDTO.getMemberZip();
		this.memberAddress 
			= memberDTO.getMemberAddressBasic() 
			+ "*"
		    + memberDTO.getMemberAddressDetail();
		this.memberJoinDate = memberDTO.getMemberJoinDate();
	}
	
	
}