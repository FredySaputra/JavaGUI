import re
import os

def refactor_jadwal():
    path = os.path.join('src', 'View', 'FrmJadwal.java')
    if not os.path.exists(path): return
        
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # Replace cmdTambah
    content = re.sub(r'cmdTambah\.addActionListener\(evt -> controller\.insert\(\)\);', '', content)
    # Replace cmdUbah
    content = re.sub(r'cmdUbah\.addActionListener\(evt -> controller\.update\(\)\);', '', content)
    # Replace cmdHapus
    content = re.sub(r'cmdHapus\.addActionListener\(evt -> controller\.delete\(\)\);', '', content)
    # Replace cmdBersih
    content = re.sub(r'cmdBersih\.addActionListener\(evt -> controller\.reset\(\)\);', '', content)
    
    # Remove item listeners
    content = re.sub(r'cbDosen\.addItemListener\(evt -> \{.*?\n\s*\}\);', '', content, flags=re.DOTALL)
    content = re.sub(r'cbMtk\.addItemListener\(evt -> \{.*?\n\s*\}\);', '', content, flags=re.DOTALL)
    
    # Remove mouse listener
    content = re.sub(r'tblJadwal\.addMouseListener\(new java\.awt\.event\.MouseAdapter\(\) \{.*?\n\s*\}\);', '', content, flags=re.DOTALL)
    
    # Wrap initComponents
    content = content.replace('private void initComponents() {', '//GEN-BEGIN:initComponents\n    private void initComponents() {')
    content = content.replace('        pack();\n    }', '        pack();\n    }\n    //GEN-END:initComponents')
    
    # Add extracted methods at the end before closing brace of class
    methods = """
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
    private void cbMtkItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.fetchNamaMtk();
        }
    }
    private void tblJadwalMouseClicked(java.awt.event.MouseEvent evt) {
        if (controller != null) controller.isiField(tblJadwal.getSelectedRow());
    }
"""
    last_brace = content.rfind('}')
    if last_brace != -1:
        content = content[:last_brace] + methods + content[last_brace:]
        
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
        
refactor_jadwal()
