package controlpage;

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
import model.Register_user;

public class control_register extends SelectorComposer<Component> {
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 134774629208583715L;

	DatabaseOperations operations = new DatabaseOperations();
	@Wire
	private A login;
	@Wire
	private Textbox user_name;
	@Wire
	private Textbox user_mail;
	@Wire
	private Textbox user_user;
	@Wire
	private Textbox user_pass;
	@Wire
	private Label state_register;

	@Listen("onClick = #login")
	public void login_send() {
		Executions.sendRedirect("/index.zul");
	}

	@Listen("onClick = #button_register")
	public void button_register() {
		boolean t_reg = false;

		DatabaseOperations operations = new DatabaseOperations();
		if ((user_name.getValue() != "") && (user_mail.getValue() != "") && (user_user.getValue() != "")
				&& (user_pass.getValue() != "")) {
			Register_user regi = new Register_user(user_name.getValue(), user_mail.getValue(), user_user.getValue(),
					user_pass.getValue());
			t_reg = operations.register_user(regi.getFull_name(), regi.getEmail(), regi.getUser(), regi.getPassword());

		}

		if (t_reg) {
			state_register.setValue("Successful registration");
			Executions.sendRedirect("/index.zul");

		} else {
			state_register.setValue("Failed registration");

		}

	}

}
