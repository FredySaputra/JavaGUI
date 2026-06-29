//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
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
        if(cbSesi != null) cbSesi.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbSesiItemStateChanged(evt); } });
        if(cbTA != null) cbTA.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbTAItemStateChanged(evt); } });
        if(cbSemester != null) cbSemester.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbSemesterItemStateChanged(evt); } });
        if(cbRuang != null) cbRuang.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbRuangItemStateChanged(evt); } });
        if(cbHari != null) cbHari.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbHariItemStateChanged(evt); } });
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
    public JTextField getTxtJamSelesai() { return txtJamSelesai; }
    public JTextField getTxtKelompok() { return txtKelompok; }
    public JTextField getTxtJamMulai() { return txtJamMulai; }
    public JTextField getTxtSesiSelesai() { return txtSesiSelesai; }
    public JTextField getTxtSks() { return txtSks; }

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
        jLabel9 = new javax.swing.JLabel();
        
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
        txtJamSelesai = new javax.swing.JTextField();
        txtJamMulai = new javax.swing.JTextField();
        txtSesiSelesai = new javax.swing.JTextField();
        txtSks = new javax.swing.JTextField();
        
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
        jLabel5.setText("Sesi Mulai");
        jLabel6.setText("Dosen");
        jLabel7.setText("Matakuliah");
        jLabel8.setText("Kelompok");
        jLabel9.setText("Sesi Selesai");

        txtNamaDosen.setEditable(false);
        txtNamaMtk.setEditable(false);
        txtJamSelesai.setEditable(false);
        txtJamMulai.setEditable(false);
        txtSesiSelesai.setEditable(false);
        txtSks.setEditable(false);

        cbHari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu" }));

        getContentPane().setLayout(null);

        // Add components and set bounds
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 15, 80, 16);
        getContentPane().add(cbTA);
        cbTA.setBounds(100, 15, 150, 25);

        getContentPane().add(jLabel3);
        jLabel3.setBounds(430, 15, 80, 16);
        getContentPane().add(cbRuang);
        cbRuang.setBounds(520, 15, 150, 25);

        getContentPane().add(jLabel2);
        jLabel2.setBounds(12, 50, 80, 16);
        getContentPane().add(cbSemester);
        cbSemester.setBounds(100, 50, 150, 25);

        getContentPane().add(jLabel4);
        jLabel4.setBounds(430, 50, 80, 16);
        getContentPane().add(cbHari);
        cbHari.setBounds(520, 50, 150, 25);

        getContentPane().add(jLabel7);
        jLabel7.setBounds(12, 85, 80, 16);
        getContentPane().add(cbMtk);
        cbMtk.setBounds(100, 85, 80, 25);
        getContentPane().add(txtNamaMtk);
        txtNamaMtk.setBounds(190, 85, 160, 25);
        getContentPane().add(txtSks);
        txtSks.setBounds(360, 85, 50, 25);

        getContentPane().add(jLabel5);
        jLabel5.setBounds(430, 85, 80, 16);
        getContentPane().add(cbSesi);
        cbSesi.setBounds(520, 85, 70, 25);
        getContentPane().add(txtJamMulai);
        txtJamMulai.setBounds(600, 85, 70, 25);

        getContentPane().add(jLabel8);
        jLabel8.setBounds(12, 120, 80, 16);
        getContentPane().add(txtKelompok);
        txtKelompok.setBounds(100, 120, 150, 25);

        getContentPane().add(jLabel9);
        jLabel9.setBounds(430, 120, 80, 16);
        getContentPane().add(txtSesiSelesai);
        txtSesiSelesai.setBounds(520, 120, 70, 25);
        getContentPane().add(txtJamSelesai);
        txtJamSelesai.setBounds(600, 120, 70, 25);

        getContentPane().add(jLabel6);
        jLabel6.setBounds(12, 155, 80, 16);
        getContentPane().add(cbDosen);
        cbDosen.setBounds(100, 155, 150, 25);
        getContentPane().add(txtNamaDosen);
        txtNamaDosen.setBounds(260, 155, 250, 25);

        
        cmdTambah.setText("Tambah");
        cmdUbah.setText("Ubah");
        cmdHapus.setText("Hapus");
        cmdBersih.setText("Bersih");
        jScrollPane1.setViewportView(tblJadwal);

        getContentPane().add(cmdTambah);
        cmdTambah.setBounds(100, 200, 80, 26);
        getContentPane().add(cmdUbah);
        cmdUbah.setBounds(190, 200, 80, 26);
        getContentPane().add(cmdHapus);
        cmdHapus.setBounds(280, 200, 80, 26);
        getContentPane().add(cmdBersih);
        cmdBersih.setBounds(370, 200, 80, 26);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(12, 250, 750, 250);
        
        setPreferredSize(new java.awt.Dimension(800, 560));
        setSize(800, 560);


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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblJadwal;
    private javax.swing.JTextField txtJamSelesai;
    private javax.swing.JTextField txtJamMulai;
    private javax.swing.JTextField txtSesiSelesai;
    private javax.swing.JTextField txtKelompok;
    private javax.swing.JTextField txtNamaDosen;
    private javax.swing.JTextField txtNamaMtk;
    private javax.swing.JTextField txtSks;

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
    private void cbSesiItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.calculateJamSelesai();
        }
    }
    
    private void cbTAItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.realTimeValidation();
        }
    }
    
    private void cbSemesterItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.realTimeValidation();
        }
    }
    
    private void cbRuangItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.realTimeValidation();
        }
    }
    
    private void cbHariItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.realTimeValidation();
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
