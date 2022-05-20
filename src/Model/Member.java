package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.*;

public class Member extends User {
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	Connection con = conn.connDb();

	public Member() {

	}

	public Member(int id, String tcno, String password, String name, String surname, String type) {
		super(id, tcno, password, name, surname, type);

	}

	public boolean register(String tcno, String password, String name, String surname) throws SQLException {
		int key = 0;
		String query = "INSERT INTO register" + "(tcno,password,name,surname,type) VALUES" + "(?,?,?,?,?)";
		boolean duplicate = false;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM register WHERE tcno='" + tcno + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu T.C. numarasýna ait bir kayýt bulunmaktadýr.");
				break;
			}

			if (!duplicate) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, surname);
				preparedStatement.setString(5, "member");
				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public ArrayList<Book> getBookList() throws SQLException {
		ArrayList<Book> list = new ArrayList<>();
		Book obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM book");
			while (rs.next()) {
				obj = new Book(rs.getInt("id"), rs.getString("name"), rs.getString("page"), rs.getString("writer"),
						rs.getString("category"), rs.getString("info"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	
}