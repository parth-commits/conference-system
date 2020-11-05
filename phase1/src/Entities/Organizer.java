package Entities;

import java.util.ArrayList;

public class Organizer extends Attendee{

    //list of all speakers created
    private ArrayList<String> speakersCreated;

    //list of all events created by this Organzier
    private ArrayList<String> eventsCreated;

    public Organizer(String user_id, String name, String passwords) {
        super(user_id, name, passwords);
        this.speakersCreated = new ArrayList<>();
        this.eventsCreated = new ArrayList<>();
    }

    public void addspeakerCreated(Integer user_id){
        this.speakersCreated.add(Integer.toString(user_id));
    }

    public void addEventCreated(String event_id){
        this.eventsCreated.add(event_id);
    }

    public boolean deleteEventCreated(String event_id){
        return this.eventsCreated.remove(event_id);
    }

}
