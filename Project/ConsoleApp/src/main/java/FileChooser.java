import javax.swing.JFileChooser;

public class FileChooser {
    private static JFileChooser instanceObj = null;

    private FileChooser() {
    }

    public static JFileChooser instance() {
        if (instanceObj == null) {
            instanceObj = new JFileChooser();
        }
        return instanceObj;
    }
}

