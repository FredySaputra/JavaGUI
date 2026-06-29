//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import javax.swing.table.DefaultTableModel;

public class KPRSTableModel extends DefaultTableModel {
    public KPRSTableModel() {
        super(new String[]{"Kode Mtk", "Nama Matakuliah", "SKS", "Kode Prasyarat", "Kelompok"}, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
