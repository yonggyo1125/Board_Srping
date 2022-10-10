package models.board;

import models.BaseDto;
import models.entity.Board;

public class BoardDto extends BaseDto {
	private String id; // 게시판 아이디
	private String title; // 게시판명
	private Integer rowsPerPage = 20; // 1페이지당 노출 갯수
	private boolean useComment; // 댓글 사용 여부
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getRowsPerPage() {
		return rowsPerPage;
	}
	
	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	public boolean isUseComment() {
		return useComment;
	}
	
	public void setUseComment(boolean useComment) {
		this.useComment = useComment;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", title=" + title + ", rowsPerPage=" + rowsPerPage + ", useComment=" + useComment
				+ ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	public static Board toEntity(BoardDto board) {
		if (board == null) {
			return null;
		}
		
		Board entity = new Board();
		entity.setId(board.getId());
		entity.setTitle(board.getTitle());
		entity.setRowsPerPage(board.getRowsPerPage());
		entity.setUseComment(board.isUseComment());
		
		return entity;
	}
	
	public static BoardDto toDto(Board entity) {
		if (entity == null) {
			return null;
		}
		
		BoardDto board = new BoardDto();
		board.setId(entity.getId());
		board.setTitle(entity.getTitle());
		board.setRowsPerPage(entity.getRowsPerPage());
		board.setUseComment(entity.isUseComment());
		board.setRegDt(entity.getRegDt());
		board.setModDt(entity.getModDt());
		
		return board;
	}
}





