package Model;

import Helper.DbHelper;

public class User {
	private int id;
	String tcno, password, name, surname, type;
	DbHelper conn = new DbHelper();

	public User(int id, String tcno, String password, String name, String surname, String type) {
		this.id = id;
		this.tcno = tcno;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.type = type;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
