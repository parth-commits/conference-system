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
}
