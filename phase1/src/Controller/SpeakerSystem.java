package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;


public class SpeakerSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;

    public SpeakerSystem (SpeakerManager speakerManager, OrganizerManager organizerManager, ChatManager chatManager,
                          AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem){
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.attendeeManager = attendeeManager;
        this.chatManager = chatManager;
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
    }


    //We need a Speaker Menu in Text Presenter which allows Speakers to:
    //1. see all Events. 2. see the Events they're assigned to. 3. Message Attendees
    public boolean start(String userID) throws IOException {
        while (true) {
            String i;
            boolean validInput = false;
            output.SpeakerMenu();
            i = input.getKeyboardInput();
            //1. see all Events.
            if (i.equals("1")) {
                eventSystem.checkAllEvents();
            }
            //2. see the Events they're assigned to.
            else if (i.equals("2")) {
                eventSystem.checkAssignedEvent(userID);
            }
            //3. Message Attendees
            else if (i.equals("3")){
                messageSystem.sendMessage(userID);
            }
            //4. logout
            else if (i.equals("4")){
                return false;
            }
            //5. shutdown
            else if (i.equals("5")){
                return true;
            }
        }
    }


    private void saveState() throws IOException {
        //save the state back in!!!
        speakerManager.saveState();
        organizerManager.saveState();
        //eventManager.saveState();
        chatManager.saveState();
        attendeeManager.saveState();

    }


    private void message(String userID) throws IOException {
        messageSystem.sendMessage(userID);
    }

    private void joinLeaveEvent() {
    }


    private boolean userExists(String userid){
        if (attendeeManager.userExist(userid)||organizerManager.userExist(userid)||speakerManager.userExist(userid)){
            return true;
        }
        return false;
    }


    private void addRemoveContact(String userID) {
        output.addRemoveContact();
        String option = input.getKeyboardInput();
        while (!(option.equals("1")||option.equals("2"))){
            output.addRemoveContact();
            option = input.getKeyboardInput();
        }
        if (option.equals("1")){
            output.enterContactUserid(false);
            String input = this.input.getKeyboardInput();
            while (!(organizerManager.userExist(input)||attendeeManager.userExist(input)||speakerManager.userExist(input))){
                output.enterContactUserid(true);
                input = this.input.getKeyboardInput();
            }
            organizerManager.addContact(userID, input);
            if (organizerManager.userExist(input)){
                organizerManager.addContact(input, userID);
            }
            else if (attendeeManager.userExist(input)){
                attendeeManager.addContact(input, userID);
            }
            else{
                speakerManager.addContact(input, userID);
            }
            chatManager.createChat(input, userID);
        }
        else {
            output.enterContactUserid(false);
            String input = this.input.getKeyboardInput();
            while (!(organizerManager.userExist(input)||attendeeManager.userExist(input)||speakerManager.userExist(input))){
                output.enterContactUserid(true);
                input = this.input.getKeyboardInput();
            }
            organizerManager.removeContact(userID, input);
            if (organizerManager.userExist(input)){
                organizerManager.removeContact(input, userID);
            }
            else if (attendeeManager.userExist(input)){
                attendeeManager.removeContact(input, userID);
            }
            else{
                speakerManager.removeContact(input, userID);
            }
            chatManager.deleteChat(input,userID);
        }

    }



}

