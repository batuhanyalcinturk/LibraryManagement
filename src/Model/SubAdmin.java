package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SubAdmin extends User {
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public SubAdmin() {
		super();
	}
	public SubAdmin(int id, String tcno, String password, String name, String surname, String type) {
		super(id, tcno, password, name, surname, type);
	}
	
}
