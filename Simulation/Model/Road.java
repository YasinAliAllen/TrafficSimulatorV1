package Model;

import java.util.ArrayList;

public abstract class Road {

    ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();
    public int length;
    boolean spawner = false;
    boolean northConnection = false;
    boolean eastConnection = false;
    boolean southConnection = false;
    boolean westConnection = false;

    //creates a vehicle in a lane
    public void createVehicle(String type,
                              int position, int vehicleNum, int speed, int laneNum) {
        switch (type) {
            case "Car":
                Vehicle car = new Car(position, vehicleNum, speed);
                vehiclesLanes.get(laneNum).add(car);
                break;
            case "Motorbike":
                Vehicle motorbike = new Motorbike(position, vehicleNum, speed);
                vehiclesLanes.get(laneNum).add(motorbike);
                break;
            case "Bus":
                Vehicle bus = new Bus(position, vehicleNum, speed);
                vehiclesLanes.get(laneNum).add(bus);
                break;
        }
    }

    //destroys a specified vehicle
    public void destroyVehicle(int vehicleNum, int laneNum) {
        for (int i = 0; i < vehiclesLanes.get(laneNum).size(); i++)
            if (vehiclesLanes.get(laneNum).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(laneNum).remove(i);
    }

    //returns a specified vehicle
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

    public abstract ArrayList<ArrayList<Vehicle>> getLanes();

    public int getLength() {
        return length;
    }

    public abstract int countLights();

    public abstract TrafficLight getTrafficLight(int t);

    public boolean isSpawner() {
        return spawner;
    }

    //returns the speed of a specified vehicle
    public int getSpeed(int vehicleNum, int laneNum) {
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

    public abstract void createTrafficLight(int trafficLightNum, int position);

    public abstract int countVehicles(int laneNum);
}

