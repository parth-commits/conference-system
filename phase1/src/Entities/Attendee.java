package Entities;

import java.util.ArrayList;

public class Attendee extends User{

    private ArrayList<Integer> signedUpEvents;

    private ArrayList<String> contacts;

    public Attendee(String user_id, String name, String passwords){
        super(user_id, name, passwords);
        this.signedUpEvents = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public ArrayList<Integer> getSignedUpEvents() {
        return signedUpEvents;
    }

    public void addContact(String user_id){
        this.contacts.add(user_id);
    }

    public boolean removeContact(String user_id){
        return this.contacts.remove(user_id);
    }

    public void addEvent(Integer EventId){
        this.signedUpEvents.add(EventId);
    }

    public boolean removeEvent(Integer EventId){
        return this.signedUpEvents.remove(EventId);
    }
}
