package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class CommentScanner extends RuleBasedScanner {

	public CommentScanner(ColorManager manager) {
		IToken string = 
			new Token(
				new TextAttribute(manager.getColor(IBallerinaColorConstants.GREEN_COMMENT)));

		IRule[] rules = new IRule[1];
		rules[0] = new SingleLineRule("//", "\n", string); 
		setRules(rules);
	}
}