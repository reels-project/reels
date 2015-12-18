package com.github.reels_project.reels.faces.controller.module;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.github.reels_project.reels.faces.controller.Navigation;
import com.github.reels_project.reels.faces.controller.module.Rendering;

public class RenderingTest {
	
	Rendering create(){
		return new Rendering() {};
	}
	
	@Test
	public void render(){
		Navigation n = create().render("index.xhtml");
		assertNotNull(n);
		assertThat(n.getOutcome().contains("faces-redirect=true"), is(false));
		
		Navigation n2 = create().render("index2.xhtml");
		assertNotNull(n2);
		
		assertThat(n, is(not(n2)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void render_empty(){
		create().render("");
	}
	
	@Test(expected=NullPointerException.class)
	public void render_null(){
		create().render(null);
	}
}
