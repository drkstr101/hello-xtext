package drkstr.hello.xtext.natural.core.tests

import com.google.inject.Inject
import drkstr.hello.xtext.natural.natural.Model
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static drkstr.hello.xtext.natural.natural.NaturalPackage.Literals.*;
import static drkstr.hello.xtext.natural.validation.IssueCodes.*;

@RunWith(XtextRunner)
@InjectWith(NaturalInjectorProvider)
class NaturalValidationTest {

	@Inject ParseHelper<Model> parseHelper

	@Inject extension ValidationTestHelper
	
	@Test 
	def void missingScenarioSteps() {
		parseHelper.parse('''
			Scenario: Hello, World!
		''') => [ assertError(SCENARIO, MISSING_SCENARIO_STEPS.id()) ]
	}
	
	@Test 
	def void missingScenarioTitle() {
		parseHelper.parse('''
			Scenario:
				Given a step
		''') => [ assertWarning(SCENARIO, MISSING_SCENARIO_TITLE.id()) ]
	}

}
