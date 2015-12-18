package com.github.reels_project.reels.faces.controller.module;

import javax.faces.context.Flash;


public interface Flashing {
	
	Flash getFlash();
	
	default void flash(String key,Object value){
		getFlash().put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	default <T> T flash(String key){
		return (T)getFlash().get(key);
	}
	
	@SuppressWarnings("unchecked")
	default <T> T flashOrDefault(String key,T defValue){
		return (T)getFlash().getOrDefault(key, defValue);
	}
	
	default void clearFlash(){
		getFlash().clear();
	}
}
