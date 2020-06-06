package drkstr.hello.xtext.natural.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(NaturalInjectorProvider)
class NaturalFormatterTest {
	@Inject extension FormatterTestHelper
	
    @Test
    def void preserveFormatting() {
        assertFormatted[
            toBeFormatted = '''
				Scenario: Hello Xtext!
					Given a precondition
					When something happens
					Then there should be a result
					And it should be correct
					* because I said so
            '''
        ]
    }
	
    @Test
    def void indentSteps() {
        assertFormatted[
            toBeFormatted = '''
				Scenario: Hello Xtext!
				Given a precondition
				When something happens
				Then there should be a result
				And it should be correct
				* because I said so
            '''
            expectation = '''
				Scenario: Hello Xtext!
					Given a precondition
					When something happens
					Then there should be a result
					And it should be correct
					* because I said so
            '''
        ]
    }
}