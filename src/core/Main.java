package core;
import edu.csus.ecs.pc2.api.IClient.ClientType;
import edu.csus.ecs.pc2.api.RunStates;
import edu.csus.ecs.pc2.api.listener.ConnectionEvent;
import edu.csus.ecs.pc2.api.listener.ContestEvent;


/**
 * 
 * @author unclesky4 02/09/2017
 *
 */
public class Main {
	
	public static void main(String[] args) {
		System.out.println("打印ConnectionEvent.Action");
		for (ConnectionEvent.Action c : ConnectionEvent.Action.values())
		    System.out.println(c);
		System.out.println("--------------------");
		
		System.out.println("打印ContestEvent.EventType");
		for (ContestEvent.EventType c : ContestEvent.EventType.values())
		    System.out.println(c);
		System.out.println("--------------------");
		
		/*run的执行状态
		UNKNOWN
		NEW
		BEING_JUDGED
		BEING_RE_JUDGED
		JUDGED*/
		for(RunStates runStates : RunStates.values()) {
			System.out.println(runStates);
		}
		
		/*客户端类型
		UNKNOWN_CLIENT
		TEAM_CLIENT
		JUDGE_CLIENT
		SCOREBOARD_CLIENT
		ADMIN_CLIENT*/
		System.out.println("---------------------");
		for(ClientType clientType : ClientType.values()){
			System.out.println(clientType);
		}
		
		Main main = new Main();
	}

}
