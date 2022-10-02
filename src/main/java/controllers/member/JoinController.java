package controllers.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commons.errors.CommonException;
import models.member.JoinRequest;
import models.member.service.JoinService;

@Controller
@RequestMapping("/member/join")
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	@GetMapping
	public String form(Model model) {
		
		JoinRequest joinRequest = new JoinRequest();
		model.addAttribute("joinRequest", joinRequest);
		
		return "member/join";
		
	}
	
	@PostMapping
	public String process(@Valid JoinRequest joinRequest, Errors errors) {
		
		try {
			joinService.join(joinRequest, errors);
			
		} catch(CommonException e) {
			String field = e.getField();
			if (field == null) {
				errors.reject(e.getMessage());
			} else {
				errors.rejectValue(field, e.getMessage());
			}
		}
		
		if (errors.hasErrors()) {
			return "member/join";
		}
		
		return "redirect:/member/login";
	}
}
