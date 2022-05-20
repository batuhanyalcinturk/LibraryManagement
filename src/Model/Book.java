package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.DbHelper;
import Helper.Helper;

public class Book {
	private int id;
	String name, page, writer, category, info;
	DbHelper conn = new DbHelper();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	Connection con = conn.connDb();

	public Book(int id, String name, String page, String writer, String category, String info) {
		super();
		this.id = id;
		this.name = name;
		this.page = page;
		this.writer = writer;
		this.category = category;
		this.info = info;
	}

	public Book() {
	}

	public boolean addBook(String name, String page, String writer, Object category, String info) throws SQLException {
		int key = 0;
		String query = "INSERT INTO book" + "(name,page,writer,category,info) VALUES" + "(?,?,?,?,?)";
		boolean duplicate = false;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM book WHERE name='" + name + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu Ýsimde Kitap Zaten Bulunmaktadýr.");
				break;
			}

			if (!duplicate) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, page);
				preparedStatement.setString(3, writer);
				preparedStatement.setObject(4, category);
				preparedStatement.setString(5, info);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
