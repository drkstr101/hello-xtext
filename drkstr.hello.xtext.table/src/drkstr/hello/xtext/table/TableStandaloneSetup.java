/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.table;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class TableStandaloneSetup extends TableStandaloneSetupGenerated {

	public static void doSetup() {
		new TableStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
