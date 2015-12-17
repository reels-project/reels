package com.github.reels_project.reels.faces.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Navigation {
	
	private String viewId;
	private Map<String, String> params;
	
	public Navigation(String viewId) {
		this(viewId,false);
	}
	public Navigation(String viewId,boolean redirect) {
		if(Objects.requireNonNull(viewId).isEmpty()){
			throw new IllegalArgumentException("Argument 'viewId' must be NOT empty.");
		}
		
		this.viewId = viewId;
		this.params = new HashMap<>();
		
		if(viewId.contains("?")){
			viewId = viewId.substring(0,viewId.indexOf("?"));
		}
		
		if(redirect){
			withParam("faces-redirect", "true");
		}
	}
	
	public Navigation withParam(String name,String value){
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Argument 'name' must be NOT empty.");
		}
		
		if(value != null){
			params.put(name, value);
		}
		return this;
	}
	
	public String getOutcome(){
		String paramString = params.entrySet().stream().map((e) -> {
			return e.getKey() + "=" + e.getValue();
		}).collect(Collectors.joining("&"));
		
		if(!paramString.isEmpty()){
			return viewId + "?" + paramString;
		}
		
		return viewId;
	}
}
