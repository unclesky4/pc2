package core;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.ClarificationListenerUtils;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.IJudgement;
import edu.csus.ecs.pc2.api.ILanguage;
import edu.csus.ecs.pc2.api.IProblem;
import edu.csus.ecs.pc2.api.IRun;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.implementation.JudgementImplementation;
import edu.csus.ecs.pc2.api.implementation.LanguageImplementation;
import edu.csus.ecs.pc2.api.implementation.ProblemImplementation;
import edu.csus.ecs.pc2.core.model.Judgement;
import edu.csus.ecs.pc2.core.model.Language;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.Run;
import edu.csus.ecs.pc2.core.model.RunResultFiles;
import edu.csus.ecs.pc2.core.model.SerializedFile;
import edu.csus.ecs.pc2.core.security.FileSecurityException;

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
		
		ProblemImplementation problemImplementation = (ProblemImplementation) problem;
		Problem submittedProblem = serverConnection.getIInternalContest().getProblem(problemImplementation.getElementId());
		
/*		ProblemsJSON problemsJSON = new ProblemsJSON();
		try {
			System.out.println("打印所有的问题");
			System.out.println(problemsJSON.createJSON(serverConnection.getIInternalContest()));
		} catch (IllegalContestState e1) {
			e1.printStackTrace();
		}*/
		
		//获取ILanguage
		ILanguage language = null;
		ILanguage[] languages = iContest.getLanguages();
		for(ILanguage iLanguage : languages) {
			System.out.println(iLanguage.getTitle());
			if(iLanguage.getTitle().equals("Java")) {
				language = iLanguage;
			}
		}
		
		LanguageImplementation languageImplementation = (LanguageImplementation) language;
		Language submittedLanguage = serverConnection.getIInternalContest().getLanguage(languageImplementation.getElementId());
		
		Logger logger = LoggerFactory.getLogger(Team.class);
		logger.info("language info : "+language.getTitle()+"   "+language.getName());
		
		//提交答案--run  (前提：开启考试 startContestClock)
		/*public void submitRun(IProblem problem,
					         ILanguage language,
					         String mainFileName,
					         String[] additionalFileNames,
					         long overrideSubmissionTimeMS,
					         long overrideRunId)
					           throws Exception
       */
		String mainFileName = "/home/uncle/Desktop/pc2_data/solve.java";
		String[] additionalFileNames = new String[0];
		SerializedFile[] otherFiles = new SerializedFile[0];
		try {
			System.out.println("开始提交Run");
		//	serverConnection.getIInternalController().submitRun(submittedProblem, submittedLanguage, mainFileName, otherFiles);
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
		
		//获取IStanding ---An IStanding object contains information about the standing (ranking) of one particular team in the contest. 
/*		System.out.println("获取IStanding");
		IStanding[] iStanding = iContest.getStandings();
		if(iStanding[0] != null) {
			System.out.println(iStanding[0].getRank());
			System.out.println(iStanding[0].getNumProblemsSolved());
		}*/
		
		//获取IStanding --> IProblemDetails
/*		System.out.println("获取IStanding --> IProblemDetails");
		IProblemDetails[] iProblemDetails = iStanding[0].getProblemDetails();
		System.out.println(iProblemDetails[0].getAttempts());
		System.out.println(iProblemDetails[0].isSolved());*/
		
		Problem[] problems = serverConnection.getIInternalContest().getProblems();
		System.out.println(problems[0].getAnswerFileName());
		
		//断开连接
		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
