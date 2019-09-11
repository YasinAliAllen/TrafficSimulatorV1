import java.util.ArrayList;

public class RoadStraight {
    private int length;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<TrafficLight> trafficLights = new ArrayList<>();

    public RoadStraight() {
        length = 20;
    }

    public RoadStraight(int length) {
        this.length = length;
    }

    void createVehicle(String type,
                       int position, int vehicleNum, int breadth, int speed, int acceleration, int deceleration) {
        switch (type) {
            case "Car":
                Car car = new Car(position, vehicleNum, breadth, speed, acceleration, deceleration);
                vehicles.add(car);
                break;
            case "Motorbike":
                Motorbike motorbike = new Motorbike(position, vehicleNum, breadth, speed, acceleration, deceleration);
                vehicles.add(motorbike);
                break;
            case "Bus":
                Bus bus = new Bus(position, vehicleNum, breadth, speed, acceleration, deceleration);
                vehicles.add(bus);
                break;
        }
    }

    void destroyVehicle(int vehicleNum) {
        for (int i = 0; i < vehicles.size(); i++)
            if (vehicles.get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehicles.remove(i);
    }

    void createTrafficLight(int trafficLightNum, int position) {
        TrafficLight trafficLight = new TrafficLight(trafficLightNum, position);
        trafficLights.add(trafficLight);
    }

    int getLength() {
        return length;
    }

    Vehicle getVehicle(int vehicleNum) {
        if (vehicles.isEmpty())
            return null;
        else
            for (Vehicle value : vehicles) { //cycles through all vehicles
                if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                    return value;
            }
        return null;
    }

    public int countVehicles() {
        return vehicles.size();
    }

    int countLights() {
        return trafficLights.size();
    }

    public TrafficLight getTrafficLight(int lightNum) {
        if (trafficLights.isEmpty())
            return null;
        else
            for (TrafficLight value : trafficLights) { //cycles through all vehicles
                if (value.getLightNumber() == lightNum) //gets car with matching number
                    return value;
            }
        return null;
    }
}
