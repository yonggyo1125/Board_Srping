package models.file;

import models.BaseDto;
import models.entity.FileInfo;
import models.member.MemberDto;

public class FileInfoDto extends BaseDto {
	private Long id; // 파일 ID
	private String gid; // 그룹 ID
	private String fileName; // 파일 원본 이름
	private String contentType; // 파일 종류
	private boolean done; // 그룹 작업 완료 여부
	private MemberDto member; // 파일 소유 회원
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGid() {
		return gid;
	}
	
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public MemberDto getMember() {
		return member;
	}
	
	public void setMember(MemberDto member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "FileInfoDto [id=" + id + ", gid=" + gid + ", fileName=" + fileName + ", contentType=" + contentType
				+ ", done=" + done + ", member=" + member + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	public static FileInfo toEntity(FileInfoDto fileInfo) {
		if (fileInfo == null) {
			return null;
		}
		
		FileInfo entity = new FileInfo();
		entity.setId(fileInfo.getId());
		entity.setGid(fileInfo.getGid());
		entity.setFileName(fileInfo.getFileName());
		entity.setContentType(fileInfo.getContentType());
		entity.setDone(fileInfo.isDone());
		entity.setMember(MemberDto.toEntity(fileInfo.getMember()));
		
		return entity;
	}
	
	public static FileInfoDto toDto(FileInfo entity) {
		if (entity == null) {
			return null;
		}
		
		FileInfoDto fileInfo = new FileInfoDto();
		fileInfo.setId(entity.getId());
		fileInfo.setGid(entity.getGid());
		fileInfo.setFileName(entity.getFileName());
		fileInfo.setContentType(entity.getContentType());
		fileInfo.setDone(entity.isDone());
		fileInfo.setMember(MemberDto.toDto(entity.getMember()));
		fileInfo.setRegDt(entity.getRegDt());
		fileInfo.setModDt(entity.getModDt());
		
		return fileInfo;
	}
}




