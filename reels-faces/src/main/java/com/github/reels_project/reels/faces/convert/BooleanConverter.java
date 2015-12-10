package com.github.reels_project.reels.faces.convert;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.github.reels_project.reels.faces.convert.BooleanConverter")
public class BooleanConverter extends javax.faces.convert.BooleanConverter{

	private Boolean optional;

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object o = super.getAsObject(context, component, value);
		if(optional != null && optional){
			return Optional.ofNullable(Boolean.class.cast(o));
		}
		return o;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && optional != null && optional){
			value = ((Optional<?>)value).orElse(null);
		}
		return super.getAsString(context, component, value);
	}
	
}
