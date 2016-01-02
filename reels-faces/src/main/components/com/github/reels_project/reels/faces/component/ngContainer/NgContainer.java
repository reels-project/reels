package com.github.reels_project.reels.faces.component.ngContainer;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;

@ResourceDependencies({
	@ResourceDependency(library="angular", name="faces.css"),
	@ResourceDependency(library="angular", name="modules.css"),

	@ResourceDependency(library="angular", name="bundle.js"),
	@ResourceDependency(library="angular", name="modules.js"),
	
//	@ResourceDependency(library="angular", name="app.js"),
//	@ResourceDependency(library="angular", name="datatable/datatable.js"),
})

@FacesComponent("com.github.reels_project.reels.faces.component.NgContainer")
public class NgContainer extends UIComponentBase {
	public NgContainer(){
//		setRendererType(ComponentDefines.NG_CONTAINER_RENDERER);
	}
	
	public String getText() {
		return (String) getStateHelper().eval("text", null);
	}

	public void setText(String value) {
		getStateHelper().put("text", value);
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("div", null);
		writer.writeAttribute("id", "angular-app", null);
		writer.writeAttribute("ng-app", "App", null);
		writer.writeAttribute("ng-controller", "AppCtrl", null);
		
		super.encodeBegin(context);
	}
	
	



	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
		super.encodeEnd(context);
	}



	@Override
	public String getFamily() {
		return ComponentDefines.NG_CONTAINER;
	}
}
