package com.github.reels_project.reels.faces.view.module;

import java.util.Objects;

import com.github.reels_project.reels.faces.view.Navigation;

public interface Rendering {
	
	default Navigation render(String viewId){
		if(Objects.requireNonNull(viewId).isEmpty()){
			throw new IllegalArgumentException("'viewId' is empty string.");
		}
		return new Navigation(viewId);
	}
}
