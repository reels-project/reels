package com.github.reels_project.reels.core;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

public class CDITest {
	
	@Test
	public void bm(){
		BeanManager bm = mock(BeanManager.class);
		
		CDI p = new CDI();
		Whitebox.setInternalState(p, "beanManager", bm);
		
		BeanManager beanManager = p.bm();
		assertThat(beanManager, is(bm));
	}
	
	@Test
	public void get(){
		BeanManager bm = mock(BeanManager.class);
		EntityManager em = mock(EntityManager.class);
		
		when(bm.getReference(any(), any(), any())).thenReturn(em);
		
		CDI p = new CDI();
		Whitebox.setInternalState(p, "beanManager", bm);
		
		EntityManager entityManager = p.get(EntityManager.class);
		assertThat(entityManager, is(em));
		
		verify(bm,times(1)).getReference(any(), any(), any());
	}
}
