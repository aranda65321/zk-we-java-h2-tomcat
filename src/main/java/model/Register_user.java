package model;

public class Register_user {
	
	private String Full_name;
	private String Email;
	private String User;
	private String password;
	
	
	public Register_user(String full_name, String email, String user, String password) {
		super();
		Full_name = full_name;
		Email = email;
		User = user;
		this.password = password;
	}
	
	
	public String getFull_name() {
		return Full_name;
	}
	public void setFull_name(String full_name) {
		Full_name = full_name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
