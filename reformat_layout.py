import xml.etree.ElementTree as ET
import re

form_file = 'src/View/FrmJadwal.form'
tree = ET.parse(form_file)
root = tree.getroot()

def set_bounds_xml(name, x, y, width, height):
    for comp in root.findall('.//Component'):
        if comp.get('name') == name:
            constraints = comp.find('.//AbsoluteConstraints')
            if constraints is not None:
                constraints.set('x', str(x))
                constraints.set('y', str(y))
                constraints.set('width', str(width))
                constraints.set('height', str(height))
    # Also handle JScrollPane which is a Container
    for comp in root.findall('.//Container'):
        if comp.get('name') == name:
            constraints = comp.find('.//AbsoluteConstraints')
            if constraints is not None:
                constraints.set('x', str(x))
                constraints.set('y', str(y))
                constraints.set('width', str(width))
                constraints.set('height', str(height))

# Create jLabel9 for Sesi Selesai if it doesn't exist
subcomponents = root.find('SubComponents')
if subcomponents is None:
    # the root itself contains components if there are floating components outside SubComponents? No, they must be in SubComponents
    subcomponents = root

has_label9 = False
for comp in root.findall('.//Component'):
    if comp.get('name') == 'jLabel9':
        has_label9 = True

if not has_label9:
    new_comp = ET.Element('Component', {'class': 'javax.swing.JLabel', 'name': 'jLabel9'})
    props = ET.SubElement(new_comp, 'Properties')
    ET.SubElement(props, 'Property', {'name': 'text', 'type': 'java.lang.String', 'value': 'Sesi Selesai'})
    constraints_tag = ET.SubElement(new_comp, 'Constraints')
    layout_tag = ET.SubElement(constraints_tag, 'Constraint', {
        'layoutClass': 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout',
        'value': 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription'
    })
    ET.SubElement(layout_tag, 'AbsoluteConstraints', {'x': '430', 'y': '120', 'width': '80', 'height': '16'})
    subcomponents.insert(0, new_comp)

# Set all bounds in XML
set_bounds_xml('jLabel1', 12, 15, 80, 16) # TA
set_bounds_xml('cbTA', 100, 15, 150, 25)

set_bounds_xml('jLabel3', 430, 15, 80, 16) # Ruang
set_bounds_xml('cbRuang', 520, 15, 150, 25)

set_bounds_xml('jLabel2', 12, 50, 80, 16) # Semester
set_bounds_xml('cbSemester', 100, 50, 150, 25)

set_bounds_xml('jLabel4', 430, 50, 80, 16) # Hari
set_bounds_xml('cbHari', 520, 50, 150, 25)

set_bounds_xml('jLabel7', 12, 85, 80, 16) # Mtk
set_bounds_xml('cbMtk', 100, 85, 150, 25)
set_bounds_xml('txtNamaMtk', 260, 85, 150, 25)

set_bounds_xml('jLabel5', 430, 85, 80, 16) # Sesi Mulai
set_bounds_xml('cbSesi', 520, 85, 150, 25)

set_bounds_xml('jLabel8', 12, 120, 80, 16) # Kelompok
set_bounds_xml('txtKelompok', 100, 120, 150, 25)

set_bounds_xml('jLabel9', 430, 120, 80, 16) # Sesi Selesai label
set_bounds_xml('txtJamSelesai', 520, 120, 150, 25) # Jam Selesai text

set_bounds_xml('jLabel6', 12, 155, 80, 16) # Dosen
set_bounds_xml('cbDosen', 100, 155, 150, 25)
set_bounds_xml('txtNamaDosen', 260, 155, 250, 25)

# Buttons
set_bounds_xml('cmdTambah', 100, 200, 80, 26)
set_bounds_xml('cmdUbah', 190, 200, 80, 26)
set_bounds_xml('cmdHapus', 280, 200, 80, 26)
set_bounds_xml('cmdBersih', 370, 200, 80, 26)

# JScrollPane
set_bounds_xml('jScrollPane1', 12, 250, 750, 250)

tree.write(form_file, encoding='UTF-8', xml_declaration=True)

