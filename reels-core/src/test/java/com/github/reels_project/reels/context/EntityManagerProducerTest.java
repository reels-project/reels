package com.github.reels_project.reels.context;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

public class EntityManagerProducerTest {
	
	@Test
	public void entityManager(){
		EntityManager em = mock(EntityManager.class);
		
		EntityManagerProducer p = new EntityManagerProducer();
		Whitebox.setInternalState(p, "entityManager", em);
		
		EntityManager entityManager = p.entityManager();
		
		assertThat(entityManager, is(em));
	}
}
