import re

dao_filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\DAO\DAO_KPRS.java"
controller_filepath = r"D:\Documents\Kuliah\IPBO\SistemAkademik\src\Controller\Controller_KPRS.java"

# 1. Update DAO_KPRS.java
with open(dao_filepath, 'r', encoding='utf-8') as f:
    dao_content = f.read()

dao_combo_methods = """
    public List<String> isicomboTA(){
        PreparedStatement statement;
        List<String> list = new ArrayList<>();
        try{
            statement = conn.prepareStatement("SELECT DISTINCT TA FROM periode ORDER BY TA");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(rs.getString("TA"));
            }
            rs.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String> isicomboSMT(){
        PreparedStatement statement;
        List<String> list = new ArrayList<>();
        try{
            statement = conn.prepareStatement("SELECT DISTINCT Semester FROM periode ORDER BY Semester");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(rs.getString("Semester"));
            }
            rs.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
"""

if "isicomboTA" not in dao_content:
    dao_content = dao_content.replace("public String getNamaMahasiswa(String nim) {", dao_combo_methods + "\n    public String getNamaMahasiswa(String nim) {")
    with open(dao_filepath, 'w', encoding='utf-8') as f:
        f.write(dao_content)

# 2. Update Controller_KPRS.java
with open(controller_filepath, 'r', encoding='utf-8') as f:
    ctrl_content = f.read()

ctrl_combo_methods = """
    public void isicomboTahunAjaran(){
        form.getCboTA().removeAllItems();
        form.getCboTA().addItem("-Pilih-");
        for(String ta: model_internal.isicomboTA()){
            form.getCboTA().addItem(ta);
        }
    }
    
    public void isicomboSemester(){
        form.getCboSemester().removeAllItems();
        form.getCboSemester().addItem("-Pilih-");
        for(String smt: model_internal.isicomboSMT()){
            form.getCboSemester().addItem(smt);
        }
    }
"""

if "isicomboTahunAjaran" not in ctrl_content:
    ctrl_content = ctrl_content.replace("public void isiTabel(){", ctrl_combo_methods + "\n    public void isiTabel(){")
    
    # Call them in reset()
    ctrl_content = ctrl_content.replace("form.getTxtNIM().setText(\"\");", "form.getTxtNIM().setText(\"\");\n        isicomboTahunAjaran();\n        isicomboSemester();")
    
    with open(controller_filepath, 'w', encoding='utf-8') as f:
        f.write(ctrl_content)
