package models.file;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.*;

@Component
public class FileInfoDao {
	
	@Autowired
	private EntityManager em;
	
	public FileInfoDto register(FileInfoDto param) {
		FileInfo entity = FileInfoDto.toEntity(param);
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
	}
	
	/**
	 * 파일 조회
	 * @param id
	 * @return
	 */
	public FileInfoDto get(Long id) {
		FileInfo entity = em.find(FileInfo.class, id);
		
		return entity == null ? null : FileInfoDto.toDto(entity);
	}
	
	/**
	 * 파일 그룹별 목록 
	 * 
	 * @param gid
	 * @return
	 */
	public List<FileInfoDto> gets(String gid) {

		TypedQuery<FileInfo> tq = em.createNamedQuery("FileInfo.getByGid", FileInfo.class);
		tq.setParameter("gid", gid);
		
		List<FileInfoDto> fileInfos = tq.getResultStream().map(FileInfoDto::toDto).toList();
		
		return fileInfos;
	}
	
	/**
	 * 파일 삭제 
	 * @param id
	 */
	public void delete(Long id) {
		FileInfo entity = em.find(FileInfo.class, id);
		em.remove(entity);
		
		em.flush();
	}
	
	/**
	 * 
	 * @param gid
	 */
	public boolean deletes(String gid) {
		
		int affectedRows = em.createNamedQuery("FileInfo.deleteByGid")
											.setParameter("gid", gid)
											.executeUpdate();
		
		return affectedRows > 0;
	}
}



