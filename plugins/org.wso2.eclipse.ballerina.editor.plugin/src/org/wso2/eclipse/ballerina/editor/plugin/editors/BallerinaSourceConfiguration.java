package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class BallerinaSourceConfiguration extends SourceViewerConfiguration {

	private CodeScanner codeScanner;
	private AnnotationScanner annotationScanner;
	private CommentScanner commentScanner;
	private ColorManager colorManager;

	public BallerinaSourceConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, BallerinaPartitionScanner.ANNOTATION,
				BallerinaPartitionScanner.COMMENT };
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(new BallerinaCompletionProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500);
		assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		return assistant;
	}

	protected CodeScanner getBALCodeScanner() {
		if (codeScanner == null) {
			codeScanner = new CodeScanner(colorManager);
			codeScanner.setDefaultReturnToken(
					new Token(new TextAttribute(colorManager.getColor(IBallerinaColorConstants.DEFAULT_BLACK))));
		}
		return codeScanner;
	}

	protected AnnotationScanner getBALAnotationScanner() {
		if (annotationScanner == null) {
			annotationScanner = new AnnotationScanner(colorManager);
			annotationScanner.setDefaultReturnToken(
					new Token(new TextAttribute(colorManager.getColor(IBallerinaColorConstants.ANNOTATION_GREY))));
		}
		return annotationScanner;
	}

	protected CommentScanner getBALCommentScanner() {
		if (commentScanner == null) {
			commentScanner = new CommentScanner(colorManager);
			commentScanner.setDefaultReturnToken(
					new Token(new TextAttribute(colorManager.getColor(IBallerinaColorConstants.GREEN_COMMENT))));
		}
		return commentScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getBALCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getBALAnotationScanner());
		reconciler.setDamager(dr, BallerinaPartitionScanner.ANNOTATION);
		reconciler.setRepairer(dr, BallerinaPartitionScanner.ANNOTATION);

		dr = new DefaultDamagerRepairer(getBALCommentScanner());
		reconciler.setDamager(dr, BallerinaPartitionScanner.COMMENT);
		reconciler.setRepairer(dr, BallerinaPartitionScanner.COMMENT);

		return reconciler;
	}

}