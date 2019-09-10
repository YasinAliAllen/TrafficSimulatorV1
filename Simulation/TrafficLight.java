public class TrafficLight {
    private int position;
    boolean status; //true = green | false = red
    int lightNum = 1;

    public void toggleColour() {
        status = !status;
    }

    public int getPosition() {
        return position;
    }

    public TrafficLight(int lightNum, int position) {
        this.position = position;
        this.lightNum = lightNum;
    }

    public boolean isStatus() {
        return status;
    }

    public int getLightNumber() {
        return lightNum;
    }
}