# Now modify the Java file
java_file = 'src/View/FrmJadwal.java'
with open(java_file, 'r', encoding='utf-8') as f:
    jcode = f.read()

# Add jLabel9 if not present
if 'private javax.swing.JLabel jLabel9;' not in jcode:
    jcode = jcode.replace('private javax.swing.JLabel jLabel8;', 'private javax.swing.JLabel jLabel8;\n    private javax.swing.JLabel jLabel9;')
    jcode = jcode.replace('jLabel8 = new javax.swing.JLabel();', 'jLabel8 = new javax.swing.JLabel();\n        jLabel9 = new javax.swing.JLabel();')
    jcode = jcode.replace('jLabel8.setText("Kelompok");', 'jLabel8.setText("Kelompok");\n        jLabel9.setText("Sesi Selesai");')
    jcode = jcode.replace('getContentPane().add(jLabel8);', 'getContentPane().add(jLabel8);\n        getContentPane().add(jLabel9);')
    jcode = jcode.replace('jLabel8.setBounds(12, 120, 80, 16);', 'jLabel8.setBounds(12, 120, 80, 16);\n        jLabel9.setBounds(430, 120, 80, 16);')

# Replace all bounds
def repl_bounds(comp, x, y, w, h, jc):
    # Match any bounds setting for the component
    pattern = rf'{comp}\.setBounds\(\d+,\s*\d+,\s*\d+,\s*\d+\);'
    replacement = f'{comp}.setBounds({x}, {y}, {w}, {h});'
    if re.search(pattern, jc):
        jc = re.sub(pattern, replacement, jc)
    else:
        # If it wasn't there, append it (unlikely unless it was totally missing)
        pass
    return jc

jcode = repl_bounds('jLabel1', 12, 15, 80, 16, jcode)
jcode = repl_bounds('cbTA', 100, 15, 150, 25, jcode)

jcode = repl_bounds('jLabel3', 430, 15, 80, 16, jcode)
jcode = repl_bounds('cbRuang', 520, 15, 150, 25, jcode)

jcode = repl_bounds('jLabel2', 12, 50, 80, 16, jcode)
jcode = repl_bounds('cbSemester', 100, 50, 150, 25, jcode)

jcode = repl_bounds('jLabel4', 430, 50, 80, 16, jcode)
jcode = repl_bounds('cbHari', 520, 50, 150, 25, jcode)

jcode = repl_bounds('jLabel7', 12, 85, 80, 16, jcode)
jcode = repl_bounds('cbMtk', 100, 85, 150, 25, jcode)
jcode = repl_bounds('txtNamaMtk', 260, 85, 150, 25, jcode)

jcode = repl_bounds('jLabel5', 430, 85, 80, 16, jcode)
jcode = repl_bounds('cbSesi', 520, 85, 150, 25, jcode)

jcode = repl_bounds('jLabel8', 12, 120, 80, 16, jcode)
jcode = repl_bounds('txtKelompok', 100, 120, 150, 25, jcode)

jcode = repl_bounds('jLabel9', 430, 120, 80, 16, jcode)
jcode = repl_bounds('txtJamSelesai', 520, 120, 150, 25, jcode)

jcode = repl_bounds('jLabel6', 12, 155, 80, 16, jcode)
jcode = repl_bounds('cbDosen', 100, 155, 150, 25, jcode)
jcode = repl_bounds('txtNamaDosen', 260, 155, 250, 25, jcode)

jcode = repl_bounds('cmdTambah', 100, 200, 80, 26, jcode)
jcode = repl_bounds('cmdUbah', 190, 200, 80, 26, jcode)
jcode = repl_bounds('cmdHapus', 280, 200, 80, 26, jcode)
jcode = repl_bounds('cmdBersih', 370, 200, 80, 26, jcode)

jcode = repl_bounds('jScrollPane1', 12, 250, 750, 250, jcode)

# Ensure window size is big enough to hold components up to y=500
if 'setSize' not in jcode:
    # It might be in generated code, but setLocationRelativeTo manages it. 
    # Just to be safe, let's inject setPreferredSize if needed. Actually AbsoluteLayout usually handles it if the window is resized.
    pass

with open(java_file, 'w', encoding='utf-8') as f:
    f.write(jcode)
