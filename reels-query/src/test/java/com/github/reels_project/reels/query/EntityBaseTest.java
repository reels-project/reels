package com.github.reels_project.reels.query;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.github.reels_project.reels.Reels;
import com.github.reels_project.reels.core.JPA;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Reels.class)
public class EntityBaseTest {
	
	@Test
	public void create(){
		PowerMockito.mockStatic(Reels.class);
		JPA jpa = mock(JPA.class);
		EntityManager em = mock(EntityManager.class);
		when(jpa.em()).thenReturn(em);
		when(Reels.jpa()).thenReturn(jpa);
		
		new EntityBase() {}.create();
		
		verify(em).persist(any());
	}
	
	@Test
	public void save(){
		PowerMockito.mockStatic(Reels.class);
		JPA jpa = mock(JPA.class);
		EntityManager em = mock(EntityManager.class);
		when(jpa.em()).thenReturn(em);
		when(Reels.jpa()).thenReturn(jpa);
		
		new EntityBase() {}.save();
		
		verify(em).merge(any());
	}
	
	@Test
	public void delete(){
		PowerMockito.mockStatic(Reels.class);
		JPA jpa = mock(JPA.class);
		EntityManager em = mock(EntityManager.class);
		when(jpa.em()).thenReturn(em);
		when(Reels.jpa()).thenReturn(jpa);
		
		new EntityBase() {}.delete();
		
		verify(em).remove(any());
	}
}
