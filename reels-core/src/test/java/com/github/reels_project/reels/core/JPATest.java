package com.github.reels_project.reels.core;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

public class JPATest {
	
	@Test
	public void em(){
		EntityManager em = mock(EntityManager.class);
		CDI cdi = mock(CDI.class);
		when(cdi.get(EntityManager.class)).thenReturn(em);
		
		JPA p = new JPA();
		Whitebox.setInternalState(p, "cdi", cdi);
		
		EntityManager entityManager = p.em();
		assertThat(entityManager, is(em));
		verify(cdi,times(1)).get(EntityManager.class);
		
	}
}
