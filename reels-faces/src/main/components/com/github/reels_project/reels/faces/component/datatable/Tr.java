package com.github.reels_project.reels.faces.component.datatable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;

//@FacesComponent("com.github.reels_project.reels.faces.component.Tr")
@FacesComponent(tagName = "tr" , createTag = true)
public class Tr extends UIInput{
	public Tr(){
		setRendererType(ComponentDefines.DATATABLE_TR_RENDERER);
	}

	@Override
	public String getFamily() {
		return ComponentDefines.DATATABLE_TR;
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
