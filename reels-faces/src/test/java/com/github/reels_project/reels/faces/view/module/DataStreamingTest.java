package com.github.reels_project.reels.faces.view.module;

import static org.junit.Assert.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class DataStreamingTest {
	
	private FacesContext facesContext;
	private ExternalContext externalContext;
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private ServletOutputStream servletOutputStream;
	
	DataStreaming create(){
		return new DataStreaming() {
			@Override
			public FacesContext getFacesContext() {
				return facesContext;
			}
		};
	}
	
	@Before
	public void before()throws Exception{
		facesContext = mock(FacesContext.class);
		externalContext = mock(ExternalContext.class);
		httpServletRequest = mock(HttpServletRequest.class);
		httpServletResponse = mock(HttpServletResponse.class);
		servletOutputStream = mock(ServletOutputStream.class);
		
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getRequest()).thenReturn(httpServletRequest);
		when(externalContext.getResponse()).thenReturn(httpServletResponse);
		when(httpServletResponse.getOutputStream()).thenReturn(servletOutputStream);
	}
	
	@Test
	public void sendData_ie(){
		
		when(httpServletRequest.getHeader("User-Agent")).thenReturn("msie");
		ArgumentCaptor<String> contentDispositon = ArgumentCaptor.forClass(String.class);
		doNothing().when(httpServletResponse).setHeader(anyString(), contentDispositon.capture());
		
		DataStreaming ds = create();
		ds.sendData("ファイル名.xls", new ByteArrayInputStream("1".getBytes()));
		
		verify(httpServletResponse,times(1)).setContentType("application/octet-stream");
		verify(httpServletResponse,times(1)).setHeader(anyString(), contentDispositon.capture());
		String disposition = contentDispositon.getValue();
		assertThat(disposition, is("attachment; filename=%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E5%90%8D.xls"));
	}
	
	@Test
	public void sendData_safari(){
		
		when(httpServletRequest.getHeader("User-Agent")).thenReturn("safari");
		ArgumentCaptor<String> contentDispositon = ArgumentCaptor.forClass(String.class);
		doNothing().when(httpServletResponse).setHeader(anyString(), contentDispositon.capture());
		
		DataStreaming ds = create();
		ds.sendData("ファイル名.xls", new ByteArrayInputStream("1".getBytes()));
		
		verify(httpServletResponse,times(1)).setContentType("application/octet-stream");
		verify(httpServletResponse,times(1)).setHeader(anyString(), contentDispositon.capture());
		String disposition = contentDispositon.getValue();
		assertThat(disposition, is("attachment; filename=ãã¡ã¤ã«å.xls"));
	}
	
	@Test
	public void sendData_mac(){
		
		when(httpServletRequest.getHeader("User-Agent")).thenReturn("mac");
		ArgumentCaptor<String> contentDispositon = ArgumentCaptor.forClass(String.class);
		doNothing().when(httpServletResponse).setHeader(anyString(), contentDispositon.capture());
		
		DataStreaming ds = create();
		ds.sendData("ファイル名.xls", new ByteArrayInputStream("1".getBytes()));
		
		verify(httpServletResponse,times(1)).setContentType("application/octet-stream");
		verify(httpServletResponse,times(1)).setHeader(anyString(), contentDispositon.capture());
		String disposition = contentDispositon.getValue();
		assertThat(disposition, is("attachment; filename=ãã¡ã¤ã«å.xls"));
	}
	
	@Test
	public void sendData_other(){
		
		when(httpServletRequest.getHeader("User-Agent")).thenReturn("firefox");
		ArgumentCaptor<String> contentDispositon = ArgumentCaptor.forClass(String.class);
		doNothing().when(httpServletResponse).setHeader(anyString(), contentDispositon.capture());
		
		DataStreaming ds = create();
		ds.sendData("ファイル名.xls", new ByteArrayInputStream("1".getBytes()));
		
		verify(httpServletResponse,times(1)).setContentType("application/octet-stream");
		verify(httpServletResponse,times(1)).setHeader(anyString(), contentDispositon.capture());
		String disposition = contentDispositon.getValue();
		assertThat(disposition, is("attachment; filename==?ISO-2022-JP?B?GyRCJVUlISUkJWtMPhsoQi54bHM=?="));
	}
}
