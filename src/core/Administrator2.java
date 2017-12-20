package core;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.implementation.Contest;
import edu.csus.ecs.pc2.core.model.Account;
import edu.csus.ecs.pc2.core.security.Permission;
/**
 * 测试添加编程语言，添加帐号，添加题目，设置Contest时间及开始与结束的状态
 * @author uncle
 *
 */
public class Administrator2{

	public static void main(String[] args) throws InterruptedException {
		ServerConnection serverConnection = new ServerConnection();
		Contest contest = null;

		try {
			contest = (Contest) serverConnection.login("administrator1", "administrator1");
			//addLanguage(String autoFillTitleName)  autoFillTitleName的列表
			System.out.println("addLanguage(String autoFillTitleName)  autoFillTitleName的列表");
			System.out.println(serverConnection.getAutoFillLanguageList().length);
			for (String aString : serverConnection.getAutoFillLanguageList()) {
				System.out.println(aString);
			}
		} catch (LoginFailureException e1) {
			e1.printStackTrace();
		}
		
		for(Account account : serverConnection.getIInternalContest().getAccounts()) {
			System.out.println(account.getTeamName());
		}
		
		
		
		//添加帐号
		//	serverConnection.addAccount("judge", "judge2", "judge2");
					
			
			//serverConnection.getIInternalContest().getAccounts(Type.ALL);
/*			for(Account accounts : serverConnection.getIInternalContest().getAccounts()) {
				System.out.println(accounts.getDisplayName());
			}*/
		
/*			System.out.println("---------------------------");
			for(IRun runs : contest.getRuns()) {
				System.out.println(runs.getSubmissionTime());
				System.out.println(runs.getJudge().getLoginName());
				System.out.println(runs.getLanguage().getTitle());
				System.out.println(runs.getRunJudgements()[0].getJudgement().getName());
				System.out.println(runs.getRunJudgements()[0].getJudgement());
			}
			System.out.println("------------------------");*/

		//添加编程语言
		/*serverConnection.addLanguage("Java", "javac {:mainfile}", "java {:basename}", true, "{:basename}.class");
		serverConnection.addLanguage("GNU C", "gcc -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", true, "{:basename}.exe");
		serverConnection.addLanguage("GNU C++", "g++ -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", true, "{:basename}.exe");
		serverConnection.addLanguage("PHP", "php -l {:mainfile}", "php {:mainfile}", true, "");
		serverConnection.addLanguage("Python", "python -m py_compile {:mainfile}", "python {:mainfile}", true, "");
		*/
		
		/*serverConnection.addLanguage("Java");
		serverConnection.addLanguage("GNU C++ (Unix / Windows)");
		serverConnection.addLanguage("GNU C (Unix / Windows)");
		serverConnection.addLanguage("PHP");
		serverConnection.addLanguage("Microsoft C++");
		serverConnection.addLanguage("Python");*/
		
/*		System.out.println("------------");
		System.out.println(serverConnection.isValidAutoFillLangauageName("GNU C++ (Unix / Windows)"));
		System.out.println(serverConnection.isValidAutoFillLangauageName("GNU C (Unix / Windows)"));
		System.out.println("是否为ADMINISTRATOR角色："+serverConnection.isValidAccountTypeName("ADMINISTRATOR"));*/
		
		//添加题目
		System.out.println(serverConnection.getIInternalContest().isAllowed(Permission.Type.ADD_PROBLEM));
		System.out.println(serverConnection.isValidAccountTypeName("ADMINISTRATOR"));
		
		Properties problemProperties = new Properties();
		File dataFile = null;
		File answerFile = null;
		try {
			dataFile = new File(Administrator2.class.getResource("/JudgeDataFile.input").toURI());
			answerFile = new File(Administrator2.class.getResource("/JudgeAnswerFile.output").toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		problemProperties.setProperty("JUDGING_TYPE", "COMPUTER_AND_MANUAL_JUDGING");
		problemProperties.setProperty("VALIDATOR_PROGRAM", "pc2.jar edu.csus.ecs.pc2.validator.Validator");
		problemProperties.setProperty("VALIDATOR_COMMAND_LINE", "DEFAULT_INTERNATIONAL_VALIDATOR_COMMAND"); 
		
//		serverConnection.addProblem("输入两个整数，求他们的和_14", "两数求和_14", dataFile, answerFile, false, problemProperties, 70);
		
		//当前线程暂停2秒，确保Problem提交到服务器前serverConnection不关闭
		TimeUnit.SECONDS.sleep(2);
		
		//查看Problem的个数
		System.out.println("查看Problem的个数:"+serverConnection.getIInternalContest().getProblems().length);
		
		//设置考试时间
		//serverConnection.setContestTimes((long)1200, (long)0, (long)1200);
		
		//开启考试
		//serverConnection.startContestClock();
		
		//判断是否开始考试
/*		System.out.println("---判断是否开始考试---------");
		System.out.println(contest.getContestClock().isContestClockRunning());
		System.out.println(contest.isCCSTestMode());
		
		System.out.println("--------获取ContestClock信息--------");
		System.out.println(contest.getContestClock().getContestLengthSecs());
		System.out.println(contest.getContestClock().getElapsedSecs());
		System.out.println(contest.getContestClock().getRemainingSecs());*/
		
		//停止考试
		//serverConnection.stopContestClock();
		
		
		//获取绝对路径
//		System.out.println(Administrator.class.getResource("/JudgeDataFile.input").toURI());
//		System.out.println(Administrator.class.getClassLoader().getResource("")); 
//		System.out.println(ClassLoader.getSystemResource("")); 
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
					
		/*if(dataFile.exists() && !dataFile.isDirectory()){
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
		 }*/
				
/*		System.out.println("APIConstants的常量值：");
		System.out.println("JUDGING_TYPE: "+APIConstants.JUDGING_TYPE);
		System.out.println("VALIDATOR_COMMAND_LINE: "+APIConstants.VALIDATOR_COMMAND_LINE);
		System.out.println("MANUAL_JUDGING_ONLY: "+APIConstants.MANUAL_JUDGING_ONLY);
		System.out.println("COMPUTER_JUDGING_ONLY: "+APIConstants.COMPUTER_JUDGING_ONLY);
		System.out.println("VALIDATOR_PROGRAM: "+APIConstants.VALIDATOR_PROGRAM);
		System.out.println("COMPUTER_AND_MANUAL_JUDGING: "+APIConstants.COMPUTER_AND_MANUAL_JUDGING);
		System.out.println("PC2_VALIDATOR_PROGRAM: "+APIConstants.DEFAULT_INTERNATIONAL_VALIDATOR_COMMAND);*/
				
				
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
