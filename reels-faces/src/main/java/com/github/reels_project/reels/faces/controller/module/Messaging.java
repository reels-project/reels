package com.github.reels_project.reels.faces.controller.module;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.message.AppMessage;
import com.github.reels_project.reels.message.AppMessage.Level;

/**
 * TODO なんかダセえ。むむむって感じ。
 * @author Takahiko Sato
 *
 */
public interface Messaging {
	
	FacesContext getFacesContext();
	
	default void addInfoMessage(String messageId,Object...args){
		addMessage(Level.INFO, null, messageId, args);
	}
	default void addInfoMessageFor(String clientId,String messageId,Object...args){
		addMessage(Level.INFO, clientId, messageId, args);
	}
	default void addWarnMessage(String messageId,Object...args){
		addMessage(Level.WARN, null, messageId, args);
	}
	default void addWarnMessageFor(String clientId,String messageId,Object...args){
		addMessage(Level.WARN, clientId, messageId, args);
	}
	default void addErrorMessage(String messageId,Object...args){
		addMessage(Level.ERROR, null, messageId, args);
	}
	
	default void addErrorMessageFor(String clientId,String messageId,Object...args){
		addMessage(Level.ERROR, clientId, messageId, args);
	}
	
	default void addMessage(Level level,String clientId,String messageId,Object...args){
		if(level == null){
			throw new IllegalArgumentException("'level' is null.");
		}
		if(messageId == null){
			throw new IllegalArgumentException("'messageId' is null.");
		}
		
		Severity severity = FacesMessage.SEVERITY_ERROR;
		switch (level) {
		case ERROR:
			severity = FacesMessage.SEVERITY_ERROR;
			break;
		case INFO:
			severity = FacesMessage.SEVERITY_INFO;
			break;
		case WARN:
			severity = FacesMessage.SEVERITY_WARN;
			break;
		}
		
		AppMessage appMessage = new AppMessage(messageId, args);
		getFacesContext().addMessage(clientId, new FacesMessage(severity, appMessage.getKey(), appMessage.getMessage()));
	}
	
	default void clearMessages(){
		//FIXME これでいいんだっけ？getMessages()の戻りってコピーじゃないんだっけ？
		getFacesContext().getMessages().remove();
	}
}
