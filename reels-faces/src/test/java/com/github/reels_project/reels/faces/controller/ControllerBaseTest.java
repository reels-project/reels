package com.github.reels_project.reels.faces.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.github.reels_project.reels.faces.controller.ControllerBase;

public class ControllerBaseTest {
	
	private FacesContext facesContext = mock(FacesContext.class);
	private Flash flash = mock(Flash.class);
	
	@Test
	public void getFacesContext(){
		ControllerBase base = new ControllerBase() {};
		Whitebox.setInternalState(base, "facesContext", facesContext);
		
		FacesContext result = base.getFacesContext();
		
		assertThat(result, is(facesContext));
	}
	
	@Test(expected=NullPointerException.class)
	public void getFacesContext_null(){
		ControllerBase base = new ControllerBase() {};
		base.getFacesContext();
	}
	
	@Test
	public void getFlash(){
		ControllerBase base = new ControllerBase() {};
		Whitebox.setInternalState(base, "flash", flash);
		
		Flash result = base.getFlash();
		
		assertThat(result, is(flash));
	}
	
	@Test(expected=NullPointerException.class)
	public void getFlash_null(){
		ControllerBase base = new ControllerBase() {};
		base.getFlash();
	}

}
