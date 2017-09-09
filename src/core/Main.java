package core;
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
	}

}
