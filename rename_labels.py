import os

def replace_in_file(filepath, replacements):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    for old, new in replacements.items():
        content = content.replace(old, new)

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

base_dir = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View"
java_file = os.path.join(base_dir, "FrmKPRS.java")
form_file = os.path.join(base_dir, "FrmKPRS.form")

replacements = {
    '"Data KRSS"': '"Data KPRS"',
    '"Kartu Rencana Studi Sementara"': '"Kartu Perubahan Rencana Studi"',
    '"KRSS"': '"KPRS"',
    '"Kartu Rencana Studi Sementara (KRSS)"': '"Kartu Perubahan Rencana Studi (KPRS)"'
}

replace_in_file(java_file, replacements)
replace_in_file(form_file, replacements)
