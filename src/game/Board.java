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
        placeDisk(3,3,false);
        placeDisk(3,4,true);
        placeDisk(4,3,true);
        placeDisk(4,4,false);
    }

    public int getDisksRemaining(){
        return nmbOfDisks;
    }

    public ArrayList<String> getAvailableSlots(Player p){
        ArrayList<String> avalibleSlots = new ArrayList<>();
        int rot=0;
        for (int i = 0; i<BOARD_SIZE;i++){
            for (int j = 0; j<BOARD_SIZE;j++){
                if (board.get(i).get(j).getColor()==p.getColor()){
                    if ((i+1)<BOARD_SIZE && board.get(i+1).get(j).getColor()!=p.getColor() && board.get(i+1).get(j).getColor()!= Disk.Color.EMPTY){
                        rot=0;
                        if (i+2<BOARD_SIZE) {
                            while (board.get(i + 1 + rot).get(j).getColor() != p.getColor() && (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || (i + 1 + rot) < BOARD_SIZE)) {
                                if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || i+1+rot>=BOARD_SIZE) {
                                    break;
                                }

                                rot++;
                            }
                            if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && i+1+rot<BOARD_SIZE) {
                                avalibleSlots.add(Integer.toString((i + 1 + rot)) + " " + Integer.toString(j));
                            }
                        }
                    }
                    if ((i-1)>-1 && board.get(i-1).get(j).getColor()!=p.getColor() && board.get(i-1).get(j).getColor()!= Disk.Color.EMPTY){
                        rot=0;
                        if (i-2>-1) {
                            while (board.get(i - 1 - rot).get(j).getColor() != p.getColor() && (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY || (i - 1 - rot) > -1)) {
                                if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                    break;
                                }

                                rot++;
                            }
                            if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                avalibleSlots.add(Integer.toString((i - 1 - rot)) + " " + Integer.toString(j));
                            }
                        }
                    }
                    if (j+1<BOARD_SIZE && board.get(i).get(j+1).getColor()!=p.getColor() && board.get(i).get(j+1).getColor()!= Disk.Color.EMPTY){
                        rot=0;
                        if (j+2<BOARD_SIZE) {
                            while (board.get(i).get(j + 1 + rot).getColor() != p.getColor() && (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY || (j + 1 + rot) < BOARD_SIZE)) {
                                if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY || j+1+rot>=BOARD_SIZE) {
                                    break;
                                }

                                rot++;
                            }
                            if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY) {
                                avalibleSlots.add(Integer.toString((i)) + " " + Integer.toString(j + 1 + rot));
                            }
                        }
                    }
                    if ((j-1)>-1 && board.get(i).get(j-1).getColor()!=p.getColor() && board.get(i).get(j-1).getColor()!= Disk.Color.EMPTY){
                        rot=0;
                        if (j-2>-1) {
                            while (board.get(i).get(j - 1 - rot).getColor() != p.getColor() && (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY || (j - 1 - rot) > -1)) {
                                if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                    break;
                                }

                                rot++;
                            }
                            if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                avalibleSlots.add(Integer.toString((i)) + " " + Integer.toString(j - 1 - rot));
                            }
                        }
                    }
                }
            }
        }
        return avalibleSlots;
    }

    public ArrayList<String> getLocationDisks(Player p){
        ArrayList<String> mySlots = new ArrayList<>();
        for (int i = 0; i<BOARD_SIZE;i++){
            for (int j = 0; j<BOARD_SIZE;j++) {
                if (board.get(i).get(j).getColor()==p.getColor()){
                    mySlots.add(Integer.toString(i) + " " + Integer.toString(j));
                }
            }
        }
        return mySlots;
    }

    public boolean placeDisk(int row,int column,boolean player){
        if (!placedOccupied(row,column)){
            board.get(row).get(column).setPlaced(player);
             return true;
        }else {
            return false;
        }
    }

    public boolean placedOccupied(int row, int column){
        return board.get(row).get(column).isPlaced();
    }

    public ArrayList<ArrayList<Disk>> getBoard() {
        return board;
    }
}
