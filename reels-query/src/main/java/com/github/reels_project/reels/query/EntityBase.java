package com.github.reels_project.reels.query;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.reels_project.reels.Reels;

@MappedSuperclass
public abstract class EntityBase {
	
	private static Logger logger = LoggerFactory.getLogger(EntityBase.class);
	
	public EntityManager em(){
		return Reels.jpa().em();
	}
	
	public void create(){
		logger.debug("{}.create() called.",this.getClass());
		try {
			em().persist(this);
		} catch (RuntimeException e) {
			//FIXME どんなときにエラーを投げ直すんだっけ
			throw e;
		}
	}
	
	public void save(){
		//FIXME どんなときにエラーを投げ直すんだっけ
		logger.debug("{}.save() called.",this.getClass());
		em().merge(this);
	}
	
	public void delete(){
		//FIXME どんなときにエラーを投げ直すんだっけ
		logger.debug("{}.delete() called.",this.getClass());
		em().remove(this);
	}
}
