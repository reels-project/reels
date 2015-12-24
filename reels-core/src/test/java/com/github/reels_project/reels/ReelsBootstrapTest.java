package com.github.reels_project.reels;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.ServletContextEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Reels.class})
public class ReelsBootstrapTest {
	
	@Test
	public void init(){
		PowerMockito.mockStatic(Reels.class);
		
		ServletContextEvent sce = mock(ServletContextEvent.class);
		new ReelsBootstrap().contextDestroyed(sce);
		
		PowerMockito.verifyStatic();
		
	}
	
	@Test
	public void destroy(){
		try {
			ServletContextEvent sce = mock(ServletContextEvent.class);
			new ReelsBootstrap().contextDestroyed(sce);
		} catch (Exception e) {
			fail();
		}
	}
}
