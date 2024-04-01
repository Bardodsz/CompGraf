import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class MainClass {
    public MainClass() {
    }

    public static void main(String[] var0) {
        MainCanvas var1 = new MainCanvas();
        JFrame var2 = new JFrame();
        var2.setSize(1000, 800);
        var2.setVisible(true);
        var2.getContentPane().add(var1);
        var2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent var1) {
                System.exit(0);
            }
        });
        var1.start();
    }
}