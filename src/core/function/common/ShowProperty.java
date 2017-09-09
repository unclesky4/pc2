package core.function.common;

import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.listener.ConnectionEvent;
import edu.csus.ecs.pc2.api.listener.ContestEvent;

/**
 * 获取一些次要的信息
 * 
 * @author unclesky4 09/09/2017
 *
 */
public class ShowProperty {

	ServerConnection serverConnection = new ServerConnection();

	// 获取问题的PropertyNames的Key
	public String[] getProblemProperty() {
		return serverConnection.getProblemPropertyNames();
	}
	
	public void a() {
		System.out.println("--------------------打印ConnectionEvent.Action--------------");
		for (ConnectionEvent.Action c : ConnectionEvent.Action.values())
		    System.out.println(c);
		System.out.println("--------------------");
		
		System.out.println("----------打印ContestEvent.EventType-----------------");
		for (ContestEvent.EventType c : ContestEvent.EventType.values())
		    System.out.println(c);
		System.out.println("--------------------");
	}

	public static void main(String[] args) {
		ShowProperty showProperty = new ShowProperty();
		for (String tmp : showProperty.getProblemProperty()) {
			System.out.println(tmp);
		}
		
		showProperty.a();
	}
}
