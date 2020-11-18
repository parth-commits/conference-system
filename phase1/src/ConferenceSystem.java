import Controller.*;
import Entities.Event;
import Entities.User;
import UseCases.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ConferenceSystem {

    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private AttendeeManager attendeeManager;

    private OrganizerSystem organizerSystem;
    private MessageSystem messageSystem;
    private LogInAndRegistrationSystem logInAndRegistrationSystem;
    private EventSystem eventSystem;
    private AttendeeSystem attendeeSystem;
    private SpeakerSystem speakerSystem;

    //constructor
    public ConferenceSystem(){
        importState();// this function will bring back the managers and initialize them
        logInAndRegistrationSystem = new LogInAndRegistrationSystem(attendeeManager, organizerManager, speakerManager);
        messageSystem = new MessageSystem(speakerManager,organizerManager, eventManager, chatManager, attendeeManager);
        eventSystem = new EventSystem(speakerManager, roomManager, organizerManager, eventManager, attendeeManager);
        organizerSystem = new OrganizerSystem(speakerManager, roomManager,organizerManager, eventManager, chatManager, attendeeManager, messageSystem, eventSystem);
        attendeeSystem = new AttendeeSystem(speakerManager, organizerManager, chatManager,attendeeManager, messageSystem, eventSystem);
        speakerSystem = new SpeakerSystem(speakerManager, organizerManager, chatManager, attendeeManager, messageSystem, eventSystem);
    }

    private void importState() {
        //this method will serialize the managers - the usecases
        SpeakerManager s = new SpeakerManager();
        this.speakerManager = s.importState();

        RoomManager r = new RoomManager();
        this.roomManager = r.importState();

        OrganizerManager o = new OrganizerManager();
        this.organizerManager = o.importState();

        EventManager e = new EventManager();
        this.eventManager = e.importState();

        ChatManager c = new ChatManager();
        this.chatManager = c.importState();

        AttendeeManager a = new AttendeeManager();
        this.attendeeManager = a.importState();


    }

    private void saveState() throws IOException {
        //save the state back in!!!
        speakerManager.saveState();
        roomManager.saveState();
        organizerManager.saveState();
        eventManager.saveState();
        chatManager.saveState();
        attendeeManager.saveState();

    }
    public void run () throws IOException, ParseException {

        boolean shutdown = false;
        listofIDPass();
        while (!shutdown) {
            String userID = logInAndRegistrationSystem.start();
            if (organizerManager.userExist(userID)) {
                shutdown = organizerSystem.start(userID);
            }
            else if (attendeeManager.userExist(userID)) {
                shutdown = attendeeSystem.start(userID);
            }
            else {
                shutdown = speakerSystem.start(userID);
            }

        }
        saveState();
    }

    private void listofIDPass() {
        ArrayList<String> speakers = speakerManager.getUserIDs();
        for (String speaker: speakers){
            System.out.println(speakerManager.getSpeaker(speaker).getName());
            System.out.println(speakerManager.getSpeaker(speaker).getUser_id());
            System.out.println(speakerManager.getSpeaker(speaker).getPasswords());
        }
    }


}


