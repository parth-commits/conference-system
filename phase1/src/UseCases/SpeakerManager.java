package UseCases;

import Entities.Attendee;
import Entities.Speaker;

import java.util.ArrayList;
import java.util.Hashtable;

public class SpeakerManager {
    private Hashtable<String, Speaker> tableOfSpeakers;

    public SpeakerManager (){
        tableOfSpeakers = new Hashtable<>();
    }

    public void addSpeaker(String user_id, String username, String passwords){
        Organizer newSpeaker = new Speaker(user_id, username, passwords);
        tableOfSpeakers.put(newSpeaker.getUser_id(), newSpeaker);
    }

    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            Speaker speaker = tableOfSpeakers.get(inputUserId);
            return speaker.getPasswords().equals(inputUserPassword);
        }return false;
    }

    private boolean userExist(String userId){
        return tableOfSpeakers.containsKey(userId);
    }

    private Speaker getSpeaker(String userId){
        return tableOfSpeakers.get(userId);
    }

    public void addEventToSpeaker (Integer EventId, String userId){
        if (userExist(userId)) {
            getSpeaker(userId).addEvent(EventId);
        }
    }

    public void removeEvent(Integer EventId, String userId) {
        if (userExist(userId)) {
            getSpeaker(userId).removeEvent(EventId);
        }
    }

    public void addContact (String userId, String otherUserId){
        if (userExist(userId) && userExist(otherUserId)){
            (getSpeaker(userId).addContact(otherUserId)) &&
                    getSpeaker(otherUserId).addContact(userId);
        }
    }

    public void removeContact (String userId, String otherUserId){
        if (userExist(userId) && userExist(otherUserId)){
            if (getSpeaker(userId).removeContact(otherUserId)) {
                getSpeaker(otherUserId).removeContact(userId);
            }
        }
    }



}
