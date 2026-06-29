//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Sesi;
import Model.varSesi;
import View.FrmSesi;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


class MyTableModelSesi extends javax.swing.table.DefaultTableModel {
    public MyTableModelSesi(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

public class Controller_Sesi {
    FrmSesi form;
    DAO_Sesi model;
    List<varSesi> list;
    String[] header = {"Kode Sesi", "Jam Mulai", "Jam Selesai"};
    
    public Controller_Sesi(FrmSesi form) {
        this.form = form;
        model = new DAO_Sesi();
    }
    
    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tblModel = new MyTableModelSesi(new Object[][]{}, header);
        for (varSesi obj : list) {
            tblModel.addRow(new Object[]{obj.getKodeSesi(), obj.getJamMulai(), obj.getJamSelesai()});
        }
        form.getTblSesi().setModel(tblModel);
    }
    
    public void isiField(int row) {
        form.getTxtKodeSesi().setText(String.valueOf(list.get(row).getKodeSesi()));
        form.getTxtJamMulai().setText(list.get(row).getJamMulai());
        form.getTxtJamSelesai().setText(list.get(row).getJamSelesai());
    }
    
    public void reset() {
        form.getTxtKodeSesi().setText("");
        form.getTxtJamMulai().setText("");
        form.getTxtJamSelesai().setText("");
        isiTabel();
    }
    
    public void insert() {
        if(form.getTxtKodeSesi().getText().trim().isEmpty() || form.getTxtJamMulai().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Data tidak boleh kosong!");
            return;
        }
        varSesi obj = new varSesi();
        try {
            obj.setKodeSesi(Integer.parseInt(form.getTxtKodeSesi().getText()));
            obj.setJamMulai(form.getTxtJamMulai().getText());
            obj.setJamSelesai(form.getTxtJamSelesai().getText());
            model.insert(obj);
            reset();
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(form, "Kode sesi harus berupa angka!");
        }
    }
    
    public void update() {
        if(form.getTxtKodeSesi().getText().trim().isEmpty()) return;
        varSesi obj = new varSesi();
        try {
            obj.setKodeSesi(Integer.parseInt(form.getTxtKodeSesi().getText()));
            obj.setJamMulai(form.getTxtJamMulai().getText());
            obj.setJamSelesai(form.getTxtJamSelesai().getText());
            model.update(obj);
            reset();
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(form, "Kode sesi harus berupa angka!");
        }
    }
    
    public void delete() {
        if(!form.getTxtKodeSesi().getText().trim().isEmpty()) {
            model.delete(form.getTxtKodeSesi().getText());
            reset();
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtKodeSesi().getText().trim());
        DefaultTableModel tblModel = new MyTableModelSesi(new Object[][]{}, header);
        for (varSesi obj : list) {
            tblModel.addRow(new Object[]{obj.getKodeSesi(), obj.getJamMulai(), obj.getJamSelesai()});
        }
        form.getTblSesi().setModel(tblModel);
    }
}
