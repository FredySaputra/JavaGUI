package View;

import Controller.Controller_Ruang;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrmRuang extends javax.swing.JFrame {
    Controller.Controller_Ruang controller;

    public FrmRuang() {
        initComponents();
        /* CUSTOM_BINDINGS_START */
        if(cmdTambah != null) cmdTambah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdTambahActionPerformed(evt); } });
        if(cmdUbah != null) cmdUbah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdUbahActionPerformed(evt); } });
        if(cmdHapus != null) cmdHapus.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdHapusActionPerformed(evt); } });
        if(cmdBersih != null) cmdBersih.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdBersihActionPerformed(evt); } });
        if(tblRuang != null) tblRuang.addMouseListener(new java.awt.event.MouseAdapter() { public void mouseClicked(java.awt.event.MouseEvent evt) { tblRuangMouseClicked(evt); } });
        if(txtKodeRuang != null) txtKodeRuang.addKeyListener(new java.awt.event.KeyAdapter() { public void keyReleased(java.awt.event.KeyEvent evt) { txtKodeRuangKeyReleased(evt); } });
        if(txtKodeRuang != null) txtKodeRuang.addKeyListener(new java.awt.event.KeyAdapter() { public void keyPressed(java.awt.event.KeyEvent evt) { txtKodeRuangKeyPressed(evt); } });
        /* CUSTOM_BINDINGS_END */
        setLocationRelativeTo(this);
        controller = new Controller_Ruang(this);
        controller.reset();
    }

    public JTable getTblRuang() { return tblRuang; }
    public JTextField getTxtKodeRuang() { return txtKodeRuang; }
    public JTextArea getTxtKeterangan() { return txtKeterangan; }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {
        lblKode = new javax.swing.JLabel();
        lblKeterangan = new javax.swing.JLabel();
        txtKodeRuang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        cmdTambah = new javax.swing.JButton();
        cmdUbah = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        cmdBersih = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRuang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entri Data Kelas (Ruang)");

        lblKode.setText("Kode Ruang :");
        lblKeterangan.setText("Keterangan :");

        

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane1.setViewportView(txtKeterangan);

        cmdTambah.setText("Tambah");
        

        cmdUbah.setText("Ubah");
        

        cmdHapus.setText("Hapus");
        

        cmdBersih.setText("Bersih");
        

        tblRuang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, new String [] {"Kode Ruang", "Keterangan"}
        ));
        
        jScrollPane3.setViewportView(tblRuang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKode)
                                .addGap(18, 18, 18)
                                .addComponent(txtKodeRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKeterangan)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdBersih))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKode)
                    .addComponent(txtKodeRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKeterangan)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdUbah)
                    .addComponent(cmdHapus)
                    .addComponent(cmdBersih))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    //GEN-END:initComponents

    private javax.swing.JButton cmdBersih;
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JButton cmdUbah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblKode;
    private javax.swing.JTable tblRuang;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKodeRuang;

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {
        controller.insert(); controller.reset();
    }
    private void cmdUbahActionPerformed(java.awt.event.ActionEvent evt) {
        controller.update(); controller.reset();
    }
    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {
        controller.delete(); controller.reset();
    }
    private void cmdBersihActionPerformed(java.awt.event.ActionEvent evt) {
        controller.reset();
    }
    private void txtKodeRuangKeyReleased(java.awt.event.KeyEvent evt) {
        controller.isiTabelCari();
    }
    private void txtKodeRuangKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            if(txtKodeRuang.getText().isEmpty()){
                controller.reset();
            } else {
                controller.isiTabelCari();
                if (tblRuang.getRowCount() > 0) {
                    controller.isiField(0);
                }
                txtKeterangan.requestFocus();
            }
        }
    }
    private void tblRuangMouseClicked(java.awt.event.MouseEvent evt) {
        controller.isiField(tblRuang.getSelectedRow());
        txtKeterangan.requestFocus();
    }
}
