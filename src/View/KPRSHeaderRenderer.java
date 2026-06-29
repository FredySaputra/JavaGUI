//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class KPRSHeaderRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(new Color(255, 200, 0));
        c.setFont(c.getFont().deriveFont(Font.BOLD));
        return c;
    }
}
