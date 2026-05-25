/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_KRSS;
import Model.varKRSS;
import View.FrmKRSS;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Freds
 */
public class Controller_KRSS {
    FrmKRSS form;
    DAO_Interface<varKRSS> model;
    DAO_KRSS model_internal;
    List<varKRSS> list;
    String[] header;
    
    public Controller_KRSS(FrmKRSS form){
        this.form = form;
        model = new DAO_KRSS();
        model_internal = new DAO_KRSS();
        list = model.getAll();
        header = new String[]{"No.","Kode","Nama Matakuliah","SKS","Kode Prasyarat"};
        form.getTblKRS().setShowGrid(true);
        form.getTblKRS().setShowVerticalLines(true);
        form.getTblKRS().setGridColor(Color.blue);
    }
    
    public void isicomboTahunAjaran(){
        form.getCboTA().removeAllItems();
        form.getCboTA().addItem("-Pilih-");
        for(varKRSS b: model_internal.isicomboTA()){
            form.getCboTA().addItem(b.getVTA());
        }
    }
    
    public void isicomboSemester(){
        form.getCboSemester().removeAllItems();
        form.getCboSemester().addItem("-Pilih-");
        for(varKRSS b: model_internal.isicomboSMT()){
            form.getCboSemester().addItem(b.getVSemester());
        }
    }
    
    public void tampilNmMahasiswa(){
        for(varKRSS b: model_internal.getNmMhs(form.getTxtNIM().getText())){
            form.getTxtNama().setText(String.valueOf(b.getVNama()));
        }
    }
    
    public void tampilDataMatakuliah(){
        for(varKRSS b: model_internal.getDataMtk(form.getTxtKdMtk().getText())){
            form.getTxtNamaMtk().setText(String.valueOf(b.getVNamaMTK()));
            form.getTxtSKS().setText(String.valueOf(b.getVSKS()));
            form.getTxtKdPrasyarat().setText(String.valueOf(b.getVKodePrasyarat()));
        }
    }
    
    public void isiTabel(){
        String ta = ""+form.getCboTA().getSelectedItem();
        String semester = ""+form.getCboSemester().getSelectedItem();
        String nim = form.getTxtNIM().getText();
        list = model_internal.getAllDetilKRS(ta, semester, nim);
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;
            }
        };
        Object[] data = new Object[header.length];
        int i=1;
        for(varKRSS objKRSS:list){
            data[0] = ""+i;
            data[1] = objKRSS.getVKdMTK();
            data[2] = objKRSS.getVNamaMTK();
            data[3] = objKRSS.getVSKS();
            data[4] = objKRSS.getVKodePrasyarat();
            tblModel.addRow(data);
            i++;
        }
        form.getTblKRS().setModel(tblModel);
        int total = 0, sks= 0;
        for(i=0;i<tblModel.getRowCount();i++){
            sks = Integer.parseInt(String.valueOf(tblModel.getValueAt(i,3).toString()));
            total = total+sks;
        }
        form.getTxtTotalSKS().setText(""+total);
    }
    
    public void reset(){
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getCboTA().setSelectedItem("Pilih");
        form.getCboSemester().setSelectedItem("Pilih");
        form.getTxtTglKRS().setText(String.valueOf(tgl.format(new Date())));
        form.getTxtNIM().setText("");
        form.getTxtNama().setText("");
        form.getTxtKdMtk().setText("");
        form.getTxtNamaMtk().setText("");
        form.getTxtSKS().setText("");
        form.getTxtKdPrasyarat().setText("");
        isicomboTahunAjaran();
        isicomboSemester();
        form.getTxtTglKRS().setEditable(false);
        form.getTxtNIM().setEditable(true);
        form.getTxtNIM().requestFocus();
        isiTabel();
    }
    
    public void resetDetil(){
        form.getTxtKdMtk().setText("");
        form.getTxtNamaMtk().setText("");
        form.getTxtSKS().setText("");
        form.getTxtKdPrasyarat().setText("");
        form.getTxtKdMtk().requestFocus();
        isiTabel();
    }
    
    public void isiField(int row){
        form.getTxtKdMtk().setText(list.get(row).getVKdMTK());
        form.getTxtNamaMtk().setText(list.get(row).getVNamaMTK());
        form.getTxtSKS().setText(""+list.get(row).getVSKS());
        form.getTxtKdPrasyarat().setText(list.get(row).getVKodePrasyarat());
    }
    
    public void insert(){
        varKRSS objKRSS = new varKRSS();
        
        objKRSS.setVTA(""+form.getCboTA().getSelectedItem());
        objKRSS.setVSemester(""+form.getCboSemester().getSelectedItem());
        objKRSS.setVNIM(form.getTxtNIM().getText());
        objKRSS.setVTglKRS(form.getTxtTglKRS().getText());
        objKRSS.setVKdMTK(form.getTxtKdMtk().getText());
        model.insert(objKRSS);
    }
    
    public void delete(){
        if(!form.getTxtNIM().getText().trim().isEmpty()){
            String ta = ""+form.getCboTA().getSelectedItem();
            String semester = ""+form.getCboSemester().getSelectedItem();
            String nim = form.getTxtNIM().getText();
            String kdmtk = form.getTxtKdMtk().getText();
            model_internal.deleteKRS(ta, semester, nim, kdmtk);
        }
        else{
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
}
