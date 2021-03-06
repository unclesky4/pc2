package core;

import edu.csus.ecs.pc2.api.ContestTestFrame;
import edu.csus.ecs.pc2.api.IClarification;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.IJudgement;
import edu.csus.ecs.pc2.api.IRun;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.listener.ConnectionEvent;
import edu.csus.ecs.pc2.api.listener.ContestEvent;

/**
 * JUDGE角色的功能
 * @author unclesky4 02/09/2017
 *
 */
public class Judge extends ContestTestFrame{

	public static void main(String[] args) {
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		Judge judge = new Judge();
		
		
		try {
			iContest = serverConnection.login("judge1", "judge1");
			
			//获取run
			IRun testRun = null;
			System.out.println("获取所有的Run");
			for (IRun run : iContest.getRuns()) {
			    System.out.println("Run " + run.getNumber() + " from site " + run.getSiteNumber());
			    System.out.println("    submitted at " + run.getSubmissionTime() + " minutes by " + run.getTeam().getDisplayName());
			    System.out.println("    For problem " + run.getProblem().getName());
			    System.out.println("    Written in " + run.getLanguage().getName());
			    
			    //judgement的状态
			    /*Yes
			    No - Compilation Error
			    No - Run-time Error
			    No - Time Limit Exceeded
			    No - Wrong Answer
			    No - Excessive Output
			    No - Output Format Error
			    No - Other - Contact Staff*/
			    for(IJudgement judgement : iContest.getJudgements()){
			    	System.out.println(judgement.getName());
			    }
			 
			    testRun = run;
			    System.out.println(iContest.getRunState(testRun));
			    
			    if (testRun.isFinalJudged()) {
			        System.out.println("    Judgement: " + testRun.getJudgementName());
			        System.out.println();
			    } else {
			        System.out.println("    Judgement: not judged yet ");
			    }
			    System.out.println("-------------------");
			    
			}
			
			//查看Clarifications
			IClarification[] iClarifications = iContest.getClarifications();
			System.out.println("查看Clarifications");
			for(IClarification iClarification : iClarifications){
				System.out.println(iClarification.getTeam().getLoginName());
				System.out.println(iClarification.getAnswer());
				System.out.println(iClarification.isAnswered());
				System.out.println(iClarification.getQuestion());
				System.out.println();
			}
			System.out.println("--------------");
			
			
			System.out.println("打印ConnectionEvent.Action");
			for (ConnectionEvent.Action c : ConnectionEvent.Action.values())
			    System.out.println(c);
			System.out.println("--------------------");
			/**
			 * result:  DROPPED
			 */
			
			System.out.println("打印ContestEvent.EventType");
			for (ContestEvent.EventType c : ContestEvent.EventType.values())
			    System.out.println(c);
			System.out.println("--------------------");
			/**
			 *  result:
			    CLIENT
				PROBLEM
				LANGUAGE
				CONTEST_CLOCK
				CONTEST_TITLE
				JUDGEMENT
				GROUP
				SITE
			 */
			
			
			serverConnection.logoff();
			 
		} catch (LoginFailureException e) {
			e.printStackTrace();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
		
	}

}
