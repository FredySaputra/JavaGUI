import os, re

def replace_table_model(f):
    path = os.path.join('src', 'Controller', f)
    if not os.path.exists(path): return
    with open(path, 'r', encoding='utf-8') as file:
        content = file.read()
        
    class_name = "MyTableModel" + f.replace("Controller_", "").replace(".java", "")
    class_def = f"""
class {class_name} extends javax.swing.table.DefaultTableModel {{
    public {class_name}(Object[][] data, Object[] columnNames) {{
        super(data, columnNames);
    }}
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {{
        return false;
    }}
}}
"""
    if class_name not in content:
        # insert before 'public class'
        content = re.sub(r'(public class )', class_def + r'\n\1', content)
        
    pattern = r'new\s+DefaultTableModel\s*\(\s*new\s+Object\[\]\[\]\{\}\s*,\s*header\s*\)\s*\{\s*public\s+boolean\s+isCellEditable\s*\(\s*int\s+rowIndex\s*,\s*int\s+columnIndex\s*\)\s*\{\s*return\s+false;\s*\}\s*\};'
    replacement = f'new {class_name}(new Object[][]{{}}, header);'
    
    content = re.sub(pattern, replacement, content)
    
    with open(path, 'w', encoding='utf-8') as file:
        file.write(content)

for f in os.listdir('src/Controller'):
    if f.endswith('.java'):
        replace_table_model(f)
