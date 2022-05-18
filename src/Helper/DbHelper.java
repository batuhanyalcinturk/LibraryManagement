package Helper;
import java.sql.*;

public class DbHelper {
	Connection c = null;
	
	public DbHelper() {	}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?user=root&password=159951");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

}
