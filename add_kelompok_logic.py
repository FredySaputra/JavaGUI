import re

dao_path = 'src/DAO/DAO_KPRS.java'
ctrl_path = 'src/Controller/Controller_KPRS.java'
view_path = 'src/View/FrmKPRS.java'

# 1. Update DAO_KPRS.java
with open(dao_path, 'r', encoding='utf-8') as f:
    dao_code = f.read()

dao_additions = """
    public List<String> isicomboKelompok(String ta, String semester, String kdMtk) {
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT kelompok FROM jadwal WHERE TA=? AND Semester=? AND kode_mtk=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, kdMtk);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("kelompok"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Object[] getJadwalInfo(String ta, String semester, String kdMtk, String kelompok) {
        Object[] info = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT hari, kode_sesi, kode_sesi_selesai FROM jadwal WHERE TA=? AND Semester=? AND kode_mtk=? AND kelompok=?");
            st.setString(1, ta);
            st.setString(2, semester);
            st.setString(3, kdMtk);
            st.setString(4, kelompok);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                info = new Object[]{rs.getString("hari"), rs.getInt("kode_sesi"), rs.getInt("kode_sesi_selesai")};
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return info;
    }
"""

if "isicomboKelompok" not in dao_code:
    dao_code = dao_code.replace("public List<String> isicomboTA(){", dao_additions + "\n    public List<String> isicomboTA(){")
    with open(dao_path, 'w', encoding='utf-8') as f:
        f.write(dao_code)


# 2. Update Controller_KPRS.java
with open(ctrl_path, 'r', encoding='utf-8') as f:
    ctrl_code = f.read()

ctrl_additions = """
    public void isicomboKelompok(String kdMtk) {
        String ta = ("" + form.getCboTA().getSelectedItem()).trim();
        String semester = ("" + form.getCboSemester().getSelectedItem()).trim();
        form.getCboKelompok().removeAllItems();
        form.getCboKelompok().addItem("-Pilih-");
        for(String klp: model_internal.isicomboKelompok(ta, semester, kdMtk)) {
            form.getCboKelompok().addItem(klp);
        }
    }
"""

if "isicomboKelompok" not in ctrl_code:
    ctrl_code = ctrl_code.replace("public void isicomboTahunAjaran(){", ctrl_additions + "\n    public void isicomboTahunAjaran(){")

# Update isiField to trigger isicomboKelompok
ctrl_code = re.sub(
    r'(form\.getTxtSKS\(\)\.setText\(""\+list\.get\(row\)\.getSKS\(\)\);\s*//.*?\n)',
    r'\1            isicomboKelompok(list.get(row).getKodeMTK());\n',
    ctrl_code
)

# Update ubahKelompok method completely
new_ubah = """
    public void ubahKelompok() {
        String nim = form.getTxtNIM().getText().trim();
        String ta = ("" + form.getCboTA().getSelectedItem()).trim();
        String semester = ("" + form.getCboSemester().getSelectedItem()).trim();
        String kdMtk = form.getTxtKdMtk().getText().trim();
        String kelompokBaru = ("" + form.getCboKelompok().getSelectedItem()).trim();
        
        if (kelompokBaru.equals("-Pilih-") || kelompokBaru.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Pilih kelompok yang baru.");
            return;
        }

        Object[] jInfo = model_internal.getJadwalInfo(ta, semester, kdMtk, kelompokBaru);
        if (jInfo == null) {
            JOptionPane.showMessageDialog(form, "Jadwal untuk kelompok tersebut tidak ditemukan.");
            return;
        }
        
        String hariBaru = (String) jInfo[0];
        int sesiMulaiBaru = (Integer) jInfo[1];
        int sesiSelesaiBaru = (Integer) jInfo[2];

        if (model_internal.cekBentrokJadwal(nim, ta, semester, hariBaru, sesiMulaiBaru, sesiSelesaiBaru, kdMtk)) {
            String[] info = model_internal.getBentrokInfo(nim, ta, semester, hariBaru, sesiMulaiBaru, sesiSelesaiBaru, kdMtk);
            if (info != null) {
                JOptionPane.showMessageDialog(form, "Bentrok dengan mata kuliah: " + info[0] + " pada hari " + info[1]);
            } else {
                JOptionPane.showMessageDialog(form, "Jadwal bentrok dengan mata kuliah lain.");
            }
            return;
        }

        varDetilKPRS obj = new varDetilKPRS();
        obj.setTA(ta);
        obj.setSemester(semester);
        obj.setNIM(nim);
        obj.setKodeMTK(kdMtk);
        obj.setKelompok(kelompokBaru);

        model_internal.update(obj);
        isiTabel();
        form.getCboKelompok().setSelectedIndex(0);
        JOptionPane.showMessageDialog(form, "Data berhasil diubah");
    }
"""
ctrl_code = re.sub(
    r'public void ubahKelompok\(.*?\) \{.*?(?=\n    \}\n)',
    new_ubah.strip(),
    ctrl_code,
    flags=re.DOTALL
)

with open(ctrl_path, 'w', encoding='utf-8') as f:
    f.write(ctrl_code)


