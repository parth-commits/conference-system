package Entities;

import java.util.ArrayList;

public class Attendee {

    private Integer user_id;
    private String passwords;
    private String name;
    private ArrayList<String> contacts;
    private ArrayList<String> signedUpEvents;

    public Attendee(Integer user_id, String name, String passwords){
    this.user_id = user_id;
    this.name = name;
    this.passwords = passwords;
    this.contacts = new ArrayList<>();
    this.signedUpEvents = new ArrayList<>();
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPasswords() {
        return passwords;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public ArrayList<String> getSignedUpEvents() {
        return signedUpEvents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
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
