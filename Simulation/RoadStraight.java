public class RoadStraight {
    private int length;
    private Vehicle vehicle;

    public RoadStraight() {
        length = 100;
    }

    public RoadStraight(int length) {
        this.length = length;
    }

    public void createVehicle(String type) {
        switch (type) {
            case "Car":
                vehicle = new Car();
                break;
            case "Motorbike":
                vehicle = new Motorbike();
                break;
            case "Bus":
                vehicle = new Bus();
                break;
        }
    }

    public int getLength() {
        return length;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
