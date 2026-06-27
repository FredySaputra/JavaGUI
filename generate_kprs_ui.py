import os

form_xml = """<?xml version='1.0' encoding='UTF-8'?>
<Form version="1.3" maxVersion="1.9" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="2" />
    <Property name="title" type="java.lang.String" value="Form Entri Data KPRS" />
    <Property name="undecorated" type="boolean" value="true" />
  </Properties>
  <SyntheticProperties>
    <SyntheticProperty name="formSizePolicy" type="int" value="1" />
    <SyntheticProperty name="generateCenter" type="boolean" value="false" />
  </SyntheticProperties>
  <Layout class="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout">
    <Property name="useNullLayout" type="boolean" value="true" />
  </Layout>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="pnlTitle">
      <Properties>
        <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
          <Color blue="0" green="a5" red="ff" type="rgb" />
        </Property>
      </Properties>
      <Constraints>
        <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription">
          <AbsoluteConstraints x="0" y="0" width="850" height="40" />
        </Constraint>
      </Constraints>
      <Layout class="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout">
        <Property name="useNullLayout" type="boolean" value="true" />
      </Layout>
      <SubComponents>
        <Component class="javax.swing.JLabel" name="lblTitle">
          <Properties>
            <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor">
              <Font name="Tahoma" size="18" style="1" />
            </Property>
            <Property name="text" type="java.lang.String" value="Form Entri Data KPRS" />
          </Properties>
          <Constraints>
            <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription">
              <AbsoluteConstraints x="10" y="10" width="300" height="20" />
            </Constraint>
          </Constraints>
        </Component>
      </SubComponents>
    </Container>
    <Component class="javax.swing.JLabel" name="lblTA"><Properties><Property name="text" type="java.lang.String" value="TA :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="20" y="60" width="80" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JComboBox" name="cmbTA"><Properties><Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor"><StringArray count="0" /></Property></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="100" y="60" width="150" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblTgl"><Properties><Property name="text" type="java.lang.String" value="Tgl. KPRS :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="500" y="60" width="80" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtTglKPRS"><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="590" y="60" width="150" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblSemester"><Properties><Property name="text" type="java.lang.String" value="Semester :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="20" y="100" width="80" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JComboBox" name="cmbSemester"><Properties><Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor"><StringArray count="0" /></Property></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="100" y="100" width="150" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblNIM"><Properties><Property name="text" type="java.lang.String" value="NIM :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="500" y="100" width="50" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtNim"><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="550" y="100" width="100" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtNamaMhs"><Properties><Property name="editable" type="boolean" value="false" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="660" y="100" width="160" height="25" /></Constraint></Constraints></Component>
    
    <Component class="javax.swing.JLabel" name="lblKdMtk"><Properties><Property name="text" type="java.lang.String" value="Kode Mtk :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="20" y="150" width="100" height="20" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JComboBox" name="cmbKodeMtk"><Properties><Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor"><StringArray count="0" /></Property></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="20" y="170" width="120" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblNamaMtk"><Properties><Property name="text" type="java.lang.String" value="Nama Matakuliah :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="160" y="150" width="200" height="20" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtNamaMtk"><Properties><Property name="editable" type="boolean" value="false" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="160" y="170" width="250" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblSks"><Properties><Property name="text" type="java.lang.String" value="SKS :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="430" y="150" width="50" height="20" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtSks"><Properties><Property name="editable" type="boolean" value="false" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="430" y="170" width="50" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblPrasyarat"><Properties><Property name="text" type="java.lang.String" value="Kode Prasyarat :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="500" y="150" width="120" height="20" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JTextField" name="txtPrasyarat"><Properties><Property name="editable" type="boolean" value="false" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="500" y="170" width="120" height="25" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JLabel" name="lblKel"><Properties><Property name="text" type="java.lang.String" value="Kelompok :" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="640" y="150" width="100" height="20" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JComboBox" name="cmbKelompok"><Properties><Property name="model" type="javax.swing.ComboBoxModel" editor="org.netbeans.modules.form.editors2.ComboBoxModelEditor"><StringArray count="0" /></Property></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="640" y="170" width="150" height="25" /></Constraint></Constraints></Component>
    
    <Component class="javax.swing.JButton" name="btnTambah"><Properties><Property name="text" type="java.lang.String" value="Tambah" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="20" y="220" width="90" height="30" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JButton" name="btnUbah"><Properties><Property name="text" type="java.lang.String" value="Ubah" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="120" y="220" width="90" height="30" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JButton" name="btnHapus"><Properties><Property name="text" type="java.lang.String" value="Hapus" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="220" y="220" width="90" height="30" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JButton" name="btnBersih"><Properties><Property name="text" type="java.lang.String" value="Bersih" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="320" y="220" width="90" height="30" /></Constraint></Constraints></Component>
    <Component class="javax.swing.JButton" name="btnSelesai"><Properties><Property name="text" type="java.lang.String" value="Selesai" /></Properties><Constraints><Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription"><AbsoluteConstraints x="730" y="220" width="90" height="30" /></Constraint></Constraints></Component>
    
    <Container class="javax.swing.JScrollPane" name="jScrollPane1">
      <Constraints>
        <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout" value="org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription">
          <AbsoluteConstraints x="20" y="270" width="800" height="300" />
        </Constraint>
      </Constraints>
      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout" />
      <SubComponents>
        <Component class="javax.swing.JTable" name="tblKPRS">
          <Properties>
            <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
              <Table columnCount="0" rowCount="0"/>
            </Property>
          </Properties>
        </Component>
      </SubComponents>
    </Container>
  </SubComponents>
</Form>
"""

