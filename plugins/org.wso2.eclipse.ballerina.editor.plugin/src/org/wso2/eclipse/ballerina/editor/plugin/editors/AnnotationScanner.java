package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class AnnotationScanner extends RuleBasedScanner {

	public AnnotationScanner(ColorManager manager) {

		IToken string = new Token(new TextAttribute(manager.getColor(IBallerinaColorConstants.STRUNG_BLUE)));

		IRule[] rules = new IRule[3];

		// Add rule for processing string
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add rule for processing string
		rules[1] = new SingleLineRule("\'", "\'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new WhitespaceDetector());

		// No need to add annotation rule as it is covered in partition rule
		// Therefore below line is purposely commented
		// IToken annotation = new Token(new
		// TextAttribute(manager.getColor(IBALColorConstants.ANNOTATION_GREY)));
		// rules[3] = new EndOfLineRule("@", annotation);
		setRules(rules);
	}
}