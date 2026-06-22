import xml.etree.ElementTree as ET

form_file = 'src/View/FrmJadwal.form'
tree = ET.parse(form_file)
root = tree.getroot()

subcomps = root.find('SubComponents')

# Find components outside SubComponents and move them
comps_to_move = []
for child in root:
    if child.tag == 'Component':
        comps_to_move.append(child)

for comp in comps_to_move:
    root.remove(comp)
    subcomps.append(comp)

# Also ensure jLabel9 is inside SubComponents (it already is based on previous script if subcomponents was found)
# Wait, my previous script did:
# subcomponents.insert(0, new_comp) for jLabel9, so it is inside!
# Just txtJamSelesai was inserted to root.

tree.write(form_file, encoding='UTF-8', xml_declaration=True)
