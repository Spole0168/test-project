package com.shang;

import java.io.File;

import org.apache.log4j.Logger;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.plus.webapp.EnvConfiguration;
import org.mortbay.jetty.webapp.Configuration;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

public class JettyMainRunner {
	static Logger logger = Logger.getLogger(JettyMainRunner.class);
	private static Server server = new Server();
	
	public static void main(String[] args)throws Exception {
		QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
		boundedThreadPool.setMaxThreads(5);
		server.setThreadPool(boundedThreadPool);

		logger.info("JettyMainRunner -Start");
		Connector connector = new SelectChannelConnector();
		connector.setPort(8011);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext("src/main/webapp", "/03_ssm-maven");
		context.setDefaultsDescriptor("src/test/resources/webdefault.xml");
		
		//数据源设置
		EnvConfiguration envConfiguration = new EnvConfiguration();
      envConfiguration.setJettyEnvXml(new File("src/test/resources/jetty/jetty-env.xml").toURI().toURL());
		Configuration[] configurations = new Configuration[]{
		        new org.mortbay.jetty.webapp.WebInfConfiguration(),
		        envConfiguration,
		        new org.mortbay.jetty.plus.webapp.Configuration(),
		        new org.mortbay.jetty.webapp.JettyWebXmlConfiguration(),
		        new org.mortbay.jetty.webapp.TagLibConfiguration()
		};
		context.setConfigurations(configurations);
		
		server.setHandler(context);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		String res = "\n\n\nStartUP 0000000000000000000000000000";
		logger.info(res);
		server.join();
	}
}
