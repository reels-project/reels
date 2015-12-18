package com.github.reels_project.reels.message;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.github.reels_project.reels.message.AppMessage.Level;


public class AppMessageTest {

	//FIXME 実装したらなおす
	@Test
	public void getTemplate(){
		String template = AppMessage.getTemplate("KEY");
		
		assertNull(template);
	}
	
	@Test
	public void constructor(){
		AppMessage appMessage = new AppMessage(Level.INFO, "KEY", "arg1","arg2");
		
		assertThat(appMessage.getLevel(), is(Level.INFO));
		assertThat(appMessage.getKey(), is("KEY"));
		assertThat(appMessage.getArgs().length, is(2));
		assertThat(appMessage.getArgs()[0], is("arg1"));
		assertThat(appMessage.getArgs()[1], is("arg2"));
	}
	
	@Test(expected=NullPointerException.class)
	public void constructor_keyIsNull(){
		new AppMessage(Level.INFO, null, "arg1","arg2");
	}
	
	@Test
	public void constructor2(){
		AppMessage appMessage = new AppMessage("KEY", "arg1","arg2");
		
		assertThat(appMessage.getLevel(), is(Level.INFO));
		assertThat(appMessage.getKey(), is("KEY"));
		assertThat(appMessage.getArgs().length, is(2));
		assertThat(appMessage.getArgs()[0], is("arg1"));
		assertThat(appMessage.getArgs()[1], is("arg2"));
	}
	
	@Test
	public void getMessage(){
		AppMessage appMessage = new AppMessage(Level.INFO, "あ{0}う{1}お", "い","え");
		String message = appMessage.getMessage();
		
		assertThat(message, is("あいうえお"));
	}
}
