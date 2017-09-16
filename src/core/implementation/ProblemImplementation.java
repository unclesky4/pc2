package core.implementation;

import edu.csus.ecs.pc2.api.IProblem;
import edu.csus.ecs.pc2.core.model.ElementId;
import edu.csus.ecs.pc2.core.model.IInternalContest;
import edu.csus.ecs.pc2.core.model.Problem;
import edu.csus.ecs.pc2.core.model.ProblemDataFiles;
import edu.csus.ecs.pc2.core.model.SerializedFile;
/**
 * 参考源代码重写ProblemImplementation
 * @author unclesky4
 *
 */
public class ProblemImplementation implements IProblem {
	
	private String name;
	private String judgesDataFileName;
	private byte[] judgesDataFileContents;
	private String judgesAnswerFileName;
	private byte[] judgesAnswerFileContents;
	private String validatorCommandLine;
	private String validatorFileName;
	private ElementId elementId;
  	private byte[] validatorFileContents;
  	private boolean externalValidator = false;
  	private boolean readsInputFromSTDIN = true;
  	private String shortName;
  	private boolean deleted = false;
  	
  	public ProblemImplementation(ElementId problemId, IInternalContest internalContest)
    {
      this(internalContest.getProblem(problemId), internalContest);
    }
    
    public ProblemImplementation(Problem problem, IInternalContest internalContest)
    {
      this.elementId = problem.getElementId();
      this.name = problem.getDisplayName();
      this.shortName = problem.getShortName();
      if ((this.shortName == null) || (this.shortName.isEmpty()))
      {
        this.shortName = this.name.toLowerCase();
        int space = this.shortName.indexOf(" ");
        if (space > 0) {
          this.shortName = this.shortName.substring(0, space);
        }
      }
      this.judgesDataFileName = problem.getDataFileName();
      this.judgesAnswerFileName = problem.getAnswerFileName();
      this.validatorFileName = problem.getValidatorProgramName();
      if ((problem.isValidatedProblem()) && 
        (!problem.isUsingPC2Validator())) {
        this.externalValidator = true;
      }
      problem.setReadInputDataFromSTDIN(true);
      this.readsInputFromSTDIN = problem.isReadInputDataFromSTDIN();
      this.deleted = (!problem.isActive());
      
      ProblemDataFiles problemDataFiles = internalContest.getProblemDataFile(problem);
      if (problemDataFiles != null)
      {
        SerializedFile serializedFile = problemDataFiles.getJudgesDataFile();
        if (serializedFile != null) {
          this.judgesDataFileContents = serializedFile.getBuffer();
        }
        serializedFile = problemDataFiles.getJudgesAnswerFile();
        if (serializedFile != null) {
          this.judgesAnswerFileContents = serializedFile.getBuffer();
        }
        serializedFile = problemDataFiles.getValidatorFile();
        if (serializedFile != null) {
          this.validatorFileContents = serializedFile.getBuffer();
        }
      }
    }

	@Override
	public byte[] getJudgesAnswerFileContents() {
		return this.judgesAnswerFileContents;
	}

	@Override
	public String getJudgesAnswerFileName() {
		return this.judgesAnswerFileName;
	}

	@Override
	public byte[] getJudgesDataFileContents() {
		return this.judgesDataFileContents;
	}

	@Override
	public String getJudgesDataFileName() {
		return this.judgesDataFileName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getShortName() {
		return this.shortName;
	}

	@Override
	public String getValidatorCommandLine() {
		return this.validatorCommandLine;
	}

	@Override
	public byte[] getValidatorFileContents() {
		return this.validatorFileContents;
	}

	@Override
	public String getValidatorFileName() {
		return this.validatorFileName;
	}

	@Override
	public boolean hasAnswerFile() {
		return this.judgesAnswerFileContents != null;
	}

	@Override
	public boolean hasDataFile() {
		return this.judgesDataFileContents != null;
	}

	@Override
	public boolean hasExternalValidator() {
		return this.externalValidator;
	}

	@Override
	public boolean isDeleted() {
		return this.deleted;
	}

	@Override
	public boolean readsInputFromFile() {
		if (hasDataFile()) {
	      return !readsInputFromStdIn();
	    }
	    return false;
	}

	@Override
	public boolean readsInputFromStdIn() {
	    if (hasDataFile()) {
		    return this.readsInputFromSTDIN;
		 }
		 return false;
	}
	
	public boolean equals(Object obj)
	{
	   if (obj == null) {
		   return false;
	   }
	   if ((obj instanceof ProblemImplementation))
	   {
		   ProblemImplementation problemImplementation = (ProblemImplementation)obj;
		   return problemImplementation.elementId.equals(this.elementId);
	   }
	   return false;
	}
  
	public int hashCode()
	{
		return this.elementId.toString().hashCode();
	}
}
