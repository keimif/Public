import java.io.Serializable;

public class Location implements Serializable{
    private String room;
    private String section;
    
    // Location constructors
    public Location(){
        this.room = "default";
        this.section = "default";
    }
    
    public Location(String room, String section){
        this.room = room;
        this.section = section;
    }
    
    // Getters and Setters
    public String getRoom(){
        return this.room;
    }
    
    public void setRoom(String room){
        this.room = room;
    }
    
    public String getSection(){
        return this.section;
    }
    
    public void setSection(String section){
        this.section = section;
    }
}