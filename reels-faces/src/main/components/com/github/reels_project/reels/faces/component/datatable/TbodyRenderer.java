package com.github.reels_project.reels.faces.component.datatable;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import com.github.reels_project.reels.faces.component.main.ComponentDefines;
import com.github.reels_project.reels.faces.component.main.DirectiveRenderer;

@FacesRenderer(componentFamily = ComponentDefines.DATATABLE_TBODY, rendererType = ComponentDefines.DATATABLE_TBODY_RENDERER)
public class TbodyRenderer extends DirectiveRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		encodeMarkup(context, component);
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("jsf-tbody");
//		super.encodeScript(context, component);
	}

	protected void encodeMarkup(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		Tbody tbody = (Tbody)component;
		ValueExpression ve = component.getValueExpression("sortable");
		String s = ve.getExpressionString();
		
		writer.startElement("jsf-tbody", null);
		writer.writeAttribute("id", component.getClientId(), null);
		
		Boolean sortable = tbody.getSortable();
		writer.writeAttribute("sortable", sortable != null ? sortable.toString() : false, null);
	}
}
