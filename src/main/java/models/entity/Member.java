package models.entity;

import javax.persistence.*;

import models.member.MemberType;

@Entity
public class Member extends BaseEntity {
	@Id @GeneratedValue
	private Long memNo;
	
	@Column(length=40, unique=true, nullable=false)
	private String memId;
	
	@Column(length=65, nullable=false)
	private String memPw;
	
	@Column(length=40, nullable=false)
	private String memNm;
	
	@Column(length=100)
	private String email;
	
	@Column(length=11)
	private String mobile;
	
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private MemberType memType = MemberType.MEMBER;
	
	/**
	@OneToMany(mappedBy="member")
	private List<FileInfo> fileInfos = new ArrayList<>();
	*/
	
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
}