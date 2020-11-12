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
                createSpeaker(userID);
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

    private void createSpeaker(String userID) {
        output.enterSpeakerName();
        String inputName = input.getKeyboardInput();
        boolean untilCorrect = true;
        boolean correct = true;
        String inputID = "";
        while (untilCorrect) {
            output.enterSpeakerID(correct);
            inputID = input.getKeyboardInput();
            if (attendeeManager.userExist(inputID) || organizerManager.userExist(inputID) || speakerManager.userExist(inputID)) {
                correct = false;
            } else {
                untilCorrect = false;
            }
        }
        untilCorrect = true;
        correct = true;
        String inputPass = "";
        while (untilCorrect) {
            output.enterPassword(correct);
            inputPass = input.getKeyboardInput();
            if (inputPass.length() > 14 || inputPass.length() < 8) {
                correct = false;
            } else {
                untilCorrect = false;
            }
        }
        speakerManager.addSpeaker(inputID,inputPass,inputName, userID);
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

    //returns true if the given userid exists in any 3 people's managers
    private boolean userExists(String userid){
        if (attendeeManager.userExist(userid)||organizerManager.userExist(userid)||speakerManager.userExist(userid)){
            return true;
        }
        return false;
    }

}
