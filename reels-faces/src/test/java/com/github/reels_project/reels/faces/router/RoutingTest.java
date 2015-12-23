package com.github.reels_project.reels.faces.router;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

public class RoutingTest {
	
	private HttpServletRequest request;
	
	@Before
	public void before(){
		request = mock(HttpServletRequest.class);
	}
	
	@Test
	public void routing(){
		when(request.getRequestURI()).thenReturn("/context/product/list");
		when(request.getContextPath()).thenReturn("/context");
		
		Routing r = new Routing(request, new RouterConfiguration());
		
		assertThat(r.from(), is("/product/list"));
		assertThat(r.to(), is("/views/product/list.xhtml"));
		assertFalse(r.isAssetsRequest());
		assertFalse(r.isResourceRequest());
		assertFalse(r.isTransrated());
	}
	
	@Test
	public void routing_jsessionidIgnore(){
		when(request.getRequestURI()).thenReturn("/context/product/list;jsessionid=ABCDDDD");
		when(request.getContextPath()).thenReturn("/context");
		
		Routing r = new Routing(request, new RouterConfiguration());
		
		assertThat(r.from(), is("/product/list"));
		assertThat(r.to(), is("/views/product/list.xhtml"));
		assertFalse(r.isAssetsRequest());
		assertFalse(r.isResourceRequest());
		assertFalse(r.isTransrated());
	}
	
	@Test
	public void transratedCase(){
		when(request.getRequestURI()).thenReturn("/context/views/product/list.xhtml");
		when(request.getContextPath()).thenReturn("/context");
		
		Routing r = new Routing(request, new RouterConfiguration());
		
		assertThat(r.from(), is("/views/product/list.xhtml"));
		assertThat(r.to(), is("/views/product/list.xhtml"));
		assertFalse(r.isAssetsRequest());
		assertFalse(r.isResourceRequest());
		assertTrue(r.isTransrated());
	}
	
	@Test
	public void assetRequetCase(){
		when(request.getRequestURI()).thenReturn("/context/assets/hoge");
		when(request.getContextPath()).thenReturn("/context");
		
		Routing r = new Routing(request, new RouterConfiguration());
		
		assertThat(r.from(), is("/assets/hoge"));
		assertThat(r.to(), is("/assets/hoge"));
		assertTrue(r.isAssetsRequest());
		assertFalse(r.isResourceRequest());
		assertFalse(r.isTransrated());
	}
	
	@Test
	public void resourceRequetCase(){
		when(request.getRequestURI()).thenReturn("/context/javax.faces.resources/hoge.js");
		when(request.getContextPath()).thenReturn("/context");
		
		Routing r = new Routing(request, new RouterConfiguration());
		
		assertThat(r.from(), is("/javax.faces.resources/hoge.js"));
		assertThat(r.to(), is("/javax.faces.resources/hoge.js"));
		assertFalse(r.isAssetsRequest());
		assertTrue(r.isResourceRequest());
		assertFalse(r.isTransrated());
	}
	
	@Test(expected=NullPointerException.class)
	public void constructor_requestIsNull(){
		new Routing(null, new RouterConfiguration());
	}
	
	@Test(expected=NullPointerException.class)
	public void constructor_requestIsConfig(){
		new Routing(request, null);
	}
	
	
}
