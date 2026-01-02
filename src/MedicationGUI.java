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

    private JTextField tfDiagnosis = new JTextField(20);
    private Medication[] medications;
    private JCheckBox[] cbMedications;

    private JButton nextButton = new JButton("Next");

    public MedicationGUI(Patient patient, Doctor doctor, Nurse nurse, Room room, int stayDays) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.room = room;
        this.stayDays = stayDays;

        setTitle("Hospital Management - Medication");
        setSize(500, 450);
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
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.add(new JLabel("Diagnosis:"));
        topPanel.add(tfDiagnosis);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel medPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        medPanel.setBorder(BorderFactory.createTitledBorder("Available Medications"));
        for (int i = 0; i < medications.length; i++) {
            cbMedications[i] = new JCheckBox(medications[i].getName() + " (RM " + medications[i].getPrice() + ")");
            medPanel.add(cbMedications[i]);
        }
        mainPanel.add(new JScrollPane(medPanel), BorderLayout.CENTER);

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
        if (tfDiagnosis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a diagnosis!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Medication> selectedMeds = new ArrayList<>();
        for (int i = 0; i < cbMedications.length; i++) {
            if (cbMedications[i].isSelected()) {
                selectedMeds.add(medications[i]);
            }
        }

        this.dispose();
        new BillingGUI(patient, doctor, nurse, room, stayDays, selectedMeds).createAndShowGUI();
    }
}
