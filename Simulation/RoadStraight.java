import java.util.ArrayList;

public class RoadStraight {
    private int length;
    private ArrayList<Vehicle> vehicle = new ArrayList<>();
    private ArrayList<TrafficLight> trafficLights = new ArrayList<>();

    public RoadStraight() {
        length = 20;
    }

    public RoadStraight(int length) {
        this.length = length;
    }

    public void createVehicle(String type, int position, int vehicleNum) {
        switch (type) {
            case "Car":
                Car car = new Car(position, vehicleNum);
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
        for (int i = 0; i < vehicle.size(); i++)
            if (vehicle.get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehicle.remove(i);
    }

    public void createTrafficLight(int position) {
        TrafficLight trafficLight = new TrafficLight(position);
        trafficLights.add(trafficLight);
    }

    public int getLength() {
        return length;
    }

    public Vehicle getVehicle(int vehicleNum) {
        if (vehicle.isEmpty())
            return null;
        else
            for (int i = 0; i < vehicle.size(); i++) { //cycles through all vehicles
                if (vehicle.get(i).getVehicleNum() == vehicleNum) //gets car with matching number
                    return vehicle.get(i);
            }
        return null;
    }

    public int countVehicles() {
        return vehicle.size();
    }

    public int countLights() {
        return trafficLights.size();
    }

    public TrafficLight getTrafficLight(int lightNum) {
        if (trafficLights.isEmpty())
            return null;
        else
            return trafficLights.get(lightNum);
    }
}
