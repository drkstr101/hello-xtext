/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.natural;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class NaturalStandaloneSetup extends NaturalStandaloneSetupGenerated {

	public static void doSetup() {
		new NaturalStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
