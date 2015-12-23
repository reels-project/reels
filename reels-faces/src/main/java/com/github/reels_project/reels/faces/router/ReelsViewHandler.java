package com.github.reels_project.reels.faces.router;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;

public class ReelsViewHandler extends ViewHandlerWrapper{
	protected ViewHandler parent;

	public ReelsViewHandler(ViewHandler parent) {
		super();
		this.parent = parent;
	}

	@Override
	public ViewHandler getWrapped() {
		return parent;
	}

	@Override
	public String getActionURL(final FacesContext context, final String viewId){
		//ここが呼ばれる場合getBookmarkableURLは呼ばれない。FormまたはoutcomeTargetXXから呼ばれる。
		String result = parent.getActionURL(context, viewId);
		//TODO 設定から取得する
		result = result.replace("/views", "").replace(".xhtml", "");
		//FIXME h:linkに直接外向きURLを指定するとDisableになる
		//FIXME NavigationHandlerでNavigationCaseがあるかどうかで判断している？
		return result;
	}
}
