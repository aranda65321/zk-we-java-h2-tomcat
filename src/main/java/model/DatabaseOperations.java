package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import BaseData.Conection;

public class DatabaseOperations extends user_login{
	
	Conection conexion = new Conection();
	ResultSet rs = null;
	Statement stmt;
	

	public boolean loginDatabase(String user, String pss) throws SQLException, ClassNotFoundException {
		boolean log = false;
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();
		Authentication_Service service = new Authentication_Service();

		try {
			PreparedStatement st = con
					.prepareStatement("SELECT * FROM USUARIO.USRDATA WHERE USER = " + "'" + user + "';");

			rs = st.executeQuery();
			rs.next();

			user_login login = new user_login(rs.getString("USER"), rs.getString("PASS"));

			if ((login.getUser().equals(user)) && (login.getPass().equals(pss))) {
				log = service.user_session(login.getUser());
				
						
				System.out.println("Sesion Iniciada");
				st.close();
				con.close();
			}

		} catch (Exception ex) {
			// Handle errors for JDBC
			System.out.println("Usuario no encontrado");
			log = false;
		}
		return log;
	}
	
	public void logout() {
		
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
		
	}
	

	public boolean register_user(String name, String email, String user, String pass) {
		boolean finish = false;

		double rs = 0;
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();

		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO USUARIO.USRDATA VALUES('" + name + "','" + email
					+ "','" + user + "','" + pass + "');");
			rs = st.executeUpdate();
			System.out.println("usuario registrado");
			st.close();
			con.close();
			finish = true;
		} catch (SQLException sQ) {
			// Handle errors for JDBC
			System.out.println("Usuario no registrado");
			finish = false;
		}
		return finish;

	}

	
	
	
	
	public void start_show(Grid list_data) {
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();
		
		
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM USUARIO.USRDATA;");
			rs = st.executeQuery();
			
			Rows rows = list_data.getRows();
			
			while(rs.next()) {
				
				
				Row row = list_Generate(rs.getString("FULL_NAME"), rs.getString("EMAIL"), rs.getString("USER"), rs.getString("PASS"));
				rows.appendChild(row);
				
				
				
			}
			st.close();
			con.close();
			} catch (Exception ex) {
			// Handle errors for JDBC
			System.out.println("Base de datos no encontrada");
		}

		
		
	
	}
	
	

	public Row list_Generate(String name, String email, String user, String pass) {
		// TODO Auto-generated method stub+
		Row row = new Row();

		Label lab0 = new Label(name);
		Label lab1 = new Label(email);
		Label lab2 = new Label(user);
		Label lab3 = new Label(pass);
		Button b1 =  new Button();
		row.appendChild(lab0);
		row.appendChild(lab1);
		row.appendChild(lab2);
		row.appendChild(lab3);
		return row;

	}
	

	
	public String[] search_user(String user) {
		
		String[] user_found = new String[5];
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();

		try {
			PreparedStatement st = con
					.prepareStatement("SELECT * FROM USUARIO.USRDATA WHERE USER = " + "'" + user + "';");

			rs = st.executeQuery();
			rs.next();			
			System.out.println("Sesion Iniciada");
			user_found[0] = rs.getString("FULL_NAME");
			user_found[1] = rs.getString("EMAIL");
			user_found[2] = rs.getString("USER");
			user_found[3] = rs.getString("PASS");
			user_found[4] = "true";
			
			
			
			st.close();
			con.close();
			

		} catch (Exception ex) {
			// Handle errors for JDBC
			System.out.println("User not found");
			user_found[4] = "false";

		}
		return user_found;
		
		
		
		
		
		
		
	}
	
	public boolean delete_user(String user) {
		double rs = 0;
		boolean delet = false;
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();

		try {
			PreparedStatement st = con
					.prepareStatement("DELETE FROM USUARIO.USRDATA"  + "WHERE USER = " +"'" + user + "';");

			rs = st.executeUpdate();

			delet = true;
			System.out.println("Usuario eliminado");
			st.close();
			con.close();
			

		} catch (Exception ex) {
			// Handle errors for JDBC
			System.out.println("Usuario no eliminado");
			delet = false;
		}
		return delet;
		
		
	}
	
	public boolean update_user(String name, String email, String user, String pass) {
		double rs = 0;
		boolean upd = false;
		conexion.Conectbasedata();
		Connection con = conexion.Conectbasedata();

		try {
			PreparedStatement st = con
					.prepareStatement("UPDATE USUARIO.USRDATA"  + "SET USER = " +"'" + user + "'" + "WHERE USER IN ("+"'"+user+"')"+";"
							+ "UPDATE USUARIO.USRDATA"  + "SET NAME = " +"'" + name + "'" + "WHERE USER IN ("+"'"+name+"')"+";"
									+ "UPDATE USUARIO.USRDATA"  + "SET EMAIL = " +"'" + email + "'" + "WHERE EMAIL IN ("+"'"+email+"')"+";"
											+ "UPDATE USUARIO.USRDATA"  + "SET PASS = " +"'" + pass + "'" + "WHERE USER IN ("+"'"+pass+"')"+";");

			rs = st.executeUpdate();

			upd = true;
			System.out.println("Usuario actualizado");
			st.close();
			con.close();
			

		} catch (Exception ex) {
			// Handle errors for JDBC
			System.out.println("Usuario no actualizado");
			upd = false;
		}
		return upd;
	}
}

	