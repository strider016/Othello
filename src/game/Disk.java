package game;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class Disk {
    
    
    public enum Color{
        BLACK, WHITE
    }
    private boolean placed;
    private Color color;

    public Disk(){

    }

    public Disk(Color color){
        this.color = color;
    }

    public Disk(int pos){
        if ((pos % pos) == 0){
            this.color= Color.BLACK;
        }
        else {
            this.color= Color.WHITE;
        }
    }

    public boolean setPlaced(){
        if (placed == false) {
            return placed = true;
        }else {
            return false;
        }
    }

    public boolean isPlaced(){
        return placed;
    }
    

    public Color getColor(){
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
}
