package edu.csus.ecs.pc2.core.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.core.IInternalController;
import edu.csus.ecs.pc2.core.model.IInternalContest;
import edu.csus.ecs.pc2.core.model.Run;
import edu.csus.ecs.pc2.core.model.RunFiles;
import edu.csus.ecs.pc2.core.model.SerializedFile;

/**
 * PC^2的Executable类 -- 用于实际生产环境
 * @author uncle
 *
 */
public class ExecuteRun{
	
	Executable executable = null;
	
	//编译运行所在目录的绝对路径
	String executeDirectoryName = null;
	
	IInternalContest iInternalContest = null;
	
	IInternalController iInternalController = null;
	
	public String getExecuteDirectoryName() {
		return this.executeDirectoryName;
	}
	
	//获取已运行的Executable对象
	public Executable getRanExecute() {
		return this.executable;
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
	
	/**
	 * 获取程序的输出文件
	 * @return
	 */
	public File getOutputFile() {
		String name = null;
		for(String tmp : this.executable.getTeamsOutputFilenames()) {
			String[] aStrings = tmp.split(File.separator);
			name = aStrings[aStrings.length-1];
		}
		File runOutputFile = new File(this.executable.getDirName(this.executable.getExecutionData().getCompileStdout())+"/"+name);
		return runOutputFile;
	}
	
	/**
	 * 获取程序的编译错误输出文件
	 * @return
	 */
	public File getErrorFile() {
		File file = null;
		if(this.executable.getExecutionData() != null && this.executable.getExecutionData().getCompileStderr() != null) {
			file = new File(this.executable.getExecutionData().getCompileStderr().getAbsolutePath());
		}
		return file;
	}

	/**
	 * 构造方法
	 * @param serverConnection  - JUDGE角色登陆后的ServerConnection对象
	 * @param run- TEAM已提交的Run
	 * @param mainProgramFile  - 主程序文件名（含路径）
	 */
	public ExecuteRun(ServerConnection serverConnection, Run run, String mainProgramFile) {
		
		//判断登陆的是否为JUDGE角色
		if(!serverConnection.isLoggedIn() || !serverConnection.isValidAccountTypeName("JUDGE")) {
			System.out.println("登陆的用户角色不是JUDGE角色类型！");
			return ;
		}
		
		iInternalContest = serverConnection.getIInternalContest();
		iInternalController = serverConnection.getIInternalController();
		
		SerializedFile[] serializedFiles = new SerializedFile[0];
		SerializedFile mainFile =  new SerializedFile(mainProgramFile);
		RunFiles runFiles = new RunFiles(run, mainFile, serializedFiles);
		this.executable = new Executable(iInternalContest, iInternalController, run, runFiles);
		
		//为编译运行的目录添加后缀，确保每次运行不在同一目录下（因为程序输出文件名相同，不能更改）
		this.executable.setExecuteDirectoryNameSuffix(UUID.randomUUID().toString().replace("-", ""));
		executable.setUsingGUI(false);
		this.executable.execute();
		
		SerializedFile tmp = null;
		tmp = executable.getExecutionData().getCompileStdout();
		if(tmp == null) {
			this.executeDirectoryName = "executesite1administrator1"+this.executable.getExecuteDirectoryNameSuffix();
		}else {
			this.executeDirectoryName = this.executable.getDirName(tmp);
		}
	}
}
