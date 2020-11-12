package Presenter;

import Entities.Chat;
import Entities.Message;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TextPresenter {
    public void PrintPrompt(){
        System.out.println("Please enter what action from the menu you would like to perform.");
    }
    public void ActionDone(){
        System.out.println("Action Done!");
    }
    public void ActionFailed(){
        System.out.println("Action Failed.");
    }
    public void AttendeeMenu(){
        System.out.println("Menu:");
        System.out.println("1. See Events");
        System.out.println("2. Sign Up for Events");
        System.out.println("3. Check Schedule for an Signed Up Event");
        System.out.println("4. Cancel an Event Signed Up for");
        System.out.println("5. Message Other Users");
    }
    public void enterName(){
        System.out.println("Please enter your name: ");
    }
    public void enterID(boolean checker){
        if (checker){
            System.out.println("Please enter your preferred UserID: ");
        }
        else{
            System.out.println("That UserID is taken, please enter another UserID:");
        }
    }
    public void enterPassword(boolean checker){
        System.out.println("A password must be 8-14 characters long.");
        if (checker) {
            System.out.println("Please enter a password: ");
        }
        else{
            System.out.println("That is an invalid password. Please enter a valid password: ");
        }
    }

    public void Events(ArrayList<String> listOfEventsSchedule){
    for (String event: listOfEventsSchedule){
        System.out.println(event);
    }
    }

    public static void main(String[] args){
        System.out.println("Menu:");
        System.out.println("Check All Events");
        System.out.println("Sign Up for Events");
        System.out.println("Cancel Events");
    }

    // I added this 2 verifying text becuz I noticed I usually encounter those confirming questions when I decide to do the action,
    // and it indeed needs a checker
    // but if you think it is overlapped with the action done/action failed method we can delete it tho
    public void verifySignUp(boolean checker){
        System.out.println("Are you sure you want to sign up for this event? ");
        if (checker){
            System.out.println("You've successfully signed up this event.");
        }
        else{
            System.out.println("Something went wrong. You might already signed up for this event.");
        }
    }

    public void verifyCancellation(boolean checker){
        System.out.println("Are you sure you want to cancel this event? It will be removed from your schedule.");
        if (checker){
            System.out.println("You've successfully cancelled this event.");
        }
        else{
            System.out.println("Something went wrong. Please try again later.");
        }
    }

    public void enterType(boolean checker) {
        if (checker) {
            System.out.println("Are you an Organizer or an Attendee?");
        }
        else {
            System.out.println("Invalid input. Are you an Organizer or an Attendee?");
        }
        System.out.println("Please select one of the following:");
        System.out.println("1. Organizer");
        System.out.println("2. Attendee");
    }

    public void loginEnterID(boolean checker){
        if (checker){
            System.out.println("Please enter your UserID.");
        }
        else {
            System.out.println("Your UserID and/or password are incorrect. Please try again.");
        }
    }

    public void loginPassword(boolean checker){
        if (checker){
            System.out.println("Please enter your password.");
        }
    }
    public void loginOrRegister(){
        System.out.println("Please selection the action you'd like to do.");
        System.out.println("1. Register");
        System.out.println("2. Login");
    }


    //Should One line outputs still be in presenter answer by parth: yes!
    public void promptRecipient(){
        System.out.println("Please enter user id of the recipient: ");
    }
    public void promptEvents(){
        System.out.println("Please enter the ids of the event (separated by a comma): ");
    }
    public void confirmCreateChat(String id){
        System.out.println("Please confirm create chat with user" + id + ": ");
        System.out.println("y/n");
    }
    public void promptContext(){
        System.out.println("Please enter the message: ");
    }

    public void promptContextEvent(String title){
        System.out.println("Please enter the message for event " + title + ": ");
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

    // Outputs for Message System
    public void sendMsgOptions(int role){
        // Organizer
        if (role == 1){
            System.out.println("Send to: 1. all speakers, 2. one speaker, 3. all attendees, or 4. one attendee? ");
        }
        // Attendee
        else if (role == 2){
            System.out.println("Would you like to send to 1. another Attendee or 2. a Speaker? ");
        }
        // Speaker
        else if (role == 3){
            System.out.println("1. Select an event to send to all participants. 2. respond to an attendee ");
        }
    }
    public void msgOptionInvalid(){
        System.out.println("Invalid Option, please enter again: ");
    }
    public void invalidRecipient(){
        System.out.println("Invalid user ID, please enter again: ");
    }

    //OrganizerSystem methods
    public void organizationSystemStartOptions(){
        System.out.println("What would you like to do:\n");
        System.out.println("Organizer Specific Options:");
        System.out.println("\t1. Create a new Speaker");
        System.out.println("\t2. schedule a Speaker");
        System.out.println("\t3. Message");
        System.out.println("\t4. Create/Delete event\n");
        System.out.println("Regular Attendee Options:");
        System.out.println("\t5. add/remove contact");
        System.out.println("\t6. Join/leave event");
        System.out.println("\t7. LOGOUT");
        System.out.println("\t8. SHUTDOWN");
    }
    public void enterSpeakerName(){
        System.out.println("Please enter the name of the speaker you wish to create: ");
    }
    public void enterSpeakerID(boolean checker){
        if (checker){
            System.out.println("Please enter the UserID for the speaker: ");
        }
        else{
            System.out.println("That UserID is taken, please enter another UserID:");
        }
    }
}
