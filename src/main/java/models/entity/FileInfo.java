package models.entity;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(
			name="FileInfo.getByGid",
			query="SELECT f FROM FileInfo f LEFT JOIN f.member m WHERE f.gid=:gid ORDER BY f.regDt"
	),
	@NamedQuery(
			name="FileInfo.deleteByGid",
			query="DELETE FROM FileInfo f WHERE f.gid = :gid"
	)
})
@Entity
public class FileInfo extends BaseEntity {
	@Id @GeneratedValue
	private Long id; // 파일 등록번호
	
	@Column(length=45, nullable=false)
	private String gid; // 그룹 ID
	
	@Column(length=100, nullable=false)
	private String fileName; // 원본파일명
	
	@Column(length=60, nullable=false)
	private String contentType; // 파일 형식
	
	@Column(length=15, nullable=false)
	private String extension; // 파일 확장자 
	
	private boolean done; // 그룹 작업 완료 여부
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memNo")
	private Member member; // 파일 소유자

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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}



