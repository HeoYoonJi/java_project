package com.ssm.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "idCheck.do", 
					method = RequestMethod.POST,
					produces = "text/plain; charset=UTF-8")
	public String isMember(@RequestParam("memberId") String memberId) {
		
		log.info("### MemberRestController : idCheck.do ###");
		return memberService.isMember(memberId.trim()) == true ? 
			   "아이디가 이미 존재합니다." : "사용할 수 있는 아이디입니다.";
	} //
	
	@RequestMapping(value = "emailCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnableEmail(@RequestParam("memberEmail") String memberEmail) {
	
		log.info("### MemberRestController : emailCheck.do ###");
		return memberService.isEnableEmail(memberEmail.trim()) == true ? 
			   "중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
	} //
	
	@RequestMapping(value = "phoneCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnablePhone(@RequestParam("memberPhone") String memberPhone) {
	
		log.info("### MemberRestController : phoneCheck.do ###");
		return memberService.isEnablePhone(memberPhone.trim()) == true ? 
			   "중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
	} //
	
	@RequestMapping(value = "emailCheckUpdate.do", 
				produces = "text/plain; charset=UTF-8")
	public String isEnableEmail(@RequestParam("memberId") String memberId,
			@RequestParam("memberEmail") String memberEmail	) {
	
	log.info("### MemberRestController : emailCheckUpdate.do ###");
	return memberService.isEnableEmail(memberId, memberEmail.trim()) == true ? 
		   "중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
	} //
	
	@RequestMapping(value = "phoneCheckUpdate.do", 
				produces = "text/plain; charset=UTF-8")
	public String isEnablePhone(@RequestParam("memberId") String memberId,
					@RequestParam("memberPhone") String memberPhone) {
	
	log.info("### MemberRestController : phoneCheckUpdate.do ###");
	return memberService.isEnablePhone(memberId, memberPhone.trim()) == true ? 
		   "중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
	} //
	

}