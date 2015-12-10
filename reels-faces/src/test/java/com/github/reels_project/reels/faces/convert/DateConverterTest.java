package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class DateConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		DateConverter converter = new DateConverter();
		converter.setOptional(optional);
		converter.setFormat("yyyy/MM/dd");
		return converter;
	}
	
	@Test
	public void nonOptional_getAsObject(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "2016/01/01");
		
		assertThat(o, instanceOf(LocalDate.class));
		LocalDate b = (LocalDate)o;
		assertThat(b.getYear(), is(2016));
		assertThat(b.getMonthValue(), is(1));
		assertThat(b.getDayOfMonth(), is(1));
	}
	
	@Test
	public void nonOptional_getAsObject_null(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, null);
		
		assertNull(o);
	}
	
	@Test(expected=DateTimeParseException.class)
	public void nonOptional_getAsObject_empty(){
		Converter converter = getConverter(false);
		converter.getAsObject(context, component, "");
	}
	
	@Test
	public void nonOptional_getAsString(){
		Converter converter = getConverter(false);
		String s = converter.getAsString(context, component, LocalDate.of(2016, 1, 1));
		
		assertThat(s, is("2016/01/01"));
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
		Object o = converter.getAsObject(context, component, "2016/01/01");
		
		assertThat(o, instanceOf(Optional.class));
		Optional<LocalDate> op = (Optional<LocalDate>)o;
		
		assertThat(op.isPresent(), is(true));
		
		LocalDate b = op.get();
		assertThat(b.getYear(), is(2016));
		assertThat(b.getMonthValue(), is(1));
		assertThat(b.getDayOfMonth(), is(1));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<LocalDate> op = (Optional<LocalDate>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	@Test(expected=DateTimeParseException.class)
	public void optional_getAsObject_empty(){
		Converter converter = getConverter(true);
		converter.getAsObject(context, component, "");
	}
	
	@Test
	public void optional_getAsString(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.of(LocalDate.of(2016, 1, 1)));
		
		assertThat(s, is("2016/01/01"));
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
