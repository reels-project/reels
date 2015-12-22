package com.github.reels_project.reels.faces.router;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

/**
 * FIXME 作りがあまりかっこよくない
 * @author Takahiko Sato
 *
 */
public class Routing {

	private static final Pattern JSESSIONID_PATTERN = Pattern.compile("(?i)^(.*);jsessionid=[\\w\\.\\-]+(.*)");
	private static final String JSESSIONID_REPLACEMENT = "$1$2";

	private final Pattern RESOURCE_PATTERN;

	private final RouterConfiguration routerConfiguration;
	private final String originalURL;
	private final String contextPath;
	
	public Routing(HttpServletRequest request,RouterConfiguration routerConfiguration) {
		this.routerConfiguration = Objects.requireNonNull(routerConfiguration);
		
		String orig = Objects.requireNonNull(request).getRequestURI();
		Matcher sessionIdMatcher = JSESSIONID_PATTERN.matcher(orig);
		if (sessionIdMatcher.matches()){
			orig = sessionIdMatcher.replaceFirst(JSESSIONID_REPLACEMENT);
		}
		this.originalURL = orig;
		this.contextPath = request.getContextPath();
		
		this.RESOURCE_PATTERN = Pattern.compile(
			String.format(
				".+\\.(%s).*",
				routerConfiguration.getResourceTypes().stream()
				.map(s->s.trim())
				.collect(Collectors.joining("|"))
			),Pattern.CASE_INSENSITIVE);
		
		//TODO 個別ルーティングのときのみパスパラメーター有効
//		queryMap = new HashMap<>();
//
//		Pattern p = Pattern.compile("\\{\\{([^\\}]+)\\}\\}");
//		Matcher m = p.matcher("/hoge/{{piyo}}//{{hage}}");
//		while(m.find()){
//			System.out.println(m.group(1));
//		}
		
	}
	
	public boolean isAssetsRequest(){
		String url = originalURL.replace(contextPath, "");
		return url.startsWith("/" + routerConfiguration.getAssetsPath());
	}
	
	public boolean isResourceRequest(){
		return !isAssetsRequest() && RESOURCE_PATTERN.matcher(originalURL).matches();
	}
	
	public boolean isTransrated(){
		return originalURL.contains(routerConfiguration.getViewsPath());
	}
	
	public String from(){
		return originalURL.replace(contextPath, "");
	}
	
	public String to(){
		if(isAssetsRequest() || isResourceRequest() || isTransrated()){
			return originalURL.replace(contextPath, "");
		}
		String orig = originalURL;
		//パラメーターはHTTPServletRequestのものが使用されるため考慮不要
		return "/" + routerConfiguration.getViewsPath() + orig.replace(contextPath, "") + routerConfiguration.getExtension();
	}
}
