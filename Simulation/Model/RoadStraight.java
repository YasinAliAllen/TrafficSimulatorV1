package Model;

import java.util.ArrayList;

public class RoadStraight extends Road {

    private ArrayList<TrafficLight> trafficLights = new ArrayList<>();

    public RoadStraight(int length, int rotations, boolean isSpawner) {
        this.length = length;
        this.spawner = isSpawner;
        if (rotations == 0) {
            this.eastConnection = true;
            this.westConnection = true;
        } else {
            this.northConnection = true;
            this.southConnection = true;
        }
        this.vehiclesLanes.add(new ArrayList<>());
        this.vehiclesLanes.add(new ArrayList<>());
    }

    public void createTrafficLight(int trafficLightNum, int position) {
        TrafficLight trafficLight = new TrafficLight(trafficLightNum, position);
        trafficLights.add(trafficLight);
    }

    @Override
    public ArrayList<ArrayList<Vehicle>> getLanes() {
        return vehiclesLanes;
    }

    public int countVehicles(int laneNum) {
        return vehiclesLanes.get(laneNum).size();
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
}
