package UseCases;
import Entities.Attendee;

import java.util.ArrayList;
import java.util.Hashtable;

public class AttendeeManager {

    //dictionary of all attendees
    private Hashtable<String, Attendee> tableOfAttendees;

    //constructor
    public AttendeeManager(){tableOfAttendees = new Hashtable<>();}

    //adds an attendee
    public void addAttendee(String user_id, String username, String passwords){
        Attendee newAttendee = new Attendee(user_id, username, passwords);
        tableOfAttendees.put(newAttendee.getUser_id(), newAttendee);
    }

    //verifys the login given the inputted credentials. true if correct login info, false otherwise
    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            return tableOfAttendees.get(inputUserId).getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    //gets all the list of userids
    public ArrayList<String> getUserIDs(){
        return new ArrayList<String>(tableOfAttendees.keySet());
    }

    //checks if the user exists
    private boolean userExist(String userId){
        return tableOfAttendees.containsKey(userId);
    }

    //returns the attendee
    private Attendee getAttendee(String userId){
        return tableOfAttendees.get(userId);
    }

    public void addEventToAttendee (Integer EventId, String userId){
        if (userExist(userId)) {
            getAttendee(userId).addEvent(EventId);
        }
    }

    public void removeEvent(Integer EventId, String userId) {
        // call the method of event manager
        if (userExist(userId)) {
            getAttendee(userId).removeEvent(EventId);
        }
    }

    public void addContact (String userId, String otherUserId){
        // let's decide if we want string?
        if (userExist(userId) && userExist(otherUserId)){
            getAttendee(userId).addContact(otherUserId);
                    getAttendee(otherUserId).addContact(userId);
        }
    }

    public void removeContact (String userId, String otherUserId){
        if (userExist(userId) && userExist(otherUserId)){
            if (getAttendee(userId).removeContact(otherUserId)) {
                getAttendee(otherUserId).removeContact(userId);
            }
        }
    }
}
