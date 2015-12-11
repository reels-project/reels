package com.github.reels_project.reels.faces.convert;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.junit.Test;

public class CharacterConverterTest {
	
	private FacesContext context = mock(FacesContext.class);
	private UIComponent component = mock(UIComponent.class);
	
	Converter getConverter(boolean optional){
		CharacterConverter converter = new CharacterConverter();
		converter.setOptional(optional);
		return converter;
	}
	
	@Test
	public void nonOptional_getAsObject(){
		Converter converter = getConverter(false);
		Object o = converter.getAsObject(context, component, "12");
		
		assertThat(o, instanceOf(Character.class));
		Character b = (Character)o;
		assertThat(b.charValue(), is('1'));
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
		String s = converter.getAsString(context, component, Character.valueOf('1'));
		assertThat(s, is("1"));
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
		Object o = converter.getAsObject(context, component, "12");
		
		assertThat(o, instanceOf(Optional.class));
		Optional<Character> op = (Optional<Character>)o;
		
		assertThat(op.isPresent(), is(true));
		
		Character b = op.get();
		assertThat(b.charValue(), is('1'));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_null(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, null);
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Character> op = (Optional<Character>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void optional_getAsObject_empty(){
		Converter converter = getConverter(true);
		Object o = converter.getAsObject(context, component, "");
		
		assertNotNull(o);
		assertThat(o, instanceOf(Optional.class));
		Optional<Character> op = (Optional<Character>)o;
		
		assertThat(op.isPresent(), is(false));
	}
	
	
	@Test
	public void optional_getAsString(){
		Converter converter = getConverter(true);
		String s = converter.getAsString(context, component, Optional.of(Character.valueOf('1')));
		
		assertThat(s, is("1"));
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
