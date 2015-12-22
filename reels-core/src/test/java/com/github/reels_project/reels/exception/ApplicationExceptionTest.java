package com.github.reels_project.reels.exception;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.github.reels_project.reels.message.AppMessage;

public class ApplicationExceptionTest {
	
	@Test
	public void constructor(){
		try {
			ApplicationException ex = new ApplicationException("message");
			ApplicationException ex2 = new ApplicationException("message", new RuntimeException());
			
			assertThat(ex.getMessage(), is("message"));
			assertThat(ex2.getMessage(), is("message"));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void constructor_appMessage(){
		try {
			ApplicationException ex = new ApplicationException(new AppMessage("key"));
			
			assertThat(ex.getMessage(), is("Application error"));
			assertThat(ex.getAppMessages().size(), is(1));
			assertThat(ex.getAppMessages().get(0).getKey(), is("key"));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void constructor_setAppMessage(){
		try {
			ApplicationException ex = new ApplicationException("It's error");
			ex.setAppMessages(new AppMessage("key"));
			
			assertThat(ex.getMessage(), is("It's error"));
			assertThat(ex.getAppMessages().size(), is(1));
			assertThat(ex.getAppMessages().get(0).getKey(), is("key"));
		} catch (Exception e) {
			fail();
		}
	}
}
