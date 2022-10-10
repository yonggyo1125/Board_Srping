package models.board;

import javax.validation.constraints.NotBlank;

import models.BaseDto;
import models.entity.BoardData;
import models.member.MemberDto;

public class BoardDataDto extends BaseDto {
	
	private Long id; // 게시글 등록번호
	private BoardDto board; // 게시판 설정
	private String gid; // 그룹 ID
	private String category; // 게시글 분류
	
	@NotBlank(message="글 제목을 입력하세요.")
	private String subject; // 글 제목
	@NotBlank(message="글 본문을 입력하세요. ")
	private String content; // 글 본문
	
	@NotBlank(message="작성자를 입력하세요.")
	private String poster; // 작성자
	
	private MemberDto member; // 작성 회원 
	private String guestPw; // 비회원 비밀번호
	private Long hit; // 조회수
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BoardDto getBoard() {
		return board;
	}
	
	public void setBoard(BoardDto board) {
		this.board = board;
	}
	
	public String getGid() {
		if (gid == null) gid = "" + System.currentTimeMillis();
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
	
	public MemberDto getMember() {
		return member;
	}
	
	public void setMember(MemberDto member) {
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

	@Override
	public String toString() {
		return "BoardDataDto [id=" + id + ", board=" + board + ", gid=" + gid + ", category=" + category + ", subject="
				+ subject + ", content=" + content + ", poster=" + poster + ", member=" + member + ", guestPw="
				+ guestPw + ", hit=" + hit + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	public static BoardData toEntity(BoardDataDto boardData) {
		if (boardData == null) {
			return null;
		}
		
		BoardData entity = new BoardData();
		entity.setId(boardData.getId());
		entity.setBoard(BoardDto.toEntity(boardData.getBoard()));
		entity.setCategory(boardData.getCategory());
		entity.setSubject(boardData.getSubject());
		entity.setContent(boardData.getContent());
		entity.setPoster(boardData.getPoster());
		entity.setGuestPw(boardData.getGuestPw());
		entity.setMember(MemberDto.toEntity(boardData.getMember()));
		entity.setGid(boardData.getGid());
		entity.setHit(boardData.getHit());
		
		return entity;
	}
	
	public static BoardDataDto toDto(BoardData entity) {
		if (entity == null) {
			return null;
		}
		
		BoardDataDto boardData = new BoardDataDto();
		boardData.setId(entity.getId());
		boardData.setBoard(BoardDto.toDto(entity.getBoard()));
		boardData.setCategory(entity.getCategory());
		boardData.setSubject(entity.getSubject());
		boardData.setContent(entity.getContent());
		boardData.setPoster(entity.getPoster());
		boardData.setGuestPw(entity.getGuestPw());
		boardData.setMember(MemberDto.toDto(entity.getMember()));
		boardData.setGid(entity.getGid());
		boardData.setHit(entity.getHit());
		boardData.setRegDt(entity.getRegDt());
		boardData.setModDt(entity.getModDt());
		
		return boardData;
	}
}






