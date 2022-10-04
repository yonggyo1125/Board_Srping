package commons.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import models.member.MemberDto;
import models.member.MemberType;

/**
 * 관리자 페이지 접근권한 체크
 * 
 * @author Administrator
 *
 */
public class AdminCheck implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		
		// 관리자인 경우 통과!
		if (member != null && member.getMemType() == MemberType.ADMIN) {
			return true;
		}
		
		// 관리자가 아니면 로그인 페이지로 이동
		
		response.sendRedirect(request.getContextPath() + "/member/login");
		return false;
	}
}
