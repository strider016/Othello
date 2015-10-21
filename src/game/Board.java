package game;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class Board {
    private static int BOARD_SIZE = 8;
    private int nmbOfDisks;
    private ArrayList<ArrayList<Disk>> board;

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

    public int getDisksRemaining(){
        return nmbOfDisks;
    }

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

    public boolean placedOccupied(int row, int column){
        if (board.get(row).get(column).isPlaced()){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<ArrayList<Disk>> getBoard() {
        return board;
    }
}
