import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI for collecting patient's personal information.
 */
public class PatientGUI extends JFrame implements ActionListener {

    private JTextField tfName = UITheme.createTextField(20);
    private JTextField tfID = UITheme.createTextField(20);
    private JTextField tfPhoneNum = UITheme.createTextField(20);
    private JTextField tfGuardianName = UITheme.createTextField(20);
    private JTextField tfGuardianPhoneNum = UITheme.createTextField(20);
    
    private JRadioButton rbMale = new JRadioButton("Male");
    private JRadioButton rbFemale = new JRadioButton("Female");
    private ButtonGroup genderGroup = new ButtonGroup();

    private JButton nextButton = UITheme.createPrimaryButton("Next");

    public PatientGUI() {
        setTitle("Hospital Management - Patient Registration");
        setSize(520, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(UITheme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel header = UITheme.createHeader("Patient Registration", "Capture patient and guardian details before assignment.");
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel formPanel = UITheme.createCard(new GridLayout(7, 2, 12, 12));

        formPanel.add(UITheme.createLabel("Patient Name:"));
        formPanel.add(tfName);

        formPanel.add(UITheme.createLabel("Patient ID/IC:"));
        formPanel.add(tfID);

        formPanel.add(UITheme.createLabel("Phone Number:"));
        formPanel.add(tfPhoneNum);

        formPanel.add(UITheme.createLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setOpaque(false);
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        UITheme.styleToggle(rbMale);
        UITheme.styleToggle(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel);

        formPanel.add(UITheme.createLabel("Guardian Name:"));
        formPanel.add(tfGuardianName);

        formPanel.add(UITheme.createLabel("Guardian Phone:"));
        formPanel.add(tfGuardianPhoneNum);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void createAndShowGUI() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateInput()) {
            Patient patient = new Patient(
                tfName.getText().trim(),
                tfID.getText().trim(),
                rbMale.isSelected() ? "Male" : "Female",
                tfPhoneNum.getText().trim(),
                tfGuardianName.getText().trim(),
                tfGuardianPhoneNum.getText().trim()
            );

            this.dispose();
            new AssignmentGUI(patient).createAndShowGUI();
        }
    }

    private boolean validateInput() {
        if (tfName.getText().trim().isEmpty() || 
            tfID.getText().trim().isEmpty() || 
            tfPhoneNum.getText().trim().isEmpty() || 
            tfGuardianName.getText().trim().isEmpty() || 
            tfGuardianPhoneNum.getText().trim().isEmpty()) {
            showError("Please fill in all fields!");
            return false;
        }

        if (genderGroup.getSelection() == null) {
            showError("Please select a gender!");
            return false;
        }

        if (!tfID.getText().trim().matches("[0-9-]+")) {
            showError("ID must contain only digits or dashes!");
            return false;
        }

        if (!tfPhoneNum.getText().trim().matches("\\d+") || !tfGuardianPhoneNum.getText().trim().matches("\\d+")) {
            showError("Phone numbers must contain only digits!");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
