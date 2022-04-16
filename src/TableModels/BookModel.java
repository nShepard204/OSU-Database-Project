package TableModels;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.sql.*;

public class BookModel extends AbstractTableModel implements TableModelListener {
    // Class Variables.
    private String[] columnNames;
    private Object[][] data;
    private Connection conn;

    public BookModel(Object[][] data, String[] columnNames, Connection conn){
        this.data = data;
        this.columnNames = columnNames;
        this.conn = conn;
        this.addTableModelListener(this);
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void tableChanged(TableModelEvent e){
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        String sql = "UPDATE Book" + " SET `" + columnName + "` = ? WHERE id = ?";
        try {
            PreparedStatement sqlStmt = conn.prepareStatement(sql);
            sqlStmt.setObject(1, data);
            sqlStmt.setInt(2, (int)this.data[row][0]);
            sqlStmt.executeUpdate();
            sqlStmt.close();
        } catch (SQLException event) {
            System.out.println(event.getMessage());
        }
        
    }
    
}
