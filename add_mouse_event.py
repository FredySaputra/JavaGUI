import re
import os

def add_mouse_event(f, tbl_name):
    path = os.path.join('src', 'View', f)
    if not os.path.exists(path): return
    with open(path, 'r', encoding='utf-8') as file: content = file.read()
    
    pattern = r'(</Properties>)(\s*)</Component>'
    replacement = r'\1\2  <Events>\n\2    <EventHandler event="mouseClicked" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="tbl' + tbl_name + r'MouseClicked"/>\n\2  </Events>\n\2</Component>'
    
    if '<Table columnCount' in content and 'EventHandler event="mouseClicked"' not in content:
        content = re.sub(pattern, replacement, content, count=1)
        with open(path, 'w', encoding='utf-8') as file: file.write(content)

add_mouse_event('FrmDosen.form', 'Dosen')
add_mouse_event('FrmRuang.form', 'Ruang')
add_mouse_event('FrmSesi.form', 'Sesi')
