package com.github.reels_project.reels.faces.component.datatable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;

//@FacesComponent("com.github.reels_project.reels.faces.component.Th")
@FacesComponent(tagName = "th" , createTag = true)
public class Th extends UIInput{
	public Th(){
		setRendererType(ComponentDefines.DATATABLE_TH_RENDERER);
	}
	
	@Override
	public String getFamily() {
		return ComponentDefines.DATATABLE_TH;
	}

	@Override
	public void processUpdates(FacesContext context) {
		super.processUpdates(context);
	}

	@Override
	public void updateModel(FacesContext context) {
		super.updateModel(context);
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		super.restoreState(context, state);
	}
}