package com.github.reels_project.reels.faces.convert;

import java.util.Optional;
import java.util.OptionalDouble;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.github.reels_project.reels.faces.convert.DoubleConverter")
public class DoubleConverter extends javax.faces.convert.DoubleConverter{

	private Boolean optional;
	private Boolean primitive;

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}

	public Boolean getPrimitive() {
		return primitive;
	}

	public void setPrimitive(Boolean primitive) {
		this.primitive = primitive;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object o = super.getAsObject(context, component, value);
		if(optional != null && optional){
			if(primitive != null && primitive){
				return OptionalDouble.of(o == null?0:((Double)o).doubleValue());
			}
			return Optional.ofNullable(Double.class.cast(o));
		}
		return o;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && optional != null && optional){
			if(primitive != null && primitive){
				value = ((OptionalDouble)value).orElse(0.0);
			}else{
				value = ((Optional<?>)value).orElse(null);
			}
		}
		return super.getAsString(context, component, value);
	}
	
}
