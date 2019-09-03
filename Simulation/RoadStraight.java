import java.util.ArrayList;

public class RoadStraight {
    private int length;
    private ArrayList<Vehicle> vehicle = new ArrayList<>();
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
                Car car = new Car(position);
                vehicle.add(car);
                break;
            case "Motorbike":
                Motorbike motorbike = new Motorbike();
                vehicle.add(motorbike);
                break;
            case "Bus":
                Bus bus = new Bus();
                vehicle.add(bus);
                break;
        }
    }

    public void destroyVehicle(int vehicleNum) {
        vehicle.remove(vehicleNum);
    }

    public void createTrafficLight(int position) {
        trafficLight = new TrafficLight(position);
    }

    public int getLength() {
        return length;
    }

    public Vehicle getVehicle(int vehicleNum) {
        if (vehicle.isEmpty())
            return null;
        else
            return vehicle.get(vehicleNum);
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }
}
