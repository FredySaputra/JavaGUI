import os

def copy_and_replace(src_java, src_form, dst_java, dst_form, replacements):
    with open(src_java, 'r', encoding='utf-8') as f:
        java_content = f.read()
    with open(src_form, 'r', encoding='utf-8') as f:
        form_content = f.read()

    for old, new in replacements.items():
        java_content = java_content.replace(old, new)
        form_content = form_content.replace(old, new)

    with open(dst_java, 'w', encoding='utf-8') as f:
        f.write(java_content)
    with open(dst_form, 'w', encoding='utf-8') as f:
        f.write(form_content)

base_dir = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\View"
copy_and_replace(
    os.path.join(base_dir, "FrmKRSS.java"),
    os.path.join(base_dir, "FrmKRSS.form"),
    os.path.join(base_dir, "FrmKPRS.java"),
    os.path.join(base_dir, "FrmKPRS.form"),
    {
        "FrmKRSS": "FrmKPRS",
        "varKRSS": "varDetilKPRS",
        "Controller_KRSS": "Controller_KPRS"
    }
)
