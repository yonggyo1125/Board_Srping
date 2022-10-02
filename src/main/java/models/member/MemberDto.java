package models.member;

import models.BaseDto;
import models.entity.Member;

public class MemberDto extends BaseDto {
	
	private Long memNo;
	private String memId;
	private String memPw;
	private String memNm;	
	private String email;
	private String mobile;
	private MemberType memType;
	
	
	public Long getMemNo() {
		return memNo;
	}
	
	public void setMemNo(Long memNo) {
		this.memNo = memNo;
	}
	
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
	
	public String getMemNm() {
		return memNm;
	}
	
	public void setMemNm(String memNm) {
		this.memNm = memNm;
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
	
	public MemberType getMemType() {
		return memType;
	}

	public void setMemType(MemberType memType) {		
		this.memType = memType;
	}

	@Override
	public String toString() {
		return "MemberDto [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memNm=" + memNm + ", email="
				+ email + ", mobile=" + mobile + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	public static Member toEntity(MemberDto member) {
		if (member == null) {
			return null;
		}
		
		Member entity = new Member();
		entity.setMemNo(member.getMemNo());
		entity.setMemId(member.getMemId());
		entity.setMemNm(member.getMemNm());
		entity.setMemPw(member.getMemPw());
		entity.setEmail(member.getEmail());
		entity.setMobile(member.getMobile());
		
		return entity;
	}
	
	public static MemberDto toDto(Member entity) {
		if (entity == null) {
			return null;
		}
		
		MemberDto member = new MemberDto();
		member.setMemNo(entity.getMemNo());
		member.setMemId(entity.getMemId());
		member.setMemNm(entity.getMemNm());
		member.setMemPw(entity.getMemPw());
		member.setEmail(entity.getEmail());
		member.setMobile(entity.getMobile());
		member.setRegDt(entity.getRegDt());
		member.setModDt(entity.getModDt());
		member.setMemType(entity.getMemType());
		
		return member;
	}
}
