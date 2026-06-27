import re

filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View\MenuAkademik.java"
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

# 1. Insert mnuKPRS variable declaration after mnuJadwal
content = re.sub(
    r'(private javax\.swing\.JMenuItem mnuJadwal;)',
    r'\1\n    private javax.swing.JMenuItem mnuKPRS;',
    content
)

# 2. Insert mnuKPRS initialization after mnuJadwal initialization
kprs_init = """
        mnuKPRS = new javax.swing.JMenuItem();
        mnuKPRS.setText("Entri data KPRS");
        mnuKPRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKPRSActionPerformed(evt);
            }
        });
        mnuTransaksi.add(mnuKPRS);
"""
# find where mnuJadwal is added
content = re.sub(
    r'(mnuTransaksi\.add\(mnuJadwal\);)',
    r'\1\n' + kprs_init,
    content
)

# 3. Insert event handler method
kprs_action = """
    private void mnuKPRSActionPerformed(java.awt.event.ActionEvent evt) {
        new FrmKPRS().setVisible(true);
    }
"""
# insert after mnuJadwalActionPerformed
content = re.sub(
    r'(private void mnuJadwalActionPerformed\(java\.awt\.event\.ActionEvent evt\) \{.*?\}\n)',
    r'\1' + kprs_action,
    content,
    flags=re.DOTALL
)

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)
