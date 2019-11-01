package Model;

public class TrafficLight {
    private int position;
    private boolean status; //true = green | false = red
    private int lightNum;

    TrafficLight(int lightNum, int position) {
        this.position = position;
        this.lightNum = lightNum;
    }

    public void toggleColour() {
        status = !status;
    }

    public int getPosition() {
        return position;
    }

    public boolean isStatus() {
        return status;
    }

    int getLightNumber() {
        return lightNum;
    }
}
