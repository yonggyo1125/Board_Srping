package models.member;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String memId;
	
	@NotBlank
	private String memPw;
	private boolean saveId;
	
	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemPw() {
		return memPw;
	}
	
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	
	public boolean isSaveId() {
		return saveId;
	}
	
	public void setSaveId(boolean saveId) {
		this.saveId = saveId;
	}

	@Override
	public String toString() {
		return "LoginRequest [memId=" + memId + ", memPw=" + memPw + ", saveId=" + saveId + "]";
	}
}
