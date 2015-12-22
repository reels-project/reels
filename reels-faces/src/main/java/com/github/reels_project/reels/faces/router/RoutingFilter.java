package com.github.reels_project.reels.faces.router;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns={"/*"})
public class RoutingFilter implements Filter{
	
	private RouterConfiguration routerConfiguration;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO グローバルルーティング設定を読み込む
		routerConfiguration = new RouterConfiguration();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		Routing routing = new Routing(req,routerConfiguration);
		//アセットリクエストでない。JSFリソースでない。
		if(routerConfiguration.isEnable() && !routing.isAssetsRequest() && !routing.isResourceRequest() && !routing.isTransrated()){
			//リライト対象
			req.getRequestDispatcher(routing.to()).forward(request, response);
		}else{
			//リソースまたはリライト後？
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
		// TODO 後処理:ログかな?
	}
}
