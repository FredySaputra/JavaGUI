//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Dosen;
import Model.varDosen;
import View.FrmDosen;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class MyTableModel extends DefaultTableModel {
    public MyTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}


class MyTableModelDosen extends javax.swing.table.DefaultTableModel {
    public MyTableModelDosen(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

public class Controller_Dosen {
    FrmDosen form;
    DAO_Dosen model;
    List<varDosen> list;
    String[] header = {"NIP", "Nama Dosen", "No HP"};
    
    public Controller_Dosen(FrmDosen form) {
        this.form = form;
        model = new DAO_Dosen();
    }
    
    public void isiTabel() {
        list = model.getAll();
        MyTableModel tblModel = new MyTableModel(new Object[][]{}, header);
        for (varDosen obj : list) {
            tblModel.addRow(new Object[]{obj.getNip(), obj.getNamaDosen(), obj.getNoHp()});
        }
        form.getTblDosen().setModel(tblModel);
    }
    
    public void isiField(int row) {
        form.getTxtNip().setText(list.get(row).getNip());
        form.getTxtNama().setText(list.get(row).getNamaDosen());
        form.getTxtNoHp().setText(list.get(row).getNoHp());
    }
    
    public void reset() {
        form.getTxtNip().setText("");
        form.getTxtNama().setText("");
        form.getTxtNoHp().setText("");
        isiTabel();
    }
    
    public void insert() {
        if(form.getTxtNip().getText().trim().isEmpty() || form.getTxtNama().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Data NIP dan Nama tidak boleh kosong!");
            return;
        }
        varDosen obj = new varDosen();
        obj.setNip(form.getTxtNip().getText());
        obj.setNamaDosen(form.getTxtNama().getText());
        obj.setNoHp(form.getTxtNoHp().getText());
        model.insert(obj);
        reset();
    }
    
    public void update() {
        if(form.getTxtNip().getText().trim().isEmpty()) return;
        varDosen obj = new varDosen();
        obj.setNip(form.getTxtNip().getText());
        obj.setNamaDosen(form.getTxtNama().getText());
        obj.setNoHp(form.getTxtNoHp().getText());
        model.update(obj);
        reset();
    }
    
    public void delete() {
        if(!form.getTxtNip().getText().trim().isEmpty()) {
            model.delete(form.getTxtNip().getText());
            reset();
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtNip().getText().trim());
        MyTableModel tblModel = new MyTableModel(new Object[][]{}, header);
        for (varDosen obj : list) {
            tblModel.addRow(new Object[]{obj.getNip(), obj.getNamaDosen(), obj.getNoHp()});
        }
        form.getTblDosen().setModel(tblModel);
    }
}
