import re

# 1. Update FrmJadwal.java to add ItemListener for cbSesi
with open('src/View/FrmJadwal.java', 'r', encoding='utf-8') as f:
    jcode = f.read()

if 'cbSesiItemStateChanged(evt);' not in jcode:
    jcode = jcode.replace(
        'if(cbMtk != null) cbMtk.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbMtkItemStateChanged(evt); } });',
        'if(cbMtk != null) cbMtk.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbMtkItemStateChanged(evt); } });\n        if(cbSesi != null) cbSesi.addItemListener(new java.awt.event.ItemListener() { public void itemStateChanged(java.awt.event.ItemEvent evt) { cbSesiItemStateChanged(evt); } });'
    )

if 'private void cbSesiItemStateChanged' not in jcode:
    jcode = jcode.replace(
        'private void cbMtkItemStateChanged(java.awt.event.ItemEvent evt) {',
        '''private void cbSesiItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (controller != null) controller.calculateJamSelesai();
        }
    }
    private void cbMtkItemStateChanged(java.awt.event.ItemEvent evt) {'''
    )

with open('src/View/FrmJadwal.java', 'w', encoding='utf-8') as f:
    f.write(jcode)
