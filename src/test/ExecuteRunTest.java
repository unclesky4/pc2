package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.execute.ExecuteRun;
import edu.csus.ecs.pc2.core.execute.ExecutionData;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.ProblemDataFiles;
import edu.csus.ecs.pc2.core.model.Run;
import edu.csus.ecs.pc2.core.model.SerializedFile;

public class ExecuteRunTest {

	/**
	 * 测试TEAM提交的Run
	 */
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
		
		//测试更新Problem的输入文件  --start
		Problem problem = null;
		for(int i=0; i<problems.length; i++) {
			if(problems[i].getShortName().equals("两数求和")){
				problem = problems[i];
				System.out.println("输入文件"+problem.getDataFileName());
				ProblemDataFiles problemDataFiles = new ProblemDataFiles(problem);
				problemDataFiles.setJudgesDataFile(new SerializedFile("/home/uncle/PC2Files/programFile/23aa3dc9c7fd4153b116c5f1632f15fe.input"));
				problemDataFiles.setJudgesAnswerFile(new SerializedFile(problem.getAnswerFileName()));
				problem.setDataFileName("/home/uncle/PC2Files/programFile/23aa3dc9c7fd4153b116c5f1632f15fe.input");
				//更新原来的Problem输入文件名
				serverConnection.getIInternalContest().updateProblem(problem, problemDataFiles);
				System.out.println("输入文件"+problem.getDataFileName());
				break;
			}
		}
		//测试更新Problem  --end
		
		//Run run = new Run(serverConnection.getIInternalContest().getClientId(), language, problems[1]);

		Run run = new Run(serverConnection.getIInternalContest().getClientId(), language, problem);
		
		//程序文件绝对路径
		String mainProgramFile = "/home/uncle/Desktop/pc2_data/solve.java";

		ExecuteRun executeRun = new ExecuteRun(serverConnection, run, mainProgramFile);
		
		//程序输出文件
		File file = executeRun.getOutputFile();
		
		String info = executeRun.readFile(file);
		
		System.out.println("编译"+executeRun.getRanExecute().getExecutionData().isCompileSuccess());
		System.out.println("运行"+executeRun.getRanExecute().getExecutionData().isExecuteSucess());
		System.out.println(info);
		System.out.println("验证"+executeRun.getRanExecute().getExecutionData().getvalidateTimeMS()+"毫秒");
		System.out.println("编译"+executeRun.getRanExecute().getExecutionData().getCompileTimeMS()+"毫秒");
		System.out.println("执行"+executeRun.getRanExecute().getExecutionData().getExecuteTimeMS()+"毫秒");
		System.out.println("超时时间："+executeRun.getRanExecute().getProblem().getTimeOutInSeconds()+"秒");
		
		//=======================
		ExecutionData executionData = executeRun.getRanExecute().getExecutionData();
		if(!executionData.isCompileSuccess()) {
			System.out.println("编译失败");
		}
		if(executionData.getExecuteExitValue() == 1) {
			System.out.println("运行失败");
		}

		List<String> filenames = executeRun.getRanExecute().getTeamsOutputFilenames();
		//executeRun.getRanExecute().getTeamsOutputFilenames() -- 》List的大小
		System.out.println(filenames.size());
		for(String name : filenames) {
			System.out.println("输出文件>>>>:"+name);
		}
		
		
		//编译之后的可执行文件
		System.out.println("getCompileExeFileName:"+executeRun.getRanExecute().getExecutionData().getCompileExeFileName());
		//编译错误的信息文件 csterr.pc2
		System.out.println("getExecutionData().getCompileStderr().getAbsolutePath():"+executeRun.getRanExecute().getExecutionData().getCompileStderr().getAbsolutePath());
		System.out.println("getExecutionData().getCompileStderr().getErrorMessage():"+executeRun.getRanExecute().getExecutionData().getCompileStderr().getErrorMessage());
		System.out.println("getExecutionData().getCompileStdout().getName():"+executeRun.getRanExecute().getExecutionData().getCompileStdout().getName());
		File compileErrorFile = new File(executeRun.getRanExecute().getExecutionData().getCompileStderr().getAbsolutePath());
		
		//---compileErrorFile一定会存在---
		if(compileErrorFile.exists()) {
			System.out.println("编译失败信息"+readFile(compileErrorFile));
		}else{
			System.out.println("编译信息文件不存在"+executeRun.getRanExecute().getExecutionData().getCompileStderr().getAbsolutePath());
		}
		System.out.println("===============");
		
		
		//显示执行的错误信息
		System.out.println("getExecutionData().getExecuteStderr().getAbsolutePath():"+executeRun.getRanExecute().getExecutionData().getExecuteStderr().getAbsolutePath());
		System.out.println("getExecutionData().getExecuteStderr().getErrorMessage():"+executeRun.getRanExecute().getExecutionData().getExecuteStderr().getErrorMessage());
		System.out.println("getExecutionData().getExecuteStderr().getName():"+executeRun.getRanExecute().getExecutionData().getExecuteStderr().getName());
		System.out.println("getExecutionData().getExecuteStderr().getErrorMessage():"+executeRun.getRanExecute().getExecutionData().getExecuteStderr().getErrorMessage());
		File executeErrorFile = new File("executeRun.getRanExecute().getExecutionData().getExecuteStderr().getAbsolutePath()");
		if(executeErrorFile.exists()) {
			System.out.println("执行错误文件存在");
		}
		
		System.out.println("getExecutionData().getExecuteProgramOutput().getName():"+executeRun.getRanExecute().getExecutionData().getExecuteProgramOutput().getName());
		System.out.println("getExecuteDirectoryName:"+executeRun.getRanExecute().getExecuteDirectoryName());
		//执行目录前缀--默认为null
		System.out.println("getExecuteDirectoryNameSuffix:"+executeRun.getRanExecute().getExecuteDirectoryNameSuffix());
		System.out.println("getFailureReason:"+executeRun.getRanExecute().getFailureReason());
		
		System.out.println("COMPILER_STDERR_FILENAME:"+executeRun.getRanExecute().COMPILER_STDERR_FILENAME);
		System.out.println("COMPILER_STDOUT_FILENAME:"+executeRun.getRanExecute().COMPILER_STDOUT_FILENAME);
		System.out.println("EXECUTE_STDERR_FILENAME:"+executeRun.getRanExecute().EXECUTE_STDERR_FILENAME);
		System.out.println("EXECUTE_STDOUT_FILENAME:"+executeRun.getRanExecute().EXECUTE_STDOUT_FILENAME);
		/*COMPILER_STDERR_FILENAME:cstderr.pc2
		COMPILER_STDOUT_FILENAME:cstdout.pc2
		EXECUTE_STDERR_FILENAME:estderr.pc2
		EXECUTE_STDOUT_FILENAME:estdout.pc2*/
	
		//getFileNameFromUser -- 让用户选择可执行文件
		//System.out.println("getFileNameFromUser:"+executeRun.getRanExecute().getFileNameFromUser());
		//========================
		
		
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
	}
	
	//读取程序输出文件内容
	public String readFile(File file) {
		BufferedReader reader = null;
		StringBuffer info = new StringBuffer();
		String tempString = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
	            info.append(tempString+"\n");
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return info.toString();
	}

}
