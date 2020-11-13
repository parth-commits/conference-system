import Controller.*;
import Entities.Event;
import Entities.User;
import UseCases.*;

import java.io.IOException;

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
        //initilaize attendeeSystem
        //initialize speakersystem
    }

    private void importState() {
        //this method will serialize the managers - the usecases
        SpeakerManager s = new SpeakerManager();
        this.speakerManager = s.importState();

    }

    public void run () throws IOException {

        boolean shutdown = false;

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

    private void saveState() throws IOException {
        //save the state back in!!!
        speakerManager.saveState();
    }
}


