package com.github.reels_project.reels;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.github.reels_project.reels.core.CDI;
import com.github.reels_project.reels.core.JPA;

public class ReelsTest {
	
	@Test
	public void good(){
		CDI cdi = mock(CDI.class);
		JPA jpa = mock(JPA.class);
		
		Reels._initialize(cdi, jpa);
		
		CDI c = Reels.cdi();
		JPA j = Reels.jpa();
		
		assertThat(c, is(cdi));
		assertThat(j, is(jpa));
	}

	@Test(expected=NullPointerException.class)
	public void _initialize_cdiIsNull(){
		JPA jpa = mock(JPA.class);
		
		Reels._initialize(null, jpa);
	}
	
	@Test(expected=NullPointerException.class)
	public void _initialize_jpaIsNull(){
		CDI cdi = mock(CDI.class);
		
		Reels._initialize(cdi, null);
	}

	@Test(expected=IllegalStateException.class)
	public void _initialize_twice(){
		CDI cdi = mock(CDI.class);
		JPA jpa = mock(JPA.class);
		
		Reels._initialize(cdi, jpa);
		Reels._initialize(cdi, jpa);
	}
}
