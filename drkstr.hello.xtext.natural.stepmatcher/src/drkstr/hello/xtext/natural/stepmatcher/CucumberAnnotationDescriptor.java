package drkstr.hello.xtext.natural.stepmatcher;

public class CucumberAnnotationDescriptor implements IAnnotationDescriptor {

	public final static String[] STEPS = { "Given", "When", "Then", "And", "But" };
	
	private static final String CUCUMBER_PACKAGE = "cucumber.api.java.en";
	
	@Override
	public String[] getNames() {
		return STEPS;
	}

	@Override
	public String getPackage() {
		return CUCUMBER_PACKAGE;
	}

}
