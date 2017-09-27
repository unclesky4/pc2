package test;

import java.io.File;

import org.junit.Test;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.execute.ExecuteRun;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;
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
		
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 自定义Run ---- 可以编译运行得到结果
	 */
	@Test
	public void testGetOutputFile_1(){
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
		
		Language language = null;
		Language[] languages = serverConnection.getIInternalContest().getLanguages();
		for(Language language2 : languages) {
			if(language2.getDisplayName().equals("Java")) {
				System.out.println(language2.getDisplayName());
				language = language2;
			}
		}
		
		Problem[] problems = serverConnection.getIInternalContest().getProblems();
		Run run = new Run(serverConnection.getIInternalContest().getClientId(), language, problems[0]);
		
		String mainProgramFile = "/home/uncle/Desktop/pc2_data/solve.java";

		ExecuteRun executeRun = new ExecuteRun(serverConnection, run, mainProgramFile);
		
		File file = executeRun.getOutputFile();
		
		String info = executeRun.readFile(file);
		
		System.out.println(info);
		System.out.println("验证"+executeRun.getRanExecute().getExecutionData().getvalidateTimeMS()+"毫秒");
		System.out.println("编译"+executeRun.getRanExecute().getExecutionData().getCompileTimeMS()+"毫秒");
		System.out.println("执行"+executeRun.getRanExecute().getExecutionData().getExecuteTimeMS()+"毫秒");
		System.out.println(executeRun.getRanExecute().getProblem().getTimeOutInSeconds()+"秒");
		
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
	}

}
