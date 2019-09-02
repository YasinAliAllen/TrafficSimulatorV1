public class RoadStraight {
    private int length;
    private Vehicle vehicle;
    private TrafficLight trafficLight;

    public RoadStraight() {
        length = 20;
    }

    public RoadStraight(int length) {
        this.length = length;
    }

    public void createVehicle(String type, int position) {
        switch (type) {
            case "Car":
                vehicle = new Car(position);
                break;
            case "Motorbike":
                vehicle = new Motorbike();
                break;
            case "Bus":
                vehicle = new Bus();
                break;
        }
    }

    public void destroyVehicle(int position) {
        vehicle = null;
    }

    public void createTrafficLight() {
        trafficLight = new TrafficLight();
    }

    public int getLength() {
        return length;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }
}
