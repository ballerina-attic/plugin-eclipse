package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

class WordDetector implements IWordDetector {
	/**
	 * 
	 */
	private final CodeScanner WordDetector;

	/**
	 * @param codeScanner
	 */
	WordDetector(CodeScanner codeScanner) {
		WordDetector = codeScanner;
	}

	@Override
	public boolean isWordStart(final char c) {
		return Character.isLetter(c);
	}

	@Override
	public boolean isWordPart(final char c) {
		return Character.isLetter(c);
	}
}