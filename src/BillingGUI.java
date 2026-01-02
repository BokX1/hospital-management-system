import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI for displaying the final bill and patient summary.
 */
public class BillingGUI extends JFrame implements ActionListener {

    private Patient patient;
    private Doctor doctor;
    private Nurse nurse;
    private Room room;
    private int stayDays;
    private ArrayList<Medication> selectedMeds;
    private String diagnosis;

    public BillingGUI(Patient patient, Doctor doctor, Nurse nurse, Room room, int stayDays, ArrayList<Medication> selectedMeds, String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.room = room;
        this.stayDays = stayDays;
        this.selectedMeds = selectedMeds;
        this.diagnosis = diagnosis;

        setTitle("Hospital Management - Final Billing");
        setSize(600, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        Billing billing = new Billing(doctor, room, stayDays, selectedMeds);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(UITheme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel header = UITheme.createHeader("Final Review", "Confirm patient details and charges before registering.");
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel infoPanel = UITheme.createCard(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UITheme.BORDER), "Patient & Stay Summary"),
            new EmptyBorder(8, 8, 8, 8)
        ));

        addInfoRow(infoPanel, "Patient Name:", patient.getName());
        addInfoRow(infoPanel, "Patient ID:", patient.getID());
        addInfoRow(infoPanel, "Doctor:", doctor.getName());
        addInfoRow(infoPanel, "Nurse:", nurse.getName());
        addInfoRow(infoPanel, "Room Type:", room.getRoomType());
        addInfoRow(infoPanel, "Stay Duration:", stayDays + " Days");
        addInfoRow(infoPanel, "Diagnosis:", diagnosis);

        JPanel feePanel = UITheme.createCard(new GridLayout(0, 2, 10, 10));
        feePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UITheme.BORDER), "Billing Details"),
            new EmptyBorder(8, 8, 8, 8)
        ));

        addFeeRow(feePanel, "Doctor Fee:", billing.getDoctorFee());
        addFeeRow(feePanel, "Room Fee:", billing.getRoomFee());
        addFeeRow(feePanel, "Medication Fee:", billing.getMedFee());
        
        JLabel totalLabel = UITheme.createLabel("TOTAL AMOUNT:");
        totalLabel.setFont(UITheme.TITLE_FONT);
        JLabel totalValue = new JLabel(String.format("RM %.2f", billing.getTotalFee()));
        totalValue.setFont(UITheme.TITLE_FONT);
        totalValue.setForeground(UITheme.ACCENT);
        
        feePanel.add(totalLabel);
        feePanel.add(totalValue);

        JPanel medSummaryPanel = UITheme.createCard(new BorderLayout(8, 8));
        medSummaryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UITheme.BORDER), "Medications"),
            new EmptyBorder(8, 8, 8, 8)
        ));
        JTextArea medArea = new JTextArea();
        medArea.setEditable(false);
        medArea.setFont(UITheme.TEXT_FONT);
        medArea.setBackground(Color.WHITE);
        medArea.setText(buildMedicationSummary());
        medSummaryPanel.add(new JScrollPane(medArea), BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(infoPanel);
        centerPanel.add(feePanel);
        centerPanel.add(medSummaryPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton finishButton = UITheme.createPrimaryButton("Finish & Register");
        finishButton.addActionListener(this);
        mainPanel.add(finishButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        panel.add(UITheme.createLabel(label));
        panel.add(UITheme.createLabel(value));
    }

    private void addFeeRow(JPanel panel, String label, double value) {
        panel.add(UITheme.createLabel(label));
        panel.add(UITheme.createLabel(String.format("RM %.2f", value)));
    }

    private String buildMedicationSummary() {
        if (selectedMeds == null || selectedMeds.isEmpty()) {
            return "No medications selected.";
        }

        StringBuilder builder = new StringBuilder();
        for (Medication med : selectedMeds) {
            builder.append("- ")
                   .append(med.getName())
                   .append(" (RM ")
                   .append(String.format("%.2f", med.getPrice()))
                   .append(")\n");
        }
        return builder.toString();
    }

    public void createAndShowGUI() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Registration Complete!\nTotal Bill: RM " + String.format("%.2f", new Billing(doctor, room, stayDays, selectedMeds).getTotalFee()), "Success", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
