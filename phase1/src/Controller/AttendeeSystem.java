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
    // 3. Check Schedule for an Signed Up Event 4. Cancel an Event Signed Up for 5. Add or Remove Attendee in Contact
    //6. Message Other Users
    public boolean start(String userID) {
        while (true) {
            String i;
            boolean validInput = false;
            output.AttendeeMenu();
            i = input.getKeyboardInput();
            //1. see Events
            if (i.equals("1")) {
                eventSystem.checkAllEvents();
            }
            //2. Sign up for Events
            else if (i.equals("2")) {
                output.enterEvent();
                eventSystem.checkEventTitleIDs();
                Integer event_id = Integer.parseInt(input.getKeyboardInput());
                eventSystem.signUpEvent(userID, event_id);
            }
            //3. Check Schedule for an Signed Up Event
            else if (i.equals("3")){
                eventSystem.checkSignedUpEvent(userID);
            }
            //4. Cancel an Event Signed Up for
            else if (i.equals("4")){
                //need the user to enter event id(or title)*
                output.enterEventCancel();
                Integer event_id = Integer.parseInt(input.getKeyboardInput());
                eventSystem.cancelSignedUpEvent(userID, event_id);
            }
            //5. Add or Remove Attendee in Contact
            else if (i.equals("5")){
                output.enterUserID();
                String user_ID = input.getKeyboardInput();
                addRemoveContact(user_ID);
            }
            //6. Message Other Users
            else if (i.equals("6")){
                messageSystem.sendMessage(userID);
            }
            //7. logout
            else if (i.equals("7")){
                return false;
            }
            //8. shutdown
            else if (i.equals("8")) {
                return true;
            }
        }
    }


    private boolean userExists(String userid){
        if (attendeeManager.userExist(userid)||organizerManager.userExist(userid)||speakerManager.userExist(userid)){
            return true;
        }
        return false;
    }



    private void addRemoveContact(String userID){
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