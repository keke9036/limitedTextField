package textfieldlimited

import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;


/**
 *
 * @author Tao
 */
public class TextFieldLimited extends TextField {  

    private int maxLength;
    private final int DEFAULT_MAX_LEN = 10;

    public TextFieldLimited() {
        this.maxLength = DEFAULT_MAX_LEN;
    }
    
    public TextFieldLimited(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setMaxlength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    public int getMaxLength() {
        return this.maxLength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        
        // get current selection
        IndexRange selection = this.getSelection();
        
        // Delete or backspace user input.
        if ( text.equals("") ) {
            super.replaceText(start, end, text);
        } else if ( getText().length() < maxLength ) {
            super.replaceText(start, end, text);
        } else if ( getText().length() == maxLength ) {
            if ( selection.getLength() != 0 ) {
                super.replaceText(selection.getStart(), selection.getEnd(), text);
            }
        }
    }

    @Override
    public void replaceSelection(String text) { 

        // get current selection
        IndexRange selection = this.getSelection();

        // Delete or backspace user input.
        if ( text.equals("") ) {
            super.replaceSelection(text);
        } else if ( getText().length() < maxLength ) {
            // Add characters, but don't exceed maxLength.
           
            if ( text.length() > maxLength - getText().length() ) {
                text = text.substring(0, maxLength - getText().length());
            }            
            super.replaceSelection(text);
        } else if ( getText().length() == maxLength ) {
            if ( selection.getLength() != 0 ) {
                if ( text.length() > maxLength - getText().length() ) {
                    text = text.substring(0, selection.getLength());
                    super.replaceSelection(text);
                }
            } 
        }
       
    }
}
