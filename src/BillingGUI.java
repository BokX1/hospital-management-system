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

    public BillingGUI(Patient patient, Doctor doctor, Nurse nurse, Room room, int stayDays, ArrayList<Medication> selectedMeds) {
        this.patient = patient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.room = room;
        this.stayDays = stayDays;
        this.selectedMeds = selectedMeds;

        setTitle("Hospital Management - Final Billing");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        Billing billing = new Billing(doctor, room, stayDays, selectedMeds);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Patient & Stay Summary"));

        addInfoRow(infoPanel, "Patient Name:", patient.getName());
        addInfoRow(infoPanel, "Patient ID:", patient.getID());
        addInfoRow(infoPanel, "Doctor:", doctor.getName());
        addInfoRow(infoPanel, "Nurse:", nurse.getName());
        addInfoRow(infoPanel, "Room Type:", room.getRoomType());
        addInfoRow(infoPanel, "Stay Duration:", stayDays + " Days");

        JPanel feePanel = new JPanel(new GridLayout(0, 2, 10, 10));
        feePanel.setBorder(BorderFactory.createTitledBorder("Billing Details"));

        addFeeRow(feePanel, "Doctor Fee:", billing.getDoctorFee());
        addFeeRow(feePanel, "Room Fee:", billing.getRoomFee());
        addFeeRow(feePanel, "Medication Fee:", billing.getMedFee());
        
        JLabel totalLabel = new JLabel("TOTAL AMOUNT:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel totalValue = new JLabel(String.format("RM %.2f", billing.getTotalFee()));
        totalValue.setFont(new Font("Arial", Font.BOLD, 16));
        totalValue.setForeground(Color.RED);
        
        feePanel.add(totalLabel);
        feePanel.add(totalValue);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(infoPanel);
        centerPanel.add(feePanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton finishButton = new JButton("Finish & Register");
        finishButton.addActionListener(this);
        mainPanel.add(finishButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addInfoRow(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value));
    }

    private void addFeeRow(JPanel panel, String label, double value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(String.format("RM %.2f", value)));
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
