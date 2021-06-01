package model;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class Authentication_Service {
	
	boolean state_sesion = false;
	
	public boolean user_session(String usr) {
		
		user_login user_l = new user_login();
		Session sess = Sessions.getCurrent();
		user_l.setUser(usr);
		
		sess.setAttribute("userCredential", user_l);
		
		
		state_sesion=true;
		
		return state_sesion;		
		
		
	}
	public boolean getuser_session(String usr) {
			boolean gsesion = false;
			
			Session sess = Sessions.getCurrent();
			user_login user_g = (user_login)sess.getAttribute("userCredential");
			if(user_g == null) {
				
				gsesion = false;
				
				
			}else{
				gsesion = true;
				
				
			}
						
			return gsesion;
		}
	public boolean close_ssesion() {
		boolean Csesion = false;

		Session sess = Sessions.getCurrent();
		user_login user_c = new user_login();
		sess.removeAttribute(user_c.getUser());
		Csesion = true;
	
					
		return Csesion;
	}

	
	
	
	

}
