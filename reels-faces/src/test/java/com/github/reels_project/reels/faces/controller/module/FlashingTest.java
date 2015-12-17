package com.github.reels_project.reels.faces.controller.module;

import static org.junit.Assert.*;

import javax.faces.context.Flash;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.reels_project.reels.faces.controller.module.Flashing;


public class FlashingTest {
	
	private Flash flash;
	
	Flashing create(){
		return new Flashing() {
			@Override
			public Flash getFlash() {
				return flash;
			}
		};
	}
	
	@Before
	public void before(){
		flash = mock(Flash.class);
	}
	
	@Test
	public void flash_put(){
		Flashing f = create();
		f.flash("KEY", "VALUE");
		
		verify(flash,times(1)).put("KEY", "VALUE");
	}
	
	@Test
	public void flash_get(){
		
		when(flash.get("KEY")).thenReturn("VALUE");
		
		Flashing f = create();
		String result = f.flash("KEY");
		
		assertNotNull(result);
		assertThat(result, is("VALUE"));
		
		verify(flash,times(1)).get("KEY");
	}
	
	@Test
	public void flash_orDefault(){
		
		String defVal = "DEF";
		
		when(flash.getOrDefault("KEY",defVal)).thenReturn(defVal);
		
		Flashing f = create();
		String result = f.flashOrDefault("KEY", defVal);
		
		assertNotNull(result);
		assertThat(result, is("DEF"));
		
		verify(flash,times(1)).getOrDefault("KEY","DEF");
	}
}
