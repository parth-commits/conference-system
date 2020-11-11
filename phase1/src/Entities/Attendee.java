package Entities;

import java.util.ArrayList;

public class Attendee extends User{

    //list of signed up events of the attendee
    private ArrayList<Integer> signedUpEvents;

    public Attendee(String user_id, String name, String passwords){
        super(user_id, name, passwords);
        this.signedUpEvents = new ArrayList<>();
    }

    //gets the list of signed up events
    public ArrayList<Integer> getSignedUpEvents() {
        return signedUpEvents;
    }

    //adds an event by id to the list of signedUpEvents
    public void addEvent(Integer EventId){
        this.signedUpEvents.add(EventId);
    }

    //removes an event by id from the list of signedUpEvents
    public boolean removeEvent(Integer EventId){
        return this.signedUpEvents.remove(EventId);
    }
}
