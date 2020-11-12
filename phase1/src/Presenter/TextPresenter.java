package Presenter;

import Entities.Chat;
import Entities.Message;

import java.util.ArrayList;

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


    //Should One line outputs still be in presenter
    public void promptRecipient(){
        System.out.println("Please enter user id of the recipient");
    }
    public void confirmCreateChat(String id){
        System.out.println("Please confirm create chat with user" + id);
        System.out.println("y/n");
    }
    public void promptContext(){
        System.out.println("Please enter the message:");
    }

    public void chatDNE(){
        System.out.println("The chat doesn't exists.");
    }

    public void printChat(Chat chat){
        ArrayList<Message> messages = chat.getMessages();
        for (Message m:messages){
            System.out.println(m.getSender()+": "+m.getContext());
        }
    }
}
