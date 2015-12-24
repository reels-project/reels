package com.github.reels_project.reels.faces.application;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class DebugPhaseListener implements PhaseListener{
	
	private static Logger logger = LoggerFactory.getLogger(DebugPhaseListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		logger.debug("after  {}",event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		logger.debug("before {}",event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
