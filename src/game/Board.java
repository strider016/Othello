package game;


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

        placeDisk(3,3,false);
        placeDisk(3,4,true);
        placeDisk(4,3,true);
        placeDisk(4,4,false);
    }
    
    /**
     * Get nmb of remaning disks
     * @return nmbOfDisks
     */
    public int getDisksRemaining(){
        return nmbOfDisks;
    }

    public ArrayList<String> getAvailableSlots(Player p){
        ArrayList<String> avalibleSlots = new ArrayList<>();
        int rot=0;
        for (int i = 0; i<BOARD_SIZE;i++){
            for (int j = 0; j<BOARD_SIZE;j++){
                if (board.get(i).get(j).getColor()==p.getColor()){
                    //Looking in down
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
                    //Looking up
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
                    //Looking right
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
                    //Looking left
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
                    //Looking up right
                    if ((i-1)>-1 && board.get(i-1).get(j).getColor()!=p.getColor() && board.get(i-1).get(j).getColor()!= Disk.Color.EMPTY){
                        if (j+1<BOARD_SIZE && board.get(i).get(j+1).getColor()!=p.getColor() && board.get(i).get(j+1).getColor()!= Disk.Color.EMPTY){
                            rot=0;
                            if (i-2>-1 && j+2<BOARD_SIZE){
                                while ((board.get(i - 1 - rot).get(j).getColor() != p.getColor() && (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY || (i - 1 - rot) > -1)) && (board.get(i).get(j + 1 + rot).getColor() != p.getColor() && (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY || (j + 1 + rot) < BOARD_SIZE))){
                                    if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                        break;
                                    }
                                    if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY || j+1+rot>=BOARD_SIZE) {
                                        break;
                                    }
                                    rot++;
                                }
                                if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                    if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY) {
                                        avalibleSlots.add(Integer.toString((i)) + " " + Integer.toString(j + 1 + rot));
                                    }
                                }
                            }
                        }
                    }
                    //Looking up left
                    if ((i-1)>-1 && board.get(i-1).get(j).getColor()!=p.getColor() && board.get(i-1).get(j).getColor()!= Disk.Color.EMPTY){
                        if ((j-1)>-1 && board.get(i).get(j-1).getColor()!=p.getColor() && board.get(i).get(j-1).getColor()!= Disk.Color.EMPTY){
                            rot = 0;
                            if (i-2>-1 && j-2<-1){
                                while ((board.get(i - 1 - rot).get(j).getColor() != p.getColor() && (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY || (i - 1 - rot) > -1)) && (board.get(i).get(j - 1 - rot).getColor() != p.getColor() && (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY || (j - 1 - rot) > -1))){
                                    if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                        break;
                                    }
                                    if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                        break;
                                    }
                                    rot++;
                                }
                                if (board.get(i - 1 - rot).get(j).getColor() == Disk.Color.EMPTY) {
                                    if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                        avalibleSlots.add(Integer.toString((i - 1 - rot)) + " " + Integer.toString(j));
                                    }
                                }
                            }
                        }
                    }
                    //Looking down right
                    if ((i+1)<BOARD_SIZE && board.get(i+1).get(j).getColor()!=p.getColor() && board.get(i+1).get(j).getColor()!= Disk.Color.EMPTY){
                        if (j+1<BOARD_SIZE && board.get(i).get(j+1).getColor()!=p.getColor() && board.get(i).get(j+1).getColor()!= Disk.Color.EMPTY){
                            rot = 0;
                            if (i+2<BOARD_SIZE && j+2<BOARD_SIZE){
                                while ((board.get(i + 1 + rot).get(j).getColor() != p.getColor() && (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || (i + 1 + rot) < BOARD_SIZE)) && (j+1<BOARD_SIZE && board.get(i).get(j+1).getColor()!=p.getColor() && board.get(i).get(j+1).getColor()!= Disk.Color.EMPTY)){
                                    if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || i+1+rot>=BOARD_SIZE) {
                                        break;
                                    }
                                    if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY || j+1+rot>=BOARD_SIZE) {
                                        break;
                                    }
                                    rot++;
                                }
                                if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && i+1+rot<BOARD_SIZE) {
                                    if (board.get(i).get(j + 1 + rot).getColor() == Disk.Color.EMPTY) {
                                        avalibleSlots.add(Integer.toString((i + 1 + rot)) + " " + Integer.toString(j));
                                    }
                                }
                            }
                        }
                    }
                    //Looking down left
                    if ((i+1)<BOARD_SIZE && board.get(i+1).get(j).getColor()!=p.getColor() && board.get(i+1).get(j).getColor()!= Disk.Color.EMPTY){
                        if ((j-1)>-1 && board.get(i).get(j-1).getColor()!=p.getColor() && board.get(i).get(j-1).getColor()!= Disk.Color.EMPTY){
                            rot=0;
                            if (i+2<BOARD_SIZE && j-2<-1){
                                while ((board.get(i + 1 + rot).get(j).getColor() != p.getColor() && (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || (i + 1 + rot) < BOARD_SIZE)) && (board.get(i).get(j - 1 - rot).getColor() != p.getColor() && (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY || (j - 1 - rot) > -1))){
                                    if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY || i+1+rot>=BOARD_SIZE) {
                                        break;
                                    }
                                    if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY && board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                        break;
                                    }
                                    rot++;
                                }
                                if (board.get(i + 1 + rot).get(j).getColor() == Disk.Color.EMPTY && i+1+rot<BOARD_SIZE) {
                                    if (board.get(i).get(j - 1 - rot).getColor() == Disk.Color.EMPTY) {
                                        avalibleSlots.add(Integer.toString((i + 1 + rot)) + " " + Integer.toString(j));
                                    }
                                }
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

    public ArrayList<String> diskToBeChanged(ArrayList<String> locaList,Player p){
        ArrayList<String> locList = locaList;
        ArrayList<String> changeList = new ArrayList<>();
        int row,column,res,resA,resX,resY;

        for (int i = 0;i<locList.size();i++){
            String tmp = locList.get(i);
            String[] tmparr = tmp.split("[^\\d]+");
            row = Integer.parseInt(tmparr[0]);
            column = Integer.parseInt(tmparr[1]);
            //If two disk on same column
            if (p.getPosX()==column){
                if (p.getPosY() > row){
                    res=p.getPosY()-row-1;
                    resA=p.getPosY();
                }else {
                    res=row-p.getPosY()-1;
                    resA=row;
                }
                for (int j = 0; j<res;j++){
                    changeList.add((resA-(res-j)) + " " +column);
                }
            }
            //If two disk on same row
            if (p.getPosY() == row){
                if (p.getPosX()>column){
                    res=p.getPosX()-column-1;
                    resA=p.getPosX();
                }else {
                    res=column-p.getPosX()-1;
                    resA=column;
                }
                for (int j = 0;j<res;j++){
                    changeList.add(row + " " + (resA-(res-j)));
                }
            }
            //Up right
            if (p.getPosX()<column && p.getPosY()>row){
                resX=column-p.getPosY()-1;
                resY=p.getPosY()-row-1;
                for (int j=0;j<resX;j++){
                    for (int h=0;h<resY;h++){
                        changeList.add((p.getPosY()-(resY-h)) + " " + (column-(resX-j)));
                    }
                }
            }
            //Down right
            if (p.getPosX()<column && p.getPosY()<row){
                resX=column-p.getPosY()-1;
                resY=row-p.getPosY()-1;
                for (int j=0;j<resX;j++){
                    for (int h=0;h<resY;h++){
                        changeList.add((row-(resY-h)) + " " + (column-(resX-j)));
                    }
                }
            }
            //Up left
            if (p.getPosX()>column && p.getPosY()>row){
                resX=p.getPosY()-column-1;
                resY=p.getPosY()-row-1;
                for (int j=0;j<resX;j++){
                    for (int h=0;h<resY;h++){
                        changeList.add((p.getPosY()-(resY-h)) + " " + (p.getPosX()-(resX-j)));
                    }
                }
            }
            //Down left
            if (p.getPosX()>column && p.getPosY()<row){
                resX=p.getPosY()-column-1;
                resY=row-p.getPosY()-1;
                for (int j=0;j<resX;j++){
                    for (int h=0;h<resY;h++){
                        changeList.add((row-(resY-h)) + " " + (p.getPosX()-(resX-j)));
                    }
                }
            }
        }
        return changeList;
    }

    /**
     * Places a disk on the desired row and colum. It returns true/false depending if the place is already occupied by a disk.
     * @param row int
     * @param column int
     * @param player boolean
     * @return true or false
     */
    public boolean placeDisk(int row,int column,boolean player){

        if (!placedOccupied(row,column)){
            board.get(row).get(column).setPlaced(player);
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
        return board.get(row).get(column).isPlaced();
    }

    public void changeColorOfDisk(int row, int column, Disk.Color color){
        board.get(row).get(column).setColor(color);
    }

    /**
     * Get the board of type ArrayList<ArrayList<Disk>>
     * @return board
     */
    public ArrayList<ArrayList<Disk>> getBoard() {
        return board;
    }
}
