package Entities;

import java.util.ArrayList;

public class Speaker extends User{

    private ArrayList<String> contacts;
    //store the user_id of an Attendee instead of the Attendee to avoid breaking clean architecture.
    private ArrayList<String> assignEvents;
    //we store the event_id of an event.
    private String createdOrganizer;
    //store the user_id of an Organizer.

    public Speaker(String user_id, String passwords, String name,  String organizer_id){
        super(user_id, name, passwords);
        this.contacts = new ArrayList<>();
        this.assignEvents = new ArrayList<>();
        this.createdOrganizer = organizer_id;

        this.contacts.add(organizer_id); //the speaker can send message to the Organizer who creates this account
    }


    public ArrayList<String> getContacts(){
        return contacts;
    }

    public ArrayList<String> getAssignEvents(){
        return assignEvents;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addContact(String user_id){
        this.contacts.add(user_id);
    }

    public boolean removeContact(String user_id){
        return this.contacts.remove(user_id);
    }

}
