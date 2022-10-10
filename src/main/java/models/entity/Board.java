package models.entity;

import javax.persistence.*;

@Entity
public class Board extends BaseEntity {
	@Id
	private String id; // 게시판 아이디
	
	@Column(length=45, nullable=false)
	private String title; // 게시판명
	
	private Integer rowsPerPage = 20; // 1페이지당 노출되는 게시글 갯수, 기본값은 20
	
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
}
