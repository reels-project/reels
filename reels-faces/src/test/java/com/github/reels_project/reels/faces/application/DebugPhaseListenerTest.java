package com.github.reels_project.reels.faces.application;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;

public class DebugPhaseListenerTest {
	
	@Test
	public void beforePhase(){
		DebugPhaseListener l = new DebugPhaseListener();
		Logger logger = mock(Logger.class);
		Whitebox.setInternalState(l, "logger", logger);
		
		PhaseEvent e = mock(PhaseEvent.class);
		when(e.getPhaseId()).thenReturn(PhaseId.APPLY_REQUEST_VALUES);
		l.beforePhase(e);
		
		verify(logger,times(1)).debug("before {}",PhaseId.APPLY_REQUEST_VALUES);
	}
	
	@Test
	public void afterPhase(){
		DebugPhaseListener l = new DebugPhaseListener();
		Logger logger = mock(Logger.class);
		Whitebox.setInternalState(l, "logger", logger);
		
		PhaseEvent e = mock(PhaseEvent.class);
		PhaseId id = PhaseId.APPLY_REQUEST_VALUES;
		when(e.getPhaseId()).thenReturn(id);
		l.afterPhase(e);
		
		verify(logger,times(1)).debug("after  {}",id);
	}
	
	@Test
	public void getPhaseId(){
		PhaseId id = new DebugPhaseListener().getPhaseId();
		assertThat(id, is(PhaseId.ANY_PHASE));
	}
}
