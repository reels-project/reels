package com.github.reels_project.reels.faces.router;

import java.util.Arrays;
import java.util.List;

public class RouterConfiguration {
	//JSFはCSSの世界まで行けないのであえてassets固定にする
	private static final String DEFAULT_ASSETS_PATH = "assets";
	private static final String DEFAULT_VIEWS_PATH = "views";
	//TODO パラメーター分解後にくっつけるイメージ
	private static final String DEFAULT_EXTENSION = ".xhtml";
	private static final List<String> DEFAULT_RESOURCE_TYPES = Arrays.asList(new String[]{"js","css","jpeg","jpg","png","gif"});

	private boolean enable;
	private String assetsPath;
	private String viewsPath;
	private String extension;
	private List<String> resourceTypes;
	
	public RouterConfiguration() {
		this.enable = true;
		this.assetsPath = DEFAULT_ASSETS_PATH;
		this.viewsPath = DEFAULT_VIEWS_PATH;
		this.extension = DEFAULT_EXTENSION;
		this.resourceTypes = DEFAULT_RESOURCE_TYPES;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getAssetsPath() {
		return assetsPath;
	}
	public void setAssetsPath(String assetsPath) {
		this.assetsPath = assetsPath;
	}
	public String getViewsPath() {
		return viewsPath;
	}
	public void setViewsPath(String viewsPath) {
		this.viewsPath = viewsPath;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public List<String> getResourceTypes() {
		return resourceTypes;
	}
	public void setResourceTypes(List<String> resourceTypes) {
		this.resourceTypes = resourceTypes;
	}
}
