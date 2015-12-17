package com.github.reels_project.reels.faces.controller.module;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.github.reels_project.reels.faces.controller.module.Messaging;
import com.github.reels_project.reels.message.AppMessage.Level;

public class MessagingTest {
	
	private FacesContext facesContext;
	
	@Before
	public void before(){
		facesContext = mock(FacesContext.class);
	}
	
	Messaging create(){
		return new Messaging() {
			@Override
			public FacesContext getFacesContext() {
				
				return facesContext;
			}
		};
	}
	
	@Test
	public void addInfoMessage(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addInfoMessage("MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNull(clientId);
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_INFO));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test
	public void addInfoMessageFor(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addInfoMessageFor("CLIENTID","MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNotNull(clientId);
		assertThat(clientId, is("CLIENTID"));
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_INFO));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test
	public void addWarnMessage(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addWarnMessage("MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNull(clientId);
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_WARN));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test
	public void addWarnMessageFor(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addWarnMessageFor("CLIENTID","MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNotNull(clientId);
		assertThat(clientId, is("CLIENTID"));
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_WARN));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test
	public void addErrorMessage(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addErrorMessage("MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNull(clientId);
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_ERROR));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test
	public void addErrorMessageFor(){
		ArgumentCaptor<String> clientIdCap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCap = ArgumentCaptor.forClass(FacesMessage.class);
		doNothing().when(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		create().addErrorMessageFor("CLIENTID","MSGID");
		
		verify(facesContext).addMessage(clientIdCap.capture(), messageCap.capture());
		
		String clientId = clientIdCap.getValue();
		assertNotNull(clientId);
		assertThat(clientId, is("CLIENTID"));
		
		FacesMessage facesMessage = messageCap.getValue();
		assertNotNull(facesMessage);
		assertThat(facesMessage.getSeverity(), is(FacesMessage.SEVERITY_ERROR));
		assertThat(facesMessage.getSummary(), is("MSGID"));
		assertThat(facesMessage.getDetail(), is("MSGID"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addMessage_levelIsNull(){
		create().addMessage(null,"CLIENTID","MSGID");
	}
	@Test(expected=IllegalArgumentException.class)
	public void addMessage_messageIdIsNull(){
		create().addMessage(Level.INFO,"CLIENTID",null);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void clearMessage(){
		Iterator<FacesMessage> messages = mock(Iterator.class);
		when(facesContext.getMessages()).thenReturn(messages);
		
		create().clearMessages();
		
		verify(messages).remove();
	}
}
