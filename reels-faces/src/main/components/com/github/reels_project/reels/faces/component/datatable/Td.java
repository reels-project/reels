package com.github.reels_project.reels.faces.component.datatable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;

//@FacesComponent("com.github.reels_project.reels.faces.component.Td")
@FacesComponent(tagName = "td" , createTag = true)
public class Td extends UIInput{
	public Td(){
		setRendererType(ComponentDefines.DATATABLE_TD_RENDERER);
	}

	@Override
	public String getFamily() {
		return ComponentDefines.DATATABLE_TD;
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
