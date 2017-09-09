package core;

import edu.csus.ecs.pc2.api.ClarificationListenerUtils;
import edu.csus.ecs.pc2.api.ContestTestFrame;
import edu.csus.ecs.pc2.api.IClarification;
import edu.csus.ecs.pc2.api.IContest;
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
			for (IRun run : iContest.getRuns()) {
			    System.out.println("Run " + run.getNumber() + " from site " + run.getSiteNumber());
			    System.out.println("    submitted at " + run.getSubmissionTime() + " minutes by " + run.getTeam().getDisplayName());
			    System.out.println("    For problem " + run.getProblem().getName());
			    System.out.println("    Written in " + run.getLanguage().getName());
			 
			    testRun = run;
			    
			    if (run.isFinalJudged()) {
			        System.out.println("    Judgement: " + run.getJudgementName());
			    } else {
			        System.out.println("    Judgement: not judged yet ");
			    }
			    
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
			
			ClarificationListenerUtils clarificationListenerUtils = new ClarificationListenerUtils();
			ClarificationListener clarificationListener = clarificationListenerUtils.getClarificationListener();
			//iContest.getClarifications()  从IContest中获取用户提交的IClarification，judge角色回复后如何返回信息给team？？？？
			System.out.println("Clarification的属性：");
			for(IClarification clarification : iContest.getClarifications()) {
				System.out.println(clarification.getSubmissionTime());
				System.out.println(clarification.getTeam());
				System.out.println(clarification.getNumber()); //应该是主键
				System.out.println();
				clarificationListener.clarificationAdded(clarification);
				//判断是否已回答
				if(!clarification.isAnswered()) {
					//如何设置clarification的answer？？？？
					
					//打印team提交的疑问
					System.out.println(clarification.getQuestion());
					clarificationListener.clarificationAnswered(clarification);
					clarificationListener.clarificationUpdated(clarification);
				}else {
					System.out.println(clarification.getAnswer());
				}
			}
			System.out.println("----------------");
			
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
