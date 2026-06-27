import re

filepath = 'src/View/FrmKPRS.java'
with open(filepath, 'r', encoding='utf-8') as f:
    view_code = f.read()

# Fix cmdTambahActionPerformed if it has two closing braces
view_code = view_code.replace("    }\n    }//GEN-LAST:event_cmdTambahActionPerformed", "    }//GEN-LAST:event_cmdTambahActionPerformed")

# Now apply Absolute Layout
# Find the line where cboSemester is added to the layout or setModel
# Actually, the easiest anchor is `cboSemester.setModel` if it exists.
# Let's check what it actually is in the file.
# The previous script used: `pre_layout_pattern = r'(cboSemester\.setModel.*?;\n)'`
# Let's use `javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());`
pre_layout_pattern = r'(cboSemester\.setModel.*?\n\s*)(?=javax\.swing\.GroupLayout layout)'
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
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(view_code)
        print("Layout applied successfully.")
    else:
        print("Post layout match failed")
else:
    print("Pre layout match failed")
