package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.rules.*;

public class BallerinaPartitionScanner extends RuleBasedPartitionScanner {
	public final static String ANNOTATION = "__bal_annotation";
	public final static String COMMENT = "__bal_comment";

	public BallerinaPartitionScanner() {

		IPredicateRule[] rules = new IPredicateRule[1];

		IToken annotation = new Token(ANNOTATION);
		rules[0] = new EndOfLineRule("@", annotation);

		// IToken comment = new Token(COMMENT);
		// rules[1] = new SingleLineRule("//", "\n", comment);

		setPredicateRules(rules);
	}
}
