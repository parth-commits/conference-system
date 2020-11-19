package UseCases;

import Entities.Speaker;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The SpeakerManager program implements an application that records all speakers from Speaker and the contact list
 * and verify LogIn. The users in the contact list can be added or removed.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */

public class SpeakerManager implements Serializable{
    private Hashtable<String, Speaker> tableOfSpeakers;

    /**
     * Constructor
     * implements a empty Hashable for recording Speakers later on.
     */
    public SpeakerManager (){
        tableOfSpeakers = new Hashtable<>();
    }

    /**
     * Add a Speaker to this SpeakerManager by user_id, passwords, name, organizerID.
     * @param user_id the user id of this user. User id is an unique integer for each user
     * @param passwords the registered passwords of this user
     * @param name the registered name of this user
     * @param organizerID the organizer who created this Speaker
     */
    public void addSpeaker(String user_id, String passwords, String name, String organizerID){
        Speaker newSpeaker = new Speaker(user_id,  passwords, name, organizerID);
        tableOfSpeakers.put(newSpeaker.getUser_id(), newSpeaker);
    }

    /**
     * Verify if the LogIn is valid.
     * @param inputUserId the user_id that user enter to the keyboard
     * @param inputUserPassword the password that user enter to the keyboard
     * @return Boolean return true if LogIn successfully, otherwise returns false
     */
    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            return tableOfSpeakers.get(inputUserId).getPasswords().equals(inputUserPassword);
        }
        return false;
    }


    /**
     * Returns a list of contacts of the given user by it's user_id.
     * @param userID the user id of this user
     * @return ArrayList returns a list of contacts of the given user
     */
    public ArrayList<String> contactList(String userID){
        return getSpeaker(userID).getContacts();
    }

    /**
     * Returns a list of user_ids of all Speakers in SpeakerManager.
     * @return ArrayList a list list of user_ids
     */
    public ArrayList<String> getUserIDs(){
        return new ArrayList<String>(tableOfSpeakers.keySet());
    }

    /**
     * Check if the speaker with its given user_id is in SpeakerManager.
     * @param userId the user id of this speaker
     * @return boolean return true if SpeakerManager contains this speaker
     */
    public boolean userExist(String userId){ return tableOfSpeakers.containsKey(userId);
    }

    /**
     * Returns a speaker refer to it's given user_id.
     * @param userId the user id of speaker
     * @return Speaker the speaker refer to the given user_id
     */
    public Speaker getSpeaker(String userId){
        return tableOfSpeakers.get(userId);
    }

    /**
     * Assign an event to a speaker with its given user_id.
     * @param EventId the event id of the event which is assigned to speaker
     * @param userId the user id of this speaker
     */
    public void addEventToSpeaker (Integer EventId, String userId){
        //assume the userid exists and is a speaker
        getSpeaker(userId).setAssignEvent(EventId);
    }

    /**
     * Cancel the assigned event from a speaker with its given user_id.
     * @param EventId the event id of the event which is assigned to speaker
     * @param userId the user id of this speaker
     */
    public void removeEvent(Integer EventId, String userId) {
        getSpeaker(userId).removeAssignEvent(EventId);
    }

    /**
     * Check if this speaker has the contact of another user with its given user_id.
     * @param userId the user id of this speaker
     * @param otherUserId the user id which needs to be checked
     * @return boolean returns true if otherUserId is in the ContactList of this speaker.
     */
    public boolean contactExists(String userId, String otherUserId){
        return getSpeaker(userId).checkContact(otherUserId);
    }

    /**
     * Add an user with its given user_id to this speaker's ContactList.
     * @param userId the user id of this speaker
     * @param otherUserId the user id which would be added
     */
    public void addContact (String userId, String otherUserId){
        // assume both users exists (checked in controller) and userid is added to otherUserID's contacts in controller as well.
        getSpeaker(userId).addContact(otherUserId);
    }

    /**
     * Remove an user with its given user_id from this speaker's ContactList.
     * @param userId the user id of this speaker
     * @param otherUserId the user id which would be removed
     */
    public void removeContact (String userId, String otherUserId){
        //assume both users exists (checked in controller) and userid is removed from otherUserID's contacts in controller as well.
        getSpeaker(userId).removeContact(otherUserId);
    }

    /**
     * Returns an ArrayList of all AssignEvents.
     * @param userId the user id of this speaker
     * @return ArrayList a list of AssignEvents
     */
    public ArrayList<Integer> getAssignedEvent (String userId){
        return getSpeaker(userId).getAssignEvents();
    }

    /**
     * Save the current state in serialized files.
     * @throws IOException throw IOException to avoid the Errors might occur
     */
    public void saveState() throws IOException{
        OutputStream file = new FileOutputStream("phase1/src/SpeakerManager.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(this); //students);
        output.close();
    }

    /**
     * Import the state which saved in serialized files.
     * @return SpeakerManager returns an implement of this use case
     */
    public SpeakerManager importState(){
        try {
            InputStream file = new FileInputStream("phase1/src/SpeakerManager.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            SpeakerManager speakerManager = (SpeakerManager) input.readObject();
            input.close();
            return speakerManager;

        } catch (ClassNotFoundException | IOException e) {
            return new SpeakerManager();
        }
    }
}
