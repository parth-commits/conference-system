package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Organizer entity implements an application that simply records users, the list
 * of event organizer created.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */

public class Organizer extends Attendee implements Serializable {
    //list of all speakers created
    private ArrayList<String> speakersCreated;
    //list of all events created by this Organzier
    private ArrayList<Integer> eventsCreated;

    /**
     * Constructor
     * @param user_id
     * @param name
     * @param passwords
     */
    public Organizer(String user_id, String name, String passwords) {
        super(user_id, name, passwords);
        this.speakersCreated = new ArrayList<>();
        this.eventsCreated = new ArrayList<>();
    }

    //adds a speaker that this organizer created
    public void addSpeakerCreated(String user_id){
        this.speakersCreated.add(user_id);
    }

    //adds an event created by this organizer. (Only adds id of event)
    public void addEventCreated(Integer event_id){
        this.eventsCreated.add(event_id);
    }

    //deletes an event created by this organizer from the list eventsCreated.
    public boolean deleteEventCreated(Integer event_id){
        return this.eventsCreated.remove(event_id);
    }


}
