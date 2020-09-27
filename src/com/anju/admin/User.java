package com.anju.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_user;
	public String user_name;
	public String password;
	public int getId_user() {
		return id_user;
	}	
	public User() {
		
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", user_name=" + user_name + ", password=" + password + "]";
	}
	
	
}
