package Controller;

import javax.swing.table.DefaultTableModel;

public class KPRSTableModel extends DefaultTableModel {
    public KPRSTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
