package core;
import edu.csus.ecs.pc2.api.ContestTestFrame;
import edu.csus.ecs.pc2.api.IClarification;
import edu.csus.ecs.pc2.api.IClient;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ILanguage;
import edu.csus.ecs.pc2.api.IProblem;
import edu.csus.ecs.pc2.api.IRun;
import edu.csus.ecs.pc2.api.IRunJudgement;
import edu.csus.ecs.pc2.api.ITeam;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.LoginFailureException;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;
import edu.csus.ecs.pc2.api.listener.ConnectionEvent;
import edu.csus.ecs.pc2.api.listener.ContestEvent;
import edu.csus.ecs.pc2.api.listener.IRunEventListener;
import edu.csus.ecs.pc2.core.model.IRunListener;
import edu.csus.ecs.pc2.core.model.RunEvent;

public class Judge extends ContestTestFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class a {
		protected void out() {
			System.out.println("dfg");
		}
	}
	
	public static void main(String[] args) {
		ServerConnection serverConnection = new ServerConnection();
		IContest iContest = null;
		
		Judge judge = new Judge();
		Judge.a b = judge.new a();
		b.out();
		
		
		
		try {
			iContest = serverConnection.login("judge1", "judge1");
			
			//获取run
			for (IRun run : iContest.getRuns()) {
			    System.out.println("Run " + run.getNumber() + " from site " + run.getSiteNumber());
			    System.out.println("    submitted at " + run.getSubmissionTime() + " minutes by " + run.getTeam().getDisplayName());
			    System.out.println("    For problem " + run.getProblem().getName());
			    System.out.println("    Written in " + run.getLanguage().getName());
			 
			    if (run.isFinalJudged()) {
			        System.out.println("    Judgement: " + run.getJudgementName());
			    } else {
			        System.out.println("    Judgement: not judged yet ");
			    }
			    
			}
			
			IRunListener iRunListener = new IRunListener() {
				
				@Override
				public void runRemoved(RunEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runChanged(RunEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runAdded(RunEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void refreshRuns(RunEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			};
			
			//查看Clarifications
			IClarification[] iClarifications = iContest.getClarifications();
			for(IClarification iClarification : iClarifications){
				System.out.println("查看Clarifications");
				System.out.println(iClarification.getTeam().getLoginName());
				System.out.println(iClarification.getAnswer());
				System.out.println(iClarification.isAnswered());
				System.out.println(iClarification.getQuestion());
			}
			
			System.out.println("打印ConnectionEvent.Action");
			for (ConnectionEvent.Action c : ConnectionEvent.Action.values())
			    System.out.println(c);
			System.out.println("--------------------");
			
			System.out.println("打印ContestEvent.EventType");
			for (ContestEvent.EventType c : ContestEvent.EventType.values())
			    System.out.println(c);
			System.out.println("--------------------");
		 	
			iContest.addRunListener(new IRunEventListener() {
				
				@Override
				public void runValidating(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runUpdated(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runSubmitted(IRun arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runJudgingCanceled(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runJudged(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runExecuting(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runDeleted(IRun arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runCompiling(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void runCheckedOut(IRun arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			serverConnection.logoff();
			 
		} catch (LoginFailureException e) {
			e.printStackTrace();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
		}
		
	}

}
