package commons.proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class TransactionProxy {
	
	@Autowired
	private EntityManager em;
	
	@Around("execution(public * models..*Dao.* (..))")
	public Object apply(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object result = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			result = joinPoint.proceed();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return result;
	}
}
