package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

	public static void main(String[] args) {
		JFrame testFrame = new JFrame("Suffice Test");
		Sheet testSheet = new Sheet("Sheet1");

		SufficeTable testTable = new SufficeTable(testSheet);

		/**
		 * This is what gets the table to show the column header's
		 * 
		 * Haven't figured out yet how to do row headers.
		 */
		TableColumnModel testColumnModel = testTable.getColumnModel();

		Enumeration<TableColumn> columns = testColumnModel.getColumns();

		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();

			column.setHeaderValue(testSheet.getColumnName(column
					.getModelIndex()));
		}

		JPanel tablePanel = new JPanel();
		tablePanel.add(new JScrollPane(testTable));

		testFrame.add(tablePanel);

		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		testFrame.pack();

		testFrame.setVisible(true);

		System.out.println(testTable.getCellEditor());

	}

}
