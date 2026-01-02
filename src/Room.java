public abstract class Room implements Billable {
    private String roomType;
    private double dailyFee;
    private int capacity;

    // default constructor
    public Room() {
        this.roomType = "";
        this.dailyFee = 0.0;
        this.capacity = 0;
    }

    // constructor
    public Room(String roomType, double dailyFee, int capacity) {
        this.roomType = roomType;
        this.dailyFee = dailyFee;
        this.capacity = capacity;
    }

    // setter and getter
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getDailyFee() {
        return dailyFee;
    }
    public void setDailyFee(double dailyFee) {
        this.dailyFee = dailyFee;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public double getFee() {
        return dailyFee;
    }

    @Override
    public String toString() {
        return roomType + " (RM " + dailyFee + "/day)";
    }
}
