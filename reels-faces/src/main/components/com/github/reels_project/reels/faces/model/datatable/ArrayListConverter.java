package com.github.reels_project.reels.faces.model.datatable;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@FacesConverter("com.github.reels_project.reels.faces.model.datatable.ArrayListConverter")
public class ArrayListConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList model = null;
		try {
			model = mapper.readValue(value, ArrayList.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
        try {
        	result = value != null ? mapper.writeValueAsString(value) : null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
