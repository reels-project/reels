package com.github.reels_project.reels.faces.router;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class RoutingFilterTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher requestDispatcher;
	private FilterConfig filterConfig;
	private FilterChain chain;
	
	@Before
	public void before(){
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		requestDispatcher = mock(RequestDispatcher.class);
		filterConfig = mock(FilterConfig.class);
		chain = mock(FilterChain.class);
	}
	
	@Test
	public void routingTarget(){
		when(request.getRequestURI()).thenReturn("/context/product/list");
		when(request.getContextPath()).thenReturn("/context");
		ArgumentCaptor<String> viewIdCapture = ArgumentCaptor.forClass(String.class);
		when(request.getRequestDispatcher(viewIdCapture.capture())).thenReturn(requestDispatcher);
		
		RoutingFilter filter = new RoutingFilter();
		try {
			filter.init(filterConfig);
			filter.doFilter(request, response, chain);
			
			
			verify(request).getRequestDispatcher(viewIdCapture.capture());
			verify(requestDispatcher).forward(request, response);
			
			assertThat(viewIdCapture.getValue(), is("/views/product/list.xhtml"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	@Test
	public void routingNonTargetAssets(){
		when(request.getRequestURI()).thenReturn("/context/assets/list");
		when(request.getContextPath()).thenReturn("/context");
		
		RoutingFilter filter = new RoutingFilter();
		try {
			filter.init(filterConfig);
			filter.doFilter(request, response, chain);
			
			verify(chain).doFilter(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	@Test
	public void routingNonTargetResources(){
		when(request.getRequestURI()).thenReturn("/context/assets/list.css");
		when(request.getContextPath()).thenReturn("/context");
		
		RoutingFilter filter = new RoutingFilter();
		try {
			filter.init(filterConfig);
			filter.doFilter(request, response, chain);
			
			verify(chain).doFilter(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	@Test
	public void routingNonTargetTransrated(){
		when(request.getRequestURI()).thenReturn("/context/views/assets/list.xhtml");
		when(request.getContextPath()).thenReturn("/context");
		
		RoutingFilter filter = new RoutingFilter();
		try {
			filter.init(filterConfig);
			filter.doFilter(request, response, chain);
			
			verify(chain).doFilter(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
}
