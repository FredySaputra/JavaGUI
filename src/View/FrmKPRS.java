package View;

import Controller.Controller_KPRS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;

public class FrmKPRS extends javax.swing.JFrame implements java.awt.event.ActionListener, java.awt.event.KeyListener, java.awt.event.ItemListener, java.awt.event.MouseListener {

    Controller_KPRS controller;

    public FrmKPRS() {
        initComponents();
        
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setSize(850, 600);
        setLocationRelativeTo(null);
        
        tblKPRS.getTableHeader().setDefaultRenderer(new KPRSHeaderRenderer());
        tblKPRS.setModel(new KPRSTableModel());

        controller = new Controller_KPRS(this);
        controller.reset();

        txtNim.addKeyListener(this);
        cmbKodeMtk.addItemListener(this);
        tblKPRS.addMouseListener(this);
        lblClose.addMouseListener(this);
        
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnBersih.addActionListener(this);
        btnSelesai.addActionListener(this);
    }

    // --- Listener Implementations ---

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        Object source = evt.getSource();
        if (source == btnUbah) {
            controller.ubahKelompok();
        } else if (source == btnHapus) {
            controller.hapus();
        } else if (source == btnBersih) {
            controller.resetDetil();
        } else if (source == btnSelesai) {
            controller.reset();
            
            // clear the JTable as well
            tblKPRS.setModel(new KPRSTableModel());
        }
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == txtNim) {
            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                controller.validasiNimEnter();
            }
        }
    }
    public void keyReleased(java.awt.event.KeyEvent evt) {}
    public void keyTyped(java.awt.event.KeyEvent evt) {}

    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getSource() == cmbKodeMtk) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                controller.isiDetailMtk();
            }
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == tblKPRS) {
            controller.isiField(tblKPRS.getSelectedRow());
        } else if (evt.getSource() == lblClose) {
            dispose();
        }
    }
    public void mousePressed(java.awt.event.MouseEvent evt) {}
    public void mouseReleased(java.awt.event.MouseEvent evt) {}
    public void mouseEntered(java.awt.event.MouseEvent evt) {}
    public void mouseExited(java.awt.event.MouseEvent evt) {}

    // --- Getters ---

    public javax.swing.JComboBox getCmbSemester() { return cmbSemester; }
    public javax.swing.JComboBox getCmbTA() { return cmbTA; }
    public javax.swing.JComboBox getCmbKelompok() { return cmbKelompok; }
    public javax.swing.JComboBox getCmbKodeMtk() { return cmbKodeMtk; }
    
    public javax.swing.JTextField getTxtNim() { return txtNim; }
    public javax.swing.JTextField getTxtNamaMhs() { return txtNamaMhs; }
    public javax.swing.JTextField getTxtNamaMtk() { return txtNamaMtk; }
    public javax.swing.JTextField getTxtPrasyarat() { return txtPrasyarat; }
    public javax.swing.JTextField getTxtSks() { return txtSks; }
    public javax.swing.JTextField getTxtTglKPRS() { return txtTglKPRS; }
    
    public javax.swing.JTable getTblKPRS() { return tblKPRS; }
    public javax.swing.JButton getBtnBersih() { return btnBersih; }
    public javax.swing.JButton getBtnHapus() { return btnHapus; }
    public javax.swing.JButton getBtnSelesai() { return btnSelesai; }
    public javax.swing.JButton getBtnUbah() { return btnUbah; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblTA = new javax.swing.JLabel();
        cmbTA = new javax.swing.JComboBox();
        lblTgl = new javax.swing.JLabel();
        txtTglKPRS = new javax.swing.JTextField();
        lblSemester = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox();
        lblNIM = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNamaMhs = new javax.swing.JTextField();
        lblKdMtk = new javax.swing.JLabel();
        cmbKodeMtk = new javax.swing.JComboBox();
        lblNamaMtk = new javax.swing.JLabel();
        txtNamaMtk = new javax.swing.JTextField();
        lblSks = new javax.swing.JLabel();
        txtSks = new javax.swing.JTextField();
        lblPrasyarat = new javax.swing.JLabel();
        txtPrasyarat = new javax.swing.JTextField();
        lblKel = new javax.swing.JLabel();
        cmbKelompok = new javax.swing.JComboBox();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKPRS = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Entri Data KPRS");
        setUndecorated(true);
        getContentPane().setLayout(null);

        pnlTitle.setBackground(new java.awt.Color(255, 165, 0));
        pnlTitle.setLayout(null);
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblTitle.setText("Form Entri Data KPRS");
        pnlTitle.add(lblTitle);
        lblTitle.setBounds(10, 10, 300, 20);
        
        lblClose.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTitle.add(lblClose);
        lblClose.setBounds(810, 10, 30, 20);
        
        getContentPane().add(pnlTitle);
        pnlTitle.setBounds(0, 0, 850, 40);

        lblTA.setText("TA :");
        getContentPane().add(lblTA);
        lblTA.setBounds(20, 60, 80, 25);
        getContentPane().add(cmbTA);
        cmbTA.setBounds(100, 60, 150, 25);

        lblTgl.setText("Tgl. KPRS :");
        getContentPane().add(lblTgl);
        lblTgl.setBounds(500, 60, 80, 25);
        getContentPane().add(txtTglKPRS);
        txtTglKPRS.setBounds(590, 60, 150, 25);

        lblSemester.setText("Semester :");
        getContentPane().add(lblSemester);
        lblSemester.setBounds(20, 100, 80, 25);
        getContentPane().add(cmbSemester);
        cmbSemester.setBounds(100, 100, 150, 25);

        lblNIM.setText("NIM :");
        getContentPane().add(lblNIM);
        lblNIM.setBounds(500, 100, 50, 25);
        getContentPane().add(txtNim);
        txtNim.setBounds(550, 100, 100, 25);

        txtNamaMhs.setEditable(false);
        getContentPane().add(txtNamaMhs);
        txtNamaMhs.setBounds(660, 100, 160, 25);

        lblKdMtk.setText("Kode Mtk :");
        getContentPane().add(lblKdMtk);
        lblKdMtk.setBounds(20, 150, 100, 20);
        getContentPane().add(cmbKodeMtk);
        cmbKodeMtk.setBounds(20, 170, 120, 25);

        lblNamaMtk.setText("Nama Matakuliah :");
        getContentPane().add(lblNamaMtk);
        lblNamaMtk.setBounds(160, 150, 200, 20);

        txtNamaMtk.setEditable(false);
        getContentPane().add(txtNamaMtk);
        txtNamaMtk.setBounds(160, 170, 250, 25);

        lblSks.setText("SKS :");
        getContentPane().add(lblSks);
        lblSks.setBounds(430, 150, 50, 20);

        txtSks.setEditable(false);
        getContentPane().add(txtSks);
        txtSks.setBounds(430, 170, 50, 25);

        lblPrasyarat.setText("Kode Prasyarat :");
        getContentPane().add(lblPrasyarat);
        lblPrasyarat.setBounds(500, 150, 120, 20);

        txtPrasyarat.setEditable(false);
        getContentPane().add(txtPrasyarat);
        txtPrasyarat.setBounds(500, 170, 120, 25);

        lblKel.setText("Kelompok :");
        getContentPane().add(lblKel);
        lblKel.setBounds(640, 150, 100, 20);
        getContentPane().add(cmbKelompok);
        cmbKelompok.setBounds(640, 170, 150, 25);

        btnUbah.setText("Simpan");
        getContentPane().add(btnUbah);
        btnUbah.setBounds(20, 220, 90, 30);

        btnHapus.setText("Hapus");
        getContentPane().add(btnHapus);
        btnHapus.setBounds(120, 220, 90, 30);

        btnBersih.setText("Batal");
        getContentPane().add(btnBersih);
        btnBersih.setBounds(220, 220, 90, 30);

        btnSelesai.setText("Selesai");
        getContentPane().add(btnSelesai);
        btnSelesai.setBounds(730, 220, 90, 30);

        tblKPRS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jScrollPane1.setViewportView(tblKPRS);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 270, 800, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox cmbKelompok;
    private javax.swing.JComboBox cmbKodeMtk;
    private javax.swing.JComboBox cmbSemester;
    private javax.swing.JComboBox cmbTA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblKdMtk;
    private javax.swing.JLabel lblKel;
    private javax.swing.JLabel lblNIM;
    private javax.swing.JLabel lblNamaMtk;
    private javax.swing.JLabel lblPrasyarat;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblSks;
    private javax.swing.JLabel lblTA;
    private javax.swing.JLabel lblTgl;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTable tblKPRS;
    private javax.swing.JTextField txtNamaMhs;
    private javax.swing.JTextField txtNamaMtk;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtPrasyarat;
    private javax.swing.JTextField txtSks;
    private javax.swing.JTextField txtTglKPRS;
    // End of variables declaration//GEN-END:variables
}
