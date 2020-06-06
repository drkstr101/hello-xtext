package drkstr.hello.xtext.natural.stepmatcher;

import java.util.Collections;
import java.util.List;

public class DefaultStepMatcher implements IStepMatcher {

	@Override
	public List<StepdefMatch> findMatches(String keyword, String description) {
		return Collections.emptyList();
	}

}
