package com.github.reels_project.reels.faces.router;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

public class ViewHandlerTest {
	
	private FacesContext facesContext;
	private ViewHandler viewHandler;
	
	@Before
	public void before(){
		facesContext = mock(FacesContext.class);
		viewHandler = mock(ViewHandler.class);
	}
	
	@Test
	public void getActionURL(){
		when(viewHandler.getActionURL(any(), anyString())).thenReturn("/context/views/product/list.xhtml");
		
		String url = new ReelsViewHandler(viewHandler).getActionURL(facesContext, "list.xhtml");
		
		assertNotNull(url);
		assertThat(url, is("/context/product/list"));
	}
	
	
}
