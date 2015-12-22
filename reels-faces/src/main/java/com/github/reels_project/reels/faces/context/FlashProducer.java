package com.github.reels_project.reels.faces.context;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

@ApplicationScoped
public class FlashProducer {
	@Inject
	private FacesContext facesContext;
	
	@Produces @RequestScoped
	public Flash getFlash(){
		return facesContext.getExternalContext().getFlash();
	}
}
