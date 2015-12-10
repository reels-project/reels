package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class DateTimeConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		DateTimeConverter converter = new DateTimeConverter();
		converter.setOptional(optional);
		converter.setFormat("yyyy/MM/dd HH:mm:ss");
		return converter;
	}
	
	@Test
	public void nonOptional_getAsObject(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "2016/01/01 00:00:00");
		
		assertThat(o, instanceOf(LocalDateTime.class));
		LocalDateTime b = (LocalDateTime)o;
		assertThat(b.getYear(), is(2016));
		assertThat(b.getMonthValue(), is(1));
		assertThat(b.getDayOfMonth(), is(1));
		assertThat(b.getHour(), is(0));
		assertThat(b.getMinute(), is(0));
		assertThat(b.getSecond(), is(0));
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
		String s = converter.getAsString(context, component, LocalDateTime.of(2016, 1, 1,0,0,0));
		
		assertThat(s, is("2016/01/01 00:00:00"));
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
		Object o = converter.getAsObject(context, component, "2016/01/01 00:00:00");
		
		assertThat(o, instanceOf(Optional.class));
		Optional<LocalDateTime> op = (Optional<LocalDateTime>)o;
		
		assertThat(op.isPresent(), is(true));
		
		LocalDateTime b = op.get();
		assertThat(b.getYear(), is(2016));
		assertThat(b.getMonthValue(), is(1));
		assertThat(b.getDayOfMonth(), is(1));
		assertThat(b.getHour(), is(0));
		assertThat(b.getMinute(), is(0));
		assertThat(b.getSecond(), is(0));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<LocalDateTime> op = (Optional<LocalDateTime>)o;
		
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
		String s = converter.getAsString(context, component, Optional.of(LocalDateTime.of(2016, 1, 1,0,0,0)));
		
		assertThat(s, is("2016/01/01 00:00:00"));
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
