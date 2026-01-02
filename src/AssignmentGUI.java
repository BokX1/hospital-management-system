import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * GUI for assigning doctor, nurse, and room to a patient.
 */
public class AssignmentGUI extends JFrame implements ActionListener {

    private Patient patient;
    private JComboBox<Doctor> cbDoctor;
    private JComboBox<Nurse> cbNurse;
    private JComboBox<String> cbRoomNumber;
    private JTextField tfStayDuration = new JTextField(10);
    
    private JRadioButton rbStandard = new JRadioButton("Standard");
    private JRadioButton rbDeluxe = new JRadioButton("Deluxe");
    private JRadioButton rbVIP = new JRadioButton("VIP");
    private ButtonGroup roomGroup = new ButtonGroup();

    private JButton nextButton = new JButton("Next");

    public AssignmentGUI(Patient patient) {
        this.patient = patient;
        setTitle("Hospital Management - Assignment");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Sample Data
        Doctor[] doctors = {
            new Doctor("D001", "Dr. Aiman", "0123456789", "Cardiology", 300),
            new Doctor("D002", "Dr. Deena", "0123456790", "Neurology", 350),
            new Doctor("D003", "Dr. Dania", "0123456791", "Orthopedics", 280)
        };

        Nurse[] nurses = {
            new Nurse("N001", "Nurse Nadhirah", "0131111111", "Ward"),
            new Nurse("N002", "Nurse Faris", "0132222222", "ICU")
        };

        String[] roomNumbers = new String[10];
        for (int i = 0; i < 10; i++) roomNumbers[i] = "Room " + (i + 1);

        cbDoctor = new JComboBox<>(doctors);
        cbNurse = new JComboBox<>(nurses);
        cbRoomNumber = new JComboBox<>(roomNumbers);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Select Doctor:"));
        formPanel.add(cbDoctor);

        formPanel.add(new JLabel("Select Nurse:"));
        formPanel.add(cbNurse);

        formPanel.add(new JLabel("Room Type:"));
        JPanel roomTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomGroup.add(rbStandard);
        roomGroup.add(rbDeluxe);
        roomGroup.add(rbVIP);
        roomTypePanel.add(rbStandard);
        roomTypePanel.add(rbDeluxe);
        roomTypePanel.add(rbVIP);
        formPanel.add(roomTypePanel);

        formPanel.add(new JLabel("Room Number:"));
        formPanel.add(cbRoomNumber);

        formPanel.add(new JLabel("Stay Duration (Days):"));
        formPanel.add(tfStayDuration);

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
            Room room = null;
            if (rbStandard.isSelected()) room = new StandardRoom();
            else if (rbDeluxe.isSelected()) room = new DeluxeRoom();
            else if (rbVIP.isSelected()) room = new VIPRoom();

            int stayDays = Integer.parseInt(tfStayDuration.getText().trim());

            this.dispose();
            new MedicationGUI(patient, (Doctor)cbDoctor.getSelectedItem(), (Nurse)cbNurse.getSelectedItem(), room, stayDays).createAndShowGUI();
        }
    }

    private boolean validateInput() {
        if (roomGroup.getSelection() == null) {
            showError("Please select a room type!");
            return false;
        }

        try {
            int days = Integer.parseInt(tfStayDuration.getText().trim());
            if (days <= 0) {
                showError("Stay duration must be a positive number!");
                return false;
            }
        } catch (NumberFormatException ex) {
            showError("Stay duration must be a valid number!");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
