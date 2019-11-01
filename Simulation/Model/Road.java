package Model;

import java.util.ArrayList;

public abstract class Road {

    public ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();
    public int length;
    public boolean spawner = false;
    public boolean northConnection = false;
    public boolean eastConnection = false;
    public boolean southConnection = false;
    public boolean westConnection = false;

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

    public void destroyVehicle(int vehicleNum, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);
    }

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

    public abstract ArrayList<ArrayList<Vehicle>> getLanes();

    public int getLength() {
        return length;
    }

    public abstract int countLights();

    public abstract TrafficLight getTrafficLight(int t);

    public boolean isSpawner() {
        return spawner;
    }

    public int getSpeed(int laneNum, int vehicleNum) {
        int vehicleIndex = 0;
        for (int numVehicles = 0; numVehicles < vehiclesLanes.get(laneNum).size(); numVehicles++) {
            if (vehiclesLanes.get(laneNum).get(numVehicles).getVehicleNum() == vehicleNum) {
                vehicleIndex = numVehicles;
            }
        }
        return vehiclesLanes.get(laneNum).get(vehicleIndex).getSpeed();
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

}

