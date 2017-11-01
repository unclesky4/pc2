package edu.csus.ecs.pc2.core.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

/**
 * 测试修改程序输出文件路径----成功
 * @author unclesky4 04/10/2017
 *
 */
public class Execute_2 extends Executable {
	

	public Execute_2(IInternalContest inContest, IInternalController inController, Run run, RunFiles runFiles) {
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
	
	public static void main(String[] args) {
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
		
		SerializedFile mainFile = new SerializedFile("/home/uncle/Desktop/pc2_data/solve.java");
		SerializedFile[] serializedFiles = new SerializedFile[0];
		
		RunFiles runFiles = new RunFiles(run, mainFile, serializedFiles);
		
		Execute_2 execute_2 = new Execute_2(iInternalContest, iInternalController, run, runFiles);
		execute_2.setExecuteDirectoryNameSuffix(UUID.randomUUID().toString().replaceAll("-", ""));
		execute_2.execute(true);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(execute_2.getExecutionData().isCompileSuccess());
		System.out.println(execute_2.getExecutionData().isExecuteSucess());
		System.out.println(execute_2.getTeamsOutputFilenames().size());
//		String info = readFile(new File(execute_2.getTeamsOutputFilenames().get(0)));
//		System.out.println(info);
		
		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
