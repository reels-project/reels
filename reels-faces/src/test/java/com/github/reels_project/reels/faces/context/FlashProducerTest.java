package com.github.reels_project.reels.faces.context;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

public class FlashProducerTest {
	
	@Test
	public void getFacesContext(){
		
		FlashProducer producer = new FlashProducer();
		FacesContext fc = mock(FacesContext.class);
		ExternalContext ec = mock(ExternalContext.class);
		when(fc.getExternalContext()).thenReturn(ec);
		when(ec.getFlash()).thenReturn(mock(Flash.class));
		
		Whitebox.setInternalState(producer, "facesContext", fc);
		
		Flash f = producer.getFlash();
		
		assertNotNull(f);
	}
}
