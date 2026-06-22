import re
import os

files = ['FrmDosen.java', 'FrmRuang.java', 'FrmSesi.java', 'FrmJadwal.java']

def process_file(f):
    path = os.path.join('src', 'View', f)
    if not os.path.exists(path):
        print(f"Skipping {f}, not found.")
        return
        
    with open(path, 'r', encoding='utf-8') as file:
        content = file.read()
        
    # We will generate a basic .form file with AbsoluteLayout
    # And we will surround initComponents with GEN-BEGIN and GEN-END
    # But event handlers need to be extracted.
    
    # Actually, NetBeans can just convert a Java file to a form if we leave it alone? No, it wipes it.
    
    # We will let the script identify all components and write the XML
    
    # To keep it simple, we can just replace the layout with an empty AbsoluteLayout,
    # but that destroys their hand-crafted GroupLayout code!
    
    # Is there a better way? Yes! If we create a .form file containing ALL their components, 
    # but put them in a FreeDesign layout? NetBeans GroupLayout XML is very complex.
    
    print(f"File: {f} has {len(content)} bytes")

for f in files:
    process_file(f)
