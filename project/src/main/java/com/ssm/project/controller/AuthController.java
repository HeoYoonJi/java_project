package com.ssm.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.project.domain.CustomUser;
import com.ssm.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
public class AuthController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/welcome")
	public void welcome() {
		
		log.info("home");
	}
	
	@RequestMapping(value = "/welcome**", 
					method = RequestMethod.GET)
	public ModelAndView defaultPage() {
	
		log.info("welcome");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Password Encoder");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;
	} //
    
    @RequestMapping(value= {"/admin/home", "/admin"}, 
					method = RequestMethod.GET)
	public String securedAdminHome(ModelMap model) {
	
    	log.info("/admin/home");
    	
		Object principal = SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
		
		CustomUser user = null;
		
		if (principal instanceof CustomUser) {
			user = ((CustomUser)principal);
		}
		
		log.info("user : " +user);
		
		String name = user.getUsername();
		model.addAttribute("username", name);
		model.addAttribute("message", "관리자 페이지에 들어오셨습니다.");
		
		return "/admin/home";
    }
    
    @RequestMapping(value= {"/secured/home", "/secured"}, 
    				method = RequestMethod.GET)
    public String securedHome(ModelMap model) {
    	
    	log.info("/secured/home");
    	
        Object principal = SecurityContextHolder.getContext()
        										.getAuthentication()
        										.getPrincipal();
        
        CustomUser user = null;
        
        if (principal instanceof CustomUser) {
        	user = ((CustomUser)principal);
        }
        
        log.info("user : "+user);
     
	    String name = user.getUsername();
	    model.addAttribute("username", name);
	    model.addAttribute("message", "일반 사용자 페이지에 들어오셨습니다.");
	    
	    return "/secured/home";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
    	
    	log.info("login");
    	
    	return "login";
    }
    
    @RequestMapping(value="/logoutProc", method = RequestMethod.GET)
    public String logout(ModelMap model,
    					 HttpServletRequest request,
    					 HttpServletResponse response) {
    	
    	log.info("logout");
    	
	    Authentication auth 
		    	= SecurityContextHolder.getContext()
		    						   .getAuthentication();
	    
	    log.info("#### auth : "+auth.getName());
	    
	    // logout !
	    if (auth != null) {    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    return "logout";
    } //
    
    @RequestMapping(value="/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
    	
	    model.addAttribute("error", "true");
	    model.addAttribute("msg", "로그인 정보가 잘못되었습니다.");
	    
	    return "login";
    } //
    
/*   
    @RequestMapping("/access-denied")
    public String accessDenied() {
    	
    	log.info("403");
    	
    	return "redirect:/login";
    }
*/
    
    // id 유무 점검 : spring security bug patch !
 	@RequestMapping("/login/idCheck")
 	public String idCheck(@RequestParam("username") String userName,
 						  Model model) {
 		
 		log.info("userName : "+userName);
 		
 		boolean flag = memberService.hasUsername(userName);
 		System.out.println(flag);
 		model.addAttribute("flag", flag);
 		
 		return "idCheck";
 	} //
 
} //