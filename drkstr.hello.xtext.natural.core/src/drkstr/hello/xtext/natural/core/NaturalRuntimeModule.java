/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.natural.core;

import drkstr.hello.xtext.natural.stepmatcher.DefaultStepMatcher;
import drkstr.hello.xtext.natural.stepmatcher.IStepMatcher;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class NaturalRuntimeModule extends AbstractNaturalRuntimeModule {
	public Class<? extends IStepMatcher> bindStepMatcher() {
		return DefaultStepMatcher.class;
	}
}