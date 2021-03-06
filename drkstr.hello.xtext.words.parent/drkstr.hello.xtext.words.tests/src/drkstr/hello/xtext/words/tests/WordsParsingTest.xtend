/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.words.tests

import com.google.inject.Inject
import drkstr.hello.xtext.words.words.WordsModel
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(WordsInjectorProvider)
class WordsParsingTest {
	@Inject
	ParseHelper<WordsModel> parseHelper
	
	@Test
	def void loadModel() {
		val result = parseHelper.parse('''
			Hello Xtext!
		''')
		Assert.assertNotNull(result)
		val errors = result.eResource.errors
		Assert.assertTrue('''Unexpected errors: «errors.join(", ")»''', errors.isEmpty)
	}
}
