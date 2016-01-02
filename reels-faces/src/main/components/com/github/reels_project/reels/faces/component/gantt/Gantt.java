package com.github.reels_project.reels.faces.component.gantt;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.model.gantt.GanttModel;

//@ResourceDependencies({
//	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
//	@ResourceDependency(library="gantt", name="angular.js"),
//	@ResourceDependency(library="gantt", name="moment-with-locales.js"),
//	@ResourceDependency(library="gantt", name="angular-moment.js"),
//	@ResourceDependency(library="gantt", name="angular-gantt.js"),
//	@ResourceDependency(library="gantt", name="angular-gantt-plugins.js"),
//	@ResourceDependency(library="gantt", name="angular-gantt.css"),
//	@ResourceDependency(library="gantt", name="angular-gantt-plugins.css"),
//})

@FacesComponent(value = "custom.gantt" ,tagName = "gantt" , createTag = true)
public class Gantt extends UIInput{
	public GanttModel getModel() {
		return (GanttModel) getStateHelper().eval("model", null);
	}

	public void setModel(GanttModel value) {
		getStateHelper().put("model", value);
	}
	
	public Gantt(){
		setRendererType(ComponentDefines.GANTT_RENDER);
	}

    @Override
	public String getFamily() {
		return ComponentDefines.GANTT;
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		// TODO Auto-generated method stub
		super.restoreState(context, state);
		
//		GanttEventListener el = getEventListener();
//		if(el != null){
//			
//		}
//		System.out.println("");
	}
    
    
}
