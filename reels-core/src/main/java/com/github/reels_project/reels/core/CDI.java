package com.github.reels_project.reels.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@ApplicationScoped
public class CDI {
	
	@Inject
	private BeanManager beanManager;
	
	public BeanManager bm(){
		return beanManager;
	}
	
	public <T> T get(Class<T> clazz){
		BeanManager bm = bm();
		Bean<?> bean = bm.resolve(bm.getBeans(clazz));
		CreationalContext<?> cc = bm.createCreationalContext(bean);
		T t = clazz.cast(bm.getReference(bean, clazz, cc));
		return t;
	}
}
