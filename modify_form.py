import xml.etree.ElementTree as ET
import os

form_path = 'src/View/FrmJadwal.form'
java_path = 'src/View/FrmJadwal.java'

tree = ET.parse(form_path)
root = tree.getroot()

# Find the AbsoluteLayout SubComponents
components = root.find('SubComponents')

def set_bounds(name, x, y, width, height):
    for comp in components:
        if comp.get('name') == name:
            for prop in comp.findall('.//AbsoluteConstraints'):
                prop.set('x', str(x))
                prop.set('y', str(y))
                prop.set('width', str(width))
                prop.set('height', str(height))

# Change labels:
# jLabel5 -> Sesi
# jLabel6 -> Dosen
# jLabel7 -> Matakuliah
for comp in components:
    if comp.get('name') == 'jLabel5':
        comp.find('.//Property[@name="text"]').set('value', 'Sesi Mulai')
    elif comp.get('name') == 'jLabel7':
        comp.find('.//Property[@name="text"]').set('value', 'Matakuliah')
    elif comp.get('name') == 'jLabel6':
        comp.find('.//Property[@name="text"]').set('value', 'Dosen')

# Apply new bounds
set_bounds('jLabel7', 12, 136, 100, 16) # Matakuliah Label
set_bounds('cbMtk', 125, 136, 150, 25)
set_bounds('txtNamaMtk', 287, 138, 250, 20)

set_bounds('jLabel5', 12, 167, 100, 16) # Sesi Label
set_bounds('cbSesi', 125, 167, 150, 25)
# Note: we need to create txtJamSelesai in the Java code and .form
# But it's easier to just recreate the entire FrmJadwal using UIConverter, NO, UIConverter doesn't exist to generate .form.
