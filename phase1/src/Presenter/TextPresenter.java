package Presenter;

public class TextPresenter {
    public void PrintPrompt(){
        System.out.println("Please enter what action from the menu you would like to perform.");
    }
    public void ActionDone(){
        System.out.println("Action Done!");
    }
    public void ActionFailed(){
        System.out.println("Action Failed");
    }
    public void AttendeeMenu(){
        System.out.println("Menu:");
        System.out.println("Check All Events");
        System.out.println("Sign Up for Events");
        System.out.println("Check Schedule for an Signed Up Event");
        System.out.println("Cancel an Event Signed Up for");
        System.out.println("Message Other Users");

    }
    public void enterName(){
        System.out.println("Please enter your name: ");
    }
    public void enterID(boolean checker){
        if (checker){
            System.out.println("Please enter your prefered UserID: ");
        }
        else{
            System.out.println("That UserID is taken, please enter another UserID:");
        }
    }
    public void enterPassword(boolean checker){
        System.out.println("A password must be 8-14 characters long");
        if (checker) {
            System.out.println("Please enter a password: ");
        }
        else{
            System.out.println("That is an invalid password. Please enter a valid password: ");
        }
    }

    public static void main(String[] args){
        System.out.println("Menu:");
        System.out.println("Check All Events");
        System.out.println("Sign Up for Events");
    }

    public void enterType(boolean checker) {
        if (checker) {
            System.out.println("Are you an Organizer or an Attendee?");
        }
        else {
            System.out.println("Invalid input, are you an Organizer or an Attendee?");
        }
        System.out.println("Please select one of the following:");
        System.out.println("1. Organizer");
        System.out.println("2. Attendee");
    }

    public void loginEnterID(boolean checker){
        if (checker){
            System.out.println("Please enter your user id");
        }
        else {
            System.out.println("Your userid and/or password are incorrect. Please try again.");
        }
    }

    public void loginPassword(boolean checker){
        if (checker){
            System.out.println("Please enter your password");
        }
    }
    public void loginOrRegister(){
        System.out.println("Would you like to register or login?");
        System.out.println("1. Register");
        System.out.println("2. Login");
    }

    // Outputs for Message System
    public void sendMsgOptions(int role){
        if (role == 1){
            System.out.println("Would you like to send to 1. another Attendee or 2. a Speaker? ");
        }
        else if (role == 2){
            System.out.println("Select an event to send to all participants");
        }
        else if (role == 3){
            System.out.println("Send to: 1. all speakers, 2. one speaker, 3. all attendees, or 4. one attendee?");
        }
    }
}
