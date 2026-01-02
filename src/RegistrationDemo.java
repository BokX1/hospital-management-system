import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RegistrationDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Keep default look and feel if system one is not available
            }

            PatientGUI frame1 = new PatientGUI();
            frame1.createAndShowGUI();
        });
    }
}
