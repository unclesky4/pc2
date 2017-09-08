package core;
import edu.csus.ecs.pc2.api.ContestTestFrame;

public class Main {
	
	public static void main(String[] args) {
		//设置site的密码
//		StartupContestDialog startupContestDialog = new StartupContestDialog();
//		startupContestDialog.setVisible(true);
		
		//测试界面
		ContestTestFrame contestTestFrame = new ContestTestFrame();
		contestTestFrame.setVisible(true);
		ContestTestFrame.usage();
//		contestTestFrame.setVisible(true);
	}

}
