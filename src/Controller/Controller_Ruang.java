//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Ruang;
import Model.varRuang;
import View.FrmRuang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


class MyTableModelRuang extends javax.swing.table.DefaultTableModel {
    public MyTableModelRuang(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

public class Controller_Ruang {
    FrmRuang form;
    DAO_Ruang model;
    List<varRuang> list;
    String[] header = {"Kode Ruang", "Keterangan"};
    
    public Controller_Ruang(FrmRuang form) {
        this.form = form;
        model = new DAO_Ruang();
    }
    
    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tblModel = new MyTableModelRuang(new Object[][]{}, header);
        for (varRuang obj : list) {
            tblModel.addRow(new Object[]{obj.getKodeRuang(), obj.getKeterangan()});
        }
        form.getTblRuang().setModel(tblModel);
    }
    
    public void isiField(int row) {
        form.getTxtKodeRuang().setText(list.get(row).getKodeRuang());
        form.getTxtKeterangan().setText(list.get(row).getKeterangan());
    }
    
    public void reset() {
        form.getTxtKodeRuang().setText("");
        form.getTxtKeterangan().setText("");
        isiTabel();
    }
    
    public void insert() {
        if(form.getTxtKodeRuang().getText().trim().isEmpty() || form.getTxtKeterangan().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Data tidak boleh kosong!");
            return;
        }
        varRuang obj = new varRuang();
        obj.setKodeRuang(form.getTxtKodeRuang().getText());
        obj.setKeterangan(form.getTxtKeterangan().getText());
        model.insert(obj);
        reset();
    }
    
    public void update() {
        if(form.getTxtKodeRuang().getText().trim().isEmpty()) return;
        varRuang obj = new varRuang();
        obj.setKodeRuang(form.getTxtKodeRuang().getText());
        obj.setKeterangan(form.getTxtKeterangan().getText());
        model.update(obj);
        reset();
    }
    
    public void delete() {
        if(!form.getTxtKodeRuang().getText().trim().isEmpty()) {
            model.delete(form.getTxtKodeRuang().getText());
            reset();
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtKodeRuang().getText().trim());
        DefaultTableModel tblModel = new MyTableModelRuang(new Object[][]{}, header);
        for (varRuang obj : list) {
            tblModel.addRow(new Object[]{obj.getKodeRuang(), obj.getKeterangan()});
        }
        form.getTblRuang().setModel(tblModel);
    }
}