with open('src/View/FrmKPRS.form', 'w', encoding='utf-8') as f:
    f.write(form_xml)


java_file = """package View;

import Controller.Controller_KPRS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;

public class FrmKPRS extends javax.swing.JFrame {

    Controller_KPRS controller;

    public FrmKPRS() {
        initComponents();
        
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setSize(850, 600);
        setLocationRelativeTo(null);
        
        tblKPRS.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(255, 200, 0));
                c.setFont(c.getFont().deriveFont(Font.BOLD));
                return c;
            }
        });
        
        tblKPRS.setModel(new KPRSTableModel());

        controller = new Controller_KPRS(this);
        controller.reset();

        txtNim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    controller.validasiNimEnter();
                }
            }
        });
        
        cmbKodeMtk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if(evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    controller.isiDetailMtk();
                }
            }
        });

        tblKPRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.isiField(tblKPRS.getSelectedRow());
            }
        });
        
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.tambah();
            }
        });
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.ubahKelompok();
            }
        });
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.hapus();
            }
        });
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.resetDetil();
            }
        });
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
    }

    public javax.swing.JComboBox<String> getCmbSemester() { return cmbSemester; }
    public javax.swing.JComboBox<String> getCmbTA() { return cmbTA; }
    public javax.swing.JComboBox<String> getCmbKelompok() { return cmbKelompok; }
    public javax.swing.JComboBox<String> getCmbKodeMtk() { return cmbKodeMtk; }
    
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
    public javax.swing.JButton getBtnTambah() { return btnTambah; }
    public javax.swing.JButton getBtnUbah() { return btnUbah; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTA = new javax.swing.JLabel();
        cmbTA = new javax.swing.JComboBox<>();
        lblTgl = new javax.swing.JLabel();
        txtTglKPRS = new javax.swing.JTextField();
        lblSemester = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox<>();
        lblNIM = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNamaMhs = new javax.swing.JTextField();
        lblKdMtk = new javax.swing.JLabel();
        cmbKodeMtk = new javax.swing.JComboBox<>();
        lblNamaMtk = new javax.swing.JLabel();
        txtNamaMtk = new javax.swing.JTextField();
        lblSks = new javax.swing.JLabel();
        txtSks = new javax.swing.JTextField();
        lblPrasyarat = new javax.swing.JLabel();
        txtPrasyarat = new javax.swing.JTextField();
        lblKel = new javax.swing.JLabel();
        cmbKelompok = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
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

        btnTambah.setText("Tambah");
        getContentPane().add(btnTambah);
        btnTambah.setBounds(20, 220, 90, 30);

        btnUbah.setText("Ubah");
        getContentPane().add(btnUbah);
        btnUbah.setBounds(120, 220, 90, 30);

        btnHapus.setText("Hapus");
        getContentPane().add(btnHapus);
        btnHapus.setBounds(220, 220, 90, 30);

        btnBersih.setText("Bersih");
        getContentPane().add(btnBersih);
        btnBersih.setBounds(320, 220, 90, 30);

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
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbKelompok;
    private javax.swing.JComboBox<String> cmbKodeMtk;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JComboBox<String> cmbTA;
    private javax.swing.JScrollPane jScrollPane1;
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
"""

with open('src/View/FrmKPRS.java', 'w', encoding='utf-8') as f:
    f.write(java_file)


table_model = """package View;

import javax.swing.table.DefaultTableModel;

public class KPRSTableModel extends DefaultTableModel {
    public KPRSTableModel() {
        super(new String[]{"Kode Mtk", "Nama Matakuliah", "SKS", "Kode Prasyarat", "Kelompok"}, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
"""
with open('src/View/KPRSTableModel.java', 'w', encoding='utf-8') as f:
    f.write(table_model)

print("Generated all UI files.")
