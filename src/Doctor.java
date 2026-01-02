/**
 * Represents a Doctor in the hospital, extending HospitalStaff and implementing Billable.
 */
public class Doctor extends HospitalStaff implements Billable {
    private String specialization;
    private double dailyFee;

    /**
     * Default constructor.
     */
    public Doctor() {
        super();
        this.specialization = "General";
        this.dailyFee = 0.0;
    }

    /**
     * Parameterized constructor.
     * @param staffID Unique identifier for the doctor.
     * @param name Full name of the doctor.
     * @param phoneNum Contact phone number.
     * @param specialization Area of medical expertise.
     * @param dailyFee Daily consultation fee.
     */
    public Doctor(String staffID, String name, String phoneNum, String specialization, double dailyFee) {
        super(staffID, name, phoneNum);
        this.specialization = specialization;
        this.dailyFee = dailyFee;
    }

    // Getters and Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getDailyFee() { return dailyFee; }
    public void setDailyFee(double dailyFee) { this.dailyFee = dailyFee; }

    @Override
    public double getFee() {
        return dailyFee;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", super.getName(), specialization);
    }
}
