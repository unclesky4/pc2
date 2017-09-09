package core.function.common;

import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;


/**
 * 登陆
 * @author unclesky4 02/09/2017
 *
 */

public class Login {
	
	ServerConnection serverConnection = null;
	
	IContest iContest = null;
	
	public Login() {
		this.serverConnection = new ServerConnection();
	}
	
	/**
	 * 角色登陆后返回IContest对象
	 * @param username
	 * @param password
	 * @return
	 */
	public IContest getIContest(String username, String password) {
		try {
			iContest = serverConnection.login(username, password);
		} catch (LoginFailureException e) {
			System.out.println(username + ": 登陆失败");
			e.printStackTrace();
		}
		return iContest;
	}
	
	/**
	 * 获取登陆后的ServerConnection对象
	 * @return
	 */
	public ServerConnection getServerConnection() {
		return serverConnection;
	}
}
