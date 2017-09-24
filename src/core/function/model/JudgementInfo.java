package core.function.model;

import java.util.Date;

/**
 * Judgement类
 * @author uncle
 *
 */
public class JudgementInfo {

	private long elapsedMS;
	
	private long executeMS;
	
	private Date date;
	
	private long whenJudgedTime;
	
	private long judgedSeconds;
	
	private long howLongToJudgeInSeconds;
	
	private boolean isJudged;
	
	private boolean isSolved;
	
	private String status;

	public long getElapsedMS() {
		return elapsedMS;
	}

	public void setElapsedMS(long elapsedMS) {
		this.elapsedMS = elapsedMS;
	}

	public long getExecuteMS() {
		return executeMS;
	}

	public void setExecuteMS(long executeMS) {
		this.executeMS = executeMS;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getWhenJudgedTime() {
		return whenJudgedTime;
	}

	public void setWhenJudgedTime(long whenJudgedTime) {
		this.whenJudgedTime = whenJudgedTime;
	}

	public long getJudgedSeconds() {
		return judgedSeconds;
	}

	public void setJudgedSeconds(long judgedSeconds) {
		this.judgedSeconds = judgedSeconds;
	}

	public long getHowLongToJudgeInSeconds() {
		return howLongToJudgeInSeconds;
	}

	public void setHowLongToJudgeInSeconds(long howLongToJudgeInSeconds) {
		this.howLongToJudgeInSeconds = howLongToJudgeInSeconds;
	}

	public boolean isJudged() {
		return isJudged;
	}

	public void setJudged(boolean isJudged) {
		this.isJudged = isJudged;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "JudgementInfo [elapsedMS=" + elapsedMS + ", executeMS=" + executeMS + ", date=" + date
				+ ", whenJudgedTime=" + whenJudgedTime + ", judgedSeconds=" + judgedSeconds
				+ ", howLongToJudgeInSeconds=" + howLongToJudgeInSeconds + ", isJudged=" + isJudged + ", isSolved="
				+ isSolved + ", status=" + status + "]";
	}
}
