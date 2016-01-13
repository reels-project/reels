package com.github.reels_project.reels.faces.component.datatable;

import java.util.ArrayList;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.model.chart.ChartModel;

//@FacesComponent("com.github.reels_project.reels.faces.component.Tbody")
@FacesComponent(tagName = "tbody", createTag = true)
public class Tbody extends UIInput {
	// public Object getValue() {
	// return getStateHelper().eval("value", null);
	// }
	//
	// public void setValue(Object value) {
	// getStateHelper().put("value", value);
	// }
	//
	// public String getVar() {
	// return (String)getStateHelper().eval("var", null);
	// }
	//
	// public void setVar(Object value) {
	// getStateHelper().put("var", value);
	// }
	public Boolean getSortable() {
		return (Boolean) getStateHelper().eval("sortable", null);
	}

	public void setSortable(Object value) {
		getStateHelper().put("sortable", value);
	}

	public Tbody() {
		setRendererType(ComponentDefines.DATATABLE_TBODY_RENDERER);
	}

	@Override
	public String getFamily() {
		return ComponentDefines.DATATABLE_TBODY;
	}

	@Override
	public void processUpdates(FacesContext context) {
		super.processUpdates(context);
	}

	// @Override
	// public void updateModel(FacesContext context) {
	// super.updateModel(context);
	// }

	@Override
	public void restoreState(FacesContext context, Object state) {
		super.restoreState(context, state);
	}
}
