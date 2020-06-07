package drkstr.hello.xtext.natural.stepmatcher;

import java.util.Collections;
import java.util.List;

public class DefaultStepMatcher implements IStepMatcher {

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public List<MatchEntry> findMatches(String keyword, String description) {
		return Collections.emptyList();
	}

}
