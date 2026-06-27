import re

filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View\FrmKPRS.java"
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace("controller.tampilDataMatakuliah();", "// controller.tampilDataMatakuliah();")
content = content.replace("controller.delete();", "// controller.delete();")
content = content.replace("controller.tampilNmMahasiswa();", "// controller.tampilNmMahasiswa();")
content = content.replace("controller.insert();", "// controller.insert();")

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)
