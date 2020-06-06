package drkstr.hello.xtext.natural.validation;

import java.util.Formatter;
import java.util.Locale;

public class IssueCodes {
	
	static final Formatter formatter = new Formatter(new StringBuilder(), Locale.US);
	
	static final String PREFIX = "org.agileware.natural.";

	public static IssueCode MISSING_SCENARIOS = new IssueCode("MissingScenarios", "At least one Scenario is required.");
	
	public static IssueCode MISSING_SCENARIO_TITLE = new IssueCode("MissingScenarioTitle", "Scenario is missing title.");

	public static IssueCode MISSING_SCENARIO_STEPS = new IssueCode("MissingScenarioSteps", "No steps defined for Scenario(%s).");
	
	public static class IssueCode {
		private String id;
		private String msg;
		
		IssueCode(String id, String msg) {
			this.id = id;
			this.msg = msg;
		}
		
		public String id() {
			return this.id;
		}
		
		public String message(Object... tokens) {
			return formatter.format(this.msg, tokens).toString();
		}
	}
}
