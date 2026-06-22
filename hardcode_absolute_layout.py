import re

java_file = 'src/View/FrmJadwal.java'
with open(java_file, 'r', encoding='utf-8') as f:
    jcode = f.read()

# Extract everything before the layout code
pre_layout_pattern = r'(cbHari\.setModel.*?;\n)'
match = re.search(pre_layout_pattern, jcode, re.DOTALL)
if not match:
    print("Pre layout not found")
pre_layout = jcode[:match.end()]

# Extract everything after the layout code
post_layout_pattern = r'(\s*pack\(\);\n\s*\}\n\s*//GEN-END:initComponents)'
match = re.search(post_layout_pattern, jcode, re.DOTALL)
if not match:
    print("Post layout not found")
post_layout = jcode[match.start():]

absolute_layout_code = """
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
        cbMtk.setBounds(100, 85, 150, 25);
        getContentPane().add(txtNamaMtk);
        txtNamaMtk.setBounds(260, 85, 150, 25);

        getContentPane().add(jLabel5);
        jLabel5.setBounds(430, 85, 80, 16);
        getContentPane().add(cbSesi);
        cbSesi.setBounds(520, 85, 150, 25);

        getContentPane().add(jLabel8);
        jLabel8.setBounds(12, 120, 80, 16);
        getContentPane().add(txtKelompok);
        txtKelompok.setBounds(100, 120, 150, 25);

        getContentPane().add(jLabel9);
        jLabel9.setBounds(430, 120, 80, 16);
        getContentPane().add(txtJamSelesai);
        txtJamSelesai.setBounds(520, 120, 150, 25);

        getContentPane().add(jLabel6);
        jLabel6.setBounds(12, 155, 80, 16);
        getContentPane().add(cbDosen);
        cbDosen.setBounds(100, 155, 150, 25);
        getContentPane().add(txtNamaDosen);
        txtNamaDosen.setBounds(260, 155, 250, 25);

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
        
        setSize(800, 560);
"""

new_jcode = pre_layout + absolute_layout_code + post_layout

with open(java_file, 'w', encoding='utf-8') as f:
    f.write(new_jcode)
