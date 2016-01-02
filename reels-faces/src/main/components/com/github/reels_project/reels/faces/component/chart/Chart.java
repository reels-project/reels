package com.github.reels_project.reels.faces.component.chart;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.model.chart.ChartModel;

//@ResourceDependencies({
//	@ResourceDependency(library="chart", name="d3.js"),
//	@ResourceDependency(library="chart", name="c3.js"),
//	@ResourceDependency(library="chart", name="c3.css"),
//})

@FacesComponent(value = "custom.chart" ,tagName = "chart" , createTag = true)
public class Chart extends UIInput{
	public ChartModel getModel() {
		return (ChartModel) getStateHelper().eval("model", null);
	}

	public void setModel(ChartModel value) {
		getStateHelper().put("model", value);
	}

	public Chart(){
		setRendererType(ComponentDefines.CHART_RENDER);
	}

	@Override
	public String getFamily() {
		return ComponentDefines.CHART;
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
		System.out.println("通知きました");
	}
	
	
}
