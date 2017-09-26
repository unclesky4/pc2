package edu.csus.ecs.pc2.core.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.IInternalController;
import edu.csus.ecs.pc2.core.model.IInternalContest;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.Run;
import edu.csus.ecs.pc2.core.model.RunFiles;
import edu.csus.ecs.pc2.core.model.SerializedFile;
import edu.csus.ecs.pc2.core.security.FileSecurityException;
import edu.csus.ecs.pc2.ui.IFileViewer;

/**
 * 测试Executable类 - java语言
 * @author uncle
 *
 */
public class Execute_3 extends Executable{

	public Execute_3(IInternalContest inContest, IInternalController inController, Run run, RunFiles runFiles) {
		super(inContest, inController, run, runFiles);
	}
	
	public static String readFile(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
	            // 显示行号
	            System.out.println("line " + line + ": " + tempString);
	            line++;
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
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, FileSecurityException {
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
		
		IInternalController iInternalController = serverConnection.getIInternalController();
		IInternalContest iInternalContest = serverConnection.getIInternalContest();
		
		Run[] runs = serverConnection.getIInternalContest().getRuns();
		System.out.println("runs[0].getStatus().toString():  "+runs[0].getStatus().toString());
		System.out.println(iInternalContest.getRunFiles(runs[0]) == null);
		
		Language language = serverConnection.getIInternalContest().getLanguage(runs[0].getLanguageId());
		Problem problem = serverConnection.getIInternalContest().getProblem(runs[0].getProblemId());
//		Run run = new Run(serverConnection.getIInternalContest().getClientId(), language, problem);
		SerializedFile mainFile = new SerializedFile("/home/uncle/Desktop/pc2_data/solve.java");
		SerializedFile[] serializedFiles = new SerializedFile[0];
//		serializedFiles[0] = new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeDataFile.input");
//		serializedFiles[1] = new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeAnswerFile.output");
		
		RunFiles runFiles = new RunFiles(runs[0], mainFile, serializedFiles);
		
		Execute_3 execute_3 = new Execute_3(serverConnection.getIInternalContest(), serverConnection.getIInternalController(), runs[0], runFiles);
		
		execute_3.getExecutionData().setRunTimeLimitExceeded(true);
		
		System.out.println("execute.getExecutionData().isRunTimeLimitExceeded(): "+execute_3.getExecutionData().isRunTimeLimitExceeded());
		
		//判断IInternalContest是否存在Problem对应的ProblemDataFiles
		/*if(iInternalContest.getProblemDataFile(problem) == null) {
			System.out.println("添加ProblemDataFiles");
			ProblemDataFiles problemDataFiles2 = new ProblemDataFiles(problem);
			problemDataFiles2.setJudgesDataFile(new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeDataFile.input"));
			problemDataFiles2.setJudgesAnswerFile(new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeAnswerFile.output"));
			iInternalContest.addProblem(problem, problemDataFiles2);
			iInternalController.addNewProblem(problem, problemDataFiles2);
		}else {
			System.out.println("获取ProblemDataFiles数据："+iInternalContest.getProblemDataFile(problem).getJudgesDataFile().getName());
		}*/
		
		
		IFileViewer iFileViewer = execute_3.execute(true);
		
		System.out.println("getExecuteDirectoryName: "+execute_3.getExecuteDirectoryName());
		System.out.println("getValidationResults: "+execute_3.getValidationResults());
		System.out.println("getExecuteDirectoryNameSuffix: "+execute_3.getExecuteDirectoryNameSuffix());
		System.out.println("getAuthorEmailAddress: "+execute_3.getAuthorEmailAddress());
		System.out.println("getFailureReason: "+execute_3.getFailureReason());
		System.out.println("getPluginTitle: "+execute_3.getPluginTitle());
		System.out.println("getExecuteTimeMS: "+execute_3.getExecutionData().getExecuteTimeMS());
		System.out.println("isTestRunOnly: "+execute_3.isTestRunOnly());
		System.out.println("getValidationResults: "+execute_3.getExecutionData().getValidationResults());
		System.out.println("execute.getExecutionData().isRunTimeLimitExceeded(): "+execute_3.getExecutionData().isRunTimeLimitExceeded());
		System.out.println("getTeamsOutputFilenames().size(): "+execute_3.getTeamsOutputFilenames().size());
		System.out.println("execute.getTeamsOutputFilenames().get(0).trim(): "+execute_3.getTeamsOutputFilenames().get(0).trim());
		System.out.println("getValidatorOutputFilenames().size(): "+execute_3.getValidatorOutputFilenames().size());
		System.out.println(execute_3.getDirName(execute_3.getExecutionData().getCompileStdout()));
		
		String name = null;
		for(String tmp : execute_3.getTeamsOutputFilenames()) {
			String[] aStrings = tmp.split(File.separator);
			name = aStrings[aStrings.length-1];
		}
		//读取程序结果输出文件
		File outputFile = new File(execute_3.getDirName(execute_3.getExecutionData().getCompileStdout())+"/"+name);
		System.out.println("---------------");
		readFile(outputFile);
		System.out.println("---------------");
		
		//删除输出文件
		//execute.clearDirectory(execute.getDirName(execute.getExecutionData().getCompileStdout()));
		
		ExecutionData eData = execute_3.getExecutionData();
		if ((eData != null) && (!eData.isCompileSuccess()))
	    {
	      System.out.println("ssssssssssssss");
	    }
		if((eData != null) && (!eData.isExecuteSucess())) {
			System.out.println("yyyyyyyyyyyyy");
			System.out.println(eData.getExecuteProgramOutput() == null);
		}
		
		//judge输出文件在ProblemDataFiles
		System.out.println("iInternalContest.getProblemDataFiles().length: "+iInternalContest.getProblemDataFiles().length);
		System.out.println(iInternalContest.getProblemDataFiles()[0].getProblemId().equals(problem.getElementId()));
		
		System.out.println("iInternalContest.getRunResultFiles(runs[0]).length: "+iInternalContest.getRunResultFiles(runs[0]).length);
		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}
	

}