# 3. Update FrmKPRS.java
with open(view_path, 'r', encoding='utf-8') as f:
    view_code = f.read()

# Add JComboBox variable getter and declaration
if "cboKelompok" not in view_code:
    view_code = view_code.replace("public javax.swing.JTextField getTxtKdMtk() {", 
        "public javax.swing.JComboBox<String> getCboKelompok() { return cboKelompok; }\n    public javax.swing.JTextField getTxtKdMtk() {")
    view_code = view_code.replace("private javax.swing.JComboBox<String> cboSemester;", 
        "private javax.swing.JComboBox<String> cboSemester;\n    private javax.swing.JComboBox<String> cboKelompok;\n    private javax.swing.JLabel lblKelompok;")
    view_code = view_code.replace("cboSemester = new javax.swing.JComboBox<>();", 
        "cboSemester = new javax.swing.JComboBox<>();\n        cboKelompok = new javax.swing.JComboBox<>();\n        lblKelompok = new javax.swing.JLabel(\"Kelompok :\");")

# Update cmdTambahActionPerformed
cmd_tambah = """
    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblKRS.getSelectedRow();
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih mata kuliah yang akan diubah jadwalnya.");
            return;
        }
        controller.ubahKelompok();
    }
"""
view_code = re.sub(
    r'private void cmdTambahActionPerformed\(java\.awt\.event\.ActionEvent evt\) \{.*?(?=\n    \}//GEN-LAST:event_cmdTambahActionPerformed)',
    cmd_tambah.strip(),
    view_code,
    flags=re.DOTALL
)

# Convert to AbsoluteLayout
pre_layout_pattern = r'(cboSemester\.setModel.*?;\n)'
match = re.search(pre_layout_pattern, view_code, re.DOTALL)
if match:
    pre_layout = view_code[:match.end()]
    
    post_layout_pattern = r'(\s*pack\(\);\n\s*\}\n\s*//GEN-END:initComponents)'
    match_post = re.search(post_layout_pattern, view_code, re.DOTALL)
    if match_post:
        post_layout = view_code[match_post.start():]
        
        absolute_layout_code = """
        getContentPane().setLayout(null);

        getContentPane().add(lblTA);
        lblTA.setBounds(20, 20, 80, 25);
        getContentPane().add(cboTA);
        cboTA.setBounds(110, 20, 150, 25);
        
        getContentPane().add(lblSemester);
        lblSemester.setBounds(300, 20, 80, 25);
        getContentPane().add(cboSemester);
        cboSemester.setBounds(390, 20, 150, 25);

        getContentPane().add(lblTglKRS);
        lblTglKRS.setBounds(560, 20, 80, 25);
        getContentPane().add(txtTglKRS);
        txtTglKRS.setBounds(650, 20, 120, 25);

        getContentPane().add(lblNIM);
        lblNIM.setBounds(20, 60, 80, 25);
        getContentPane().add(txtNIM);
        txtNIM.setBounds(110, 60, 150, 25);
        getContentPane().add(txtNama);
        txtNama.setBounds(270, 60, 300, 25);

        getContentPane().add(lblKdMtk);
        lblKdMtk.setBounds(20, 100, 80, 25);
        getContentPane().add(txtKdMtk);
        txtKdMtk.setBounds(110, 100, 100, 25);
        
        getContentPane().add(lblNamaMtk);
        lblNamaMtk.setBounds(220, 100, 110, 25);
        getContentPane().add(txtNamaMtk);
        txtNamaMtk.setBounds(340, 100, 230, 25);

        getContentPane().add(lblSKS);
        lblSKS.setBounds(590, 100, 40, 25);
        getContentPane().add(txtSKS);
        txtSKS.setBounds(640, 100, 50, 25);
        
        getContentPane().add(lblKdPrasyarat);
        lblKdPrasyarat.setBounds(20, 140, 100, 25);
        getContentPane().add(txtKdPrasyarat);
        txtKdPrasyarat.setBounds(130, 140, 100, 25);

        getContentPane().add(lblKelompok);
        lblKelompok.setBounds(250, 140, 80, 25);
        getContentPane().add(cboKelompok);
        cboKelompok.setBounds(340, 140, 150, 25);

        getContentPane().add(cmdTambah);
        cmdTambah.setBounds(110, 180, 80, 30);
        getContentPane().add(cmdHapus);
        cmdHapus.setBounds(200, 180, 80, 30);
        getContentPane().add(cmdSelesai);
        cmdSelesai.setBounds(290, 180, 80, 30);
        getContentPane().add(cmdBatal);
        cmdBatal.setBounds(380, 180, 80, 30);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 230, 750, 200);

        getContentPane().add(lblTotalSKS);
        lblTotalSKS.setBounds(600, 440, 80, 25);
        getContentPane().add(txtTotalSKS);
        txtTotalSKS.setBounds(690, 440, 80, 25);

        setSize(810, 530);
"""
        view_code = pre_layout + absolute_layout_code + post_layout

with open(view_path, 'w', encoding='utf-8') as f:
    f.write(view_code)
