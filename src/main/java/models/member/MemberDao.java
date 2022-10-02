package models.member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Member;

@Component
public class MemberDao {
	
	@Autowired
	private EntityManager em;
	
	public MemberDto register(MemberDto params) {
		Member entity = MemberDto.toEntity(params);
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getMemNo());
	}
	
	public MemberDto get(Long memNo) {
		
		Member entity = em.find(Member.class, memNo);
		
		return MemberDto.toDto(entity);
	}
	
	public MemberDto get(String memId) {
		
		try {
			String sql = "SELECT m FROM Member m WHERE m.memId = :memId";
			TypedQuery<Member> tq = em.createQuery(sql, Member.class);
			tq.setParameter("memId", memId);
			Member entity = tq.getSingleResult();
			
			return MemberDto.toDto(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
}