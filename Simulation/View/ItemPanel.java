package View;


import javax.swing.*;

public class ItemPanel extends JPanel {
    private String rotation = "";
    private String roadType = "";
    private boolean hasRoad = false;
    private boolean hasLights = false;
    private boolean isEndPiece = false;
    public ItemPanel() {

    }

    public boolean hasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    public void setIsEndPiece(boolean isEndPiece) {
        this.isEndPiece = isEndPiece;
    }

    public boolean isEndPiece() {
        return isEndPiece;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }
}