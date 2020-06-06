/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.natural.ui.wizard


import org.eclipse.xtext.ui.wizard.template.FileTemplate
import org.eclipse.xtext.ui.wizard.template.IFileGenerator
import org.eclipse.xtext.ui.wizard.template.IFileTemplateProvider

/**
 * Create a list with all file templates to be shown in the template new file wizard.
 * 
 * Each template is able to generate one or more files.
 */
class NaturalFileTemplateProvider implements IFileTemplateProvider {
	override getFileTemplates() {
		#[new HelloWorldFile]
	}
}

@FileTemplate(label="Hello World", icon="file_template.png", description="Create a hello world for Natural.")
final class HelloWorldFile {
	val helloName = combo("Hello Name:", #["Xtext", "World", "Foo", "Bar"], "The name to say 'Hello' to")

	override generateFiles(IFileGenerator generator) {
		generator.generate('''«folder»/«name».natural''', '''
			Scenario: Hello, «helloName»!
				Given a precondition
				When something happens
				Then there should be a result
				And it should be correct
				* because I said so
		''')
	}
}