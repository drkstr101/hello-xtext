/*
 * generated by Xtext 2.21.0
 */
package drkstr.hello.xtext.natural.core.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

import drkstr.hello.xtext.natural.core.natural.Scenario;
import drkstr.hello.xtext.natural.core.natural.Step;


/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
public class NaturalLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public NaturalLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	String text(Scenario model) {
		return model.getTitle() == null ? "Scenario" : model.getTitle();
	}

	String image(Scenario model) {
		return "scenario.png";
	}
	
	String text(Step model) {
		return model.getKeyword() + " " + model.getDescription().trim();
	}

	String image(Step model) {
		return "step.gif";
	}
}