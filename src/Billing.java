import java.util.List;

/**
 * Handles the calculation of fees for a patient's hospital stay.
 */
public class Billing {
    private Doctor doctor;
    private Room room;
    private int stayDays;
    private List<Medication> medications;

    /**
     * Constructor for Billing.
     * @param doctor The assigned doctor.
     * @param room The assigned room.
     * @param stayDays Number of days spent in the hospital.
     * @param medications List of medications prescribed.
     */
    public Billing(Doctor doctor, Room room, int stayDays, List<Medication> medications) {
        this.doctor = doctor;
        this.room = room;
        this.stayDays = stayDays;
        this.medications = medications;
    }

    /**
     * Calculates the total doctor fee based on stay duration.
     * @return Total doctor fee.
     */
    public double getDoctorFee() {
        return (doctor != null) ? doctor.getFee() * stayDays : 0.0;
    }

    /**
     * Calculates the total room fee based on stay duration.
     * @return Total room fee.
     */
    public double getRoomFee() {
        return (room != null) ? room.getFee() * stayDays : 0.0;
    }

    /**
     * Calculates the total medication fee.
     * @return Total medication fee.
     */
    public double getMedFee() {
        double total = 0.0;
        if (medications != null) {
            for (Medication m : medications) {
                total += m.getFee();
            }
        }
        return total;
    }

    /**
     * Calculates the overall total fee.
     * @return Total hospital bill.
     */
    public double getTotalFee() {
        return getDoctorFee() + getRoomFee() + getMedFee();
    }
}
