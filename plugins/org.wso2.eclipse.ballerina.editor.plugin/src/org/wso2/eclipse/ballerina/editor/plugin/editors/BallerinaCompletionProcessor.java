package org.wso2.eclipse.ballerina.editor.plugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.texteditor.IDocumentProvider;

public class BallerinaCompletionProcessor implements IContentAssistProcessor {
	
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int documentOffset) {
		 
		String[] fgProposals = null;
		String currentText = currentText(viewer.getDocument(), documentOffset);
		if (!currentText.isEmpty()) {
			fgProposals = BalerinaLanguage.getInstance().getProposals(currentText);
		}
		ICompletionProposal[] result= new ICompletionProposal[fgProposals.length];
		for (int i= 0; i < fgProposals.length; i++) {
			
			String informationDisplayString = String.format("CompletionProcessor.Proposal.ContextInfo.pattern", new Object[] { fgProposals[i] }); //$NON-NLS-1$
			IContextInformation info = new ContextInformation(fgProposals[i], informationDisplayString);
				
			String additionalProposalInfo = String.format("CompletionProcessor.Proposal.hoverinfo.pattern", new Object[] { fgProposals[i]}); //$NON-NLS-1$
			result[i]= new CompletionProposal(fgProposals[i], documentOffset, 0, fgProposals[i].length(), null, fgProposals[i], info, additionalProposalInfo); 
		}
		return result;
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { ':' };
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String currentText(IDocument document, int documentOffset)
    {
        try
        {
            ITypedRegion region = document.getPartition(documentOffset);

            int partitionOffset = region.getOffset();
            int partitionLength = region.getLength();
            int index = documentOffset - partitionOffset;
            String partitionText = document.get(partitionOffset, partitionLength);
            //System.out.println("Partition text: " + document.get(partitionOffset, region.getLength()));
            char c = partitionText.charAt(index-1);

            if (Character.isWhitespace(c) || Character.isWhitespace(partitionText.charAt(index - 1))) {
                return "";
            } else {
                int start = index-1;
                c = partitionText.charAt(start);

                while (!Character.isWhitespace(c) && start >= 0) {
                    start--;
                    c = partitionText.charAt(start);
                }
                
                start++;
                int end = index-1;
                c = partitionText.charAt(end);
            
                String subString = partitionText.substring(start, end);
                return subString;
            }

        }
        catch (BadLocationException e)
        {
            e.printStackTrace();
        }
        return null;
    }
	


}
