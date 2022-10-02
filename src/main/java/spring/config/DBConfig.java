package spring.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import commons.proxy.TransactionProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DBConfig {
	
	@Bean
	public TransactionProxy transactionProxy() {
		return new TransactionProxy();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("spring_board");
		return emf;
	}
	
	@Bean
	public EntityManager entityManager() {
		EntityManagerFactory emf = entityManagerFactory();
		
		return emf.createEntityManager();
	}
}