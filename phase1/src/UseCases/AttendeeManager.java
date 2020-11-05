package UseCases;
import Entities.Attendee;

import java.util.ArrayList;
import java.util.Hashtable;

public class AttendeeManager {
/*    private ArrayList<Attendee> listOfAttendees;
    private ArrayList<String> listOfAttendeeId;*/
    private Hashtable<String, Attendee> tableOfAttendees;

    public AttendeeManager(){tableOfAttendees = new Hashtable<>();}

    public void addAttendee(Integer user_id, String username, String passwords){
        Attendee newAttendee = new Attendee(user_id, username, passwords);
        tableOfAttendees.put(user_id.toString(), newAttendee);
//        listOfAttendees.add(newAttendee);
//        listOfAttendeeId.add(user_id.toString());
    }

    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            Attendee a = tableOfAttendees.get(inputUserId);
            return a.getPasswords().equals(inputUserPassword);
        }return false;
    }

    private boolean userExist(String userId){
        if (tableOfAttendees.containsKey(userId)){
            return true;
        }
        return false;
    }

    private Attendee getAttendee(String userId){
        Attendee a = tableOfAttendees.get(userId);
        return a;
    }

    public void addEvent (String title, String userId){
        // call the method of event manager
        if (userExist(userId)) {
            getAttendee(userId).addEvent(title);
        }
    }

    public void removeEvent(String title, String userId) {
        // call the method of event manager
        if (userExist(userId)) {
            getAttendee(userId).removeEvent(title);
        }
    }

    public void addContact (String userId, String otherUserId){
        // let's decide if we want string?
        if (userExist(userId) && userExist(otherUserId)){
            getAttendee(userId).addContact(userId) &&
                    getAttendee(otherUserId).addContact(otherUserId);
        }
    }

    public void removeContact (String userId, String otherUserId){
        if (userExist(userId) && userExist(otherUserId)){
            getAttendee(userId).removeContact(userId) &&
                    getAttendee(otherUserId).removeContact(otherUserId);
        }
    }
}
