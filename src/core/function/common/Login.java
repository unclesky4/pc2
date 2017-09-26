package core.function.common;

import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;


/**
 * 登陆
 * @author unclesky4 02/09/2017
 *
 */

public class Login {
	
	ServerConnection serverConnection = null;
	
	IContest iContest = null;
	
	public Login(String username, String password) {
		this.serverConnection = new ServerConnection();
		try {
			iContest = serverConnection.login(username, password);
		} catch (LoginFailureException e) {
			System.out.println(username + ": 登陆失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取登陆后的IContest对象
	 * @param username
	 * @param password
	 * @return
	 */
	public IContest getIContest(String username, String password) {
		return this.iContest;
	}
	
	/**
	 * 获取登陆后的ServerConnection对象
	 * @return
	 */
	public ServerConnection getServerConnection() {
		return this.serverConnection;
	}
}
