package drkstr.hello.xtext.natural.core.tests

import com.google.inject.Inject
import drkstr.hello.xtext.natural.core.natural.Model
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.validation.Issue
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

import static drkstr.hello.xtext.natural.core.natural.NaturalPackage.Literals.*
import static drkstr.hello.xtext.natural.core.validation.IssueCodes.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*

@RunWith(XtextRunner)
@InjectWith(NaturalInjectorProvider)
class NaturalValidationTest {

	@Inject ParseHelper<Model> parseHelper

	@Inject extension ValidationTestHelper
	
	def static Matcher<Issue> theError(String code) {
		return allOf(
			hasProperty("severity", equalTo(Severity.ERROR)), 
			hasProperty("code", equalTo(code))
		)
	}

	def static Matcher<Issue> theWarning(String code) {
		return allOf(
			hasProperty("severity", equalTo(Severity.WARNING)), 
			hasProperty("code", equalTo(code))
		)
	}
	
	@Test 
	def void missingScenarioSteps() {
		val issues = parseHelper.parse('''
			Scenario: Hello, World!
		''').validate()
		
		assertThat(issues, contains(theError(MISSING_SCENARIO_STEPS.id())))
	}
	
	@Test 
	def void missingScenarioTitle() {
		val issues = parseHelper.parse('''
			Scenario:
				Given a step
		''').validate()
		
		assertThat(issues, contains(theWarning(MISSING_SCENARIO_TITLE.id())))
	}

}
