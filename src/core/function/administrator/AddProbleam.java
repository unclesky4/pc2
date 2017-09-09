package core.function.administrator;

import java.util.Properties;

import org.junit.experimental.theories.Theories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.utils.DateUtils;
import edu.csus.ecs.pc2.api.ServerConnection;

/**
 * ADMINISTRATOR角色--添加题目功能
 * 
 * @author unclesky4 09/09/2017
 *
 */
public class AddProbleam {

	Logger logger = LoggerFactory.getLogger(Theories.class);

	ServerConnection serverConnection = null;

	Properties problemProperties = null;

	/**
	 * 管理员添加题目  todo:stdin
	 * 
	 * @param serverConnection
	 *            - administrator登陆后的ServerConnection对象
	 * @param title
	 *            - 题目
	 * @param shortName
	 *            - 题目概述
	 */
	public AddProbleam(ServerConnection serverConnection, String title, String shortName) {

		this.serverConnection = serverConnection;

		if (serverConnection == null) {
			logger.warn(DateUtils.getNowDate() + " serverConnection为null");
		} else if (serverConnection.isValidAccountTypeName("ADMINISTRATOR")) {
			problemProperties.setProperty("JUDGING_TYPE", "COMPUTER_AND_MANUAL_JUDGING");
			problemProperties.setProperty("VALIDATOR_PROGRAM", "pc2.jar edu.csus.ecs.pc2.validator.Validator");
			serverConnection.addProblem(title, shortName, null, null, true, problemProperties);
		} else {
			logger.info(DateUtils.getNowDate() + " ADMINISTRATOR角色未登陆！");
		}
	}
}
