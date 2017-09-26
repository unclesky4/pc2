package edu.csus.ecs.pc2.core.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ILanguage;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.implementation.LanguageImplementation;
import edu.csus.ecs.pc2.core.IInternalController;
import edu.csus.ecs.pc2.core.model.IInternalContest;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.ProblemDataFiles;
import edu.csus.ecs.pc2.core.model.Run;
import edu.csus.ecs.pc2.core.model.RunFiles;
import edu.csus.ecs.pc2.core.model.SerializedFile;
import edu.csus.ecs.pc2.core.security.FileSecurityException;
import edu.csus.ecs.pc2.ui.IFileViewer;

/**
 * 测试Executable类-----c语言
 * @author uncle
 *
 */
public class Execute_1 extends Executable {

	public Execute_1(IInternalContest inContest, IInternalController inController, Run run, RunFiles runFiles) {
		super(inContest, inController, run, runFiles);
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, FileSecurityException {
		//连接服务器
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		//登陆--team1
		try {
			iContest = serverConnection.login("team1", "team1");
			iContest = serverConnection.getContest();
		} catch (LoginFailureException e) {
			e.printStackTrace();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
		
		IInternalController iInternalController = serverConnection.getIInternalController();
		IInternalContest iInternalContest = serverConnection.getIInternalContest();
		//获取Language
		ILanguage[] languages = iContest.getLanguages();
		Language language = null;
		for(ILanguage language2 : languages) {
			System.out.println(language2.getTitle()+"   "+language2.getName());
			if(language2.getTitle().trim().equals("GNU C")) {
				LanguageImplementation languageImplementation = (LanguageImplementation) language2;
				language = iInternalContest.getLanguage(languageImplementation.getElementId());
			}
		}
		
		//获取Problem
		Problem problemId = iInternalContest.getProblems()[0];
		if(iInternalContest.getClientId() == null || language == null || problemId == null) {
			System.out.println(iInternalContest.getClientId() == null);
			System.out.println(language == null);
			System.out.println(problemId == null);
		}
		
		//实例化一个Run
		Run run = new Run(iInternalContest.getClientId(), language, problemId);
		System.out.println("run.getNumber():  "+run.getNumber());

		//序列化主程序文件，附加的文件
		SerializedFile mainFile = new SerializedFile("/home/uncle/Desktop/pc2_data/solve.c");
		SerializedFile[] serializedFiles = new SerializedFile[2];
		serializedFiles[0] = new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeDataFile.input");
		serializedFiles[1] = new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeAnswerFile.output");
		
		//实例化RunFiles
		RunFiles runFiles = new RunFiles(run, mainFile, serializedFiles);
		
		Run newRun = iInternalContest.acceptRun(run, runFiles);
		
		//实例化一个Execute
		Execute_1 execute = new Execute_1(serverConnection.getIInternalContest(), serverConnection.getIInternalController(), newRun, runFiles);

//		System.out.println("execute.compileProgram():  "+execute.compileProgram());
//		System.out.println("execute.executeProgram(0):  "+execute.executeProgram(0));
		
		execute.getExecutionData().setRunTimeLimitExceeded(true);
		
		System.out.println("execute.getExecutionData().isRunTimeLimitExceeded(): "+execute.getExecutionData().isRunTimeLimitExceeded());
		
		//判断IInternalContest是否存在Problem对应的ProblemDataFiles
		if(iInternalContest.getProblemDataFile(problemId) == null) {
			System.out.println("添加ProblemDataFiles");
			ProblemDataFiles problemDataFiles2 = new ProblemDataFiles(problemId);
			problemDataFiles2.setJudgesDataFile(new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeDataFile.input"));
			problemDataFiles2.setJudgesAnswerFile(new SerializedFile("/home/uncle/Desktop/pc2_data/JudgeAnswerFile.output"));
			iInternalContest.addProblem(problemId, problemDataFiles2);
			iInternalController.addNewProblem(problemId, problemDataFiles2);
		}else {
			System.out.println("获取ProblemDataFiles数据："+iInternalContest.getProblemDataFile(problemId).getJudgesDataFile().getName());
		}
		
		
		IFileViewer iFileViewer = execute.execute();
		
		System.out.println("getExecuteDirectoryName: "+execute.getExecuteDirectoryName());
		System.out.println("getValidationResults: "+execute.getValidationResults());
		System.out.println("getExecuteDirectoryNameSuffix: "+execute.getExecuteDirectoryNameSuffix());
		System.out.println("getAuthorEmailAddress: "+execute.getAuthorEmailAddress());
		System.out.println("getFailureReason: "+execute.getFailureReason());
		System.out.println("getPluginTitle: "+execute.getPluginTitle());
		System.out.println("getExecuteTimeMS: "+execute.getExecutionData().getExecuteTimeMS());
		System.out.println("isTestRunOnly: "+execute.isTestRunOnly());
		System.out.println("getValidationResults: "+execute.getExecutionData().getValidationResults());
		System.out.println("execute.getExecutionData().isRunTimeLimitExceeded(): "+execute.getExecutionData().isRunTimeLimitExceeded());
		System.out.println("getTeamsOutputFilenames().size(): "+execute.getTeamsOutputFilenames().size());
		System.out.println("execute.getTeamsOutputFilenames().get(0).trim(): "+execute.getTeamsOutputFilenames().get(0).trim());
		System.out.println("getValidatorOutputFilenames().size(): "+execute.getValidatorOutputFilenames().size());
		System.out.println(execute.getDirName(execute.getExecutionData().getCompileStdout()));
		
		String name = null;
		for(String tmp : execute.getTeamsOutputFilenames()) {
			System.out.println(tmp);
			String[] aStrings = tmp.split(File.separator);
			name = aStrings[aStrings.length-1];
		}
		//读取程序结果输出文件
		File outputFile = new File(execute.getDirName(execute.getExecutionData().getCompileStdout())+"/"+name);
		System.out.println("---------------");
		execute.readFile(outputFile);
		System.out.println("---------------");
		if(execute.getExecutionData() != null) {
			System.out.println("problemId.getTimeOutInSeconds():  "+problemId.getTimeOutInSeconds());
			System.out.println("execute.getExecutionData().getvalidateTimeMS():  "+execute.getExecutionData().getvalidateTimeMS());
			System.out.println("execute.getExecutionData().getCompileTimeMS():  "+execute.getExecutionData().getCompileTimeMS());
			System.out.println("execute.getExecutionData().getExecuteTimeMS():  "+execute.getExecutionData().getExecuteTimeMS());
			
			System.out.println("---------------------------------");
			System.out.println("execute.execute.getTeamsOutputFilenames().size():    "+execute.getTeamsOutputFilenames().size());
			System.out.println("execute.execute.getTeamsOutputFilenames().size():  "+execute.getValidatorErrFilenames().size());
			System.out.println("execute.getValidatorOutputFilenames().size():  "+execute.getValidatorOutputFilenames().size());
			System.out.println("---------------------------------");
		}
		
		//删除输出文件
		//execute.clearDirectory(execute.getDirName(execute.getExecutionData().getCompileStdout()));
		
		ExecutionData eData = execute.getExecutionData();
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
		
		System.out.println("iInternalContest.getRunResultFiles(runs[0]).length: "+iInternalContest.getRunResultFiles(newRun).length);
		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}

	}
	
	public String readFile(File file) {
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

}
