package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class user_login implements Serializable{
	private String user, pass;

	

	public user_login(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	public user_login() {
		
	}
	
	
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}




}
