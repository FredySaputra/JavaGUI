//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package View;

import Controller.Controller_Sesi;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FrmSesi extends javax.swing.JFrame {
    Controller.Controller_Sesi controller;

    public FrmSesi() {
        initComponents();
        /* CUSTOM_BINDINGS_START */
        if(cmdTambah != null) cmdTambah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdTambahActionPerformed(evt); } });
        if(cmdUbah != null) cmdUbah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdUbahActionPerformed(evt); } });
        if(cmdHapus != null) cmdHapus.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdHapusActionPerformed(evt); } });
        if(cmdBersih != null) cmdBersih.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdBersihActionPerformed(evt); } });
        if(tblSesi != null) tblSesi.addMouseListener(new java.awt.event.MouseAdapter() { public void mouseClicked(java.awt.event.MouseEvent evt) { tblSesiMouseClicked(evt); } });
        if(txtKodeSesi != null) txtKodeSesi.addKeyListener(new java.awt.event.KeyAdapter() { public void keyReleased(java.awt.event.KeyEvent evt) { txtKodeSesiKeyReleased(evt); } });
        if(txtKodeSesi != null) txtKodeSesi.addKeyListener(new java.awt.event.KeyAdapter() { public void keyPressed(java.awt.event.KeyEvent evt) { txtKodeSesiKeyPressed(evt); } });
        /* CUSTOM_BINDINGS_END */
        setLocationRelativeTo(this);
        controller = new Controller_Sesi(this);
        controller.reset();
    }

    public JTable getTblSesi() { return tblSesi; }
    public JTextField getTxtKodeSesi() { return txtKodeSesi; }
    public JTextField getTxtJamMulai() { return txtJamMulai; }
    public JTextField getTxtJamSelesai() { return txtJamSelesai; }

    @SuppressWarnings("unchecked")
//GEN-BEGIN:initComponents
    private void initComponents() {
        lblKode = new javax.swing.JLabel();
        lblMulai = new javax.swing.JLabel();
        lblSelesai = new javax.swing.JLabel();
        txtKodeSesi = new javax.swing.JTextField();
        txtJamMulai = new javax.swing.JTextField();
        txtJamSelesai = new javax.swing.JTextField();
        cmdTambah = new javax.swing.JButton();
        cmdUbah = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        cmdBersih = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSesi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entri Data Sesi");

        lblKode.setText("Kode Sesi   :");
        lblMulai.setText("Jam Mulai   :");
        lblSelesai.setText("Jam Selesai :");

        

        cmdTambah.setText("Tambah");
        

        cmdUbah.setText("Ubah");
        

        cmdHapus.setText("Hapus");
        

        cmdBersih.setText("Bersih");
        

        tblSesi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, new String [] {"Kode Sesi", "Jam Mulai", "Jam Selesai"}
        ));
        
        jScrollPane3.setViewportView(tblSesi);

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
                                .addComponent(txtKodeSesi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSelesai)
                                .addGap(18, 18, 18)
                                .addComponent(txtJamSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMulai)
                                .addGap(18, 18, 18)
                                .addComponent(txtJamMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(txtKodeSesi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMulai)
                    .addComponent(txtJamMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelesai)
                    .addComponent(txtJamSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JTable tblSesi;
    private javax.swing.JTextField txtJamMulai;
    private javax.swing.JTextField txtJamSelesai;
    private javax.swing.JTextField txtKodeSesi;

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
    private void txtKodeSesiKeyReleased(java.awt.event.KeyEvent evt) {
        controller.isiTabelCari();
    }
    private void txtKodeSesiKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            if(txtKodeSesi.getText().isEmpty()){
                controller.reset();
            } else {
                controller.isiTabelCari();
                if (tblSesi.getRowCount() > 0) {
                    controller.isiField(0);
                }
                txtJamMulai.requestFocus();
            }
        }
    }
    private void tblSesiMouseClicked(java.awt.event.MouseEvent evt) {
        controller.isiField(tblSesi.getSelectedRow());
        txtJamMulai.requestFocus();
    }
}
