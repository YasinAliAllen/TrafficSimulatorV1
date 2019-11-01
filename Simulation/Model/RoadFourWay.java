package Model;

import java.util.ArrayList;

public class RoadFourWay extends Road {

    final int NUMLANES = 8;
    int length;
    private boolean spawner = false;
    boolean northConnection = true;
    boolean eastConnection = true;
    boolean southConnection = true;
    boolean westConnection = true;
    private ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();

    public RoadFourWay(int length, boolean isSpawner) {
        this.length = length;
        this.spawner = isSpawner;
        for (int i = 0; i < NUMLANES; i++) {
            vehiclesLanes.add(new ArrayList<Vehicle>());
        }
    }

    @Override
    public void createVehicle(String type, int position, int vehicleNum, int speed, int laneNum) {
        for (int i = 0; i < vehiclesLanes.get(laneNum).size(); i++)
            if (vehiclesLanes.get(laneNum).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(laneNum).remove(i);

    }

    @Override
    public void destroyVehicle(int vehicleNum, int laneNum) {
        for (int i = 0; i < vehiclesLanes.get(laneNum).size(); i++)
            if (vehiclesLanes.get(laneNum).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(laneNum).remove(i);
    }

    @Override
    public Vehicle getVehicle(int vehicleNum, int laneNum) {
        if (vehiclesLanes.get(laneNum).isEmpty())
            return null;
        else
            for (Vehicle value : vehiclesLanes.get(laneNum)) { //cycles through all vehicles
                if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                    return value;
            }
        return null;
    }

    @Override
    public ArrayList<ArrayList<Vehicle>> getLanes() {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int countLights() {
        return 0;
    }

    @Override
    public TrafficLight getTrafficLight(int t) {
        return null;
    }

    @Override
    public boolean isSpawner() {
        return false;
    }

    @Override
    public int getSpeed(int laneNum, int vehicleNum) {
        return vehiclesLanes.get(laneNum).get(vehicleNum).getSpeed();
    }

    @Override
    public boolean hasNorthConnection() {
        return northConnection;
    }

    @Override
    public boolean hasEastConnection() {
        return eastConnection;
    }

    @Override
    public boolean hasSouthConnection() {
        return southConnection;
    }

    @Override
    public boolean hasWestConnection() {
        return westConnection;
    }

    @Override
    public void createTrafficLight(int trafficLightNum, int position) {

    }

    @Override
    public int countVehicles(int laneNum) {
        return 0;
    }
}
