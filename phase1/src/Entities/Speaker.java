package Entities;

import java.util.ArrayList;

public class Speaker extends User{

    //list of assigned events
    private ArrayList<Integer> assignEvents;

    //userid of the organizer who created this event
    private String createdOrganizer;

    public Speaker(String user_id, String passwords, String name,  String organizer_id){
        super(user_id, name, passwords);
        this.assignEvents = new ArrayList<>();
        this.createdOrganizer = organizer_id;
        this.addContact(organizer_id);//the speaker can send message to the Organizer who creates this account
    }

    //returns the list of assigned events
    public ArrayList<Integer> getAssignEvents(){
        return assignEvents;
    }

    //assigns the speaker a new event
    public void setAssignEvent(int event_id){
        assignEvents.add(event_id);
    }

    //removes an assigned event
    public void removeAssignEvent(int event_id){assignEvents.remove(event_id);}

    //gets the organizer who created this speaker
    public String getCreatedOrganizer(){
        return this.createdOrganizer;
    }
}
