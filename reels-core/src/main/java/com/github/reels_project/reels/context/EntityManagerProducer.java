package com.github.reels_project.reels.context;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class EntityManagerProducer {
	
	private static Logger logger = LoggerFactory.getLogger(EntityManagerProducer.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Produces
	public EntityManager entityManager(){
		logger.debug("new EntityManager {}",entityManager);
		return entityManager;
	}
}
