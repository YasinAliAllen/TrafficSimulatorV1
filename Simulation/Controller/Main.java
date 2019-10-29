package Controller;

import View.ItemPanel;
import View.TrafficSimGUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        TrafficSimGUI trafficSimGUI = new TrafficSimGUI();

        trafficSimGUI.addExitActionListener(actionEvent -> System.exit(0));

        trafficSimGUI.addSimulationActionListener(actionEvent -> {
            trafficSimGUI.updateStatus("Building...");
            int edgePieces = 0;
            ArrayList<ArrayList<ItemPanel>> items = trafficSimGUI.getItems();
            for (ArrayList<ItemPanel> item : items) {
                for (ItemPanel itemPanel : item) {
                    if (itemPanel.isEndPiece() && itemPanel.hasRoad()) {
                        edgePieces++;
                    }
                }
            }
            if (edgePieces > 1) {
                simulation.createSimulation(2, 2, 2);
                trafficSimGUI.updateStatus("Ready to Run!");
            } else {
                trafficSimGUI.updateStatus("You need atleast 2 edge pieces!");
            }
        });
        trafficSimGUI.addRunActionListener(actionEvent -> {
            simulation.runSimulation();
            trafficSimGUI.updateStatus("Running...");
        });
    }
}
