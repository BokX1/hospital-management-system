/**
 * Represents a general staff member in the hospital.
 * This class serves as a base for specific staff roles like Doctor and Nurse.
 */
public abstract class HospitalStaff {
    private String staffID;
    private String name;
    private String phoneNum;

    /**
     * Default constructor.
     */
    public HospitalStaff() {
        this.staffID = "UNKNOWN";
        this.name = "UNKNOWN";
        this.phoneNum = "UNKNOWN";
    }

    /**
     * Parameterized constructor.
     * @param staffID Unique identifier for the staff member.
     * @param name Full name of the staff member.
     * @param phoneNum Contact phone number.
     */
    public HospitalStaff(String staffID, String name, String phoneNum) {
        this.staffID = staffID;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    // Getters and Setters
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    @Override 
    public String toString() {
        return String.format("%s - %s", staffID, name);
    }
}
