package models.board;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardData;

@Component
public class BoardDataDao {
	
	@Autowired
	private EntityManager em;
	
	/**
	 * 게시글 등록
	 * @param param
	 * @return
	 */
	public BoardDataDto register(BoardDataDto param) {
		BoardData entity = BoardDataDto.toEntity(param);
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
		
	}
	
	/**
	 * 게시글 조회
	 * @param id
	 * @return
	 */
	public BoardDataDto get(Long id) {
		
		BoardData entity = em.find(BoardData.class, id);
		
		return BoardDataDto.toDto(entity);
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		BoardData entity = em.find(BoardData.class, id);
		if (entity != null) {
			em.remove(entity);
			em.flush();
		}
	}
}



