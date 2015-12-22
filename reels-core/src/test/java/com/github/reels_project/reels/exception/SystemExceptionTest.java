package com.github.reels_project.reels.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class SystemExceptionTest {
	
	@Test
	public void constructor(){
		try {
			new SystemException("message");
			new SystemException(new RuntimeException());
			new SystemException("message", new RuntimeException());
		} catch (Exception e) {
			fail();
		}
	}
}
