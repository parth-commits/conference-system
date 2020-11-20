package Controller;

import Entities.Attendee;
import Entities.Organizer;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.AttendeeManager;
import UseCases.OrganizerManager;
import UseCases.SpeakerManager;

import java.util.ArrayList;

// We will combine LogInSystem and Registration System into one class with two methods (register user and login user)
public class LogInAndRegistrationSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;

    //constructor
    public LogInAndRegistrationSystem(AttendeeManager a, OrganizerManager b, SpeakerManager c) {
        this.output = new TextPresenter();
        this.input = new KeyboardInput();
        this.attendeeManager = a;
        this.organizerManager = b;
        this.speakerManager = c;
    }

    //start
    public String start() {
        String in;
        String userID = "false";
        while (true) {
            output.loginOrRegister();
            in = input.getKeyboardInput();
            if (in.equals("1")) {
                userID = registerUser();
            } else if (in.equals("2")) {
                userID = loginUser();
            } else if (in.equals("3")) {
                return "SHUTDOWN";
            } else {
                output.invalidInput();
            }
            if (!userID.equals("false")){
                return userID;
            }
        }

    }

    //register method
    public String registerUser() {
        boolean selectType = false;
        while (!selectType){
            output.enterType(true);
            String inputType = input.getKeyboardInput();
            if (!(inputType.equals("1") || inputType.equals("2")||inputType.equals("0"))){
                output.invalidInput();
            }
            else if (inputType.equals("0")){
                return "false";
            }
            else{
                boolean nameBack = false;
                while (!nameBack){
                    output.enterName();
                    String inputName = input.getKeyboardInput();
                    if (inputName.equals("0")){
                        nameBack = true;
                    }
                    else{
                        boolean untilCorrect = true;
                        boolean correct = true;
                        String inputID = "";
                        while (untilCorrect) {
                            output.enterID(correct);
                            inputID = input.getKeyboardInput();
                            if (attendeeManager.userExist(inputID) || organizerManager.userExist(inputID) || speakerManager.userExist(inputID)) {
                                correct = false;
                            }
                            else if(inputID.equals("0")) {
                                untilCorrect = false;
                            }
                            else{
                                boolean untilCorrect1 = true;
                                boolean correct1= true;
                                String inputPass = "";
                                while (untilCorrect1) {
                                    output.enterPassword(correct1);
                                    inputPass = input.getKeyboardInput();
                                    if(inputPass.equals("0")) {
                                        untilCorrect1 = false;
                                    }
                                    else if (inputPass.length() > 14 || inputPass.length() < 8) {
                                        correct1 = false;
                                    }
                                    else{
                                        if (inputType.equals("1")){
                                            organizerManager.addOrganizer(inputID, inputName, inputPass);
                                        }
                                        else {
                                            attendeeManager.addAttendee(inputID, inputName, inputPass);
                                        }
                                        return inputID;
                                    }
                                }
                            }
                        }
                    }
            }
            }
        }



        /*
        output.enterType(true);
        String inputType = input.getKeyboardInput();
        while (!(inputType.equals("1") || inputType.equals("2")||inputType.equals("0"))) {
            output.enterType(false);
            inputType = input.getKeyboardInput();
        }
        if (inputType.equals("0")){
            return "false";
        }

        output.enterName();
        String inputName = input.getKeyboardInput();
        boolean untilCorrect = true;
        boolean correct = true;
        String inputID = "";
        while (untilCorrect) {
            output.enterID(correct);
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
        //now we have a valid name, id, and password
        if (inputType.equals("1")) {
            organizerManager.addOrganizer(inputID, inputName, inputPass);
        } else {
            attendeeManager.addAttendee(inputID, inputName, inputPass);
        }
        return inputID;
        */
        return "false";
    }


    //login method
    //this method loops until a correct userid and password are provided. returns the userid if verified.
    public String loginUser() {
        output.loginEnterID(true);
        String inputID = "";
        inputID = input.getKeyboardInput();
        output.loginPassword(true);
        String inputPassword = "";
        inputPassword = input.getKeyboardInput();
        if (attendeeManager.verifyLogIn(inputID, inputPassword)) {
            return inputID;
        } else if (organizerManager.verifyLogIn(inputID, inputPassword)) {
            return inputID;
        } else if (speakerManager.verifyLogIn(inputID, inputPassword)) {
            return inputID;
        } else {
            output.loginEnterID(false);
            return "false";
        }
    }
}

