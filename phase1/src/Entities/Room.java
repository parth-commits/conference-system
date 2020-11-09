package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Room {
    private String name;
    private Integer capacity = 2;
    //should we use date or time???
    private Hashtable<Date, Integer> bookedTime;

    public Room(String name){
        this.name = name;
        this.bookedTime = new Hashtable<>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public Integer getCapacity(){
        return this.capacity;}

    public Hashtable<Date, Integer> getBookedTime(){
        return this.bookedTime;
    }

    public boolean addBookedTime(Date date, Integer EventId){
        if (bookedTime.containsKey(date)){
            return false;
        }
        bookedTime.put(date, EventId);
        return true;
    }

    public boolean removeBookedTime(Date date, Integer EventId){
        if (bookedTime.containsKey(date) && bookedTime.get(date).equals(EventId)){
            bookedTime.remove(date, EventId);
            return true;
        }
        return false;
    }

}
