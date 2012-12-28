package cn.heapstack.servlet.listener;

import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.rss.RssBuilder;

/**
 * Application Lifecycle Listener implementation class SimpleBlogContextListener
 * 
 */
public final class SimpleBlogContextListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(SimpleBlogContextListener.class);
	
	/**
	 * Default constructor.
	 */
	public SimpleBlogContextListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent evt) {
		ServletContext context = evt.getServletContext();
		DAOFactory.runDemo = (context.getInitParameter("runDemo")
				.compareToIgnoreCase("true") == 0);
		logger.info("Are we running in demo mode ? {}", DAOFactory.runDemo);
		
		Properties p =  new Properties();
		try {
			p.load(SimpleBlogContextListener.class.getResourceAsStream("../../../../dbconf.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String jndi_name = p.getProperty("jndi-name");
		String connection_url = p.getProperty("connection-url");
		String driver_class = p.getProperty("driver-class");
		String user_name = p.getProperty("user-name");
		String password = p.getProperty("password");
		logger.info("\n\tjndi-name:{} \n\tconnection-url:{} \n\tdriver-class:{}", new String[]{jndi_name,connection_url,driver_class,user_name,password});

		//new MysqlDataSource()
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driver_class);
		ds.setUrl(connection_url);
		ds.setUsername(user_name);
		ds.setPassword(password);
		
		DAOFactory.getDAOFactory().setDataSource(ds);
		
		String rssFile = context.getRealPath("rss.xml");
		//start demon process to build rss file
		Timer t = new Timer();
		/*
task task to be scheduled.
delay delay in milliseconds before task is to be executed.
period time in milliseconds between successive task executions.
		 */
		t.schedule(new RssBuilder(5,rssFile), 1000*60, 1000*60*60);
		logger.info("generate rss file schedule, destnation : {}",rssFile);
		logger.info("<!!!>SimpleBlog Initialization Done<!!!>");	
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent evt) {
	}

}
