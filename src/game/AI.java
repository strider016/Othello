package game;

import java.util.ArrayList;

/**
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class AI extends Player {
    
   
    private int rowMax, columnMax;
    private Disk.Color color;
    private ArrayList<String> availableSlots;
    private ArrayList<String> locationDisks;
    
    private ArrayList<Pos> avalibleSlotsTransformed;
    private ArrayList<Pos> locationDisksTransformed;
    
    /** 
     * Constructor of AI
     */
    public AI(Disk.Color color){
        this.color = color;
    }
    
    private class Pos{
        private int row;
        private int column;
        
        public int getRow(){
            return this.row;
        }
        
        public void setRow(int row){
            this.row = row;
        }
        
        public int getColumn(){
            return this.column;
        }
        
        public void setColumn(int column){
            this.column = column;
        }
    }
    
    
    public void checkForMoveWithMaxPoint(){
        transform();
        Pos maxPos = new Pos();
        int maxDistance=0;
        int distance=0;
        
        for(int i=0; i<avalibleSlotsTransformed.size() ; i++){
            for(int j=0; j<locationDisksTransformed.size() ; j++){
                
                //Check Column
                if(avalibleSlotsTransformed.get(i).getColumn() == locationDisksTransformed.get(j).getColumn()){
                    if(avalibleSlotsTransformed.get(i).getRow() > locationDisksTransformed.get(j).getRow()){
                        distance = avalibleSlotsTransformed.get(i).getRow() - locationDisksTransformed.get(j).getRow();
                        if(distance > maxDistance){
                            maxPos.setColumn(avalibleSlotsTransformed.get(i).getColumn());
                            maxPos.setRow(avalibleSlotsTransformed.get(i).getRow());
                        }
                    }
                    else if (avalibleSlotsTransformed.get(i).getRow() < locationDisksTransformed.get(j).getRow()){
                        distance = locationDisksTransformed.get(j).getRow()  - avalibleSlotsTransformed.get(i).getRow();
                        if(distance > maxDistance){
                            maxPos.setColumn(avalibleSlotsTransformed.get(i).getColumn());
                            maxPos.setRow(avalibleSlotsTransformed.get(i).getRow());
                        }
                    }
                }
                
                //Check row
                if(avalibleSlotsTransformed.get(i).getRow() == locationDisksTransformed.get(j).getRow()){
                    if(avalibleSlotsTransformed.get(i).getColumn() > locationDisksTransformed.get(j).getColumn()){
                        distance = avalibleSlotsTransformed.get(i).getColumn() - locationDisksTransformed.get(j).getColumn();
                        if(distance > maxDistance){
                            maxPos.setColumn(avalibleSlotsTransformed.get(i).getColumn());
                            maxPos.setRow(avalibleSlotsTransformed.get(i).getRow());
                        }
                    }
                    else if (avalibleSlotsTransformed.get(i).getColumn() < locationDisksTransformed.get(j).getColumn()){
                        distance = locationDisksTransformed.get(j).getColumn()  - avalibleSlotsTransformed.get(i).getColumn();
                        if(distance > maxDistance){
                            maxPos.setColumn(avalibleSlotsTransformed.get(i).getColumn());
                            maxPos.setRow(avalibleSlotsTransformed.get(i).getRow());
                        }
                    }
                  
                 //Checks diagonal??
                }
            }
        }
        
    }
    
    private void transform(){
        for (int i = 0;i<availableSlots.size();i++){
                String tmp = availableSlots.get(i);
                String[] tmparr = tmp.split("[^\\d]+");
                avalibleSlotsTransformed.get(i).setRow(Integer.parseInt(tmparr[0]));
                avalibleSlotsTransformed.get(i).setColumn(Integer.parseInt(tmparr[1]));
        }
        
        for (int j = 0; j<locationDisks.size(); j++){
                String tmp = locationDisks.get(j);
                String[] tmparr = tmp.split("[^\\d]+");
                locationDisksTransformed.get(j).setRow(Integer.parseInt(tmparr[0]));
                locationDisksTransformed.get(j).setColumn(Integer.parseInt(tmparr[1]));
        } 
    }
    
    
    public void setAvailableSlots(ArrayList<String> avalibleSlots){
        this.availableSlots = avalibleSlots;
    }
    
    public void setLocationDisks(ArrayList<String> locationDisks){
        this.locationDisks = locationDisks;
    }
    
    public ArrayList<String> getAvailableSlots(){
        return this.availableSlots;
    }
    
    public int getRowMax(){
        return this.rowMax;
    }
    
    public int getColumnMax(){
        return this.columnMax;
    }
}
