import javax.swing.JFileChooser;

public class FileChooser {
    private static JFileChooser instanceObj = null;
    public static final int APPROVE_OPTION = JFileChooser.APPROVE_OPTION;

    private FileChooser(){}

    public static JFileChooser instance() {
        if (instanceObj == null) {
            instanceObj = new JFileChooser();
        }
        return instanceObj;
    }
}

