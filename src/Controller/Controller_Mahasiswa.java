//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Mahasiswa;
import Model.varMahasiswa;
import View.FrmMahasiswa;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Freds
 */
public class Controller_Mahasiswa {
    FrmMahasiswa form;
    DAO_Interface<varMahasiswa> model;
    List<varMahasiswa> list;
    String[] header;
    
    public Controller_Mahasiswa(FrmMahasiswa form){
        this.form = form;
        model = new DAO_Mahasiswa();
        list = model.getAll();
        header = new String[]{"NIM","Nama","Alamat"};
        form.getTblMahasiswa().setShowGrid(true);
        form.getTblMahasiswa().setShowVerticalLines(true);
        form.getTblMahasiswa().setGridColor(Color.BLUE);
    }
    
    private class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }

    public void isiTabel(){
        list = model.getAll();
        NonEditableTableModel tblModel = new NonEditableTableModel(new Object[][]{}, header);
        Object[] data = new Object[header.length];
        for(varMahasiswa objMhs : list){
            data[0] = objMhs.getVNIM();
            data[1] = objMhs.getVNama();
            data[2] = objMhs.getVAlamat();
            tblModel.addRow(data);
        }
        form.getTblMahasiswa().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtNIM().setText(list.get(row).getVNIM());
        form.getTxtNama().setText(list.get(row).getVNama());
        form.getTxtAlamat().setText(list.get(row).getVAlamat());
    }
    
    public void cariData() {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getVNIM().equalsIgnoreCase(form.getTxtNIM().getText().trim())) {
                    isiField(i);
                    break;
                }
            }
        }
    }

    public void reset(){
        form.getTxtNIM().setText("");
        form.getTxtNama().setText("");
        form.getTxtAlamat().setText("");
        form.getTxtNIM().requestFocus();
        isiTabel();
    }
    
    public void insert(){
        varMahasiswa objMhs = new varMahasiswa();
        objMhs.setVNIM(form.getTxtNIM().getText().trim());
        objMhs.setVNama(form.getTxtNama().getText().trim());
        objMhs.setVAlamat(form.getTxtAlamat().getText().trim());
        model.insert(objMhs);
    }
    
    public void update(){
        varMahasiswa objMhs = new varMahasiswa();
        objMhs.setVNIM(form.getTxtNIM().getText().trim());
        objMhs.setVNama(form.getTxtNama().getText().trim());
        objMhs.setVAlamat(form.getTxtAlamat().getText().trim());
        model.update(objMhs);
    }
    
    public void delete(){
        if(!form.getTxtNama().getText().trim().isEmpty()){
            String nim = form.getTxtNIM().getText().trim();
            model.delete(nim);
        }
        else{
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari(){
        list = model.getCari(form.getTxtNIM().getText().trim());
        NonEditableTableModel tblModel = new NonEditableTableModel(new Object[][]{},header);
        Object[] data = new Object[header.length];
        for(varMahasiswa objMhs : list){
            data[0] = objMhs.getVNIM();
            data[1] = objMhs.getVNama();
            data[2] = objMhs.getVAlamat();
            tblModel.addRow(data);
        }
        form.getTblMahasiswa().setModel(tblModel);
    }
}
