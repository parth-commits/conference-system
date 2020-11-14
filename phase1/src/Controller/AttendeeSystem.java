package Controller;

import java.util.ArrayList;

import Entities.Attendee;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class AttendeeSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private ChatManager chatManager;
    private RoomManager roomManager;
    private EventManager eventManager;

    public AttendeeSystem(AttendeeManager attendeeManager, RoomManager roomManager, EventManager eventManager) {
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();

    }

    public boolean start(String userID) {
        while (true) {
            boolean validInput = false;
            i = input.getKeyboardInput()
            if (i.equals("1")) {
                message(userID);
            }
            else if (i.equals("2")) {
                addRemoveContact(userID);
            }
            else if (o.equals("3")){
                joinLeaveEvent();
            }
            else if (o.equals("4")){
                return false;
            }
            else if (o.equals("4")){
                return true;
            }
        }
    }


    private void message(String userID) {
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