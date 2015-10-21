package game;

/**
 * A disk class for the game Othello. Used with a board.
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class Disk {
    
    /**
     * A enum class containing the colors for the disk. It also contains a type used for checking if it's being used or not.
     */
    public enum Color{
        BLACK, WHITE
    }
    private boolean placed;
    private Color color;
    
    /**
     * Constructor for the disk
     */
    public Disk(){

    }
    
    /**
     * Constructor for the disk. Sets the color of the disk on construction.
     * @param color 
     */
    public Disk(Color color){
        this.color = color;
    }
    
    /**
     * If the disk is not placed, it places the disk. Otherwise it returns false, the disk is not placed.
     * @return true or false
     */
    public boolean setPlaced(){
        if (placed == false) {
            return placed = true;
        }else {
            return false;
        }
    }
    
    /**
     * Checks if the disk is placed or not, returns a boolean
     * @return placed
     */
    public boolean isPlaced(){
        return placed;
    }
    
    /**
     * Gets the color of the disk, of type Color
     * @return color
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Sets the color of the disk, type Color
     * @param color 
     */
    public void setColor(Color color){
        this.color = color;
    }
}
