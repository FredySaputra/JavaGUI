import re

java_file = 'src/View/FrmJadwal.java'
with open(java_file, 'r', encoding='utf-8') as f:
    jcode = f.read()

# Fix button texts and scrollpane
insert_code = """
        cmdTambah.setText("Tambah");
        cmdUbah.setText("Ubah");
        cmdHapus.setText("Hapus");
        cmdBersih.setText("Bersih");
        jScrollPane1.setViewportView(tblJadwal);
"""
jcode = jcode.replace('getContentPane().add(cmdTambah);', insert_code + '\n        getContentPane().add(cmdTambah);')

# Fix setSize and pack
jcode = jcode.replace('setSize(800, 560);', 'setPreferredSize(new java.awt.Dimension(800, 560));\n        setSize(800, 560);')

# Fix item state change listener for real time validation
# Actually, the user just wants the existing itemStateChanged to be wired up for Sesi and Mtk. It is already wired up!
# Let's add DocumentListener to txtKelompok and ItemListener to others just to call a real-time validation if needed.
# For now, let's just make sure the missing components are fixed.

with open(java_file, 'w', encoding='utf-8') as f:
    f.write(jcode)
