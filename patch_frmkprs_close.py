import xml.etree.ElementTree as ET

tree = ET.parse('src/View/FrmKPRS.form')
root = tree.getroot()

pnlTitle = root.find(".//Component[@name='pnlTitle']")
if pnlTitle is None:
    pnlTitle = root.find(".//Container[@name='pnlTitle']")

# Check if lblClose already exists
exists = False
if pnlTitle is not None:
    for comp in pnlTitle.findall(".//Component"):
        if comp.attrib.get('name') == 'lblClose':
            exists = True
            break
            
if not exists and pnlTitle is not None:
    subcomponents = pnlTitle.find('SubComponents')
    if subcomponents is None:
        subcomponents = ET.SubElement(pnlTitle, 'SubComponents')
        
    lblClose = ET.SubElement(subcomponents, 'Component')
    lblClose.set('class', 'javax.swing.JLabel')
    lblClose.set('name', 'lblClose')
    
    props = ET.SubElement(lblClose, 'Properties')
    
    font_prop = ET.SubElement(props, 'Property')
    font_prop.set('name', 'font')
    font_prop.set('type', 'java.awt.Font')
    font_prop.set('editor', 'org.netbeans.beaninfo.editors.FontEditor')
    font_elem = ET.SubElement(font_prop, 'Font')
    font_elem.set('name', 'Tahoma')
    font_elem.set('size', '18')
    font_elem.set('style', '1')
    
    halign_prop = ET.SubElement(props, 'Property')
    halign_prop.set('name', 'horizontalAlignment')
    halign_prop.set('type', 'int')
    halign_prop.set('value', '0')
    
    text_prop = ET.SubElement(props, 'Property')
    text_prop.set('name', 'text')
    text_prop.set('type', 'java.lang.String')
    text_prop.set('value', 'X')
    
    cursor_prop = ET.SubElement(props, 'Property')
    cursor_prop.set('name', 'cursor')
    cursor_prop.set('type', 'java.awt.Cursor')
    cursor_prop.set('editor', 'org.netbeans.modules.form.editors2.CursorEditor')
    cursor_elem = ET.SubElement(cursor_prop, 'Color')
    cursor_elem.set('id', 'Hand Cursor')
    
    constraints = ET.SubElement(lblClose, 'Constraints')
    constraint = ET.SubElement(constraints, 'Constraint')
    constraint.set('layoutClass', 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout')
    constraint.set('value', 'org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription')
    abs_con = ET.SubElement(constraint, 'AbsoluteConstraints')
    abs_con.set('x', '810')
    abs_con.set('y', '10')
    abs_con.set('width', '30')
    abs_con.set('height', '20')

tree.write('src/View/FrmKPRS.form', encoding='utf-8', xml_declaration=True)
