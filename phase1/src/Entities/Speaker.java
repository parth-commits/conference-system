package Entities;

import java.util.ArrayList;

public class Speaker {

    private Integer user_id;
    private String passwords;
    private String name;
    private ArrayList<Integer> contacts;
    //store the user_id of an Attendee instead of the Attendee to avoid breaking clean architecture.
    private ArrayList<Integer> assignEvents;
    //we store the event_id of an event.
    private Integer createdOrganizer;
    //store the user_id of an Organizer.

    public Speaker(Integer user_id, String passwords, String name,  Integer organizer_id){
        this.user_id = user_id;
        this.passwords = passwords;
        this.name = name;
        this.contacts = new ArrayList<>();
        this.assignEvents = new ArrayList<>();
        this.createdOrganizer = organizer_id;

        this.contacts.add(organizer_id); //the speaker can send message to the Organizer who creates this account
    }

    public Integer getUser_id(){
        return user_id;
    }

    public String getPasswords(){
        return passwords;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Integer> getContacts(){
        return contacts;
    }

    public ArrayList<Integer> getAssignEvents(){
        return assignEvents;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPasswords(String passwords){
        this.passwords = passwords;
    }

    public void addContact(Integer user_id){
        this.contacts.add(user_id);
    }

    public boolean removeContact(Integer user_id){
        return this.contacts.remove(user_id);
    }

    public void addEvent(Integer event_id){
        this.assignEvents.add(event_id);
    }

    public boolean removeEvent(Integer event_id){
        return this.assignEvents.remove(event_id);
    }

}
