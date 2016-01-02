package com.github.reels_project.reels.faces.component.chart;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.component.main.DirectiveRenderer;

@FacesRenderer(componentFamily = ComponentDefines.CHART, rendererType = ComponentDefines.CHART_RENDER)
public class ChartRenderer extends DirectiveRenderer {

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		encodeMarkup(context, component);
		super.encodeScript(context, component);
	}

	protected void encodeMarkup(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("div", null);
		writer.writeAttribute("id", component.getClientId(), null);
		writer.writeAttribute("class", "jsf-chart", null);
		writer.endElement("div");
	}
}
