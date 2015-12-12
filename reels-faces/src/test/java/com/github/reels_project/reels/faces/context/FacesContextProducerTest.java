package com.github.reels_project.reels.faces.context;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class FacesContextProducerTest {
	
	@Test
	public void getFacesContext(){
		PowerMockito.mockStatic(FacesContext.class);
		when(FacesContext.getCurrentInstance()).thenReturn(mock(FacesContext.class));
		
		FacesContextProducer producer = new FacesContextProducer();
		FacesContext fc = producer.getFacesContext();
		
		PowerMockito.verifyStatic();
		assertNotNull(fc);
	}
}
