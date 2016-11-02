package com.j4gdl.ee;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.jboss.weld.environment.se.Weld;

public class Main {

	public static void main(String[] args) throws Exception, LifecycleException {
		new Main().start();
	}

	public void start() throws ServletException, LifecycleException, MalformedURLException {

		Weld weld = new Weld();
		weld.initialize();

		// Define a folder to hold web application contents.
		String webappDirLocation = "WebContent/";
		Tomcat tomcat = new Tomcat();

		// Define port number for the web application
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8081";
		}
		// Bind the port to Tomcat server
		tomcat.setPort(Integer.valueOf(webPort));

		// Define a web application context.
		Context context = tomcat.addWebapp("/tomcatembedded", new File(webappDirLocation).getAbsolutePath());

		// CDI Weld listener
		// context.addApplicationListener(Listener.class.getName());

		// Define and bind web.xml file location.
		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
		context.setConfigFile(configFile.toURI().toURL());

		// Dfine and bind beans.xml file locationt to enable CDI
		File beansFile = new File(webappDirLocation + "WEB-INF/beans.xml");
		context.setConfigFile(beansFile.toURI().toURL());

		// Declare an alternative location for your "WEB-INF/classes" dir
		// Servlet 3.0 annotation will work
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(context);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));

		// Declare an alternative location for your "WEB-INF/lib" dir
		File additionalJars = new File("target/lib");
		resources.addJarResources(new DirResourceSet(resources, "/WEB-INF/lib", additionalJars.getAbsolutePath(), "/"));
		context.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}

}
