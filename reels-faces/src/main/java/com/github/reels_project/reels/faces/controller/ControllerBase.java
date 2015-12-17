package com.github.reels_project.reels.faces.controller;

import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import com.github.reels_project.reels.faces.controller.module.DataStreaming;
import com.github.reels_project.reels.faces.controller.module.Flashing;
import com.github.reels_project.reels.faces.controller.module.Redirecting;
import com.github.reels_project.reels.faces.controller.module.Rendering;

public abstract class ControllerBase implements 
	Rendering,
	Redirecting,
	DataStreaming,
	Flashing{
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private Flash flash;

	@Override
	public Flash getFlash() {
		return Objects.requireNonNull(flash);
	}

	@Override
	public FacesContext getFacesContext() {
		return Objects.requireNonNull(facesContext);
	}
}
