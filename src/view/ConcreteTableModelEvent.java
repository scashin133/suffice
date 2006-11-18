package view;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

public class ConcreteTableModelEvent
extends TableModelEvent{
	
	private static final long serialVersionUID = 1L;

	public ConcreteTableModelEvent(TableModel source, int firstRow, int lastRow, int column, int type) {
		super(source, firstRow, lastRow, column, type);
		// TODO Auto-generated constructor stub
	}

	public ConcreteTableModelEvent(TableModel source, int firstRow, int lastRow, int column) {
		super(source, firstRow, lastRow, column);
		// TODO Auto-generated constructor stub
	}

	public ConcreteTableModelEvent(TableModel source, int firstRow, int lastRow) {
		super(source, firstRow, lastRow);
		// TODO Auto-generated constructor stub
	}

	public ConcreteTableModelEvent(TableModel source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
