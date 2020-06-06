package drkstr.hello.xtext.natural.stepmatcher;

import java.util.List;

public interface IStepMatcher {
	public List<StepdefMatch> findMatches(String keyword, String description);
}
