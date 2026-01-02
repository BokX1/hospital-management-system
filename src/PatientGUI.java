import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI for collecting patient's personal information.
 */
public class PatientGUI extends JFrame implements ActionListener {

    private JTextField tfName = new JTextField(20);
    private JTextField tfID = new JTextField(20);
    private JTextField tfPhoneNum = new JTextField(20);
    private JTextField tfGuardianName = new JTextField(20);
    private JTextField tfGuardianPhoneNum = new JTextField(20);
    
    private JRadioButton rbMale = new JRadioButton("Male");
    private JRadioButton rbFemale = new JRadioButton("Female");
    private ButtonGroup genderGroup = new ButtonGroup();

    private JButton nextButton = new JButton("Next");

    public PatientGUI() {
        setTitle("Hospital Management - Patient Registration");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        formPanel.add(new JLabel("Patient Name:"));
        formPanel.add(tfName);

        formPanel.add(new JLabel("Patient ID/IC:"));
        formPanel.add(tfID);

        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(tfPhoneNum);

        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Guardian Name:"));
        formPanel.add(tfGuardianName);

        formPanel.add(new JLabel("Guardian Phone:"));
        formPanel.add(tfGuardianPhoneNum);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
