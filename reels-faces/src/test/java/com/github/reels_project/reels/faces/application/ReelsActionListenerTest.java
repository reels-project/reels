package com.github.reels_project.reels.faces.application;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.ActionSource2;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.github.reels_project.reels.faces.view.Navigation;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class ReelsActionListenerTest {
	
	private FacesContext facesContext;
	private ELContext elContext;
	private Application application;
	private ActionSource2 actionSource2;
	private MethodExpression methodExpression;
	private ActionEvent actionEvent;
	private NavigationHandler navigationHandler;
	
	@Before
	public void before(){
		PowerMockito.mockStatic(FacesContext.class);
		facesContext = mock(FacesContext.class);
		elContext = mock(ELContext.class);
		application = mock(Application.class);
		actionSource2 = mock(UICommand.class);
		methodExpression = mock(MethodExpression.class);
		actionEvent = mock(ActionEvent.class);
		navigationHandler = mock(NavigationHandler.class);
		
		when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
		when(facesContext.getELContext()).thenReturn(elContext);
		
		when(actionEvent.getComponent()).thenReturn((UIComponent)actionSource2);
		when(actionSource2.getActionExpression()).thenReturn(methodExpression);
		
		when(facesContext.getApplication()).thenReturn(application);
		when(application.getNavigationHandler()).thenReturn(navigationHandler);
	}
	
	@Test
	public void processAction_stringOutcome(){
		when(methodExpression.invoke(any(), any())).thenReturn("/index.xhtml");
		
		new ReelsActionListener().processAction(actionEvent);
		
		verify(navigationHandler, times(1)).handleNavigation(any(), any(), any());
		verify(facesContext,times(1)).renderResponse();
	}
	
	@Test
	public void processAction_stringNavigationOutcome(){
		when(methodExpression.invoke(any(), any())).thenReturn(new Navigation("/index.xhtml"));
		
		new ReelsActionListener().processAction(actionEvent);
		
		verify(navigationHandler, times(1)).handleNavigation(any(), any(), any());
		verify(facesContext,times(1)).renderResponse();
	}
	
	@Test(expected=AbortProcessingException.class)
	public void processAction_exception(){
		when(methodExpression.invoke(any(), any())).thenThrow(new RuntimeException("any"));
		
		new ReelsActionListener().processAction(actionEvent);
	}
}

