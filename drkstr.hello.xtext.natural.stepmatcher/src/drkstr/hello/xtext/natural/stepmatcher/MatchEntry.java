package drkstr.hello.xtext.natural.stepmatcher;

import org.eclipse.jdt.core.IMethod;

public class MatchEntry {

	private String annotationValue;
	
	public String getAnnotationValue() {
		return annotationValue;
	}
	
	private IMethod method;

	public IMethod getMethod() {
		return method;
	}

	public MatchEntry(String annotationValue, IMethod method) {
		this.annotationValue = annotationValue;
		this.method = method;
	}
}
