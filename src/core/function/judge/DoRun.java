package core.function.judge;

import edu.csus.ecs.pc2.api.IRun;
import edu.csus.ecs.pc2.api.RunListenerUtils;

/**
 * JUDGE编译TEAM提交的Run
 * @author unclesky4  09/09/2017
 *
 */
public class DoRun extends RunListenerUtils{

	RunListenerUtils runListenerUtils = new RunListenerUtils();
	RunListener runListener = runListenerUtils.getRunlistener();
	
	/**
	 * 编译运行Run
	 * @param run
	 * @param isFinal
	 */
	public DoRun(IRun run, boolean isFinal) {
		runListener.runSubmitted(run);
		runListener.runCheckedOut(run, isFinal);
		runListener.runCompiling(run, isFinal);
		runListener.runExecuting(run, isFinal);
		runListener.runValidating(run, isFinal);
		runListener.runJudged(run, isFinal);
	}
	
}
