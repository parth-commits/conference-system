package Gateway;

import Entities.Room;
import Entities.Speaker;
import UseCases.*;

import java.io.*;

public class Serialization {

    final String directory = "phase2";
    public Serialization(){}


    /**
     * Saves states of attendee manager.
     * @throws IOException throw IOException to avoid errors that might occur
     */
    public void saveState(Object obj, String type) throws IOException {
        String fileName = directory + File.separator + "src" + File.separator + type + ".ser";
        OutputStream file = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        switch (type) {
            case "AttendeeManager" -> {
                AttendeeManager attendeeManager = (AttendeeManager) obj;
                output.writeObject(attendeeManager);
                output.close();
            }
            case "ChatManager" -> {
                ChatManager chatManager = (ChatManager) obj;
                output.writeObject(chatManager);
                output.close();
            }
            case "EventManager" -> {
                EventManager eventManager = (EventManager) obj;
                output.writeObject(eventManager);
                output.close();
            }
            case "OrganizerManager" -> {
                OrganizerManager organizerManager = (OrganizerManager) obj;
                output.writeObject(organizerManager);
                output.close();
            }
            case "RoomManager" -> {
                RoomManager roomManager = (RoomManager) obj;
                output.writeObject(roomManager);
                output.close();
            }
            case "SpeakerManager" -> {
                SpeakerManager speakerManager = (SpeakerManager) obj;
                output.writeObject(speakerManager);
                output.close();
            }
            case "RequestManager" -> {
                RequestManager requestManager = (RequestManager) obj;
                output.writeObject(requestManager);
                output.close();
            }
        }
    }

    /**
     * Imports ser files.
     * @return AttendeeManager returns an implement of this use case
     */
    public Object importState(String type) {
        String fileName = directory + File.separator + "src" + File.separator + type + ".ser";
        try {
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            switch (type) {
                case "AttendeeManager" -> {
                    AttendeeManager attendeeManager = (AttendeeManager) input.readObject();
                    input.close();
                    return attendeeManager;
                }
                case "ChatManager" -> {
                    ChatManager chatManager = (ChatManager) input.readObject();
                    input.close();
                    return chatManager;
                }
                case "EventManager" -> {
                    EventManager eventManager = (EventManager) input.readObject();
                    input.close();
                    return eventManager;
                }
                case "OrganizerManager" -> {
                    OrganizerManager organizerManager = (OrganizerManager) input.readObject();
                    input.close();
                    return organizerManager;
                }
                case "RoomManager" -> {
                    RoomManager roomManager = (RoomManager) input.readObject();
                    input.close();
                    return roomManager;
                }
                case "RequestManager" -> {
                    RequestManager requestManager = (RequestManager) input.readObject();
                    input.close();
                    return requestManager;
                }
                default -> {
                    SpeakerManager speakerManager = (SpeakerManager) input.readObject();
                    input.close();
                    return speakerManager;
                }
            }
        }
        catch (ClassNotFoundException | IOException e) {
            switch (type) {
                case "AttendeeManager" -> {
                    return new AttendeeManager();
                }
                case "ChatManager" -> {
                    return new ChatManager();
                }
                case "EventManager" -> {
                    return new EventManager();
                }
                case "OrganizerManager" -> {
                    return new OrganizerManager();
                }
                case "RoomManager" -> {
                    return new RoomManager();
                }
                case "RequestManager" -> {
                    return new RequestManager();
                }
                default -> {
                    return new SpeakerManager();
                }
            }
        }
    }
}
