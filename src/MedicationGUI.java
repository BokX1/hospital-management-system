import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI for prescribing medications to a patient.
 */
public class MedicationGUI extends JFrame implements ActionListener {

    private Patient patient;
    private Doctor doctor;
    private Nurse nurse;
    private Room room;
    private int stayDays;

    private JTextField tfDiagnosis = UITheme.createTextField(20);
    private Medication[] medications;
    private JCheckBox[] cbMedications;

    private JButton nextButton = UITheme.createPrimaryButton("Next");

    public MedicationGUI(Patient patient, Doctor doctor, Nurse nurse, Room room, int stayDays) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.room = room;
        this.stayDays = stayDays;

        setTitle("Hospital Management - Medication");
        setSize(560, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        medications = new Medication[] {
            new Medication("Paracetamol", 5.00, "Painkiller", "Tablet"),
            new Medication("Ibuprofen", 8.00, "Anti-inflammatory", "Tablet"),
            new Medication("Amoxicillin", 12.00, "Antibiotic", "Capsule"),
            new Medication("Cetirizine", 6.00, "Antihistamine", "Tablet"),
            new Medication("Salbutamol", 15.00, "Respiratory", "Inhaler")
        };

        cbMedications = new JCheckBox[medications.length];

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(UITheme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel header = UITheme.createHeader("Medication & Diagnosis", "Capture diagnosis and select medications for " + patient.getName() + ".");
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel topPanel = UITheme.createCard(new GridLayout(2, 1, 6, 6));
        topPanel.add(UITheme.createLabel("Diagnosis:"));
        topPanel.add(tfDiagnosis);

        JPanel medPanel = UITheme.createCard(new GridLayout(0, 2, 8, 8));
        medPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UITheme.BORDER), "Available Medications"),
            new EmptyBorder(8, 8, 8, 8)
        ));
        for (int i = 0; i < medications.length; i++) {
            cbMedications[i] = new JCheckBox(medications[i].getName() + " (RM " + medications[i].getPrice() + ")");
            UITheme.styleToggle(cbMedications[i]);
            medPanel.add(cbMedications[i]);
        }

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(medPanel), BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

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
        if (tfDiagnosis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a diagnosis!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String diagnosis = tfDiagnosis.getText().trim();
        ArrayList<Medication> selectedMeds = new ArrayList<>();
        for (int i = 0; i < cbMedications.length; i++) {
            if (cbMedications[i].isSelected()) {
                selectedMeds.add(medications[i]);
            }
        }

        this.dispose();
        new BillingGUI(patient, doctor, nurse, room, stayDays, selectedMeds, diagnosis).createAndShowGUI();
    }
}
