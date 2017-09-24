package edu.csus.ecs.pc2.ui;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.model.Run;

public class Test extends SelectJudgementFrame {

	public static void main(String[] args) {
		
		//连接服务器
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		//登陆--team1
		try {
			iContest = serverConnection.login("team1", "team1");
		} catch (LoginFailureException e) {
			e.printStackTrace();
		}
		
		Run[] runs = serverConnection.getIInternalContest().getRuns();
		
		Test test = new Test();
		
		test.setRun(runs[0], false);

		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
