package View;

import java.awt.Component;
import java.awt.Container;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class UIConverter {
    public static void main(String[] args) throws Exception {
        convert("View.FrmDosen", "src/View/FrmDosen.form");
        convert("View.FrmRuang", "src/View/FrmRuang.form");
        convert("View.FrmSesi", "src/View/FrmSesi.form");
        convert("View.FrmJadwal", "src/View/FrmJadwal.form");
    }

    public static void convert(String className, String outFile) throws Exception {
        Class<?> clazz = Class.forName(className);
        JFrame frame = (JFrame) clazz.getDeclaredConstructor().newInstance();
        frame.pack();

        Map<Component, String> compNames = new HashMap<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            Object val = f.get(frame);
            if (val instanceof Component) {
                compNames.put((Component) val, f.getName());
            }
        }

        try (PrintWriter out = new PrintWriter(outFile)) {
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<Form version=\"1.3\" maxVersion=\"1.9\" type=\"org.netbeans.modules.form.forminfo.JFrameFormInfo\">");
            out.println("  <Properties>");
            out.println("    <Property name=\"defaultCloseOperation\" type=\"int\" value=\"2\"/>");
            out.println("    <Property name=\"title\" type=\"java.lang.String\" value=\"" + frame.getTitle() + "\"/>");
            out.println("  </Properties>");
            out.println("  <SyntheticProperties>");
            out.println("    <SyntheticProperty name=\"formSizePolicy\" type=\"int\" value=\"1\"/>");
            out.println("    <SyntheticProperty name=\"generateCenter\" type=\"boolean\" value=\"false\"/>");
            out.println("  </SyntheticProperties>");
            out.println("  <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout\">");
            out.println("    <Property name=\"useNullLayout\" type=\"boolean\" value=\"true\"/>");
            out.println("  </Layout>");
            out.println("  <SubComponents>");

            writeComponents(frame.getContentPane(), compNames, out, 4);

            out.println("  </SubComponents>");
            out.println("</Form>");
        }
        System.out.println("Generated " + outFile);
    }

    private static void writeComponents(Container container, Map<Component, String> compNames, PrintWriter out, int indent) {
        String ind = "";
        for (int i = 0; i < indent; i++) ind += " ";

        for (Component c : container.getComponents()) {
            String name = compNames.get(c);
            if (name == null) {
                if (c instanceof JScrollPane) name = "jScrollPane1";
                else if (c instanceof JTable) name = "jTable1";
                else name = "comp" + c.hashCode();
            }

            if (c instanceof JScrollPane) {
                out.println(ind + "<Container class=\"javax.swing.JScrollPane\" name=\"" + name + "\">");
                out.println(ind + "  <Constraints>");
                out.println(ind + "    <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription\">");
                out.println(ind + "      <AbsoluteConstraints x=\"" + c.getX() + "\" y=\"" + c.getY() + "\" width=\"" + c.getWidth() + "\" height=\"" + c.getHeight() + "\"/>");
                out.println(ind + "    </Constraint>");
                out.println(ind + "  </Constraints>");
                out.println(ind + "  <Layout class=\"org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout\"/>");
                out.println(ind + "  <SubComponents>");
                if (((JScrollPane) c).getViewport().getView() != null) {
                    Component view = ((JScrollPane) c).getViewport().getView();
                    String viewName = compNames.get(view);
                    if (viewName == null) viewName = "jTable1";
                    
                    out.println(ind + "    <Component class=\"" + view.getClass().getName() + "\" name=\"" + viewName + "\">");
                    
                    if (view instanceof JTable) {
                        out.println(ind + "      <Properties>");
                        out.println(ind + "        <Property name=\"model\" type=\"javax.swing.table.TableModel\" editor=\"org.netbeans.modules.form.editors2.TableModelEditor\">");
                        out.println(ind + "          <Table columnCount=\"4\" rowCount=\"4\">");
                        out.println(ind + "            <Column editable=\"true\" title=\"Title 1\" type=\"java.lang.Object\"/>");
                        out.println(ind + "            <Column editable=\"true\" title=\"Title 2\" type=\"java.lang.Object\"/>");
                        out.println(ind + "            <Column editable=\"true\" title=\"Title 3\" type=\"java.lang.Object\"/>");
                        out.println(ind + "            <Column editable=\"true\" title=\"Title 4\" type=\"java.lang.Object\"/>");
                        out.println(ind + "          </Table>");
                        out.println(ind + "        </Property>");
                        out.println(ind + "      </Properties>");
                    }
                    out.println(ind + "    </Component>");
                }
                out.println(ind + "  </SubComponents>");
                out.println(ind + "</Container>");
                continue;
            }

            String className = c.getClass().getName();
            out.println(ind + "<Component class=\"" + className + "\" name=\"" + name + "\">");

            out.println(ind + "  <Properties>");
            if (c instanceof JLabel) {
                out.println(ind + "    <Property name=\"text\" type=\"java.lang.String\" value=\"" + ((JLabel) c).getText() + "\"/>");
            } else if (c instanceof JButton) {
                out.println(ind + "    <Property name=\"text\" type=\"java.lang.String\" value=\"" + ((JButton) c).getText() + "\"/>");
            }
            out.println(ind + "  </Properties>");
            
            // Events mapping manually generated for known buttons to keep things intact
            if (name.equals("cmdTambah")) {
                out.println(ind + "  <Events>");
                out.println(ind + "    <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"" + name + "ActionPerformed\"/>");
                out.println(ind + "  </Events>");
            } else if (name.equals("cmdUbah") || name.equals("cmdHapus") || name.equals("cmdBersih")) {
                out.println(ind + "  <Events>");
                out.println(ind + "    <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"" + name + "ActionPerformed\"/>");
                out.println(ind + "  </Events>");
            } else if (c instanceof JTextField) {
                if (name.equals("txtNip") || name.equals("txtKodeRuang") || name.equals("txtKodeSesi")) {
                    out.println(ind + "  <Events>");
                    out.println(ind + "    <EventHandler event=\"keyPressed\" listener=\"java.awt.event.KeyListener\" parameters=\"java.awt.event.KeyEvent\" handler=\"" + name + "KeyPressed\"/>");
                    out.println(ind + "    <EventHandler event=\"keyReleased\" listener=\"java.awt.event.KeyListener\" parameters=\"java.awt.event.KeyEvent\" handler=\"" + name + "KeyReleased\"/>");
                    out.println(ind + "  </Events>");
                }
            }

            out.println(ind + "  <Constraints>");
            out.println(ind + "    <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignAbsoluteLayout$AbsoluteConstraintsDescription\">");
            out.println(ind + "      <AbsoluteConstraints x=\"" + c.getX() + "\" y=\"" + c.getY() + "\" width=\"" + c.getWidth() + "\" height=\"" + c.getHeight() + "\"/>");
            out.println(ind + "    </Constraint>");
            out.println(ind + "  </Constraints>");
            out.println(ind + "</Component>");
        }
    }
}
