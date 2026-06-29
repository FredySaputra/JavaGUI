//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Matakuliah;
import Model.varMatakuliah;
import View.FrmMatakuliah;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Freds
 */
public class Controller_Matakuliah {
    FrmMatakuliah form;
    DAO_Interface<varMatakuliah> model;
    List<varMatakuliah> list;
    String[] header;
    
    public Controller_Matakuliah(FrmMatakuliah form){
        this.form = form;
        model = new DAO_Matakuliah();
        list = model.getAll();
        header = new String[]{"KodeMTK","NamaMTK","SKS","KodePrasyarat"};
        form.getTblMatakuliah().setShowGrid(true);
        form.getTblMatakuliah().setShowVerticalLines(true);
        form.getTblMatakuliah().setGridColor(Color.BLUE);
    }
    
    public void isiTabel(){
        list = model.getAll();
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) { public boolean isCellEditable(int rowIndex, int columnIndex) { return false; } };
        Object[] data = new Object[header.length];
        for(varMatakuliah objMhs : list){
            data[0] = objMhs.getVKodeMTK();
            data[1] = objMhs.getVNamaMTK();
            data[2] = objMhs.getVSKS();
            data[3] = objMhs.getVKodePrasyarat();
            tblModel.addRow(data);
        }
        form.getTblMatakuliah().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtKodeMTK().setText(list.get(row).getVKodeMTK());
        form.getTxtNamaMTK().setText(list.get(row).getVNamaMTK());
        form.getTxtSKS().setText(String.valueOf(list.get(row).getVSKS()));
        form.getTxtKodePrasyarat().setText(list.get(row).getVKodePrasyarat());
    }
    
    public void reset(){
        form.getTxtKodeMTK().setText("");
        form.getTxtNamaMTK().setText("");
        form.getTxtSKS().setText("");
        form.getTxtKodePrasyarat().setText("");
        form.getTxtKodeMTK().requestFocus();
        isiTabel();
    }
    
    public void insert(){
        varMatakuliah objMhs = new varMatakuliah();
        objMhs.setVKodeMTK(form.getTxtKodeMTK().getText());
        objMhs.setVNamaMTK(form.getTxtNamaMTK().getText());
        objMhs.setVSKS(Integer.parseInt((form.getTxtSKS().getText())));
        objMhs.setVKodePrasyarat(form.getTxtKodePrasyarat().getText());
        model.insert(objMhs);
    }
    
    public void update(){
        varMatakuliah objMhs = new varMatakuliah();
        objMhs.setVKodeMTK(form.getTxtKodeMTK().getText());
        objMhs.setVNamaMTK(form.getTxtNamaMTK().getText());
        objMhs.setVSKS(Integer.parseInt((form.getTxtSKS().getText())));
        objMhs.setVKodePrasyarat(form.getTxtKodePrasyarat().getText());
        model.update(objMhs);
    }
    
    public void delete(){
        if(!form.getTxtNamaMTK().getText().trim().isEmpty()){
            String kodeMTK = form.getTxtKodeMTK().getText();
            model.delete(kodeMTK);
        }
        else{
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari(){
        list = model.getCari(form.getTxtKodeMTK().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header);
        Object[] data = new Object[header.length];
        for(varMatakuliah objMhs : list){
            data[0] = objMhs.getVKodeMTK();
            data[1] = objMhs.getVNamaMTK();
            data[2] = objMhs.getVSKS();
            data[3] = objMhs.getVKodePrasyarat();
            tblModel.addRow(data);
        }
        form.getTblMatakuliah().setModel(tblModel);
    }
}
