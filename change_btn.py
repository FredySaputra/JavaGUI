import re

filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View\FrmKPRS.java"
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace('cmdTambah.setText("Tambah");', 'cmdTambah.setText("Ubah");')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

filepath_form = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View\FrmKPRS.form"
with open(filepath_form, 'r', encoding='utf-8') as f:
    content_form = f.read()

content_form = content_form.replace('value="Tambah"', 'value="Ubah"')

with open(filepath_form, 'w', encoding='utf-8') as f:
    f.write(content_form)
