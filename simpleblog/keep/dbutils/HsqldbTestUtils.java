package cn.heapstack.usermanage.testutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.jdbc.jdbcDataSource;

public class HsqldbTestUtils {
	private static final Logger logger = Logger.getLogger(HsqldbTestUtils.class);
	public static  Server server = null;
	public static  DataSource dataSource = null ;
	
	public static void setUpBeforeClass() throws Exception {
		logger.info("Junit init hsqldb in memory mode ");
		server = new Server();
		server.setDatabaseName(0, "userdatabase");
		server.setDatabasePath(0, "mem:userdatabase");
		server.setSilent(true);
		//server.setLogWriter(new PrintWriter( System.out));
		//server.setErrWriter(new PrintWriter( System.err));
		server.start();
		
		Class.forName("org.hsqldb.jdbcDriver");	//jdbc:hsqldb:hsql://localhost/test
		jdbcDataSource jdbc = new jdbcDataSource();
		jdbc.setDatabase("jdbc:hsqldb:hsql://localhost/userdatabase");
		jdbc.setUser("sa");
		dataSource = jdbc;
		
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("" +
				"CREATE TABLE USERS (" +
				"		username VARCHAR(50) NOT NULL," +
				"		password VARCHAR_IGNORECASE(50) NOT NULL," +
				"		id IDENTITY," +
				"		unique(username)" +
				");" +
				"CREATE TABLE USERS_INFO (" +
				"		firstname VARCHAR(50) NOT NULL," +
				"		lastname VARCHAR(50) NOT NULL," +
				"		address varchar(100)," +
				"		zipcode varchar (6)," +
				"		mobilephone char(11)," +
				"		info varchar(200)," +
				"		email varchar(100)," +
				"		id INTEGER not null primary key," +
				"		FOREIGN key (id) references USERS (id) on delete cascade on update cascade" +
				");");
		ps.execute();
		ps.close();
	}

	
	public static void tearDownAfterClass() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/userdatabase", "sa", "");
		Statement statement = conn.createStatement();
		statement.execute("SHUTDOWN");
		statement.close();
		conn.close();
		logger.info("Junit shutdown hsqldb");
	}
	
	public static void setUpBeforeClassRealDB() throws Exception {
		logger.info("Junit init hsqldb connect server mode db");
		
		Class.forName("org.hsqldb.jdbcDriver");	//jdbc:hsqldb:hsql://localhost/test
		jdbcDataSource jdbc = new jdbcDataSource();
		jdbc.setDatabase("jdbc:hsqldb:hsql://localhost/userdatabase");
		jdbc.setUser("sa");
		dataSource = jdbc;
	}

	
	public static void tearDownAfterClassRealDB() throws Exception {
		logger.info("Junit end");
	}

}
