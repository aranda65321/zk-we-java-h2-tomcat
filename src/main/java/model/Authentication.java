package model;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class Authentication implements Initiator {
	DatabaseOperations operation = new DatabaseOperations();
	
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		Authentication_Service auservice = new Authentication_Service();
		user_login user = new user_login();
		if(auservice.getuser_session(user.getUser()) == false) {
			Executions.sendRedirect("/index.zul");
			return;
			
			
		}
		
	}

}
