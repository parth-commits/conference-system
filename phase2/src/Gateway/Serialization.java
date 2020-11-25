package Gateway;

import Entities.Room;
import Entities.Speaker;
import UseCases.*;

import java.io.*;

public class Serialization {

    public Serialization(){}

    /**
     * Saves states of attendee manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveStateAttendeeManager(AttendeeManager attendeeManager) throws IOException {
        String fileName = "phase1" + File.separator + "src" + File.separator + "AttendeeManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(attendeeManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return AttendeeManager returns an implement of this use case
     */
    public AttendeeManager importStateAttendeeManager() {
        String fileName = "phase1" + File.separator + "src" + File.separator + "AttendeeManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            AttendeeManager attendeeManager = (AttendeeManager) input.readObject();
            input.close();
            return attendeeManager;

        } catch (ClassNotFoundException | IOException e) {
            return new AttendeeManager();
        }
    }

    /**
     * Saves states of chat manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveStateChatManager(ChatManager chatManager) throws IOException {
        String fileName = "phase1" + File.separator + "src" + File.separator + "ChatManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(chatManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return ChatManager returns an implement of this use case
     */
    public ChatManager importStateChatManager() {
        String fileName = "phase1" + File.separator + "src" + File.separator + "ChatManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            ChatManager chatManager = (ChatManager) input.readObject();
            input.close();
            return chatManager;

        } catch (ClassNotFoundException | IOException e) {
            return new ChatManager();
        }
    }

    /**
     * Saves states of event manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveStateEventManager(EventManager eventManager) throws IOException {
        String fileName = "phase1" + File.separator + "src" + File.separator + "EventManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(eventManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return EventManager an implement of this use case
     */
    public EventManager importStateEventManager() {
        String fileName = "phase1" + File.separator + "src" + File.separator + "EventManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            EventManager eventManager = (EventManager) input.readObject();
            input.close();
            return eventManager;

        } catch (ClassNotFoundException | IOException e) {
            return new EventManager();
        }
    }

    /**
     * Saves states of organizer manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveStateOrganizerManager(OrganizerManager organizerManager) throws IOException {
        String fileName = "phase1" + File.separator + "src" + File.separator + "OrganizerManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(organizerManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return OrganizerManager returns an implement of this use case
     */
    public OrganizerManager importStateOrganizerManager() {
        String fileName = "phase1" + File.separator + "src" + File.separator + "OrganizerManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            OrganizerManager organizerManager = (OrganizerManager) input.readObject();
            input.close();
            return organizerManager;

        } catch (ClassNotFoundException | IOException e) {
            return new OrganizerManager();
        }
    }

    /**
     * Saves states of room manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveStateRoomManager(RoomManager roomManager) throws IOException{
        String fileName = "phase1" + File.separator + "src" + File.separator + "RoomManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(roomManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return RoomManager returns an implement of this use case
     */
    public RoomManager importStateRoomManager() {
        String fileName = "phase1" + File.separator + "src" + File.separator + "RoomManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            RoomManager roomManager = (RoomManager) input.readObject();
            input.close();
            return roomManager;

        } catch (ClassNotFoundException | IOException e) {
            return new RoomManager();
        }
    }

    /**
     * Saves states for speaker manager.
     * @throws IOException throw IOException to avoid the Errors might occur
     */
    public void saveStateSpeakerManager(SpeakerManager speakerManager) throws IOException{
        String fileName = "phase1" + File.separator + "src" + File.separator + "SpeakerManager.ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(speakerManager);
        output.close();
    }

    /**
     * Imports ser files.
     * @return SpeakerManager returns an implement of this use case
     */
    public SpeakerManager importStateSpeakerManager(){
        String fileName = "phase1" + File.separator + "src" + File.separator + "SpeakerManager.ser";
        try {
            InputStream file = new FileInputStream(fileName);
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
