//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import Controller.Controller_Dosen;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FrmDosen extends javax.swing.JFrame {
    Controller.Controller_Dosen controller;

    public FrmDosen() {
        initComponents();
        /* CUSTOM_BINDINGS_START */
        if(cmdTambah != null) cmdTambah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdTambahActionPerformed(evt); } });
        if(cmdUbah != null) cmdUbah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdUbahActionPerformed(evt); } });
        if(cmdHapus != null) cmdHapus.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdHapusActionPerformed(evt); } });
        if(cmdBersih != null) cmdBersih.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdBersihActionPerformed(evt); } });
        if(tblDosen != null) tblDosen.addMouseListener(new java.awt.event.MouseAdapter() { public void mouseClicked(java.awt.event.MouseEvent evt) { tblDosenMouseClicked(evt); } });
        if(txtNip != null) txtNip.addKeyListener(new java.awt.event.KeyAdapter() { public void keyReleased(java.awt.event.KeyEvent evt) { txtNipKeyReleased(evt); } });
        if(txtNip != null) txtNip.addKeyListener(new java.awt.event.KeyAdapter() { public void keyPressed(java.awt.event.KeyEvent evt) { txtNipKeyPressed(evt); } });
        /* CUSTOM_BINDINGS_END */
        setLocationRelativeTo(this);
        controller = new Controller_Dosen(this);
        controller.reset();
    }

    public JTable getTblDosen() { return tblDosen; }
    public JTextField getTxtNip() { return txtNip; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtNoHp() { return txtNoHp; }

    @SuppressWarnings("unchecked")
//GEN-BEGIN:initComponents
    private void initComponents() {
        lblNip = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        cmdTambah = new javax.swing.JButton();
        cmdUbah = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        cmdBersih = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDosen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entri Data Dosen");

        lblNip.setText("NIP       :");
        lblNama.setText("Nama      :");
        lblNoHp.setText("No. HP    :");

        

        cmdTambah.setText("Tambah");
        

        cmdUbah.setText("Ubah");
        

        cmdHapus.setText("Hapus");
        

        cmdBersih.setText("Bersih");
        

        tblDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, new String [] {"NIP", "Nama Dosen", "No HP"}
        ));
        
        jScrollPane3.setViewportView(tblDosen);

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
                                .addComponent(lblNip)
                                .addGap(18, 18, 18)
                                .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNoHp)
                                .addGap(18, 18, 18)
                                .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNama)
                                .addGap(18, 18, 18)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(lblNip)
                    .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoHp)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNip;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JTable tblDosen;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtNoHp;

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
    private void txtNipKeyReleased(java.awt.event.KeyEvent evt) {
        controller.isiTabelCari();
    }
    private void txtNipKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            if(txtNip.getText().isEmpty()){
                controller.reset();
            } else {
                controller.isiTabelCari();
                if (tblDosen.getRowCount() > 0) {
                    controller.isiField(0);
                }
                txtNama.requestFocus();
            }
        }
    }
    private void tblDosenMouseClicked(java.awt.event.MouseEvent evt) {
        controller.isiField(tblDosen.getSelectedRow());
        txtNama.requestFocus();
    }
}
