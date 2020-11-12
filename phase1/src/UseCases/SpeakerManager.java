package UseCases;

import Entities.Speaker;

import java.util.ArrayList;
import java.util.Hashtable;

public class SpeakerManager {
    private Hashtable<String, Speaker> tableOfSpeakers;

    public SpeakerManager (){
        tableOfSpeakers = new Hashtable<>();
    }

    public void addSpeaker(String user_id, String passwords, String name, String organizerID){
        Speaker newSpeaker = new Speaker(user_id, name, passwords, organizerID);
        tableOfSpeakers.put(newSpeaker.getUser_id(), newSpeaker);
    }

    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            return tableOfSpeakers.get(inputUserId).getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    public ArrayList<String> getUserIDs(){
        return new ArrayList<String>(tableOfSpeakers.keySet());
    }

    public boolean userExist(String userId){ return tableOfSpeakers.containsKey(userId);
    }

    private Speaker getSpeaker(String userId){
        return tableOfSpeakers.get(userId);
    }

    public void addEventToSpeaker (Integer EventId, String userId){
        //assume the userid exists and is a speaker
        getSpeaker(userId).setAssignEvent(EventId);
    }

    public void removeEvent(Integer EventId, String userId) {
        getSpeaker(userId).removeAssignEvent(EventId);
    }

    //check if contact is in
    public boolean contactExists(String userId, String otherUserId){
        return getOrganizer(userId).checkContact(otherUserId);
    }

    public void addContact (String userId, String otherUserId){
        // assume both users exists (checked in controller) and userid is added to otherUserID's contacts in controller as well.
        getSpeaker(userId).addContact(otherUserId);
    }

    public void removeContact (String userId, String otherUserId){
        //assume both users exists (checked in controller) and userid is removed from otherUserID's contacts in controller as well.
        getSpeaker(userId).removeContact(otherUserId);
    }



}
