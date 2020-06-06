package drkstr.hello.xtext.natural.core.ui.syntaxcoloring;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;

import drkstr.hello.xtext.natural.natural.Model;
import drkstr.hello.xtext.natural.natural.Scenario;
import drkstr.hello.xtext.natural.natural.Step;

public class NaturalSemanticHighlightingCalculator implements ISemanticHighlightingCalculator {

	@Override
	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor,
			CancelIndicator cancelIndicator) {
		if (resource == null || resource.getParseResult() == null || resource.getContents().size() <= 0) {
			return;
		}
		Model model = (Model) resource.getContents().get(0);
		for (Scenario scenario : model.getScenarios()) {
			provideHighlightingForSteps(scenario.getSteps(), acceptor);
		}

	}

	private void provideHighlightingForSteps(EList<Step> steps, IHighlightedPositionAcceptor acceptor) {
		for (Step step : steps) {
			INode node = NodeModelUtils.getNode(step);
			acceptor.addPosition(node.getOffset(), node.getText().trim().indexOf(" "),
					NaturalHighlightingConfiguration.STEP_KEYWORD);
//			if (step.eContainer() instanceof ScenarioOutline && step.getDescription() != null) {
//				this.provideHighlightingForPlaceholders(node.getText(), node, 0, acceptor);
//			}
		}
	}

//	private void provideHighlightingForPlaceholders(String description, INode node, int current, IHighlightedPositionAcceptor acceptor) {
//		int start = description.indexOf('<', current);
//		int stop = description.indexOf('>', start);
//		if (start > 0 && stop > 0 && description.charAt(start + 1) != ' ') {
//			acceptor.addPosition(node.getTotalOffset() + start, stop - start + 1,
//					NaturalHighlightingConfiguration.PLACEHOLDER);
//			this.provideHighlightingForPlaceholders(description, node, stop + 1, acceptor);
//		}
//	}

}