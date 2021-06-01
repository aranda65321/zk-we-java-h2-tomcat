package controlpage;

import javax.swing.JOptionPane;

import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

import model.Authentication_Service;
import model.DatabaseOperations;
import model.user_login;

public class Control_dashboard extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271402931401892418L;

	@Wire
	private Grid list_data;
	@Wire
	private Grid aux_up;
	@Wire
	private Grid update_user;
	@Wire
	private Label tx1;
	@Wire
	private Label tx2;
	@Wire
	private Label tx3;
	@Wire
	private Label tx4;
	@Wire
	private Grid search_user;
	@Wire
	private Grid create_us;
	@Wire
	private Grid del;
	@Wire
	private Combobox operation;
	@Wire
	private Button b1;
	@Wire
	private Button b2;
	@Wire
	private Button b3;
	@Wire
	private Button bb4;
	@Wire
	private Button b5;
	@Wire
	private Button bb6;
	@Wire
	private Button btn_salir;
	@Wire
	private Textbox sh_user;
	@Wire
	private Textbox t1;
	@Wire
	private Textbox t2;
	@Wire
	private Textbox t3;
	@Wire
	private Textbox t4;
	@Wire
	private Textbox t5;
	@Wire
	private Textbox t6;
	@Wire
	private Textbox t7;
	@Wire
	private Textbox t8;
	@Wire
	private Textbox t9;
	@Wire
	private Textbox u_up;

	DatabaseOperations operations = new DatabaseOperations();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		search_user.setVisible(false);
		create_us.setVisible(false);
		del.setVisible(false);
		update_user.setVisible(false);
		aux_up.setVisible(false);

		operations.start_show(list_data);

	}

	@Listen("onClick = #b1")
	public void search_use() {

		if (sh_user.getValue() != "") {

			list_data.setVisible(false);
			del.setVisible(false);
			create_us.setVisible(false);
			search_user.setVisible(false);
			aux_up.setVisible(false);
			String[] search = operations.search_user(sh_user.getValue());
			
			if(search[4].equals("true")) {
				
				tx1.setValue(search[0]);
				tx2.setValue(search[1]);
				tx3.setValue(search[2]);
				tx4.setValue(search[3]);				
				search_user.setVisible(true);
			}else{
				alert("Usuario no encontrado");
			}
			
			

		}

	}

	@Listen("onClick = #b2")
	public void opera() {
		String op = operation.getValue();
		if ((op.equals("Create user"))) {
			search_user.setVisible(false);
			list_data.setVisible(false);
			del.setVisible(false);
			update_user.setVisible(false);
			aux_up.setVisible(false);

			create_us.setVisible(true);

		}
		if ((op.equals("Delete user"))) {
			search_user.setVisible(false);
			list_data.setVisible(false);
			create_us.setVisible(false);
			aux_up.setVisible(false);
			update_user.setVisible(false);

			del.setVisible(true);

		}

		if ((op.equals("Update user"))) {
			search_user.setVisible(false);
			list_data.setVisible(false);
			create_us.setVisible(false);
			del.setVisible(false);
			aux_up.setVisible(true);
			update_user.setVisible(false);

		}
		if ((op.equals("View user data"))) {
			search_user.setVisible(false);
			list_data.setVisible(true);
			create_us.setVisible(false);
			del.setVisible(false);
			aux_up.setVisible(false);
			update_user.setVisible(false);

		}
		
		

	}

	@Listen("onClick = #b3")
	public void cre_user() {
		boolean cre = false;

		String op = operation.getValue();

		if ((op.equals("Create user"))) {
			cre = operations.register_user(t1.getValue(), t2.getValue(), t3.getValue(), t4.getValue());
			create_us.setVisible(true);
			if (cre) {
				alert("Successful user creation");
				Executions.sendRedirect("/Dashboard.zul");

			}
		}
	}

	@Listen("onClick = #b3")
	public void del_user() {
		boolean cre = false;

		String op = operation.getValue();

		if ((op.equals("Delete user"))) {
			create_us.setVisible(true);
			cre = operations.delete_user(t5.getValue());
			if (cre) {
				alert("Successful user delete");
				Executions.sendRedirect("/Dashboard.zul");

			} else {
				alert("Faile user delete");

			}

		}
	}

	@Listen("onClick = #b5")
	public void btn_update() {
		String[] search = operations.search_user(u_up.getValue());
		if (search[0] != "") {
			t6.setValue(search[0]);
			t7.setValue(search[1]);
			t8.setValue(search[2]);
			t9.setValue(search[3]);
			aux_up.setVisible(true);
			update_user.setVisible(true);
			alert("Usuario encontrado");
			
		} else {

			alert("Usuario no encontrado");
		}

	}

	@Listen("onClick = #bb6")
	public void btn_fup() {

		boolean F_up = operations.update_user(t6.getValue(), t7.getValue(), t8.getValue(), t9.getValue());
		if (F_up) {
			alert("User updated successfully");
			Executions.sendRedirect("/Dashboard.zul");

		}else {
			
			alert("User updated successfully");
		}

	}
	
	
	
	
	@Listen("onClick = #logout")
	public void btn_logout() {

		Authentication_Service auservice = new Authentication_Service();
		if(auservice.close_ssesion()) {
			operations.logout();
			Executions.sendRedirect("/index.zul");			
		}else {
			
			alert("Faild close session");
		}
		
		

	}
	
	
	
	


}



