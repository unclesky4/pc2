package core.function.administrator;

import java.util.ArrayList;
import java.util.Hashtable;

import core.Judge;
import core.function.common.ServerConnection;
import core.function.model.JudgementInfo;
import edu.csus.ecs.pc2.core.model.IInternalContest;
import edu.csus.ecs.pc2.core.model.Run;

/**
 * Administrator角色执行的方法
 * @author uncle
 *
 */
public class AdminOperator {
	
	protected ServerConnection serverConnection;
	
	protected IInternalContest iInternalContest;
	
	public AdminOperator(ServerConnection serverConnection) {
		this.serverConnection = serverConnection;
		
		this.iInternalContest = serverConnection.getIInternalContest();
	}

	/**
	 * 获取所有Judgement
	 * @return
	 */
	public ArrayList<JudgementInfo> getAllJudgementInfo() {
		ArrayList<JudgementInfo> list = new ArrayList<JudgementInfo>();
		System.out.println("iInternalContest.getRuns().length:"+iInternalContest.getRuns().length);
		System.out.println("iInternalContest.getRuns()[0].getAllJudgementRecords().length"+iInternalContest.getRuns()[0].getAllJudgementRecords().length);
		for(Run run : iInternalContest.getRuns()) {
			JudgementInfo judgementInfo = new JudgementInfo();
			judgementInfo.setDate(run.getDate());
			System.out.println(run.getRunTestCases().length);
			judgementInfo.setElapsedMS(run.getElapsedMS());
			judgementInfo.setExecuteMS(run.getJudgementRecord().getExecuteMS());
			judgementInfo.setHowLongToJudgeInSeconds(run.getJudgementRecord().getHowLongToJudgeInSeconds());
			judgementInfo.setSolved(run.isSolved());
			judgementInfo.setSolved(run.isSolved());
			judgementInfo.setStatus(run.getStatus().toString());
			judgementInfo.setWhenJudgedTime(run.getJudgementRecord().getWhenJudgedTime());
			judgementInfo.setJudgedSeconds(run.getJudgementRecord().getJudgedSeconds());
			list.add(judgementInfo);
		}
		return list;
	}
}
