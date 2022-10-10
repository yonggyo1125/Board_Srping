package models.entity;

import javax.persistence.*;

@Entity
public class BoardData extends BaseEntity {
	@Id @GeneratedValue
	private Long id; // 게시글 등록번호
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="boardId")
	private Board board; // 게시판 설정 
	
	@Column(length=45)
	private String gid; // 그룹 ID
	
	@Column(length=45)
	private String category; // 게시글 분류
	
	@Column(nullable=false)
	private String subject; // 글 제목
	
	@Lob
	@Column(nullable=false)
	private String content; // 글 본문
	
	@Column(nullable=false)
	private String poster; // 작성자
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memNo")
	private Member member; // 회원
	
	@Column(length=65)
	private String guestPw; //  비회원 비밀번호
	
	private Long hit; // 조회수

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getGuestPw() {
		return guestPw;
	}

	public void setGuestPw(String guestPw) {
		this.guestPw = guestPw;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}
}
