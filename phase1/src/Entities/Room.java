package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Room {

    //name of the room
    private String name;

    //capacity of the room
    private Integer capacity = 2;

    //location of the room
    private String roomLocation;

    //the key is the  time, and the value is the eventid
    private Hashtable<Date, Integer> bookedTime;


    //the constructor
    public Room(String name, String roomLoc){
        this.name = name;
        this.roomLocation = roomLoc;
        this.bookedTime = new Hashtable<>();
    }

    //returns the name of the room
    public String getName(){
        return this.name;
    }

    //get the room location
    public String getRoomLocation() {
        return roomLocation;
    }

    //sets the name of the room
    public void setName(String newName){
        this.name = newName;
    }

    //gets the capacity of the room
    public Integer getCapacity(){
        return this.capacity;
    }

    //gets the dictionary of the date - event booked
    public Hashtable<Date, Integer> getBookedTime(){
        return this.bookedTime;
    }

    //adds an event to the given time in our dictionary
    public boolean addBookedTime(Date date, Integer EventId){
        //if the room is already booked, you cant add a new event!
        if (bookedTime.containsKey(date)){
            return false;
        }
        //if it isnt booked, add the new event
        bookedTime.put(date, EventId);
        return true;
    }

    //removes event at given time from our dictionary
    public boolean removeBookedTime(Date date, Integer EventId){
        //if the event exists at the time inour dictionary, then delete the event
        if (bookedTime.containsKey(date) && bookedTime.get(date).equals(EventId)){
            bookedTime.remove(date, EventId);
            return true;
        }
        //otherwise return false, ie. the event wasnt deleted from the time
        return false;
    }
}
