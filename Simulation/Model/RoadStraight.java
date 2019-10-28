package Model;

import java.util.ArrayList;

public class RoadStraight {
    private int length;
    private ArrayList<Vehicle> vehiclesLane0 = new ArrayList<>();
    private ArrayList<Vehicle> vehiclesLane1 = new ArrayList<>();
    private ArrayList<TrafficLight> trafficLights = new ArrayList<>();

    public RoadStraight(int length) {
        this.length = length;
    }

    public void createVehicle(String type,
                              int position, int vehicleNum, int speed, int lane) {
        switch (type) {
            case "Model.Car":
                Vehicle car = new Car(position, vehicleNum, speed);
                if (lane == 0) {
                    vehiclesLane0.add(car);
                } else {
                    vehiclesLane1.add(car);
                }
                break;
            case "Model.Motorbike":
                Vehicle motorbike = new Motorbike(position, vehicleNum, speed);
                if (lane == 0) {
                    vehiclesLane0.add(motorbike);
                } else {
                    vehiclesLane1.add(motorbike);
                }
                break;
            case "Model.Bus":
                Vehicle bus = new Bus(position, vehicleNum, speed);
                if (lane == 0) {
                    vehiclesLane0.add(bus);
                } else {
                    vehiclesLane1.add(bus);
                }
                break;
        }
    }

    public void destroyVehicle(int vehicleNum, int lane) {
        if (lane == 0) {
            for (int i = 0; i < vehiclesLane0.size(); i++)
                if (vehiclesLane0.get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                    vehiclesLane0.remove(i);
        } else {
            for (int i = 0; i < vehiclesLane1.size(); i++)
                if (vehiclesLane1.get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                    vehiclesLane1.remove(i);
        }
    }

    public void createTrafficLight(int trafficLightNum, int position) {
        TrafficLight trafficLight = new TrafficLight(trafficLightNum, position);
        trafficLights.add(trafficLight);
    }

    public int getLength() {
        return length;
    }

    public Vehicle getVehicle(int vehicleNum, int lane) {
        if (lane == 0) {
            if (vehiclesLane0.isEmpty())
                return null;
            else
                for (Vehicle value : vehiclesLane0) { //cycles through all vehicles
                    if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                        return value;
                }
        } else {
            if (vehiclesLane1.isEmpty())
                return null;
            else
                for (Vehicle value : vehiclesLane1) { //cycles through all vehicles
                    if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                        return value;
                }
        }
        return null;
    }

    public int countVehicles(int lane) {
        if (lane == 0) {
            return vehiclesLane0.size();
        } else {
            return vehiclesLane1.size();
        }
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
