package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUITest {

	public static void main(String args[]) {
	    try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	       // handle exception
	    }
	    
		new WorkbookEditor();
	}
}
