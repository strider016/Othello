package game;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents a board containing a collection of disks in the form of an 2d ArrayList.
 * This class also reads and writes to a .ser file
 * 
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class Board {
    private static int BOARD_SIZE = 8;
    private int nmbOfDisks;
    private ArrayList<ArrayList<Disk>> board;
    
    
    /**
     * Constructor creates a new arraylist (8x8) and fills it with disks.
     * It also places the 4 starting disks which you have at a start of an Othello game.
     */
    public Board(){
        board = new ArrayList<>();
        nmbOfDisks=64;
        ArrayList<Disk> rowBoard;
        for (int i = 0;i<BOARD_SIZE;i++){
            rowBoard = new ArrayList<>();
            for (int j = 0;j<BOARD_SIZE;j++){
                rowBoard.add(new Disk());
            }
            board.add(rowBoard);
        }
        
        placeDisk(3,3);
        placeDisk(3,4);
        placeDisk(4,3);
        placeDisk(4,4);
    }
    
    /**
     * Get nmb of remaning disks
     * @return nmbOfDisks
     */
    public int getDisksRemaining(){
        return nmbOfDisks;
    }
    
    /**
     * Places a disk on the desired row and colum. It returns true/false depending if the place is already occupied by a disk. 
     * @param row
     * @param column
     * @return true or false
     */
    public boolean placeDisk(int row,int column){
        Disk d = new Disk();
        d.setPlaced();
        if (!placedOccupied(row,column)){
            board.get(row).get(column).setPlaced();
             return true;
        }else {
            return false;
        }
    }
    
    /**
     * Checks if the position on the board is occupied via inputed row and column
     * @param row
     * @param column
     * @return true or false
     */
    public boolean placedOccupied(int row, int column){
        if (board.get(row).get(column).isPlaced()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Get the board of type ArrayList<ArrayList<Disk>>
     * @return board
     */
    public ArrayList<ArrayList<Disk>> getBoard() {
        return board;
    }
}
