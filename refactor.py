import re
import os

def refactor_file(file_name, main_field, table_name, txt_focus):
    path = os.path.join('src', 'View', file_name)
    if not os.path.exists(path):
        return
        
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # Replace cmdTambah
    content = re.sub(r'cmdTambah\.addActionListener\([^)]+\);\s*\}\);|cmdTambah\.addActionListener\(evt -> \{?[^\}]+\}?\);', '', content)
    # Replace cmdUbah
    content = re.sub(r'cmdUbah\.addActionListener\([^)]+\);\s*\}\);|cmdUbah\.addActionListener\(evt -> \{?[^\}]+\}?\);', '', content)
    # Replace cmdHapus
    content = re.sub(r'cmdHapus\.addActionListener\([^)]+\);\s*\}\);|cmdHapus\.addActionListener\(evt -> \{?[^\}]+\}?\);', '', content)
    # Replace cmdBersih
    content = re.sub(r'cmdBersih\.addActionListener\([^)]+\);\s*\}\);|cmdBersih\.addActionListener\(evt -> \{?[^\}]+\}?\);', '', content)
    
    # Remove key listeners
    content = re.sub(r'txt'+main_field+r'\.addKeyListener\(new java\.awt\.event\.KeyAdapter\(\) \{.*?\n\s*\}\);', '', content, flags=re.DOTALL)
    
    # Remove mouse listener
    content = re.sub(r'tbl'+table_name+r'\.addMouseListener\(new java\.awt\.event\.MouseAdapter\(\) \{.*?\n\s*\}\);', '', content, flags=re.DOTALL)
    
    # Wrap initComponents
    content = content.replace('private void initComponents() {', '//GEN-BEGIN:initComponents\n    private void initComponents() {')
    content = content.replace('        pack();\n    }', '        pack();\n    }\n    //GEN-END:initComponents')
    
    # Add extracted methods at the end before closing brace of class
    methods = f"""
    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {{
        controller.insert(); controller.reset();
    }}
    private void cmdUbahActionPerformed(java.awt.event.ActionEvent evt) {{
        controller.update(); controller.reset();
    }}
    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {{
        controller.delete(); controller.reset();
    }}
    private void cmdBersihActionPerformed(java.awt.event.ActionEvent evt) {{
        controller.reset();
    }}
    private void txt{main_field}KeyReleased(java.awt.event.KeyEvent evt) {{
        controller.isiTabelCari();
    }}
    private void txt{main_field}KeyPressed(java.awt.event.KeyEvent evt) {{
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){{
            if(txt{main_field}.getText().isEmpty()){{
                controller.reset();
            }} else {{
                controller.isiTabelCari();
                if (tbl{table_name}.getRowCount() > 0) {{
                    controller.isiField(0);
                }}
                {txt_focus}.requestFocus();
            }}
        }}
    }}
    private void tbl{table_name}MouseClicked(java.awt.event.MouseEvent evt) {{
        controller.isiField(tbl{table_name}.getSelectedRow());
        {txt_focus}.requestFocus();
    }}
"""
    # Insert methods
    last_brace = content.rfind('}')
    if last_brace != -1:
        content = content[:last_brace] + methods + content[last_brace:]
        
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
        
refactor_file('FrmDosen.java', 'Nip', 'Dosen', 'txtNama')
refactor_file('FrmRuang.java', 'KodeRuang', 'Ruang', 'txtKeterangan')
refactor_file('FrmSesi.java', 'KodeSesi', 'Sesi', 'txtJamMulai')

