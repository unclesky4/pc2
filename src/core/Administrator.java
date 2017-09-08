package core;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import edu.csus.ecs.pc2.api.APIConstants;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.implementation.Contest;

public class Administrator {

	public static void main(String[] args) {
		ServerConnection serverConnection = new ServerConnection();
		Contest contest = null;
		try {
			//登陆 -- admin
			contest = (Contest) serverConnection.login("administrator1", "administrator1");
			//添加帐号
	//		serverConnection.addAccount("JUDGE", "judge1", "judge1");

			//添加编程语言
			/*serverConnection.addLanguage("Java", "javac {:mainfile}", "java {:basename}", false, "{:basename}.class");
			serverConnection.addLanguage("GNU C", "gcc -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", false, "{:basename}.exe");
			serverConnection.addLanguage("GNU C++", "g++ -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", true, "{:basename}.exe");
			serverConnection.addLanguage("PHP", "php -l {:mainfile}", "php {:mainfile}", false, "");
			serverConnection.addLanguage("Python", "python -m py_compile {:mainfile}", "python {:mainfile}", false, "");*/
			
			/*serverConnection.addLanguage("Java");
			serverConnection.addLanguage("GNU C++ (Unix / Windows)");
			serverConnection.addLanguage("GNU C (Unix / Windows)");
			serverConnection.addLanguage("PHP");
			serverConnection.addLanguage("Microsoft C++");
			serverConnection.addLanguage("Python");*/
			
			
			//获取问题的PropertyNames  
			String names[] = serverConnection.getProblemPropertyNames();
			for(String tmp : names){
				System.out.println(tmp);
			}
			
			//添加题目
			Properties problemProperties = new Properties();
			File dataFile = new File(Administrator.class.getResource("/JudgeDataFile.input").toURI());
			File answerFile = new File(Administrator.class.getResource("/JudgeAnswerFile.output").toURI());
			problemProperties.setProperty("JUDGING_TYPE", "JUDGING_TYPE");
			problemProperties.setProperty("VALIDATOR_PROGRAM", "VALIDATOR_PROGRAM");
			problemProperties.setProperty("VALIDATOR_COMMAND_LINE", "VALIDATOR_COMMAND_LINE");
//			serverConnection.addProblem("输入两个整数，求他们的和", "两数求和", dataFile, answerFile, true, problemProperties);
			
			//获取服务器，contest的信息
			System.out.println("获取服务器，contest的信息");
			System.out.println(contest.getContestTitle());
			System.out.println(contest.getTitle());
			System.out.println(contest.getSiteName());
			
			
			//设置考试时间
			serverConnection.setContestTimes((long)1200, (long)0, (long)1200);
			
			//开启考试
			serverConnection.startContestClock();
			
			//判断是否开始考试
			System.out.println("---判断是否开始考试---------");
			System.out.println(contest.getContestClock().isContestClockRunning());
			System.out.println(contest.isCCSTestMode());
			
			System.out.println("--------获取ContestClock信息--------");
			System.out.println(contest.getContestClock().getContestLengthSecs());
			System.out.println(contest.getContestClock().getElapsedSecs());
			System.out.println(contest.getContestClock().getRemainingSecs());
			
			//停止考试
			serverConnection.stopContestClock();
			
			
			//获取绝对路径
//			System.out.println(Administrator.class.getResource("/JudgeDataFile.input").toURI());
//			System.out.println(Administrator.class.getClassLoader().getResource("")); 
//			System.out.println(ClassLoader.getSystemResource("")); 
//			System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
			
			if(dataFile.exists() && !dataFile.isDirectory()){
				BufferedReader br=new BufferedReader(new FileReader(dataFile));
		        String temp=null;
		        StringBuffer sb=new StringBuffer();
		        temp=br.readLine();
		        while(temp!=null){
		            System.out.println(temp);
		            temp=br.readLine();
		        }
			 }else{
				 System.out.println("not file");
			 }
			
			

		} catch (LoginFailureException e) {
			System.out.println("Could not login because " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("APIConstants的常量值：");
		System.out.println("JUDGING_TYPE: "+APIConstants.JUDGING_TYPE);
		System.out.println("VALIDATOR_COMMAND_LINE: "+APIConstants.VALIDATOR_COMMAND_LINE);
		System.out.println("MANUAL_JUDGING_ONLY: "+APIConstants.MANUAL_JUDGING_ONLY);
		System.out.println("COMPUTER_JUDGING_ONLY: "+APIConstants.COMPUTER_JUDGING_ONLY);
		System.out.println("VALIDATOR_PROGRAM: "+APIConstants.VALIDATOR_PROGRAM);
		System.out.println("COMPUTER_AND_MANUAL_JUDGING: "+APIConstants.COMPUTER_AND_MANUAL_JUDGING);
		System.out.println("PC2_VALIDATOR_PROGRAM: "+APIConstants.DEFAULT_INTERNATIONAL_VALIDATOR_COMMAND);
		
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}

	}

}
