package com.github.reels_project.reels.faces.component.datatable;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.component.main.DirectiveRenderer;

@FacesRenderer(componentFamily = ComponentDefines.DATATABLE_TD, rendererType = ComponentDefines.DATATABLE_TD_RENDERER)
public class TdRenderer extends DirectiveRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		encodeMarkup(context, component);
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("jsf-td");
//		super.encodeScript(context, component);
	}

	protected void encodeMarkup(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("jsf-td", null);
		writer.writeAttribute("id", component.getClientId(), null);
	}
}
