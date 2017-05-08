package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class CodeScanner extends RuleBasedScanner {

	public CodeScanner(ColorManager manager) {

		IToken procInstr = new Token(new TextAttribute(manager.getColor(IBallerinaColorConstants.KEYWORD_PURPLE)));
		IToken string = new Token(new TextAttribute(manager.getColor(IBallerinaColorConstants.STRUNG_BLUE)));

		IRule[] rules = new IRule[5];

		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add rule for processing string
		rules[1] = new SingleLineRule("\'", "\'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new WhitespaceDetector());

		// Add rule for keywords
		WordRule wordRule = new WordRule(new WordDetector(this));
		String[] keyWords = BalerinaLanguage.getInstance().getKeyWords();
		for (String keyWord : keyWords) {
			wordRule.addWord(keyWord.trim(), procInstr);
		}

		rules[3] = wordRule;

		IToken comment = new Token(new TextAttribute(manager.getColor(IBallerinaColorConstants.GREEN_COMMENT)));
		rules[4] = new EndOfLineRule("//", comment); //$NON-NLS-1$

		setRules(rules);
	}
}
