package UseCases;
import Entities.Attendee;

import java.util.ArrayList;
import java.util.Hashtable;

public class  AttendeeManager {

    //dictionary of all attendees
    private Hashtable<String, Attendee> tableOfAttendees;

    //constructor
    public AttendeeManager(){tableOfAttendees = new Hashtable<>();}

    //adds an attendee
    public void addAttendee(String user_id, String username, String passwords){
        Attendee newAttendee = new Attendee(user_id, username, passwords);
        tableOfAttendees.put(newAttendee.getUser_id(), newAttendee);
    }

    //verifies the login given the inputted credentials. true if correct login info, false otherwise
    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            return tableOfAttendees.get(inputUserId).getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    //gets the list of all userids
    public ArrayList<String> getUserIDs(){
        return new ArrayList<String>(tableOfAttendees.keySet());
    }

    //checks if the user exists
    public boolean userExist(String userId){
        return tableOfAttendees.containsKey(userId);
    }

    //returns the attendee
    public Attendee getAttendee(String userId){
        return tableOfAttendees.get(userId);
    }

    public void addEventToAttendee (Integer EventId, String userId){
        getAttendee(userId).addEvent(EventId);

    }

    public boolean SignedUp(Integer EventId, String userId) {
        if(getAttendee(userId).getSignedUpEvents().contains(EventId)){
            return true;
        }
        return false;
    }

    public void removeEvent(Integer EventId, String userId) {
        getAttendee(userId).removeEvent(EventId);
    }

    //check if contact already exists
    public boolean contactExists(String userId, String otherUserId){
        return getAttendee(userId).checkContact(otherUserId);
    }

    public void addContact (String userId, String otherUserId){
        // assume both users exists (checked in controller) and userid is added to otherUserID's contacts in controller as well.
        getAttendee(userId).addContact(otherUserId);
        }

    public void removeContact (String userId, String otherUserId){
        //assume both users exists (checked in controller) and userid is removed from otherUserID's contacts in controller as well.
        getAttendee(userId).removeContact(otherUserId);
    }

    public ArrayList<Integer> getSignedUpEvents (String userId){
        return getAttendee(userId).getSignedUpEvents();
    }
}
