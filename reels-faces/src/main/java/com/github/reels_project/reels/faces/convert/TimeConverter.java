package com.github.reels_project.reels.faces.convert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("com.github.reels_project.reels.faces.convert.TimeConverter")
public class TimeConverter implements Converter{
	private Boolean optional;
	private String format;

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null){
			if(optional != null && optional){
				return Optional.empty();
			}
			return null;
		}
		DateTimeFormatter dateTimeFormatter = format == null?DateTimeFormatter.ISO_TIME:DateTimeFormatter.ofPattern(format);
		LocalTime d = LocalTime.parse(value, dateTimeFormatter);
		if(optional != null && optional){
			return Optional.of(d);
		}
		return d;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null && optional != null && optional){
			value = ((Optional<?>)value).orElse(null);
		}
		
		if(value == null){
			return "";
		}
		DateTimeFormatter dateTimeFormatter = format == null?DateTimeFormatter.ISO_TIME:DateTimeFormatter.ofPattern(format);
		String s = ((LocalTime)value).format(dateTimeFormatter);
		return s;
	}
}
