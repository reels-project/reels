package com.github.reels_project.reels;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReelsExceptionTest {
	
	@Test
	public void constructor(){
		try {
			new ReelsException("message");
			new ReelsException(new RuntimeException());
			new ReelsException("message", new RuntimeException());
		} catch (Exception e) {
			fail();
		}
	}
}
