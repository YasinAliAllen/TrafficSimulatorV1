package Controller;

import View.TrafficSimGUI;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        TrafficSimGUI trafficSimGUI = new TrafficSimGUI();


        trafficSimGUI.addExitActionListener(actionEvent -> System.exit(0));
        trafficSimGUI.addSimulationActionListener(actionEvent -> simulation.createSimulation());
        trafficSimGUI.addRunActionListener(actionEvent -> simulation.runSimulation());
    }
}
