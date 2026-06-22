import os, re

def fix_events(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # Find all event handler methods
    action_handlers = re.findall(r'private void ([a-zA-Z0-9_]+)ActionPerformed\(java\.awt\.event\.ActionEvent [a-zA-Z0-9_]+\)', content)
    mouse_handlers = re.findall(r'private void ([a-zA-Z0-9_]+)MouseClicked\(java\.awt\.event\.MouseEvent [a-zA-Z0-9_]+\)', content)
    key_released_handlers = re.findall(r'private void ([a-zA-Z0-9_]+)KeyReleased\(java\.awt\.event\.KeyEvent [a-zA-Z0-9_]+\)', content)
    key_pressed_handlers = re.findall(r'private void ([a-zA-Z0-9_]+)KeyPressed\(java\.awt\.event\.KeyEvent [a-zA-Z0-9_]+\)', content)
    item_state_handlers = re.findall(r'private void ([a-zA-Z0-9_]+)ItemStateChanged\(java\.awt\.event\.ItemEvent [a-zA-Z0-9_]+\)', content)

    bindings = []
    for h in action_handlers:
        comp = h.replace('ActionPerformed', '')
        bindings.append(f"        if({comp} != null) {comp}.addActionListener(new java.awt.event.ActionListener() {{ public void actionPerformed(java.awt.event.ActionEvent evt) {{ {h}(evt); }} }});")
    
    for h in mouse_handlers:
        comp = h.replace('MouseClicked', '')
        bindings.append(f"        if({comp} != null) {comp}.addMouseListener(new java.awt.event.MouseAdapter() {{ public void mouseClicked(java.awt.event.MouseEvent evt) {{ {h}(evt); }} }});")

    for h in key_released_handlers:
        comp = h.replace('KeyReleased', '')
        bindings.append(f"        if({comp} != null) {comp}.addKeyListener(new java.awt.event.KeyAdapter() {{ public void keyReleased(java.awt.event.KeyEvent evt) {{ {h}(evt); }} }});")

    for h in key_pressed_handlers:
        comp = h.replace('KeyPressed', '')
        bindings.append(f"        if({comp} != null) {comp}.addKeyListener(new java.awt.event.KeyAdapter() {{ public void keyPressed(java.awt.event.KeyEvent evt) {{ {h}(evt); }} }});")

    for h in item_state_handlers:
        comp = h.replace('ItemStateChanged', '')
        bindings.append(f"        if({comp} != null) {comp}.addItemListener(new java.awt.event.ItemListener() {{ public void itemStateChanged(java.awt.event.ItemEvent evt) {{ {h}(evt); }} }});")

    if not bindings:
        return

    bindings_code = "\n".join(bindings)

    # Insert right after initComponents(); but avoid double inserting
    if "/* CUSTOM_BINDINGS_START */" in content:
        # replace existing
        content = re.sub(r'/\* CUSTOM_BINDINGS_START \*/.*?/\* CUSTOM_BINDINGS_END \*/', 
                         f'/* CUSTOM_BINDINGS_START */\n{bindings_code}\n        /* CUSTOM_BINDINGS_END */', 
                         content, flags=re.DOTALL)
    else:
        # inject after initComponents();
        replacement = f'initComponents();\n        /* CUSTOM_BINDINGS_START */\n{bindings_code}\n        /* CUSTOM_BINDINGS_END */'
        content = content.replace('initComponents();', replacement, 1)

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

for root, dirs, files in os.walk('src/View'):
    for file in files:
        if file.endswith('.java'):
            fix_events(os.path.join(root, file))
