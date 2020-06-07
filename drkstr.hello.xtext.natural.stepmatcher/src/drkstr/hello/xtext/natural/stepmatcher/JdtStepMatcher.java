package drkstr.hello.xtext.natural.stepmatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;

import com.google.inject.Inject;

public class JdtStepMatcher implements IStepMatcher {

	public static boolean checkSimpleName(IJavaElement element, String simpleName) {
		String name = element.getElementName();
		if (name.indexOf('.') > 0)
			name = name.substring(name.lastIndexOf('.') + 1);
		return simpleName.equals(name);
	}

	public static boolean checkPackage(IJavaElement element, String packageName) {
		if (element.getElementName().indexOf('.') > 0) {
			return packageName.equals(element.getElementName().substring(0, element.getElementName().lastIndexOf('.')));
		}
		try {
			IType container = (IType) element.getAncestor(IJavaElement.TYPE);
			for (String[] names : container.resolveType(element.getElementName())) {
				if (names[0].equals(packageName))
					return true;
			}
		} catch (Exception e) {
			// ignore silently
		}
		return false;
	}
	
	@Inject
	private IAnnotationDescriptor descriptor;

	private Map<ICompilationUnit, List<MatchEntry>> cache = new HashMap<ICompilationUnit, List<MatchEntry>>();
	
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public List<MatchEntry> findMatches(String keyword, String description) {
		System.out.println("DefaultStepMatcher.findMatches(%s, %s)".format(keyword, description));
		if (!cache.isEmpty()) {
			for (List<MatchEntry> entries : cache.values()) {
				for (MatchEntry entry : entries) {
					if (description.matches(entry.getAnnotationValue())) {
						// TODO...
						//command.match(entry.getAnnotationValue(), entry.getMethod());
					}
				}
			}
			
			// TODO...
			return Collections.emptyList();
		}

		// combine search patterns
		long time = System.currentTimeMillis();
		SearchPattern pattern = null;
		for (final String annotationName : descriptor.getNames()) {
			SearchPattern current = SearchPattern.createPattern(annotationName, IJavaSearchConstants.ANNOTATION_TYPE,
					IJavaSearchConstants.ANNOTATION_TYPE_REFERENCE,
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE);
			if (pattern == null) {
				pattern = current;
			} else {
				pattern = SearchPattern.createOrPattern(pattern, current);
			}
		}
		// execute search
		try {
			new SearchEngine().search(pattern, new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() },
					this.getScope(null), new SearchRequestor() {
						public void acceptSearchMatch(SearchMatch match) throws CoreException {
							if (match.getElement() instanceof IMethod) {
								IMethod method = (IMethod) match.getElement();
								IAnnotation[] annotations = method.getAnnotations();
								for (IAnnotation type : annotations) {
									// check annotation package
									if (checkPackage(type, descriptor.getPackage())) {
										// verify pattern
										String annotationValue = (String) type.getMemberValuePairs()[0].getValue();
										List<MatchEntry> entries = cache.get(method.getCompilationUnit());
										if (entries == null) {
											entries = new ArrayList<MatchEntry>();
											cache.put(method.getCompilationUnit(), entries);
										}
										entries.add(new MatchEntry(annotationValue, method));
										if (description.matches(annotationValue)) {
											// TODO...
											// command.match(annotationValue, method);
										}
									}
								}
							}
						}
					}, null);
		} 
		catch (CoreException e) {
			e.printStackTrace();
		}
		System.out.println("stepdef match lookup completed in " + (System.currentTimeMillis() - time) + "ms");
		
		// TODO...
		return Collections.emptyList();
	}
	
	protected IJavaSearchScope getScope(String filter) {
		if (filter == null)
			return SearchEngine.createWorkspaceScope();
		
		String[] names = filter.split(",");
		final List<IJavaElement> packages = new ArrayList<IJavaElement>();
		
		SearchPattern pattern = null;
		for (String name : names) {
			SearchPattern current = SearchPattern.createPattern(name.trim(), 
					IJavaSearchConstants.PACKAGE, 
					IJavaSearchConstants.ALL_OCCURRENCES,
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE);
			if (pattern == null) {
				pattern = current;
			} else {
				pattern = SearchPattern.createOrPattern(pattern, current);
			}
		}
		try {
			new SearchEngine().search(pattern,
					new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, SearchEngine.createWorkspaceScope(), new SearchRequestor() {
						public void acceptSearchMatch(SearchMatch match) throws CoreException {
							packages.add((IJavaElement) match.getElement());
						}
					}, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return SearchEngine.createJavaSearchScope(packages.toArray(new IJavaElement[0]));
	}

}
