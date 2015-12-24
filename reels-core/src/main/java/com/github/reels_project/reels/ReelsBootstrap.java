package com.github.reels_project.reels;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.reels_project.reels.core.CDI;
import com.github.reels_project.reels.core.JPA;


//FIXME ServletContainerInitializer?
@WebListener
public class ReelsBootstrap implements ServletContextListener{
	
	private static Logger logger = LoggerFactory.getLogger(ReelsBootstrap.class);
	
	@Inject
	private CDI cdi;
	
	@Inject
	private JPA jpa;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Reels initialize
		Reels._initialize(cdi, jpa);
		logger.info("Reels initialized.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
}
