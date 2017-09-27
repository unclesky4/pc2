package core;

import core.function.common.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.implementation.Contest;
import edu.csus.ecs.pc2.core.model.Judgement;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.ProblemDataFiles;
import edu.csus.ecs.pc2.core.model.Run;

/**
 * 测试获取程序运行时间，是否达到题目要求
 * @author uncle
 *
 */
public class Administrator1 {

	public static void main(String[] args) {
		ServerConnection serverConnection = new ServerConnection();
		Contest contest = null;
		
		try {
			contest = (Contest) serverConnection.login("administrator1", "administrator1");
		} catch (LoginFailureException e) {
			e.printStackTrace();
		}

		//获取Run
		System.out.println("-------------------------------------");
		Run[] runs = serverConnection.getIInternalContest().getRuns();
		System.out.println(runs.length);
		Problem problem = serverConnection.getIInternalContest().getProblem(runs[0].getProblemId());
		System.out.println("获取提交Run的Team名称:"+runs[0].getSubmitter().getName());

		System.out.println("runs[0].isJudged():"+runs[0].getSubmitter());
		System.out.println("runs[0].isJudged():"+runs[0].isJudged());
		System.out.println("runs[0].isSolved():"+runs[0].isSolved());
		System.out.println("runs[0].getJudgedMinutes():"+runs[0].getJudgedMinutes());
		System.out.println("runs[0].getDate():"+runs[0].getDate());
		System.out.println("runs[0].getElapsedMS():"+runs[0].getElapsedMS());
		System.out.println("runs[0].getCreateDate():"+runs[0].getCreateDate());
		System.out.println("runs[0].getSystemOS():"+runs[0].getSystemOS());
		System.out.println("runs[0].getOriginalElapsedMS():"+runs[0].getOriginalElapsedMS());
		System.out.println("runs[0].getOverRideElapsedTimeMS():"+runs[0].getOverRideElapsedTimeMS());
		System.out.println("runs[0].getSubmitter().getClientNumber():"+runs[0].getSubmitter().getClientNumber());
		System.out.println(runs[0].getRunTestCases() == null);
//		System.out.println("runs[0].getRunTestCases()[0].getElapsedMS()():"+runs[0].getRunTestCases()[0].getElapsedMS());
//		System.out.println("runs[0].getRunTestCases()[0].getTestNumber()():"+runs[0].getRunTestCases()[0].getTestNumber());
//		System.out.println("runs[0].getRunTestCases()[0].getDate()():"+runs[0].getRunTestCases()[0].getDate());
//		System.out.println("runs[0].getStatus():"+runs[0].getStatus());
		
		System.out.println("-------------------------------------");
		System.out.println("runs[0].getStatus().toString():"+runs[0].getStatus().toString());
		System.out.println("runs[0].getJudgementRecord().getExecuteMS():"+runs[0].getJudgementRecord().getExecuteMS());
		System.out.println("runs[0].getJudgementRecord().getHowLongToJudgeInSeconds():"+runs[0].getJudgementRecord().getHowLongToJudgeInSeconds());
		System.out.println("runs[0].getJudgementRecord().getJudgedSeconds():"+runs[0].getJudgementRecord().getJudgedSeconds());
		System.out.println("runs[0].getJudgementRecord().isSendToTeam():"+runs[0].getJudgementRecord().isSendToTeam());
		System.out.println("runs[0].getJudgementRecord().getWhenJudgedTime():"+runs[0].getJudgementRecord().getWhenJudgedTime());
		System.out.println("runs[0].getJudgementRecord().getValidatorResultString():"+runs[0].getJudgementRecord().getValidatorResultString());
		System.out.println("runs[0].getJudgementRecord().isPreliminaryJudgement():"+runs[0].getJudgementRecord().isPreliminaryJudgement());
//		System.out.println("runs[0].getJudgementruns[0].getRunTestCases()[0].getElapsedMS():"+runs[0].getRunTestCases()[0].getElapsedMS());
//		System.out.println("runs[0].getJudgementruns[0].getRunTestCases()[0].getDate():"+runs[0].getRunTestCases()[0].getDate());
//		System.out.println("runs[0].getJudgementruns[0].getRunTestCases()[0].getTestNumber():"+runs[0].getRunTestCases()[0].getTestNumber());
		System.out.println();
		System.out.println("runs[0].getRunTestCases().length:"+runs[0].getRunTestCases().length);
		System.out.println("runs[1].getRunTestCases().length:"+runs[1].getRunTestCases().length);
		System.out.println("runs[2].getRunTestCases().length:"+runs[2].getRunTestCases().length);
		System.out.println("runs[0].getAllJudgementRecords().length:"+runs[0].getAllJudgementRecords().length);
		System.out.println("runs[1].getAllJudgementRecords().length:"+runs[1].getAllJudgementRecords().length);
		System.out.println("runs[2].getAllJudgementRecords().length:"+runs[2].getAllJudgementRecords().length);
		
		
		Problem problem1 = serverConnection.getIInternalContest().getProblem(runs[1].getProblemId());
		System.out.println("该Run的Problem的题目："+problem1.getDisplayName());
		
		System.out.println("runs[1].getElapsedMS:"+runs[1].getElapsedMS());
		System.out.println("runs[2].getElapsedMS:"+runs[2].getElapsedMS());
//		System.out.println("runs[1].getRunTestCases().getElapsedMS():"+runs[1].getRunTestCases()[0].getElapsedMS());
		System.out.println("runs[1].getJudgementRecord().getExecuteMS():"+runs[1].getJudgementRecord().getExecuteMS());
		System.out.println("runs[1].getJudgementRecord().getHowLongToJudgeInSeconds():"+runs[1].getJudgementRecord().getHowLongToJudgeInSeconds());
		System.out.println("runs[1].getJudgementRecord().getJudgedSeconds():"+runs[1].getJudgementRecord().getJudgedSeconds());
		System.out.println("runs[1].getJudgementRecord().isSendToTeam():"+runs[1].getJudgementRecord().isSendToTeam());
		System.out.println("runs[1].getJudgementRecord().getWhenJudgedTime():"+runs[1].getJudgementRecord().getWhenJudgedTime());
		System.out.println("runs[1].getJudgementRecord().getValidatorResultString():"+runs[1].getJudgementRecord().getValidatorResultString());
		System.out.println("runs[1].getJudgementRecord().isPreliminaryJudgement():"+runs[1].getJudgementRecord().isPreliminaryJudgement());
//		System.out.println("runs[1].getJudgementruns[0].getRunTestCases()[0].getElapsedMS():"+runs[1].getRunTestCases()[0].getElapsedMS());
//		System.out.println("runs[1].getJudgementruns[0].getRunTestCases()[0].getDate():"+runs[1].getRunTestCases()[0].getDate());
//		System.out.println("runs[1].getJudgementruns[0].getRunTestCases()[0].getTestNumber():"+runs[1].getRunTestCases()[0].getTestNumber());
		System.out.println("-------------------------------------");
		System.out.println("runs[2].getJudgementRecord().getExecuteMS():"+runs[2].getJudgementRecord().getExecuteMS());
		System.out.println("runs[2].getJudgementRecord().getHowLongToJudgeInSeconds():"+runs[2].getJudgementRecord().getHowLongToJudgeInSeconds());
		System.out.println("runs[2].getJudgementRecord().getJudgedSeconds():"+runs[2].getJudgementRecord().getJudgedSeconds());
		System.out.println("runs[2].getJudgementRecord().isSendToTeam():"+runs[2].getJudgementRecord().isSendToTeam());
		System.out.println("runs[2].getJudgementRecord().getWhenJudgedTime():"+runs[2].getJudgementRecord().getWhenJudgedTime());
		System.out.println("runs[2].getJudgementRecord().getValidatorResultString():"+runs[2].getJudgementRecord().getValidatorResultString());
		System.out.println("runs[2].getJudgementRecord().isPreliminaryJudgement():"+runs[2].getJudgementRecord().isPreliminaryJudgement());
		
		System.out.println("-----------------------");
		//Run -->Problem --> ProblemDataFile
		Problem problem2 = serverConnection.getIInternalContest().getProblem(runs[0].getProblemId());
		ProblemDataFiles problemDataFiles = serverConnection.getIInternalContest().getProblemDataFile(problem2);
		
		int size = problemDataFiles.getJudgesDataFiles().length;
		String[] teamOutputNames = new String[size];
		System.out.println(size);
		System.out.println(problemDataFiles.getJudgesDataFile().getAbsolutePath());
		
		
		System.out.println("-------------------------------------------");
		
		Judgement judgement = serverConnection.getIInternalContest().getJudgement(runs[0].getElementId());
		
		if(judgement != null) {
			System.out.println(judgement.getDisplayName());
		}else {
			System.out.println("judgement is null");
		}

		try {
			serverConnection.logoff();
		} catch (NotLoggedInException e) {
			System.out.println("Unable to execute API method");
			e.printStackTrace();
		}
	}

}
