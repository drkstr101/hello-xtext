/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.natural.core.generator

import drkstr.hello.xtext.natural.core.natural.Model
import drkstr.hello.xtext.natural.core.natural.Scenario
import drkstr.hello.xtext.natural.core.natural.Step
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class NaturalGenerator extends AbstractGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		fsa.generateFile(resource.getURI().toString(), serialize(resource.contents.head as Model))
	}

	static def serialize(Model model) '''
		«FOR s : model.scenarios»
			«serialize(s)»
		«ENDFOR»
	'''

	static def serialize(Scenario model) '''
		Scenario: «model.title»
		«FOR s : model.steps»
			«serialize(s)»
		«ENDFOR»
	'''

	static def serialize(Step model) '''
		«model.keyword» «model.description»
	'''
}