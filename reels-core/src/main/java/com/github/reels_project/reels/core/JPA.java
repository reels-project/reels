package com.github.reels_project.reels.core;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class JPA {
	
	@Inject 
	private CDI cdi;
	
	public EntityManager em(){
		//TODO unitId
		return cdi.get(EntityManager.class);
	}
}
