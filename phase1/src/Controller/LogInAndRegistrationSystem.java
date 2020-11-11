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
        public void LoginAndRegistrationSystem(AttendeeManager a, OrganizerManager b, SpeakerManager c) {
            this.output = new TextPresenter();
            this.input = new KeyboardInput();
            this.attendeeManager = a;
            this.organizerManager = b;
            this.speakerManager = c;
        }

        //start
        public String start(){
            output.loginOrRegister();
            String in = input.getKeyboardInput();
            while (!(in.equals("1") || in.equals("2"))) {
                output.loginOrRegister();
                in = input.getKeyboardInput();
            }
            if (in.equals("1")){
                return registerUser();
            }
            else{
                return loginUser();
            }

        }

        //register method
        public String registerUser() {
            output.enterType(true);
            String inputType = input.getKeyboardInput();
            while (!(inputType.equals("1") || inputType.equals("2"))) {
                output.enterType(false);
                inputType = input.getKeyboardInput();
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
        }


        //login method
        //this method loops until a correct userid and password are provided. returns the userid if verified.
        public String loginUser() {
            while (true) {
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
                }
            }
        }
}

