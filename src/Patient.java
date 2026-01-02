/**
 * Represents a Patient in the hospital management system.
 */
public class Patient {
    private String name;
    private String id;
    private String gender;
    private String phoneNum;
    private String guardianName;
    private String guardianPhoneNum;

    /**
     * Default constructor.
     */
    public Patient() {
        this.name = "UNKNOWN";
        this.id = "UNKNOWN";
        this.gender = "UNKNOWN";
        this.phoneNum = "UNKNOWN";
        this.guardianName = "UNKNOWN";
        this.guardianPhoneNum = "UNKNOWN";
    }

    /**
     * Parameterized constructor.
     * @param name Patient's full name.
     * @param id Patient's ID or IC number.
     * @param gender Patient's gender.
     * @param phoneNum Patient's contact number.
     * @param guardianName Name of the patient's guardian.
     * @param guardianPhoneNum Contact number of the guardian.
     */
    public Patient(String name, String id, String gender, String phoneNum, String guardianName, String guardianPhoneNum) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.guardianName = guardianName;
        this.guardianPhoneNum = guardianPhoneNum;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getID() { return id; }
    public void setID(String id) { this.id = id; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }

    public String getGuardianPhoneNum() { return guardianPhoneNum; }
    public void setGuardianPhoneNum(String guardianPhoneNum) { this.guardianPhoneNum = guardianPhoneNum; }
}
