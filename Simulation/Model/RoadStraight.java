package Model;

import java.util.ArrayList;

public class RoadStraight extends Road {

    private boolean spawner = false;
    private boolean northConnection = false;
    private boolean eastConnection = false;
    private boolean southConnection = false;
    private boolean westConnection = false;
    private int length;
    private ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();
    private ArrayList<TrafficLight> trafficLights = new ArrayList<>();

    public RoadStraight(int length, int rotations, boolean isSpawner) {
        this.length = length;
        this.spawner = isSpawner;
        if (rotations == 0) {
            eastConnection = true;
            westConnection = true;
        } else {
            northConnection = true;
            southConnection = true;
        }
        vehiclesLanes.add(new ArrayList<Vehicle>());
        vehiclesLanes.add(new ArrayList<Vehicle>());
    }

    @Override
    public void createVehicle(String type,
                              int position, int vehicleNum, int speed, int lane) {
        switch (type) {
            case "Car":
                Vehicle car = new Car(position, vehicleNum, speed);
                vehiclesLanes.get(lane).add(car);
                break;
            case "Motorbike":
                Vehicle motorbike = new Motorbike(position, vehicleNum, speed);
                vehiclesLanes.get(lane).add(motorbike);
                break;
            case "Bus":
                Vehicle bus = new Bus(position, vehicleNum, speed);
                vehiclesLanes.get(lane).add(bus);
                break;
        }
    }

    @Override
    public void destroyVehicle(int vehicleNum, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);
    }

    public void createTrafficLight(int trafficLightNum, int position) {
        TrafficLight trafficLight = new TrafficLight(trafficLightNum, position);
        trafficLights.add(trafficLight);
    }

    public int getLength() {
        return length;
    }

    @Override
    public Vehicle getVehicle(int vehicleNum, int lane) {
        if (vehiclesLanes.get(lane).isEmpty())
            return null;
        else
            for (Vehicle value : vehiclesLanes.get(lane)) { //cycles through all vehicles
                if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                    return value;
            }
        return null;
    }

    public int countVehicles(int lane) {
        return vehiclesLanes.get(lane).size();
    }

    public int countLights() {
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

    public boolean hasNorthConnection() {
        return northConnection;
    }

    public boolean hasEastConnection() {
        return eastConnection;
    }

    public boolean hasSouthConnection() {
        return southConnection;
    }

    public boolean hasWestConnection() {
        return westConnection;
    }

    public ArrayList<ArrayList<Vehicle>> getNumLanes() {
        return vehiclesLanes;
    }
}
