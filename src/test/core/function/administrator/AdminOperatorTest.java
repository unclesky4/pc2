package test.core.function.administrator;

import java.util.ArrayList;

import org.junit.Test;

import core.function.administrator.AdminOperator;
import core.function.common.ServerConnection;
import core.function.model.JudgementInfo;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;

public class AdminOperatorTest {

	@Test
	public void testGetAllJudgementInfo() {
		ServerConnection serverConnection = new ServerConnection();
		IContest contest = null;
		try {
			contest = serverConnection.login("administrator1", "administrator1");
		} catch (LoginFailureException e) {
			e.printStackTrace();
		}
		
		AdminOperator adminOperator = new AdminOperator(serverConnection);
		ArrayList<JudgementInfo> list = adminOperator.getAllJudgementInfo();
		System.out.println(list.get(0).toString());
	}

}
