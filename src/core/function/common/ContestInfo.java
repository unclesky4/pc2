package core.function.common;

import edu.csus.ecs.pc2.api.IContest;
import edu.csus.ecs.pc2.api.ILanguage;

/**
 * 获取PC^2的Contest信息--需要登陆
 * @author unclesky4  09/09/2017
 *
 */
public class ContestInfo {
	
	IContest contest = null;
	
	public ContestInfo(IContest contest) {
		this.contest = contest;
	}
	
	//获取Content的标题
	public String getContestTitle() {
		return contest.getContestTitle();
	}
	
	//获取PC^2站点名
	public String getSiteName() {
		return contest.getSiteName();
	}
	
	//获取主机名
	public String getServerHostName() {
		return contest.getServerHostName();
	}
	
	//获取Content中添加的编程语言
	public ILanguage[] getLanguages() {
		return contest.getLanguages();
	}
}
