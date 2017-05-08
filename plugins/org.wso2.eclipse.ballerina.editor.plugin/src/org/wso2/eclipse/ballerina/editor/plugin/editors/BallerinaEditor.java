package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class BallerinaEditor extends TextEditor {

	private ColorManager colorManager;

	public BallerinaEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new BallerinaSourceConfiguration(colorManager));
		setDocumentProvider(new BallerinaDocumentProvider());
	}

	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
