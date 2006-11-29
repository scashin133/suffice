package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.SufficeController;
import model.Sheet;
import model.WorkBook;

public class WorkbookEditor extends JFrame {

	private JMenuBar menubar;

	private JMenu menu;

	private JMenuItem newwb;

	private JMenuItem openfile;

	private JMenuItem saveas;

	private JMenuItem save;

	private JMenuItem exit;

	private JMenuItem newsheet;

	private JMenuItem removesheet;

	private JMenuItem renamesheet;

	private JTabbedPane tabpane;

	private JFileChooser fileOpener;

	private JFileChooser fileSaver;

	private WorkBook workbook;

	private File currentFile;

	private boolean fileActive;

	private ArrayList<SufficeTable> tables;

	private int totalSheetCount;

	public WorkbookEditor() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			// alternate look and feel. this one is much more usable than the OSX look and feel
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			// handle exception
		}

		workbook = SufficeController.newWorkbook();

		totalSheetCount = 0;

		tables = new ArrayList<SufficeTable>();

		fileOpener = new JFileChooser();
		fileSaver = new JFileChooser();

		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("sfwb");
		filter.setDescription("Suffice Workbook File");

		fileOpener.setFileFilter(filter);
		fileSaver.setFileFilter(filter);

		menubar = new JMenuBar();
		formMenu();
		this.setJMenuBar(menubar);

		tabpane = new JTabbedPane();

		tabpane.addTab("Sheet 1", makeSheetPanel("1"));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(tabpane);

		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.pack();
		this.setTitle("Suffice");
		this.setResizable(true);

		fileActive = false;
	}

	private void formMenu() {
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription(
				"Perform file operations");
		menubar.add(menu);

		newwb = new JMenuItem("New Workbook");
		newwb.addActionListener(new NewWorkbookListener());
		openfile = new JMenuItem("Open File...");
		openfile.addActionListener(new OpenFileListener());
		saveas = new JMenuItem("Save As...");
		saveas.addActionListener(new SaveFileAsListener());
		save = new JMenuItem("Save");
		save.addActionListener(new SaveFileListener());
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		menu.add(newwb);
		menu.addSeparator();
		menu.add(openfile);
		menu.addSeparator();
		menu.add(save);
		menu.add(saveas);
		menu.addSeparator();
		menu.add(exit);

		menu = new JMenu("Workbook");
		menu.getAccessibleContext().setAccessibleDescription(
				"Perform actions on this workbook");
		menubar.add(menu);

		newsheet = new JMenuItem("Add new spreadsheet");
		newsheet.addActionListener(new AddNewSheetListener());
		removesheet = new JMenuItem("Remove current spreadsheet");
		removesheet.addActionListener(new RemoveSheetListener());
		renamesheet = new JMenuItem("Rename current spreadsheet");
		renamesheet.addActionListener(new RenameSheetListener());
		menu.add(newsheet);
		menu.addSeparator();
		menu.add(renamesheet);
		menu.add(removesheet);

	}

	private JPanel makeSheetPanel(String title) {
		Sheet sheet = new Sheet("Sheet " + (totalSheetCount + 1));
		totalSheetCount++;
		workbook.addSheet(sheet);
		return makeSheetPanel(sheet);
	}

	private JPanel makeSheetPanel(Sheet sheet) {

		JPanel tablePanel = new JPanel();
		tablePanel.add(makeScrollPane(sheet));
		return tablePanel;
	}

	private JScrollPane makeScrollPane(Sheet sheet) {
		SufficeTable table = new SufficeTable(sheet);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel testColumnModel = table.getColumnModel();
		Enumeration<TableColumn> columns = testColumnModel.getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = columns.nextElement();

			column.setHeaderValue(sheet.getColumnName(column.getModelIndex()));
		}
		RowHeaderListModel lm = new RowHeaderListModel(sheet);
		JList rowHeader = new JList(lm);
		rowHeader.setFixedCellWidth(50);

		rowHeader.setFixedCellHeight(table.getRowHeight()
				+ table.getRowMargin() - table.getIntercellSpacing().height);
		rowHeader.setCellRenderer(new RowHeaderRenderer(table));
		JScrollPane jsp = new JScrollPane(table);
		jsp.setRowHeaderView(rowHeader);

		tables.add(table);
		return jsp;

	}

	private void addNewSheet() {
		tabpane.addTab("Sheet " + (tabpane.getTabCount() + 1),
				makeSheetPanel("Test"));
	}

	private void removeSheet() {
		int selectedIndex = tabpane.getSelectedIndex();
		Sheet currentSheet = (Sheet) tables.get(selectedIndex).getModel();
		workbook.removeSheet(currentSheet.getName());
		tables.remove(selectedIndex);
		tabpane.remove(tabpane.getSelectedIndex());
		String sheetTitleRegExp = "Sheet [0-9]*";
		for (int x = 0; x < tabpane.getTabCount(); x++) {
			String tabTitle = tabpane.getTitleAt(x);
			if (tabTitle.matches(sheetTitleRegExp)) {
				tabpane.setTitleAt(x, "Sheet " + (x + 1));
			}
		}
	}

	private void renameSheet() {
		int index = tabpane.getSelectedIndex();
		String newName = JOptionPane.showInputDialog("Rename "
				+ tabpane.getTitleAt(index) + " to:");
		if (!(newName == null)) {
			tabpane.setTitleAt(index, newName);
		}
		int selectedIndex = tabpane.getSelectedIndex();
	}

	private void open() throws FileNotFoundException {
		JOptionPane pane = new JOptionPane(
				"Do you want to save before opening a different file?",
				JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		JDialog dialog = pane.createDialog(this, "Save before opening?");
		dialog.show();
		Object selectedValue = pane.getValue();
		if (selectedValue.equals(JOptionPane.YES_OPTION)) {
			try {
				saveFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			openFile();
		} else if (selectedValue.equals(JOptionPane.NO_OPTION)) {
			openFile();
		} else {

		}
	}

	private void openFile() throws FileNotFoundException {
		int rval = fileOpener.showOpenDialog(this);
		if (rval == JFileChooser.APPROVE_OPTION) {
			currentFile = fileOpener.getSelectedFile();
			fileActive = true;
			workbook = SufficeController.load(new FileInputStream(currentFile));
			tabpane.removeAll();
			tables = new ArrayList<SufficeTable>();

			ArrayList<Sheet> sheets = workbook.getSheets();
			System.out.println(sheets.size());
			for (Sheet s : sheets) {
				tabpane.addTab("Sheet " + (tabpane.getTabCount() + 1),
						makeSheetPanel(s));
				s.initializeSheet();
			}
			this.setTitle("Suffice: " + currentFile.getPath());
		}

	}

	private void saveFile() throws FileNotFoundException {
		SufficeTable st = this.getCurrentTable();

		ArrayList<ArrayList<TableModelListener>> list = new ArrayList<ArrayList<TableModelListener>>();
		ArrayList<Sheet> sheets = workbook.getSheets();
		int counter = 0;
		for (Sheet sheet : sheets) {
			TableModelListener[] tmls = sheet.getTableModelListeners();
			list.add(new ArrayList<TableModelListener>());

			for (TableModelListener tml : tmls) {
				list.get(counter).add(tml);
				sheet.removeTableModelListener(tml);
			}
			counter++;

		}

		if (!(st.isEditing())) {
			if (fileActive) {

				SufficeController.save(workbook, new FileOutputStream(
						currentFile));
			} else {
				saveFileAs();
			}
			counter = 0;
			for (Sheet sheet : sheets) {
				for (TableModelListener tml : list.get(counter)) {
					sheet.addTableModelListener(tml);
				}
				counter++;
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"You are currently editing the open spreadsheet, and you cannot save the workbook until you are finished editing.");
		}
	}

	private void saveFileAs() throws FileNotFoundException {
		SufficeTable st = this.getCurrentTable();
		ArrayList<ArrayList<TableModelListener>> list = new ArrayList<ArrayList<TableModelListener>>();
		ArrayList<Sheet> sheets = workbook.getSheets();
		int counter = 0;
		for (Sheet sheet : sheets) {
			TableModelListener[] tmls = sheet.getTableModelListeners();
			list.add(new ArrayList<TableModelListener>());

			for (TableModelListener tml : tmls) {
				list.get(counter).add(tml);
				sheet.removeTableModelListener(tml);
			}
			counter++;

		}
		if (!(st.isEditing())) {
			int rval = fileSaver.showSaveDialog(this);
			if (rval == JFileChooser.APPROVE_OPTION) {
				currentFile = fileSaver.getSelectedFile();
				String fileName = currentFile.getPath();
				if (!(fileName.endsWith(".sfwb"))) {
					currentFile = new File(fileName + ".sfwb");
				}
				fileActive = true;
				SufficeController.save(workbook, new FileOutputStream(
						currentFile));
				this.setTitle("Suffice: " + currentFile.getPath());
				counter = 0;
				for (Sheet sheet : sheets) {
					for (TableModelListener tml : list.get(counter)) {
						sheet.addTableModelListener(tml);
					}
					counter++;
				}
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"You are currently editing the open spreadsheet, and you cannot save the workbook until you are finished editing.");
		}
	}

	private SufficeTable getCurrentTable() {
		return tables.get(tabpane.getSelectedIndex());
	}

	private void exitThisWindow() {
		JOptionPane pane = new JOptionPane(
				"Do you want to save before exiting?",
				JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		JDialog dialog = pane.createDialog(this, "Save before exit?");
		dialog.setVisible(true);
		Object selectedValue = pane.getValue();
		if (selectedValue.equals(JOptionPane.YES_OPTION)) {
			try {
				saveFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			this.dispose();
		} else if (selectedValue.equals(JOptionPane.NO_OPTION)) {
			this.dispose();
		} else {

		}
	}

	private void newWorkbook() {
		new WorkbookEditor();
	}

	private class AddNewSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			addNewSheet();
		}
	}

	private class RemoveSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			removeSheet();
		}
	}

	private class RenameSheetListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			renameSheet();
		}
	}

	private class OpenFileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				open();
			} catch (Exception e) {
			}
		}
	}

	private class SaveFileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				saveFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private class SaveFileAsListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				saveFileAs();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			System.out.println("Exit listener caught");
			exitThisWindow();
		}
	}

	private class NewWorkbookListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			newWorkbook();
		}
	}
}
