package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class OptionalStringConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		OptionalStringConverter converter = new OptionalStringConverter();
		return converter;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAsObject(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "1234.5");
		
		assertThat(o, instanceOf(Optional.class));
		Optional<String> op = (Optional<String>)o;
		
		assertThat(op.isPresent(), is(true));
		
		String b = op.get();
		assertThat(b, is("1234.5"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<String> op = (Optional<String>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAsObject_empty(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "");
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<String> op = (Optional<String>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	
	@Test
	public void getAsString(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.of("1234.5"));
		
		assertThat(s, is("1234.5"));
	}
	
	@Test
	public void getAsString_empty(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.empty());
		
		assertThat(s, is(""));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getAsString_null(){
		Converter converter = getConverter(true);
		converter.getAsString(context, component, null);
	}
}
