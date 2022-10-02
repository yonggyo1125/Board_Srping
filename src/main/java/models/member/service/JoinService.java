package models.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.member.JoinRequest;
import models.member.MemberDao;
import models.member.MemberDto;
import models.member.exception.DuplicatedMemberException;
import models.member.exception.MobilePatternException;
import models.member.exception.PasswordIncorrectException;

import org.mindrot.bcrypt.BCrypt;

@Service
public class JoinService {
	
	@Autowired
	private MemberDao memberDao;
	
	public void join(JoinRequest request, Errors errors) {
		if (errors.hasErrors()) { // 이미 앞선 오류(커맨드 객체 애노테이션)가 있으면 중단 
			return;
		}
		
		/**
		 * 유효성검사
		 * 1. 이미 가입된 아이디인지? - DuplicatedMemberException
		 * 2. 비밀번호 확인이 일치하는지? - PasswordIncorrectException
		 * 3. 휴대전화번호가 필수는 아니지만 입력된 경우 형식 체크 - MobilePatternException
		 * 
		 * 회원 가입 처리(DB 처리)
		 *
		 */
		// 1. 이미 가입된 아이디인지?
		String memId = request.getMemId();
		MemberDto member = memberDao.get(memId);
		if (member != null) { // 이미 가입된 회원
			throw new DuplicatedMemberException();
		}
		
		// 2. 비밀번호 확인이 일치하는지
		String memPw = request.getMemPw();
		String memPwRe = request.getMemPwRe();
		
		if (!memPw.equals(memPwRe)) {
			throw new PasswordIncorrectException();
		}
		
		//  3.휴대전화번호가 필수는 아니지만 입력된 경우 형식 체크
		String mobile = request.getMobile();
		if (mobile != null && !mobile.isBlank()) { // 휴대전화번호가 있는 경우  \D -> [^0-9] : 숫자가 아닌 패턴 \d -> [0-9]
			mobile = mobile.replaceAll("\\D", ""); // 숫자가 아닌 문자 제거 -> 숫자만 남는다.
			boolean isMatch = mobile.matches("01[016]\\d{3,4}\\d{4}");
			if (!isMatch) { // 패턴이 일치하지 않는 경우 
				throw new MobilePatternException();
			}
		}
		
		String hash = BCrypt.hashpw(memPw, BCrypt.gensalt(12));
		MemberDto param = new MemberDto();
		param.setMemId(memId);
		param.setMemPw(hash);
		param.setMemNm(request.getMemNm());
		param.setEmail(request.getEmail());
		param.setMobile(mobile);
		
		memberDao.register(param);
	}
}