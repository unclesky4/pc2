package core;
import java.io.File;
import java.nio.channels.FileLock;
import java.util.Date;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.ClarificationListenerUtils;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.IJudgement;
import edu.csus.ecs.pc2.api.ILanguage;
import edu.csus.ecs.pc2.api.IProblem;
import edu.csus.ecs.pc2.api.IProblemDetails;
import edu.csus.ecs.pc2.api.IStanding;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;

/**
 * TEAM角色的功能
 * @author uncle 02/09/2017
 *
 */
public class Team extends ClarificationListenerUtils{

	public static void main(String[] args) {
		//连接服务器
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		//登陆--team1
		try {
			iContest = serverConnection.login("team1", "team1");
		} catch (LoginFailureException e) {
			e.printStackTrace();
		}
		//获取问题
		IProblem[] iProblems = iContest.getProblems();
		IProblem problem = iProblems[0];
		System.out.println("获取题目");
		for(IProblem iProblem : iProblems){
			System.out.println(iProblem.getJudgesAnswerFileContents()); //null
			System.out.println(iProblem.getJudgesAnswerFileName());
			System.out.println(iProblem.getJudgesDataFileName());
			System.out.println(iProblem.getName());
			System.out.println(iProblem.getShortName());
			System.out.println(iProblem.getValidatorCommandLine()); //null
			System.out.println("----------------");
		}
		
		//获取ILanguage
		ILanguage language = null;
		ILanguage[] languages = iContest.getLanguages();
		for(ILanguage iLanguage : languages) {
			System.out.println(iLanguage.getTitle());
			if(iLanguage.getTitle().equals("Java")) {
				language = iLanguage;
			}
		}
		
		//提交答案--run  (前提：开启考试 startContestClock)
		/*public void submitRun(IProblem problem,
					         ILanguage language,
					         String mainFileName,
					         String[] additionalFileNames,
					         long overrideSubmissionTimeMS,
					         long overrideRunId)
					           throws Exception
       */
		String mainFileName = "/home/uncle/Desktop/pc2_data/solve.class";
		String[] additionalFileNames = new String[0];
		try {
			System.out.println("开始提交Run");
			serverConnection.submitRun(problem, language, mainFileName, additionalFileNames, (Long)new Date().getTime(), (Long)new Date().getTime());
			System.out.println("已提交Run");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//team提交Clarification --> clarificationAdded（） --> getClarifications() -->  	clarificationAnswered -->  	clarificationUpdated
		
		//提交Clarification (前提：开启考试  startContestClock)
/*		if(iProblems[0] != null){
			try {
				serverConnection.submitClarification(iProblems[0], "just test");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
		//查看judement
		System.out.println("查看judement");
		for (IJudgement judgement : iContest.getJudgements()) {
		     System.out.println(judgement.getName());
		}
		
		//获取IStanding ---An IStanding object contains information about the standing (ranking) of one particular team in the contest. 
		System.out.println("获取IStanding");
		IStanding[] iStanding = iContest.getStandings();
		if(iStanding[0] != null) {
			System.out.println(iStanding[0].getRank());
			System.out.println(iStanding[0].getNumProblemsSolved());
		}
		
		//获取IStanding --> IProblemDetails
		System.out.println("获取IStanding --> IProblemDetails");
		IProblemDetails[] iProblemDetails = iStanding[0].getProblemDetails();
		System.out.println(iProblemDetails[0].getAttempts());
		System.out.println(iProblemDetails[0].isSolved());
		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
