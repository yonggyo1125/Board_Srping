package models.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.member.LoginRequest;
import models.member.MemberDao;
import models.member.MemberDto;
import models.member.exception.MemberNotFoundException;
import models.member.exception.PasswordIncorrectException;

import org.mindrot.bcrypt.BCrypt;

@Service
public class LoginService {
	
	@Autowired
	private MemberDao memberDao;
	
	public MemberDto login(LoginRequest request, Errors errors) {
		
		if (errors.hasErrors()) { // 필수 항목이 누락된 경우 하단 로직 실행 X
			return null;
		}
		
		/**
		 * 유효성 검사 
		 * 1. 아이디에 해당하는 회원이 있는지? MemberNotFoundException
		 * 2. 비번이 일치하는지? PasswordIncorrectException
		 * 
		 * 3. 이상 없으면 MemberDto 객체 반환 
		 */
		 // 1. 아이디에 해당하는 회원이 있는지
		MemberDto member = memberDao.get(request.getMemId());
		if (member == null) { // 아이디에 해당하는 회원이 없는 경우 
			throw new MemberNotFoundException();
		}
		
		// 2. 비번이 일치하는지?
		boolean isMatched = BCrypt.checkpw(request.getMemPw(), member.getMemPw());
		if (!isMatched) {
			throw new PasswordIncorrectException();
		}
		
		return member;
	}
	
}
