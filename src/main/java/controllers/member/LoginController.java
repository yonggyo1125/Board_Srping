package controllers.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commons.errors.CommonException;
import models.member.LoginRequest;
import models.member.MemberDto;
import models.member.service.LoginService;

@Controller
@RequestMapping("/member/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping
	public String form(Model model, @CookieValue(name="savedId", required=false) String savedId) {
		
		LoginRequest loginRequest = new LoginRequest();
		
		if (savedId != null) {
			loginRequest.setSaveId(true);
			loginRequest.setMemId(savedId);
		}
		
		model.addAttribute(loginRequest);
		
		return "member/login";
	}
	
	@PostMapping
	public String process(@Valid LoginRequest loginRequest, Errors errors, HttpSession session, HttpServletResponse response) {
		
		try {
			MemberDto member = loginService.login(loginRequest, errors);
			
			session.setAttribute("member", member); // 로그인 처리 완료
			
			Cookie savedId = new Cookie("savedId", member.getMemId());
			if (loginRequest.isSaveId()) { // 아이디 저장이 체크된 경우 
				savedId.setMaxAge(60 * 60 * 24 * 365); // 1년간 저장
			} else { // 아이디 저장이 체크가 안된 경우 
				savedId.setMaxAge(0); // 쿠키 삭제
			}
			
			response.addCookie(savedId);
			
		} catch (CommonException e) {
			errors.rejectValue(e.getField(), e.getMessage());
		}
		
		if (errors.hasErrors()) {
			return "member/login";
		}
		
		return "redirect:/"; // 로그인이 완료 되면 메인페이지 이동
	}
}
