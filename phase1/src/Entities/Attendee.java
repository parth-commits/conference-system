package Entities;

import java.util.ArrayList;

public class Attendee extends User{

    private ArrayList<String> signedUpEvents;

    private ArrayList<String> contacts;

    public Attendee(String user_id, String name, String passwords){
        super(user_id, name, passwords);
        this.signedUpEvents = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public ArrayList<String> getSignedUpEvents() {
        return signedUpEvents;
    }

    public void addContact(Integer user_id){
        this.contacts.add(Integer.toString(user_id));
    }

    public boolean removeContact(Integer user_id){
        return this.contacts.remove(Integer.toString(user_id));
    }

    public void addEvent(String eventTitle){
        this.signedUpEvents.add(eventTitle);
    }

    public boolean removeEvent(String eventTitle){
        return this.signedUpEvents.remove(eventTitle);
    }
}
