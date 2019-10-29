package View;


import javax.swing.*;

public class ItemPanel extends JPanel {
    private boolean hasRoad = false;
    public ItemPanel() {

    }

    public boolean hasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }
}