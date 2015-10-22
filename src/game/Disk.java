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
        BLACK, WHITE , EMPTY
    }
    private boolean placed;
    private Color color;
    
    /**
     * Constructor for the disk
     */
    public Disk(){
        color=Color.EMPTY;
    }
    
    /**
     * Constructor for the disk. Sets the color of the disk on construction.
     * @param color 
     */
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
    /**
     * If the disk is not placed, it places the disk. Otherwise it returns false, the disk is not placed.
     * @param owner boolean
     * @return true or false
     */
    public boolean setPlaced(boolean owner){
        if (placed == false) {
            if (!owner){
                color = Color.WHITE;
            }else {
                color = Color.BLACK;
            }
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
