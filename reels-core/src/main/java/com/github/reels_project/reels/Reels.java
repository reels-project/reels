package com.github.reels_project.reels;

import java.util.Objects;

import com.github.reels_project.reels.core.CDI;
import com.github.reels_project.reels.core.JPA;

/**
 * Reels
 * 
 * @author Takahiko Sato
 *
 */
public class Reels {
	
	private static CDI _cdi;
	private static JPA _jpa;
	
	public static void _initialize(CDI cdi,JPA jpa){
		Objects.requireNonNull(cdi,"CDI is null.");
		Objects.requireNonNull(jpa,"JPA is null.");
		
		if(_cdi != null || _jpa != null){
			throw new IllegalStateException("Reels is already initialied.");
		}
		_cdi = cdi;
		_jpa = jpa;
	}
	
	public static CDI cdi(){
		return _cdi;
	}
	
	public static JPA jpa(){
		return _jpa;
	}
}
