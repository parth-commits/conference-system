package Presenter;

import Entities.Chat;
import Entities.Event;
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

    //AttendeeSystem
    public void AttendeeMenu(){
        System.out.println("Menu:");
        System.out.println("\t1. See Events");
        System.out.println("\t2. Sign Up for Events");
        System.out.println("\t3. Check Schedule for an Signed Up Event");
        System.out.println("\t4. Cancel an Event Signed Up for");
        System.out.println("\t5. Add or Remove Attendee in Contact");
        System.out.println("\t6. Message Other Users");
        System.out.println("\t7. LOGOUT");
        System.out.println("\t8. SHUTDOWN");
    }
    public void enterEvent(){
        System.out.println("Please enter the event ID for the event you want to sign up for: ");
    }
    public void enterEventCancel(){
        System.out.println("Please enter the event ID for the event you want to cancel: ");
    }
    public void enterUserID(){
        System.out.println("Please enter the User ID that you want to add or remove from your contact list");
    }

    //SpeakerSystem
    public void SpeakerMenu(){
        System.out.println("Menu:");
        System.out.println("\t1. See all Events");
        System.out.println("\t2. see assigned Events");
        System.out.println("\t3. Message Attendee");
        System.out.println("\t4. LOGOUT");
        System.out.println("\t5. SHUTDOWN");
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

    public void youHaveNoContacts(){
        System.out.println("You have no contacts! Please add a contact before trying again.");
    }

    public void addRemoveContact(){
        System.out.println("Would you like to add or remove contact?");
        System.out.println("\t1. Add contact");
        System.out.println("\t2. Remove contact");
    }

    public void enterContactUserid(boolean invalid){
        if (invalid){
            System.out.println("Invalid userID. Enter valid UserID of the contact:");
        }
        else {
            System.out.println("Enter the userID of the contact:");
        }
    }

    //Should One line outputs still be in presenter. answer by parth: yes!
    public void promptRecipient(ArrayList<String> contactList, boolean invalid){
        if (invalid){
            System.out.println("Invalid input, try again:");
        }
        System.out.println("Please select a contact:");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1)+". "+ contactList.get(i));
        }
        System.out.println("Enter 0 if you would like to cancel.");
    }
    public void promptEvents(ArrayList<ArrayList<String>> eventList, boolean invalid){
        if (invalid){
            System.out.println("Invalid input, try again:");
        }
        System.out.println("Please select a Event to send an Automatic message to all Attendees of that event:");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println((i+1)+". ID: "+ eventList.get(i).get(0) + " Name: " + eventList.get(i).get(0));
        }
        System.out.println("Enter the Event ID of your choice:");
    }
    public void confirmCreateChat(String id){
        System.out.println("Please confirm create chat with user" + id + ": ");
        System.out.println("y/n");
    }
    public void promptContext(){
        System.out.println("Please enter the message, or enter 'return' to cancel: ");
    }

    public void promptContextEvent(String title){
        System.out.println("Please enter the Automated message for " + title + " event: ");
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
            System.out.println("Who would you like to send a message to?");
            System.out.println("Note: to send a message to a single user, they must be on your contact list!");
            System.out.println("\t1. All Speakers");
            System.out.println("\t2. Single Speaker");
            System.out.println("\t3. All Organizers");
            System.out.println("\t4. Single Organizer");
            System.out.println("\t5. All Attendees");
            System.out.println("\t6. Single Attendee");
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
    public void addContactFailed(){
        System.out.println("Failed to add contact, please check the input ID.");
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

    public void youHaveNoEvents(){
        System.out.println("You have no events, please contact an Organizer to be assigned an event");
    }

    public void scheduleSpeaker(){
        System.out.println("Please enter the userid of the speaker you wish to schedule.");
        System.out.println("If you would like to go back and select a different event id, please press 0");
    }

    public void scheduleSpeakerInvalidEventID(){
        System.out.println("That event id does not exist. Please try again.");
    }

    public void scheduleSpeakerNoSpeakerlessEvents(){
        System.out.println("Currently all events have a speaker. There are no events that you can schedule a speaker for.");
    }

    public void scheduleSpeakerSelectEvent(ArrayList<Event> events){
        System.out.println("Please select one of the following events\n");
        for (int i=1; i<events.size()+1; i++){
            String tempstr = Integer.toString(i);
            System.out.println("\t" + tempstr + " " + events.get(i).getTitle());

        }
        System.out.println("\nTo return to the previous menu please press 0");
    }

    public void scheduleSpeakerSpeakerBusy(){
        System.out.println("This speaker is already giving a talk at another event at this time.");
    }
    public void scheduleSpeakerInvalidSpeakerID(){
        System.out.println("The speaker id you entered is not valid. Please try again");
    }

    public void invalidEventID(){
        System.out.println("The event id you entered was invalid, please try again");
    }

    public void joinEvent(){
        System.out.println("Please type the event id you wish to join.");
    }

    public void joinOrLeave(){
        System.out.println("Would you like to join or leave this event?");
        System.out.println("1. Join");
        System.out.println("2. Leave");
        System.out.println("If you would like to go back to the previous menu, please press 0");
    }

    public void joinLeaveInvalidResponse(){
        System.out.println("That was an invalid response, please try again");
    }

    public void joinDeleteEventSelector(ArrayList<Event> listOfEvents){
        System.out.println("Please select one of the following events\n");
        for (int i=1; i<listOfEvents.size()+1; i++){
            String tempstr = Integer.toString(i);
            System.out.println("\t" + tempstr + " " + listOfEvents.get(i).getTitle());

        }
        System.out.println("\nTo return to the previous menu please press 0");
    }


}
