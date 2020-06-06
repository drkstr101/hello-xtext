package drkstr.hello.xtext.natural.core.ui.folding

import com.google.inject.Inject
import drkstr.hello.xtext.natural.core.natural.Model
import drkstr.hello.xtext.natural.core.services.NaturalGrammarAccess
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.folding.DefaultFoldingRegionProvider
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionAcceptor
import org.eclipse.xtext.util.ITextRegion
import org.eclipse.xtext.util.TextRegion

class NaturalFoldingRegionProvider extends DefaultFoldingRegionProvider {

	@Inject extension NaturalGrammarAccess

	override protected void computeObjectFolding(EObject model,
			IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
		if (model instanceof Model) {
			var XtextResource res = (model.eResource() as XtextResource)
			computeScenariosFolding(res, foldingRegionAcceptor)
		} else {
			super.computeObjectFolding(model, foldingRegionAcceptor)
		}
	}

	override protected boolean isHandled(EObject o) {
		return super.isHandled(o) || o instanceof Model
	}

	def private void computeScenariosFolding(XtextResource res, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
		val begin = scenarioAccess.EOLTerminalRuleCall_3
		val end = stepAccess.EOLTerminalRuleCall_2
		computeFoldingRegions(res, textRegionBetween(res, begin, end), foldingRegionAcceptor);
	}

	def private void computeFoldingRegions(XtextResource it, ITextRegion region, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
		if (region !== null) {
			foldingRegionAcceptor.accept(region.getOffset(), region.getLength())
		}
	}

	/** 
	 * Determine the text region between the start keyword and the end keyword.
	 */
	def private ITextRegion textRegionBetween(XtextResource res, EObject begin, EObject end) {
		var INode startNode = null
		for (INode node : res.getParseResult().getRootNode().getAsTreeIterable()) {
			var EObject grammarElement = node.getGrammarElement()
			if (grammarElement === begin) {
				startNode = node
			}
			if (grammarElement === end && startNode !== null) {
				return textRegionBetween(res, startNode, node)
			}
		}
		return null
	}

	/** 
	 * Determine the text region between the start node and the end node.
	 */
	def private ITextRegion textRegionBetween(XtextResource res, INode startNode, INode endNode) {
		var int offset = startNode.getOffset()
		var int endOffset = endNode.getEndOffset()
		var int length = endOffset - offset
		return new TextRegion(offset, length)
	}
}
