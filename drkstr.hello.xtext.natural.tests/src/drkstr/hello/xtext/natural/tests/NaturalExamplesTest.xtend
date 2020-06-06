package drkstr.hello.xtext.natural.tests

import com.google.inject.Inject
import drkstr.hello.xtext.natural.natural.Model
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.hamcrest.Matchers.*
import static org.hamcrest.MatcherAssert.*

@RunWith(XtextRunner)
@InjectWith(NaturalInjectorProvider)
class NaturalExamplesTest {

	@Inject
	ParseHelper<Model> parseHelper

	@Test
	def void example_01() {
		assertNoParserIssues('''
			Scenario:
		''')
	}

	@Test
	def void example_02() {
		assertNoParserIssues('''
			Scenario: Hello, World!
		''')
	}

	private def void assertNoParserIssues(String content) {
		val model = parseHelper.parse(content)
		assertThat(model, notNullValue())
		assertThat(model.eResource.errors, equalTo(#[]))
	}
}
