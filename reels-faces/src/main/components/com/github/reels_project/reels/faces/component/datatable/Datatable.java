package com.github.reels_project.reels.faces.component.datatable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.model.datatable.DatatableModel;

//@FacesComponent("com.github.reels_project.reels.faces.component.Datatable")
@FacesComponent(value = "custom.dataTable" ,tagName = "dataTable" , createTag = true)
public class Datatable extends UIInput{
	public Datatable(){
		setRendererType(ComponentDefines.DATATABLE_RENDERER);
	}

	public DatatableModel getModel() {
		return (DatatableModel) getStateHelper().eval("model", null);
		
	}
	
	public void setModel(DatatableModel value) {
		getStateHelper().put("model", value);
	}
	
	@Override
	public String getFamily() {
		return ComponentDefines.DATATABLE;
	}

	@Override
	public void processUpdates(FacesContext context) {
		// TODO Auto-generated method stub
		super.processUpdates(context);
	}

	@Override
	public void updateModel(FacesContext context) {
		// TODO Auto-generated method stub
		super.updateModel(context);
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		// TODO Auto-generated method stub
		super.restoreState(context, state);
	}
}
