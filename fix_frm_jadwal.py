import xml.etree.ElementTree as ET

# 1. Update .form file
form_file = 'src/View/FrmJadwal.form'
tree = ET.parse(form_file)
root = tree.getroot()

# The container holds all subcomponents
# Actually wait, AbsoluteLayout puts constraints inside <Component><Constraints>...
for comp in root.findall('.//Component'):
    name = comp.get('name')
    constraints = comp.find('.//AbsoluteConstraints')
    
    if name == 'jLabel5': # Sesi Mulai
        comp.find('.//Property[@name="text"]').set('value', 'Sesi Mulai')
        if constraints is not None:
            constraints.set('y', '167')
    elif name == 'cbSesi':
        if constraints is not None:
            constraints.set('y', '167')
            
    elif name == 'jLabel7': # Matakuliah
        comp.find('.//Property[@name="text"]').set('value', 'Matakuliah')
        if constraints is not None:
            constraints.set('y', '136')
    elif name == 'cbMtk':
        if constraints is not None:
            constraints.set('y', '136')
    elif name == 'txtNamaMtk':
        if constraints is not None:
            constraints.set('y', '138')

    elif name == 'jLabel6': # Dosen
        comp.find('.//Property[@name="text"]').set('value', 'Dosen')
        if constraints is not None:
            constraints.set('y', '198')
    elif name == 'cbDosen':
        if constraints is not None:
            constraints.set('y', '198')
    elif name == 'txtNamaDosen':
        if constraints is not None:
            constraints.set('y', '200')

# Create txtJamSelesai in .form
new_comp = ET.Element('Component', {'class': 'javax.swing.JTextField', 'name': 'txtJamSelesai'})
props = ET.SubElement(new_comp, 'Properties')
# read only
editable_prop = ET.SubElement(props, 'Property', {'name': 'editable', 'type': 'boolean', 'value': 'false'})

constraints_tag = ET.SubElement(new_comp, 'Constraints')
layout_tag = ET.SubElement(constraints_tag, 'Constraint', {
    'layoutClass': 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout',
    'value': 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription'
})
abs_constraints = ET.SubElement(layout_tag, 'AbsoluteConstraints', {
    'x': '287', 'y': '169', 'width': '250', 'height': '20'
})

root.insert(len(root) - 1, new_comp) # Add to root

tree.write(form_file, encoding='UTF-8', xml_declaration=True)

# 2. Update .java file
java_file = 'src/View/FrmJadwal.java'
with open(java_file, 'r', encoding='utf-8') as f:
    jcode = f.read()

if 'public JTextField getTxtJamSelesai()' not in jcode:
    jcode = jcode.replace('public JTextField getTxtKelompok()', 'public JTextField getTxtJamSelesai() { return txtJamSelesai; }\n    public JTextField getTxtKelompok()')

if 'txtJamSelesai = new javax.swing.JTextField();' not in jcode:
    jcode = jcode.replace('txtKelompok = new javax.swing.JTextField();', 'txtKelompok = new javax.swing.JTextField();\n        txtJamSelesai = new javax.swing.JTextField();')

if 'txtJamSelesai.setEditable(false);' not in jcode:
    jcode = jcode.replace('txtNamaMtk.setEditable(false);', 'txtNamaMtk.setEditable(false);\n        txtJamSelesai.setEditable(false);')

if 'private javax.swing.JTextField txtJamSelesai;' not in jcode:
    jcode = jcode.replace('private javax.swing.JTextField txtKelompok;', 'private javax.swing.JTextField txtJamSelesai;\n    private javax.swing.JTextField txtKelompok;')

if 'jLabel5.setText("Sesi");' in jcode:
    jcode = jcode.replace('jLabel5.setText("Sesi");', 'jLabel5.setText("Sesi Mulai");')

# Now fix the NetBeans generated layout block!
# Because it's AbsoluteLayout, we can just replace the layout block. Actually wait, the layout code might still reflect the old Y values.
# Let's just use regex to replace the bounds in the java file.
import re
jcode = re.sub(r'cbMtk\.setBounds\(\d+, \d+, \d+, \d+\);', 'cbMtk.setBounds(125, 136, 150, 25);', jcode)
jcode = re.sub(r'jLabel7\.setBounds\(\d+, \d+, \d+, \d+\);', 'jLabel7.setBounds(12, 136, 100, 16);', jcode)
jcode = re.sub(r'txtNamaMtk\.setBounds\(\d+, \d+, \d+, \d+\);', 'txtNamaMtk.setBounds(287, 138, 250, 20);', jcode)

jcode = re.sub(r'cbSesi\.setBounds\(\d+, \d+, \d+, \d+\);', 'cbSesi.setBounds(125, 167, 150, 25);', jcode)
jcode = re.sub(r'jLabel5\.setBounds\(\d+, \d+, \d+, \d+\);', 'jLabel5.setBounds(12, 167, 100, 16);', jcode)

jcode = re.sub(r'cbDosen\.setBounds\(\d+, \d+, \d+, \d+\);', 'cbDosen.setBounds(125, 198, 150, 25);', jcode)
jcode = re.sub(r'jLabel6\.setBounds\(\d+, \d+, \d+, \d+\);', 'jLabel6.setBounds(12, 198, 100, 16);', jcode)
jcode = re.sub(r'txtNamaDosen\.setBounds\(\d+, \d+, \d+, \d+\);', 'txtNamaDosen.setBounds(287, 200, 250, 20);', jcode)

if 'txtJamSelesai.setBounds' not in jcode:
    jcode = jcode.replace('getContentPane().add(txtKelompok);', 'getContentPane().add(txtJamSelesai);\n        txtJamSelesai.setBounds(287, 169, 250, 20);\n        getContentPane().add(txtKelompok);')

with open(java_file, 'w', encoding='utf-8') as f:
    f.write(jcode)
