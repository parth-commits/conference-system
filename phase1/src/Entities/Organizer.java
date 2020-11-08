package Entities;

import java.util.ArrayList;

public class Organizer extends Attendee{

    //list of all contacts of this Organizer
    private ArrayList<String> contacts;

    //list of all events that this Organizer signed up for
    private ArrayList<Integer> signedUpEvents;

    //list of all speakers created
    private ArrayList<String> speakersCreated;

    //list of all events created by this Organzier
    private ArrayList<String> eventsCreated;

    public Organizer(String user_id, String name, String passwords) {
        super(user_id, name, passwords);
        this.contacts = new ArrayList<>();
        this.signedUpEvents = new ArrayList<>();
        this.speakersCreated = new ArrayList<>();
        this.eventsCreated = new ArrayList<>();
    }

    public void addSpeakerCreated(String user_id){
        this.speakersCreated.add(user_id);
    }

    public void addEventCreated(String event_id){
        this.eventsCreated.add(event_id);
    }

    public boolean deleteEventCreated(String event_id){
        return this.eventsCreated.remove(event_id);
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public boolean addContact(String user_id){
        return this.contacts.add(user_id);
    }

    public boolean removeContact(String user_id){
        return this.contacts.remove(user_id);
    }

    public ArrayList<Integer> getSignedUpEvents() {
        return signedUpEvents;
    }

    public void addEvent(Integer eventTitle){
        this.signedUpEvents.add(eventTitle);
    }

    public boolean removeEvent(String eventTitle){
        return this.signedUpEvents.remove(eventTitle);
    }
}
