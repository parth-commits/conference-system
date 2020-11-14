package Controller;

import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class AttendeeSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;

    public AttendeeSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, ChatManager chatManager,
                          AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.attendeeManager = attendeeManager;
        this.chatManager = chatManager;
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();

    }
    // Attendee is allowed to 1. see Events. 2. Sign up for Events
    // 3. Check Schedule for an Signed Up Event 4. Cancel an Event Signed Up for 5. Message Other Users
    public boolean start(String userID) {
        while (true) {
            boolean validInput = false;
            i = input.getKeyboardInput();

            //see Events
            if (i.equals("1")) {
                eventSystem.checkAllEvents();
            }

            //Sign up for Events
            else if (i.equals("2")) {
                //how should ppl signup?? by eventid? or event title?*
                output.EventID;
                Integer event_id = Integer.parseInt(input.getKeyboardInput());
                eventSystem.signUpEvent(userID, event_id);
                //addRemoveContact(userID);
            }
            //Check Schedule for an Signed Up Event
            else if (i.equals("3")){
                // event id in checkSignedUpEvent is never used!!*
                eventSystem.checkSignedUpEvent(userID);
            }

            //4. Cancel an Event Signed Up for
            else if (i.equals("4")){
                //need the user to enter event id(or title)*
                output.EventID;
                Integer event_id = Integer.parseInt(input.getKeyboardInput());
                eventSystem.cancelSignedUpEvent(userID, event_id);
            }

            //5. Message Other Users
            else if (i.equals("5")){
                // just want to make sure we check if the userID is in the contact list in MessageSystem*
                messageSystem.sendMessage(userID);
            }
        }
    }


    //Not sure why we have this
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