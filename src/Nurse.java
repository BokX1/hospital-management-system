/**
 * Represents a Nurse in the hospital, extending HospitalStaff.
 */
public class Nurse extends HospitalStaff {
    private String role;

    /**
     * Default constructor.
     */
    public Nurse() {
        super();
        this.role = "General";
    }

    /**
     * Parameterized constructor.
     * @param staffID Unique identifier for the nurse.
     * @param name Full name of the nurse.
     * @param phoneNum Contact phone number.
     * @param role Specific role or department.
     */
    public Nurse(String staffID, String name, String phoneNum, String role) {
        super(staffID, name, phoneNum);
        this.role = role;
    }

    // Getters and Setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return String.format("%s (%s)", super.getName(), role);
    }
}
