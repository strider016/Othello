package game;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class Disk {
    private Enum color;
    private boolean placed;

    public Disk(){
    }

    public boolean setPlaced(){
        if (placed == false) {
            return placed = true;
        }else {
            return false;
        }
    }

    public Enum getColor(){
        return color;
    }
}
