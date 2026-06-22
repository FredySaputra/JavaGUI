import re

java_file = 'src/Controller/Controller_Jadwal.java'
with open(java_file, 'r', encoding='utf-8') as f:
    jcode = f.read()

# Add realTimeValidation method
if 'public void realTimeValidation()' not in jcode:
    jcode = jcode.replace('public boolean isValidInput() {', '''
    public void realTimeValidation() {
        if(!isValidInput()) return;
        
        String ta = form.getCbTA().getSelectedItem().toString();
        String sem = form.getCbSemester().getSelectedItem().toString();
        String ruang = form.getCbRuang().getSelectedItem().toString().trim();
        String hari = form.getCbHari().getSelectedItem().toString();
        int sesi = Integer.parseInt(form.getCbSesi().getSelectedItem().toString().trim());
        String nip = form.getCbDosen().getSelectedItem().toString().trim();
        
        // Let checkConflict show the pane if there's a conflict
        checkConflict(ta, sem, ruang, hari, sesi, calculatedSesiSelesai, nip, selectedId);
    }
    
    public boolean isValidInput() {''')

# In fetchNamaMtk and calculateJamSelesai, call realTimeValidation at the end
jcode = jcode.replace('calculateJamSelesai();\n    }', 'calculateJamSelesai();\n        realTimeValidation();\n    }')
jcode = jcode.replace('form.getTxtJamSelesai().setText("");\n            }\n        }', 'form.getTxtJamSelesai().setText("");\n            }\n        }\n        realTimeValidation();')


with open(java_file, 'w', encoding='utf-8') as f:
    f.write(jcode)


# Now FrmJadwal.java: Add item listeners to ALL combo boxes
frm_file = 'src/View/FrmJadwal.java'
with open(frm_file, 'r', encoding='utf-8') as f:
    fcode = f.read()

def add_listener(combo_name, fcode):
    if f'{combo_name}ItemStateChanged' not in fcode:
        fcode = fcode.replace('/* CUSTOM_BINDINGS_END */', f'if({combo_name} != null) {combo_name}.addItemListener(new java.awt.event.ItemListener() {{ public void itemStateChanged(java.awt.event.ItemEvent evt) {{ {combo_name}ItemStateChanged(evt); }} }});\n        /* CUSTOM_BINDINGS_END */')
        
        method_str = f"""
    private void {combo_name}ItemStateChanged(java.awt.event.ItemEvent evt) {{
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {{
            if (controller != null) controller.realTimeValidation();
        }}
    }}
"""
        fcode = fcode.replace('private void cbMtkItemStateChanged', method_str + '    private void cbMtkItemStateChanged')
    return fcode

fcode = add_listener('cbTA', fcode)
fcode = add_listener('cbSemester', fcode)
fcode = add_listener('cbRuang', fcode)
fcode = add_listener('cbHari', fcode)

with open(frm_file, 'w', encoding='utf-8') as f:
    f.write(fcode)
