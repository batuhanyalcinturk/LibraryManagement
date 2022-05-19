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
						rs.getString("category"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean addBook(String name, String page, String writer, String category) throws SQLException {
		String query = "INSERT INTO book" + "(name,page,writer,category) VALUES " + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, page);
			preparedStatement.setString(3, writer);
			preparedStatement.setString(4, category);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}

	public boolean delBook(int id) throws SQLException {
		String query = "DELETE FROM book WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}

	public boolean updBook(int id, String name, String page, String writer, Object category) throws SQLException {
		String query = "UPDATE book SET name = ?, page = ?, writer = ?, category = ? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, page);
			preparedStatement.setString(3, writer);
			preparedStatement.setObject(4, category);
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;

	}
}