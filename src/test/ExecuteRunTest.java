package test;

import java.io.File;

import org.junit.Test;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.execute.ExecuteRun;
import edu.csus.ecs.pc2.core.model.Run;

public class ExecuteRunTest {

	@Test
	public void testGetOutputFile() {
		
		//连接服务器
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		//登陆--judge1
		try {
			iContest = serverConnection.login("judge1", "judge1");
			iContest = serverConnection.getContest();
		} catch (LoginFailureException e) {
			e.printStackTrace();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
		
		Run[] runs = serverConnection.getIInternalContest().getRuns();
		String mainProgramFile = "/home/uncle/Desktop/pc2_data/solve.java";

		ExecuteRun executeRun = new ExecuteRun(serverConnection, runs[0], mainProgramFile);
		
		File file = executeRun.getOutputFile();
		
		String info = executeRun.readFile(file);
		
		System.out.println(info);
	}

}
