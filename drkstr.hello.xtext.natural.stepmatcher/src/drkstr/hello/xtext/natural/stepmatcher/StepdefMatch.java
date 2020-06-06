package drkstr.hello.xtext.natural.stepmatcher;

public class StepdefMatch {

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public StepdefMatch(String keyword, String description) {
		this.keyword = keyword;
		this.description = description;
	}
}
