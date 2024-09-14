package dbconnection;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Dbutil {

	private Dbutil() {

	}

	static {
		// step 1. load and register Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Servlet\\CRUDApp\\Application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		System.out.println(properties.getProperty("url"));

		Connection conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
				properties.getProperty("password"));
		return conn;

	}

	public static void cleanUp(Connection conn, Statement stms, ResultSet rs) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stms != null) {
			stms.close();
		}
		if (rs != null) {
			rs.close();
		}

	}

}
