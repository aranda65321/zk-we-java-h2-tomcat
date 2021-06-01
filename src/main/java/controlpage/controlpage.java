package controlpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import model.DatabaseOperations;
import model.user_login;

public class controlpage extends SelectorComposer<Component> {

	/**
	 * 
	 */

	private static final long serialVersionUID = 5108986899502300817L;
	Connection cn;
	Statement st;
	ResultSet rs;

	@Wire
	private Textbox User;
	@Wire
	private Textbox Pass;
	@Wire
	private Label login_status;
	@Wire
	private A register;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	@Listen("onClick = #login_button")
	public void button_iniciar(Event ev) throws SQLException, ClassNotFoundException {
		boolean next=false;
		String usr = User.getValue();
		String psw = Pass.getValue();
		user_login user = new user_login(usr, psw);
		


		if ((user.getUser() != "") && (user.getPass() != "")) {
			DatabaseOperations co = new DatabaseOperations();
 
			next = co.loginDatabase(user.getUser(), user.getPass());

			if (next) {

				login_status.setValue("Successful login");
				Executions.sendRedirect("/Dashboard.zul");

			} else {
				
				login_status.setValue("Incorrect username or password");

			}

		}

	}

	@Listen("onClick = #register")
	public void register_send() {
		Executions.sendRedirect("/User_register.zul");

	}
	
	

}
