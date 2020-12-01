package Controller;

import Entities.Event;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminSystem {

    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;
    private RoomManager roomManager;
    private EventManager eventManager;
    private AdminManager adminManager;

    /**
     * Constructor
     * @param speakerManager   The speaker manager implements by SpeakerManager use case
     * @param organizerManager The organizer manager implements by OrganizerManager use case
     * @param chatManager      The chat manager implements by ChatManager use case
     * @param attendeeManager  The attendee manager implements by AttendeeManager use case
     * @param messageSystem    The message system implements by MessageSystem Controller
     * @param eventSystem      The event system implements by EventSystem Controller
     * @param roomManager      The room manager implements by RoomManager use case
     * @param eventManager     The event manager implements by EventManager use case
     */


    public AdminSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, ChatManager chatManager, AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem, EventManager eventManager, RoomManager roomManager, AdminManager adminManager) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.attendeeManager = attendeeManager;
        this.chatManager = chatManager;
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.adminManager = adminManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
    }

    private ArrayList GetNoAttendeeEvent(){
        ArrayList<Integer> NoAttendee = new ArrayList<Integer>();
        for(Event event : eventManager.getListOfEvents()){
            if(event.getAttendees().size() == 0){
                NoAttendee.add(event.getID());
            }
        }
        return NoAttendee;
    }


    public void RemoveEmptyEvent(){
    }

    public boolean start(String userID) throws IOException {
            while (true) {
                String i;
                boolean validInput = false;
                output.AdminMenu();
                i = input.getKeyboardInput();
                //1. Delete Empty Event
                if (i.equals("1")) {
                    adminManager.deleteEvent(eventid);
                }
                //2. Delete Chat
                else if (i.equals("2")) {
                    adminManager.deleteChat(userId);
                }
                //3. logout
                else if (i.equals("3")) {
                    return false;
                }
                //4. shutdown
                else if (i.equals("4")) {
                    return true;
                }
                saveState();
            }
    }


    public void deleteChat() {
        boolean



    }

}
