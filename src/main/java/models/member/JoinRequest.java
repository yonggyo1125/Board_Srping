package models.member;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JoinRequest {
	@NotBlank
	@Size(min=6)
	private String memId;
	
	@NotBlank
	private String memNm;
	
	@NotBlank
	@Size(min=8)
	private String memPw;
	
	@NotBlank
	private String memPwRe;
	
	@Email
	private String email;
	private String mobile;
	
	@AssertTrue
	private boolean agreeTerms;
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemNm() {
		return memNm;
	}
	
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	
	public String getMemPwRe() {
		return memPwRe;
	}
	
	public void setMemPwRe(String memPwRe) {
		this.memPwRe = memPwRe;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isAgreeTerms() {
		return agreeTerms;
	}

	public void setAgreeTerms(boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}

	@Override
	public String toString() {
		return "JoinRequest [memId=" + memId + ", memNm=" + memNm + ", memPw=" + memPw + ", memPwRe=" + memPwRe
				+ ", email=" + email + ", mobile=" + mobile + ", agreeTerms=" + agreeTerms + "]";
	}
}
