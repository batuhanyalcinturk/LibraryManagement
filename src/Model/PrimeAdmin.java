package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrimeAdmin extends User {
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public PrimeAdmin(int id, String tcno, String password, String name, String surname, String type) {
		super(id, tcno, password, name, surname, type);

	}

	public PrimeAdmin() {
	}

	public ArrayList<User> getAdminList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM admin WHERE type = 'sub'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"), rs.getString("name"),
						rs.getString("surname"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean addAdmin(String tcno, String password, String name, String surname) throws SQLException {
		String query = "INSERT INTO admin" + "(tcno, password, name, surname, type) VALUES " + "(?,?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, surname);
			preparedStatement.setString(5, "sub");
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

	public boolean delAdmin(int id) throws SQLException {
		String query = "DELETE FROM admin WHERE id = ?";
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

	public boolean updAdmin(int id, String tcno, String password, String name, String surname) throws SQLException {
		String query = "UPDATE admin SET name = ?, surname = ?, tcno = ?, password = ? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, tcno);
			preparedStatement.setString(4, password);
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

	public ArrayList<User> getMemberList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM register");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"), rs.getString("name"),
						rs.getString("surname"), rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean delMember(int id) throws SQLException {
		String query = "DELETE FROM register WHERE id = ?";
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

	public boolean updMember(int id, String tcno, String password, String name, String surname, String type) throws SQLException {
		String query = "UPDATE register SET name = ?, surname = ?, tcno = ?, password = ?, type = ? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, tcno);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, type);
			preparedStatement.setInt(6, id);
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
