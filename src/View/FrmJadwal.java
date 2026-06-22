package View;

import Controller.Controller_Jadwal;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FrmJadwal extends javax.swing.JFrame {
    Controller.Controller_Jadwal controller;

    public FrmJadwal() {
        initComponents();
        /* CUSTOM_BINDINGS_START */
        if(cmdTambah != null) cmdTambah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdTambahActionPerformed(evt); } });
        if(cmdUbah != null) cmdUbah.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdUbahActionPerformed(evt); } });
        if(cmdHapus != null) cmdHapus.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdHapusActionPerformed(evt); } });
        if(cmdBersih != null) cmdBersih.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cmdBersihActionPerformed(evt); } });
        if(tblJadwal != null) tblJadwal.addMouseListener(new java.awt.event.MouseAdapter() { public void mouseClicked(java.awt.event.MouseEvent evt) { tblJadwalMouseClicked(evt); } });
        if(cbDosen != null) cbDosen.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbDosenItemStateChanged(evt); } });
        if(cbMtk != null) cbMtk.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbMtkItemStateChanged(evt); } });
        /* CUSTOM_BINDINGS_END */
        setLocationRelativeTo(this);
        controller = new Controller_Jadwal(this);
        controller.loadCombos();
        controller.reset();
    }

    public JComboBox<String> getCbTA() { return cbTA; }
    public JComboBox<String> getCbSemester() { return cbSemester; }
    public JComboBox<String> getCbRuang() { return cbRuang; }
    public JComboBox<String> getCbHari() { return cbHari; }
    public JComboBox<String> getCbSesi() { return cbSesi; }
    public JComboBox<String> getCbDosen() { return cbDosen; }
    public JComboBox<String> getCbMtk() { return cbMtk; }

    public JTextField getTxtNamaDosen() { return txtNamaDosen; }
    public JTextField getTxtNamaMtk() { return txtNamaMtk; }
    public JTextField getTxtKelompok() { return txtKelompok; }

    public JTable getTblJadwal() { return tblJadwal; }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        
        cbTA = new javax.swing.JComboBox<>();
        cbSemester = new javax.swing.JComboBox<>();
        cbRuang = new javax.swing.JComboBox<>();
        cbHari = new javax.swing.JComboBox<>();
        cbSesi = new javax.swing.JComboBox<>();
        cbDosen = new javax.swing.JComboBox<>();
        cbMtk = new javax.swing.JComboBox<>();
        
        txtNamaDosen = new javax.swing.JTextField();
        txtNamaMtk = new javax.swing.JTextField();
        txtKelompok = new javax.swing.JTextField();
        
        cmdTambah = new javax.swing.JButton();
        cmdUbah = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        cmdBersih = new javax.swing.JButton();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJadwal = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Transaksi Jadwal");

        jLabel1.setText("Tahun Akademik");
        jLabel2.setText("Semester");
        jLabel3.setText("Ruang");
        jLabel4.setText("Hari");
        jLabel5.setText("Sesi");
        jLabel6.setText("Dosen");
        jLabel7.setText("Matakuliah");
        jLabel8.setText("Kelompok");

        txtNamaDosen.setEditable(false);
        txtNamaMtk.setEditable(false);

        cbHari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu" }));

        

        

        cmdTambah.setText("Tambah");
        

        cmdUbah.setText("Ubah");
        

        cmdHapus.setText("Hapus");
        

        cmdBersih.setText("Bersih");
        

        tblJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID Jadwal", "TA", "Semester", "Ruang", "Hari", "Waktu", "Dosen", "Matakuliah", "Kelompok"
            }
        ));
        
        jScrollPane1.setViewportView(tblJadwal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdBersih))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTA, 0, 150, Short.MAX_VALUE)
                                    .addComponent(cbSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbRuang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbHari, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbSesi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbDosen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbMtk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtKelompok))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamaDosen, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtNamaMtk))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbSesi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbMtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaMtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtKelompok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdUbah)
                    .addComponent(cmdHapus)
                    .addComponent(cmdBersih))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    //GEN-END:initComponents

    private javax.swing.JComboBox<String> cbDosen;
    private javax.swing.JComboBox<String> cbHari;
    private javax.swing.JComboBox<String> cbMtk;
    private javax.swing.JComboBox<String> cbRuang;
    private javax.swing.JComboBox<String> cbSemester;
    private javax.swing.JComboBox<String> cbSesi;
    private javax.swing.JComboBox<String> cbTA;
    private javax.swing.JButton cmdBersih;
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JButton cmdUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblJadwal;
    private javax.swing.JTextField txtKelompok;
    private javax.swing.JTextField txtNamaDosen;
    private javax.swing.JTextField txtNamaMtk;

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) controller.insert();
    }
    private void cmdUbahActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) controller.update();
    }
    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) controller.delete();
    }
    private void cmdBersihActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) controller.reset();
    }
    private void cbDosenItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.fetchNamaDosen();
        }
    }
    private void cbMtkItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.fetchNamaMtk();
        }
    }
    private void tblJadwalMouseClicked(java.awt.event.MouseEvent evt) {
        if (controller != null) controller.isiField(tblJadwal.getSelectedRow());
    }
}
