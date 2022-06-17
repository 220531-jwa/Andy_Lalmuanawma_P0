package BankingAppAPI.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil cu;
	private static Properties dbProps;
	
	// This constructor sets up the ConnectionUtil object with the appropriate credentials to connect to the database
	
	private ConnectionUtil() {
		dbProps = new Properties();
		InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
		try {
			dbProps.load(props);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// This method checks to make sure the connection to the database is succesful
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null) {
			cu = new ConnectionUtil();
		}
		return cu;
	}
	
	// This method actually makes the connection to the database
	
	public Connection getConnection() {
		Connection cnct = null;
		
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		try {
			cnct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnct;
	}
}