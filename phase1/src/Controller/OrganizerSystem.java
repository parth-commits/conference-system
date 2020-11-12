package Controller;

import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class OrganizerSystem {

    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;


    public OrganizerSystem(SpeakerManager speakerManager, RoomManager roomManager, OrganizerManager organizerManager, EventManager eventManager, ChatManager chatManager, AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem) {
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.speakerManager = speakerManager;
        this.chatManager = chatManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
    }

    public boolean start(String userID) {
        while(true){
            String o;
            boolean validInput = false;
            output.organizationSystemStartOptions();
            o = input.getKeyboardInput();
            if (o.equals("1")){
                createSpeaker();
            }
            else if (o.equals("2")){
                scheduleASpeaker();
            }
            else if (o.equals("3")){
                message();
            }
            else if (o.equals("4")){
                createDeleteEvent();
            }
            else if (o.equals("5")){
                addRemoveContact();
            }
            else if (o.equals("6")){
                joinLeaveEvent();
            }
            else if (o.equals("7")){
                return false;
            }
            else if (o.equals("8")){
                return true;
            }
        }

    }

    private void createSpeaker() {
    }

    private void scheduleASpeaker() {
    }

    private void message() {
    }

    private void createDeleteEvent() {
    }

    private void addRemoveContact() {
    }

    private void joinLeaveEvent() {
    }





}
