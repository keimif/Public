import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Animal implements Serializable {
    private String id;
    private String type;
    private int weight;
    private Date dateObtained;
    private Location location;
    
    // Animal constructors
    public Animal(){
        this.id = "default";
        this.type = "default";
        this.weight = 0;
        this.dateObtained = new Date(0,0,0);
        this.location = new Location("default","default");
    }
    
    public Animal(String animalId, String animalType, int  animalWeight, Location animalLocation, Date dateObtained){
        this.id = animalId;
        this.dateObtained = dateObtained;
        this.type = animalType;
        this.weight = animalWeight;
        this.location = animalLocation;
    }
    
    // The toString method which prints out all of the animal details
    public String toString(){
        return "Animal " + this.id + " is a " + this.type + ", obtained on " + new SimpleDateFormat("dd/MM/yyyy").format(this.dateObtained) + ", weighing " + this.weight + "Kg and is currently located in Room " + this.location.getRoom() + " Section " + this.location.getSection();
    }
    
    // Getters and Setters
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }   
    
    public Date getDate(){
        return this.dateObtained;
    }
    
    public void setDate(Date dateObtained){
        this.dateObtained = dateObtained;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public String getLocationRoom(){
        return this.location.getRoom();
    }
    
    public String getLocationSection(){
        return this.location.getSection();
    }
    
    public Location getLocation(){
        return this.location;
    }
    
    public void setLocation(String room, String section){
        this.location = new Location(room, section);
    }
    
    public void setLocation(Location location){
        this.location = location;
    }
}