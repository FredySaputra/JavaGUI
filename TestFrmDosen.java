import View.FrmDosen;

public class TestFrmDosen {
    public static void main(String[] args) {
        try {
            System.out.println("Instantiating FrmDosen...");
            FrmDosen form = new FrmDosen();
            System.out.println("Success!");
            System.exit(0);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
