package cn.heapstack.dbutils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.Server;

public class HsqldbUtils {
	private static Server server=null;
	public void startDataBase()
	{ 
		server = new Server();
		server.setDatabaseName(0, "userdatabase");
		server.setDatabasePath(0, "file:userdatabase");

		server.setLogWriter(new PrintWriter( System.out));
		server.setErrWriter(new PrintWriter( System.err));
		server.start();
	}
	public void stopDataBase() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.hsqldb.jdbcDriver");	//jdbc:hsqldb:hsql://localhost/test
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/userdatabase", "sa", "");
		Statement statement = conn.createStatement();
		statement.execute("SHUTDOWN");
		statement.close();
		conn.close();
	}
	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		HsqldbUtils m = new HsqldbUtils();
		if(args.length==0)
			throw new IllegalArgumentException(new Integer(args.length).toString());
		else if(args[0].equalsIgnoreCase("-start"))
		{
			m.startDataBase();
		}
		else if(args[0].equalsIgnoreCase("-stop"))
		{
			m.stopDataBase();
		}
	}
}
