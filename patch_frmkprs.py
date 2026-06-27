import re

filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View\FrmKPRS.java"

with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

# Add KeyListener for txtNIM
key_listener_code = """
        txtNIM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    controller.validasiNimEnter();
                }
            }
        });
        
        // Remove old txtNIM action listener if it was empty, but it's okay to keep
"""

content = content.replace("controller.reset();", "controller.reset();" + key_listener_code)

# Change cmdTambah action to be cmdUbah logic
cmd_ubah_logic = """
        int row = tblKRS.getSelectedRow();
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih mata kuliah yang akan diubah jadwalnya.");
            return;
        }
        String kelompokBaru = javax.swing.JOptionPane.showInputDialog(this, "Masukkan Kelompok Baru:");
        if (kelompokBaru == null || kelompokBaru.trim().isEmpty()) return;
        
        String hariBaru = javax.swing.JOptionPane.showInputDialog(this, "Masukkan Hari Baru:");
        if (hariBaru == null || hariBaru.trim().isEmpty()) return;
        
        String sesiMulaiStr = javax.swing.JOptionPane.showInputDialog(this, "Masukkan Sesi Mulai:");
        if (sesiMulaiStr == null || sesiMulaiStr.trim().isEmpty()) return;
        
        String sesiSelesaiStr = javax.swing.JOptionPane.showInputDialog(this, "Masukkan Sesi Selesai:");
        if (sesiSelesaiStr == null || sesiSelesaiStr.trim().isEmpty()) return;
        
        try {
            int sesiMulai = Integer.parseInt(sesiMulaiStr.trim());
            int sesiSelesai = Integer.parseInt(sesiSelesaiStr.trim());
            controller.ubahKelompok(kelompokBaru.trim(), hariBaru.trim(), sesiMulai, sesiSelesai);
        } catch(NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Sesi harus berupa angka.");
        }
"""

content = re.sub(r'private void cmdTambahActionPerformed\(java\.awt\.event\.ActionEvent evt\) \{.*?\n    \}', 
                 'private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {\n' + cmd_ubah_logic + '\n    }', 
                 content, flags=re.DOTALL)

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)
