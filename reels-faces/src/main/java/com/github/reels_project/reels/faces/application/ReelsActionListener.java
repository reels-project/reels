package com.github.reels_project.reels.faces.application;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.ActionSource2;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.reels_project.reels.faces.controller.Navigation;

public class ReelsActionListener implements ActionListener{
	
	private static Logger logger = LoggerFactory.getLogger(ReelsActionListener.class);

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ActionSource2 actionSource = (ActionSource2) event.getComponent();  
		MethodExpression methodExpression = actionSource.getActionExpression();

		String fromAction = null;
		String outcome = null;
		if (methodExpression != null) {
			fromAction = methodExpression.getExpressionString();
			logger.debug("[開始][{}]",fromAction);
			try {
				Object returnedValue = methodExpression.invoke(facesContext.getELContext(), null);
				if(returnedValue instanceof String){
					logger.warn("Action method returns java.lang.String!!");
					outcome = String.class.cast(returnedValue);
				}else if(returnedValue != null && returnedValue instanceof Navigation){
					outcome = Navigation.class.cast(returnedValue).getOutcome();
				}
			} catch (Exception e) {
				//TODO アプリケーション例外を作ったら実装する
//				Throwable t = e.getCause();
//				if(t instanceof ApplicationException){
//					logger.debug("ApplicationException was thrown when action method called. action method={}",fromAction);
//					ApplicationException ae = (ApplicationException)t;
//					ae.putAttribute(YellowHawkExceptionHandler.KEY_FROM_ACTION, fromAction);
//				}
				throw new AbortProcessingException(e);
			}
			logger.debug("[終了][{}]",fromAction);
		}
		
		logger.debug("outcome={}",outcome);
		
		//FIXME メッセージの仕組み：Messengerは必要？そしてこれをやるならPhaseListenerな気がする。
//		if(outcome != null){
//			Messenger messenger = CDI.get(Messenger.class);
//			if(messenger != null && !messenger.getAppMessages().isEmpty()){
//				AppLog.d(YellowHawkActionListener.class,"put messenger to flash. messages[{}] messenger[{}]",messenger.getAppMessages().size(),messenger);
//				Flash flash = CDI.get(Flash.class);
//				flash.put(Messenger.KEY, messenger.getAppMessages());
//			}
//		}
		
		Application application = facesContext.getApplication();
		NavigationHandler navigationHandler = application.getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, fromAction, outcome);
		
		//Render Response
		facesContext.renderResponse();
	}
}
