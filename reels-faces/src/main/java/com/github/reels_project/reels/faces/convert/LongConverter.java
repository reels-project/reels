package com.github.reels_project.reels.faces.convert;

import java.util.Optional;
import java.util.OptionalLong;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.github.reels_project.reels.faces.convert.LongConverter")
public class LongConverter extends javax.faces.convert.LongConverter{

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
				return OptionalLong.of(o == null?0:((Long)o).longValue());
			}
			return Optional.ofNullable(Long.class.cast(o));
		}
		return o;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && optional != null && optional){
			if(primitive != null && primitive){
				value = ((OptionalLong)value).orElse(0L);
			}else{
				value = ((Optional<?>)value).orElse(null);
			}
		}
		return super.getAsString(context, component, value);
	}
	
}
