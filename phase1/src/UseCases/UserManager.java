package UseCases;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import Entities.User;

import java.util.Hashtable;

public class UserManager {

    //A dictionary of all the attendees
    private Hashtable<String, Attendee> tableOfAttendees;

    //A dictionary of all the speakers
    private Hashtable<String, Speaker> tableOfSpeakers;

    //A dictionary of all the organizers
    private Hashtable<String, Organizer> tableOfOrganizers;

    //The constructor of UserManager, initializes all dictionaries to first be empty
    public UserManager() {
        this.tableOfAttendees = new Hashtable<>();
        this.tableOfSpeakers = new Hashtable<>();
        this.tableOfOrganizers = new Hashtable<>();
    }

    //adds the given user to its respective dictionary. The type variable is for who the person is. 1 = organizer.
    //2 = attendee
    public void addUser(String user_id, String username, String passwords, int type) {
        if (type == 1) {
            Organizer newOrganizer = new Organizer(user_id, username, passwords);
            tableOfOrganizers.put(newOrganizer.getUser_id(), newOrganizer);
        } else if (type == 2) {
            Attendee newAttendee = new Attendee(user_id, username, passwords);
            tableOfAttendees.put(newAttendee.getUser_id(), newAttendee);
        }
    }

    //adding speaker is seperate, as there is 1 extra parameter - organizerID
    public void addSpeaker(String user_id, String username, String passwords, String organizerID) {
        Speaker newSpeaker = new Speaker(user_id, username, passwords, organizerID);
        tableOfSpeakers.put(newSpeaker.getUser_id(), newSpeaker);
    }

    //verifys the login info given
    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        int exists = userExistandType(inputUserId);
        if (exists == 1) {
            Organizer organizer = tableOfOrganizers.get(inputUserId);
            return organizer.getPasswords().equals(inputUserPassword);
        }
        else if (exists == 2){
            Attendee attendee = tableOfAttendees.get(inputUserId);
            return attendee.getPasswords().equals(inputUserPassword);
        }
        else if (exists == 3){
            Speaker speaker = tableOfSpeakers.get(inputUserId);
            return speaker.getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    //gets the user of the given user id. precondition: userid is a valid user id
    private User getUser(String userId){
        int exists = userExistandType(userId);
        if (exists == 1){
            return tableOfOrganizers.get(userId);
        }
        else if (exists == 2){
            return tableOfAttendees.get(userId);
        }
        else{
            return tableOfSpeakers.get(userId);
        }
    }

    //gets the user of the given user id. precondition: userid is a valid user id
    private Organizer getOrganizer(String userId){
        int exists = userExistandType(userId);
        if (exists == 1){
            return tableOfOrganizers.get(userId);
        }
        else if (exists == 2){
            return tableOfAttendees.get(userId);
        }
        else{
            return tableOfSpeakers.get(userId);
        }
    }

    //returns 1 if entered id is a organizer, 2 if its a attendee, 3 if its a speaker. returns -1 if the given id doesnt
    //exist.
    private int userExistandType(String userId){
        int exists = -1;
        if (tableOfOrganizers.containsKey(userId)){
            exists = 1;
        }
        else if (tableOfAttendees.containsKey(userId)){
            exists = 2;
        }
        else if (tableOfSpeakers.containsKey(userId)){
            exists = 3;
        }
        return exists;
    }

    //adds the given contact to the current user, if it works, returns true, else returns false
    public boolean addContact (String currentUserId, String otherUserId){
        int current = userExistandType(currentUserId);
        int other = userExistandType(otherUserId);
        if (current == -1 || other == -1){
            return false;
        }
        else{
            getUser(currentUserId).addContact(currentUserId);
            getUser(otherUserId).addContact(currentUserId);
            return true;
        }
    }

    //removes the given contact id from the current user, if it works, returns true, else returns false
    public boolean removeContact (String currentUserId, String otherUserId){
        int current = userExistandType(currentUserId);
        int other = userExistandType(otherUserId);
        if (current == -1 || other == -1){
            return false;
        }
        else{
            getUser(currentUserId).removeContact(currentUserId);
            getUser(otherUserId).removeContact(currentUserId);
            return true;
        }
    }

    public void addEventToAttendeeOrOrganizer (Integer EventId, String userId){
        int current = userExistandType(userId);
        if (current == 1 || current == 2) {
            getUser(userId).addEvent(EventId);
        }
    }

    public void removeEvent(Integer EventId, String userId) {
        // call the method of event manager
        if (userExist(userId)) {
            getAttendee(userId).removeEvent(EventId);
        }
    }
}