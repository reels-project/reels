package com.github.reels_project.reels.faces.view.module;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.github.reels_project.reels.faces.view.Navigation;

public class RedirectingTest {
	
	Redirecting create(){
		return new Redirecting() {};
	}
	
	@Test
	public void render(){
		Navigation n = create().redirectTo("index.xhtml");
		assertNotNull(n);
		assertThat(n.getOutcome().contains("faces-redirect=true"), is(true));
		
		Navigation n2 = create().redirectTo("index2.xhtml");
		assertNotNull(n2);
		
		assertThat(n, is(not(n2)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void render_empty(){
		create().redirectTo("");
	}
	
	@Test(expected=NullPointerException.class)
	public void render_null(){
		create().redirectTo(null);
	}
}
