package UseCases;

import Entities.Attendee;
import Entities.Organizer;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrganizerManager {

    private Hashtable<String, Organizer> tableOfOrganizers;

    public OrganizerManager (){
        tableOfOrganizers = new Hashtable<>();
    }

    public void addOrganizer(String user_id, String username, String passwords){
        Organizer newOrganizer = new Organizer(user_id, username, passwords);
        tableOfOrganizers.put(newOrganizer.getUser_id(), newOrganizer);
    }

    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            Organizer organizer = tableOfOrganizers.get(inputUserId);
            return organizer.getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    public ArrayList<String> getUserIDs(){
        return new ArrayList<String>(tableOfOrganizers.keySet());
    }

    public boolean userExist(String userId){
        return tableOfOrganizers.containsKey(userId);
    }

    private Organizer getOrganizer(String userId){
        return tableOfOrganizers.get(userId);
    }

    //An attendee action
    public void addEventToOrganizer (Integer EventId, String userId){
        if (userExist(userId)) {
            getOrganizer(userId).addEvent(EventId);
        }
    }

    //An attendee action
    public void removeEvent(Integer EventId, String userId) {
        // call the method of event manager
        if (userExist(userId)) {
            getOrganizer(userId).removeEvent(EventId);
        }
    }

    //An attendee action
    public void addContact (String userId, String otherUserId){
        // let's decide if we want string?
        if (userExist(userId) && userExist(otherUserId)){
            getOrganizer(userId).addContact(otherUserId);
            getOrganizer(otherUserId).addContact(userId);
        }
    }

    //An attendee action
    public void removeContact (String userId, String otherUserId){
        if (userExist(userId) && userExist(otherUserId)){
            if (getOrganizer(userId).removeContact(otherUserId)) {
                getOrganizer(otherUserId).removeContact(userId);
            }
        }
    }


    /*but we need to actually create the speaker!!!*/
    //Following are Organizer ONLY actions
    public void setAddSpeakerCreated(String user_id, String speaker_user_id){
        if (userExist(user_id)) {
            getOrganizer(user_id).addSpeakerCreated(speaker_user_id);
        }
    }

    //but we actually have to add event to event system stuff!!!
    //this only tells that this user made the event, doesnt acc make the event
    public void setAddEventCreated(String user_id, String event_id){
        if (userExist(user_id)) {
            getOrganizer(user_id).addEventCreated(event_id);
        }
    }

    //but we acc have to make a event!!
    //this only deletes it from the organizers list!!!
    public void setDeleteEventCreated(String user_id, String event_id){
        if (userExist(user_id)) {
            getOrganizer(user_id).deleteEventCreated(event_id);
        }
    }

}
