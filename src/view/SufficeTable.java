package view;

import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Had to make our own version of JTable solely because the setCellEditor function
 * did not appear to work.  The only difference between our table and the regular JTable
 * is the getCellEditor function.
 * 
 * @author Sean
 *
 */
public class SufficeTable extends JTable {

	public SufficeTable() {

	}

	public SufficeTable(TableModel dm) {
		super(dm);

		// TODO Auto-generated constructor stub
	}

	public SufficeTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);

		// TODO Auto-generated constructor stub
	}

	public SufficeTable(int numRows, int numColumns) {
		super(numRows, numColumns);

		// TODO Auto-generated constructor stub
	}

	public SufficeTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);

		// TODO Auto-generated constructor stub
	}

	public SufficeTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);

		// TODO Auto-generated constructor stub
	}

	public SufficeTable(TableModel dm, TableColumnModel cm,
			ListSelectionModel sm) {
		super(dm, cm, sm);

		// TODO Auto-generated constructor stub
	}

	/**
	 * The only method that is different from the regular JTable.
	 * @return The SufficeCellEditor
	 */
	public TableCellEditor getCellEditor(int row, int column) {
		return new SufficeCellEditor(new JTextField());
	}

}
