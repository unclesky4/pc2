package core.function.administrator;

import org.junit.experimental.theories.Theories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.csus.ecs.pc2.api.ContestTestFrame;
import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ServerConnection;
import edu.csus.ecs.pc2.api.exceptions.NotLoggedInException;

/**
 * 关于Contest竞赛时间的查询和操作
 * @author unclesky4  09/09/2017
 *
 */
public class ContestTimeOperator extends ContestTestFrame{
	
	Logger logger = LoggerFactory.getLogger(Theories.class);
	
	//ADMINISTRATOR角色登陆后的ServerConnection对象
	ServerConnection serverConnection = null;
	
	IContest contest = null;
	
	public ContestTimeOperator(ServerConnection serverConnection) {
		this.serverConnection = serverConnection;
		try {
			this.contest = serverConnection.getContest();
		} catch (NotLoggedInException e) {
			e.printStackTrace();
			logger.error("ContestTimeOperator类中serverConnection.getContest()发生错误！");
		}
	}
	
	//竞赛时间是否开始
	public boolean isContestClockRunning() {
		return contest.isContestClockRunning();  //contest.getContestClock().isContestClockRunning()
	}
	
	//开始竞赛 - 待测试
	public void startContest() {
		startContest();
	}
	
	//停止竞赛 - 待测试
	public void stopContest() {
		stopContest();
	}
	
	
}
