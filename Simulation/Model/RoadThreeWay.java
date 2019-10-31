package Model;

import java.util.ArrayList;

public class RoadThreeWay extends Road {
    final int NUMLANES = 6;
    int length;
    private ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();
    private boolean spawner = false;
    private boolean northConnection = true;
    private boolean eastConnection = true;
    private boolean southConnection = true;
    private boolean westConnection = true;

    public RoadThreeWay(int length, int rotations, boolean isSpawner) {
        this.length = length;
        this.spawner = isSpawner;
        for (int i = 0; i < NUMLANES; i++) {
            vehiclesLanes.add(new ArrayList<Vehicle>());
        }
        switch (rotations) {
            case 0:
                northConnection = false;
                break;
            case 1:
                westConnection = false;
                break;
            case 2:
                southConnection = false;
                break;
            case 3:
                eastConnection = false;
                break;
        }

    }

    @Override
    public void createVehicle(String type, int position, int vehicleNum, int speed, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);

    }

    @Override
    public void destroyVehicle(int vehicleNum, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);
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
}
