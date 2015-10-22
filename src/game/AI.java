package game;

import java.util.ArrayList;

/**
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class AI extends Player {
    
   
    private int rowMax, columnMax; 
    private ArrayList<String> availableSlots;
    private ArrayList<String> locationDisks;
    
    private ArrayList<Pos> avalibleSlotsTransformed;
    private ArrayList<Pos> locationDisksTransformed;
    
    /** 
     * Constructor of AI
     */
    public AI(){
        
    }
    
    private class Pos{
        private int row;
        private int column;
        
        public void setRow(int row){
            this.row = row;
        }
        public void setColumn(int column){
            this.column = column;
        }
    }
    
    public void checkForMoveWithMaxPoint(){
        transform();
        
        for(int i=0; i<avalibleSlotsTransformed.size() ; i++){
            for(int j=0; j<locationDisksTransformed.size() ; j++){
                
                
                
                
                
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
