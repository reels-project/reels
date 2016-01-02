package com.github.reels_project.reels.faces.component.main;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

public class DirectiveRenderer extends Renderer{
	private final String appName = "angular-app";
	private Integer renderCnt = 0;
	@Override
	public void decode(FacesContext context, UIComponent component) {
		super.decode(context, component);
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		super.encodeBegin(context, component);
	}
	protected void encodeScript(FacesContext context, UIComponent component) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("script", null);
		writer.write("var scope = angular.element(document.getElementById('" + this.appName + "')).scope();");
		
		writer.write("if(scope != undefined) {scope.compileElement('" + component.getClientId() + "');};");
		writer.endElement("script");
		
		this.renderCnt ++;
	}
}
