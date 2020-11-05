package UseCases;
import Entities.Attendee;

import java.util.ArrayList;
import java.util.Hashtable;

public class AttendeeManager {
/*    private ArrayList<Attendee> listOfAttendees;
    private ArrayList<String> listOfAttendeeId;*/
    private Hashtable<String, Attendee> tableOfAttendees;

    public AttendeeManager(){tableOfAttendees = new Hashtable<>();}

    public void addAttendee(String user_id, String username, String passwords){
        Attendee newAttendee = new Attendee(user_id, username, passwords);
        tableOfAttendees.put(newAttendee.getUser_id(), newAttendee);
//        listOfAttendees.add(newAttendee);
//        listOfAttendeeId.add(user_id.toString());
    }

    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            Attendee attendee = tableOfAttendees.get(inputUserId);
            return attendee.getPasswords().equals(inputUserPassword);
        }return false;
    }

    private boolean userExist(String userId){
        return tableOfAttendees.containsKey(userId);
    }

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
            (getAttendee(userId).addContact(otherUserId)) &&
                    getAttendee(otherUserId).addContact(userId)
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
