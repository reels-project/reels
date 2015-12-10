package com.github.reels_project.reels.faces.convert;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("com.github.reels_project.reels.faces.convert.OptionalStringConverter")
public class OptionalStringConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if("".equals(value)){
			value = null;
		}
		return Optional.ofNullable(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null){
			throw new IllegalArgumentException("Optional must be not null value.");
		}
		
		if(!(value instanceof Optional)){
			throw new IllegalArgumentException("Argument 'value' is not Optional.");
		}
		Optional<?> optional = (Optional<?>)value;
		return optional.map(v -> v.toString()).orElse("");
	}
}
