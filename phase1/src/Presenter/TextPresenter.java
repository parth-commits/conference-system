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
    public static void main(String[] args){
        System.out.println("Menu:");
        System.out.println("Check All Events");
        System.out.println("Sign Up for Events");
    }
}
