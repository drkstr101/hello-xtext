package drkstr.hello.xtext.natural.stepmatcher;

import java.util.List;

public interface IStepMatcher {
	
	public boolean isEnabled();
	
	public List<MatchEntry> findMatches(String keyword, String description);
}
