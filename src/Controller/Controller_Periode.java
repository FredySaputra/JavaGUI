/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Periode;
import Model.varPeriode;
import View.FrmPeriode;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Freds
 */
public class Controller_Periode {
    FrmPeriode form;
    DAO_Interface<varPeriode> model;
    List<varPeriode> list;
    String[] header;
    
    public Controller_Periode(FrmPeriode form){
        this.form = form;
        model = new DAO_Periode();
        list = model.getAll();
        header = new String[]{"TA","Semester"};
        form.getTblPeriode().setShowGrid(true);
        form.getTblPeriode().setShowVerticalLines(true);
        form.getTblPeriode().setGridColor(Color.BLUE);
    }
    
    public void isiTabel(){
        list = model.getAll();
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) { public boolean isCellEditable(int rowIndex, int columnIndex) { return false; } };
        Object[] data = new Object[header.length];
        for(varPeriode objMhs : list){
            data[0] = objMhs.getVTA();
            data[1] = objMhs.getVSemester();
            tblModel.addRow(data);
        }
        form.getTblPeriode().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtTA().setText(list.get(row).getVTA());
        form.getTxtSemester().setText(list.get(row).getVSemester());
    }
    
    public void reset(){
        form.getTxtTA().setText("");
        form.getTxtSemester().setText("");
        form.getTxtTA().requestFocus();
        isiTabel();
    }
    
    public void insert(){
        varPeriode objMhs = new varPeriode();
        objMhs.setVTA(form.getTxtTA().getText());
        objMhs.setVSemester(form.getTxtSemester().getText());
        model.insert(objMhs);
    }
    
    public void update(){
        varPeriode objMhs = new varPeriode();
        objMhs.setVTA(form.getTxtTA().getText());
        objMhs.setVSemester(form.getTxtSemester().getText());
        model.update(objMhs);
    }
    
    public void delete(){
        if(!form.getTxtSemester().getText().trim().isEmpty()){
            String TA = form.getTxtTA().getText();
            model.delete(TA);
        }
        else{
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTabelCari(){
        list = model.getCari(form.getTxtTA().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header);
        Object[] data = new Object[header.length];
        for(varPeriode objMhs : list){
            data[0] = objMhs.getVTA();
            data[1] = objMhs.getVSemester();
            tblModel.addRow(data);
        }
        form.getTblPeriode().setModel(tblModel);
    }
}
