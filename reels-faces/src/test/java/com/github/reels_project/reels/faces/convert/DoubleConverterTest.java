package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.OptionalDouble;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class DoubleConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		DoubleConverter converter = new DoubleConverter();
		converter.setOptional(optional);
		return converter;
	}
	Converter getConverter(boolean optional,boolean primitive){
		DoubleConverter converter = new DoubleConverter();
		converter.setOptional(optional);
		converter.setPrimitive(primitive);
		return converter;
	}
	
	@Test
	public void nonOptional_getAsObject(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(Double.class));
		Double b = (Double)o;
		assertThat(b.intValue(), is(12345));
	}
	
	@Test
	public void nonOptional_getAsObject_primitive(){
		Converter converter = getConverter(false,true);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(Double.class));
		Double b = (Double)o;
		assertThat(b.intValue(), is(12345));
	}
	
	@Test
	public void nonOptional_getAsObject_null(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, null);
		
		assertNull(o);
	}
	
	@Test
	public void nonOptional_getAsObject_empty(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "");
		
		assertNull(o);
	}
	
	@Test
	public void nonOptional_getAsString(){
		Converter converter = getConverter(false);
		String s = converter.getAsString(context, component, Double.valueOf(12345));
		
		assertThat(s, is("12345.0"));
	}
	
	@Test
	public void nonOptional_getAsString_primitive(){
		Converter converter = getConverter(false,true);
		String s = converter.getAsString(context, component, 12345);
		
		assertThat(s, is("12345.0"));
	}
	
	@Test
	public void nonOptional_getAsString_null(){
		Converter converter = getConverter(false);
		String s = converter.getAsString(context, component, null);
		
		assertThat(s, is(""));
	}


	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(Optional.class));
		Optional<Double> op = (Optional<Double>)o;
		
		assertThat(op.isPresent(), is(true));
		
		Double b = op.get();
		assertThat(b.intValue(), is(12345));
	}
	
	@Test
	public void optional_getAsObject_primitive(){
		Converter converter = getConverter(true,true);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(OptionalDouble.class));
		OptionalDouble op = (OptionalDouble)o;
		
		assertThat(op.isPresent(), is(true));
		
		double b = op.getAsDouble();
		assertThat(b, is(12345.0));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Double> op = (Optional<Double>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_empty(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "");
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Double> op = (Optional<Double>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	
	@Test
	public void optional_getAsString(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.of(Double.valueOf(12345)));
		
		assertThat(s, is("12345.0"));
	}
	
	@Test
	public void optional_getAsString_primitive(){
		Converter converter = getConverter(true,true);
		String s = converter.getAsString(context, component, OptionalDouble.of(12345));
		
		assertThat(s, is("12345.0"));
	}
	
	@Test
	public void optional_getAsString_empty(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.empty());
		
		assertThat(s, is(""));
	}
	
	@Test
	public void optional_getAsString_null(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, null);
		
		assertThat(s, is(""));
	}
}
