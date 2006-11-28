package view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Sheet;

/**
 * NOT in any way shape or form how our view code should function.  This is merely
 * an example of how things could look.  Make sure to use SufficeTable as the JTable and Sheet
 * as the TableModel.
 * @author Sean's Account
 *
 */
public class ViewExample {

	public ViewExample() {

		JFrame testFrame = new JFrame("Suffice Test");
		Sheet sheet = new Sheet("Sheet1");
		SufficeTable table = new SufficeTable(sheet);
		TableColumnModel testColumnModel = table.getColumnModel();
		Enumeration<TableColumn> columns = testColumnModel.getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();
			column.setHeaderValue(sheet.getColumnName(column
					.getModelIndex()));
		}
		
		RowHeaderListModel lm = new RowHeaderListModel(sheet);
		JList rowHeader = new JList(lm);    
		rowHeader.setFixedCellWidth(50);
		
		rowHeader.setFixedCellHeight(table.getRowHeight()
					   + table.getRowMargin() - table.getIntercellSpacing().height);
		rowHeader.setCellRenderer(new RowHeaderRenderer(table));

		JPanel tablePanel = new JPanel();
		JScrollPane jsp = new JScrollPane(table);
		jsp.setRowHeaderView(rowHeader);
		tablePanel.add(jsp);
		testFrame.add(tablePanel);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.pack();
		testFrame.setVisible(true);

	}
	
	
	public static void main(String[] args) {

		new ViewExample();


	}
}


	

		  
