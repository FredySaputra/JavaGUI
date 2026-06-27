import xml.etree.ElementTree as ET

tree = ET.parse('src/View/FrmKPRS.form')
root = tree.getroot()

for component in root.findall('.//Component'):
    if component.attrib.get('class') == 'javax.swing.JButton':
        name = component.attrib.get('name')
        if name == 'btnTambah':
            root.find('.//SubComponents').remove(component)
        elif name == 'btnUbah':
            for prop in component.findall('.//Property'):
                if prop.attrib.get('name') == 'text':
                    prop.attrib['value'] = 'Simpan'
            for constr in component.findall('.//AbsoluteConstraints'):
                constr.attrib['x'] = '20'
        elif name == 'btnHapus':
            for constr in component.findall('.//AbsoluteConstraints'):
                constr.attrib['x'] = '120'
        elif name == 'btnBersih':
            for prop in component.findall('.//Property'):
                if prop.attrib.get('name') == 'text':
                    prop.attrib['value'] = 'Batal'
            for constr in component.findall('.//AbsoluteConstraints'):
                constr.attrib['x'] = '220'

tree.write('src/View/FrmKPRS.form', encoding='utf-8', xml_declaration=True)
