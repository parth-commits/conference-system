package Controller;

import Entities.Attendee;
import Entities.Organizer;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.AttendeeManager;
import UseCases.OrganizerManager;
import UseCases.UserManager;

import java.util.ArrayList;

public class RegistrationSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private UserManager userManager;
    public RegistrationSystem() {
        this.output = new TextPresenter();
        this.input = new KeyboardInput();
    }

    public User registerUser(ArrayList<String> a, ArrayList<String> b, ArrayList<String> c){
        output.enterType(true);
        String inputType = input.getKeyboardInput();
        while (!(inputType.equals("1") || inputType.equals("2"))){
            output.enterType(false);
            inputType = input.getKeyboardInput();
        }
        output.enterName();
        String inputName = "";
        inputName = input.getKeyboardInput();
        boolean untilCorrect = true;
        boolean correct = true;
        String inputID = "";
        while (untilCorrect){
            output.enterID(correct);
            inputID = input.getKeyboardInput();
            if (a.contains(inputID)||b.contains(inputID)||c.contains(inputID)){
                correct = false;
            }
            else {
                untilCorrect = false;
            }
        }
        untilCorrect = true;
        correct = true;
        String inputPass = "";
        while (untilCorrect){
            output.enterPassword(correct);
            inputPass = input.getKeyboardInput();
            if (inputPass.length() >14 || inputPass.length()<8){
                correct = false;
            }
            else {
                untilCorrect = false;
            }
        }
        //now we have a valid name, id, and password
        if (inputType.equals("1")){
            return new Organizer(inputID,inputName,inputPass);
        }
        else{
            return new Attendee(inputID,inputName, inputPass);
        }
    }
}
