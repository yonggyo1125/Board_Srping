package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import models.member.MemberDao;

@Controller
public class ExamController {
	
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/ex")
	@ResponseBody
	public void exam() {
		memberDao.testMethod();
	}
}
