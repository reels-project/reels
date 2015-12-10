package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.OptionalInt;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class IntegerConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		IntegerConverter converter = new IntegerConverter();
		converter.setOptional(optional);
		return converter;
	}
	Converter getConverter(boolean optional,boolean primitive){
		IntegerConverter converter = new IntegerConverter();
		converter.setOptional(optional);
		converter.setPrimitive(primitive);
		return converter;
	}
	
	@Test
	public void nonOptional_getAsObject(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(Integer.class));
		Integer b = (Integer)o;
		assertThat(b.intValue(), is(12345));
	}
	
	@Test
	public void nonOptional_getAsObject_primitive(){
		Converter converter = getConverter(false,true);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(Integer.class));
		Integer b = (Integer)o;
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
		String s = converter.getAsString(context, component, Integer.valueOf(12345));
		
		assertThat(s, is("12345"));
	}
	
	@Test
	public void nonOptional_getAsString_primitive(){
		Converter converter = getConverter(false,true);
		String s = converter.getAsString(context, component, 12345);
		
		assertThat(s, is("12345"));
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
		Optional<Integer> op = (Optional<Integer>)o;
		
		assertThat(op.isPresent(), is(true));
		
		Integer b = op.get();
		assertThat(b.intValue(), is(12345));
	}
	
	@Test
	public void optional_getAsObject_primitive(){
		Converter converter = getConverter(true,true);
		Object o = converter.getAsObject(context, component, "12345");
		
		assertThat(o, instanceOf(OptionalInt.class));
		OptionalInt op = (OptionalInt)o;
		
		assertThat(op.isPresent(), is(true));
		
		int b = op.getAsInt();
		assertThat(b, is(12345));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Integer> op = (Optional<Integer>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_empty(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "");
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Integer> op = (Optional<Integer>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	
	@Test
	public void optional_getAsString(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.of(Integer.valueOf(12345)));
		
		assertThat(s, is("12345"));
	}
	
	@Test
	public void optional_getAsString_primitive(){
		Converter converter = getConverter(true,true);
		String s = converter.getAsString(context, component, OptionalInt.of(12345));
		
		assertThat(s, is("12345"));
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
