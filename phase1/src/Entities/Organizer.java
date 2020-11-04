package Entities;

import java.util.ArrayList;

public class Organizer extends Attendee{

    //list of all contacts of this Organizer
    private ArrayList<String> contacts;

    //list of all events that this Organizer signed up for
    private ArrayList<String> signedUpEvents;

    //list of all speakers created
    private ArrayList<String> speakersCreated;

    //list of all events created by this Organzier
    private ArrayList<String> eventsCreated;

    public Organizer(Integer user_id, String name, String passwords) {
        super(user_id, name, passwords);
        this.contacts = new ArrayList<>();
        this.signedUpEvents = new ArrayList<>();
        this.speakersCreated = new ArrayList<>();
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void addContact(Integer user_id){
        this.contacts.add(Integer.toString(user_id));
    }

    public boolean removeContact(Integer user_id){
        return this.contacts.remove(Integer.toString(user_id));
    }

    public ArrayList<String> getSignedUpEvents() {
        return signedUpEvents;
    }

    public void addEvent(String eventTitle){
        this.signedUpEvents.add(eventTitle);
    }

    public boolean removeEvent(String eventTitle){
        return this.signedUpEvents.remove(eventTitle);
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
