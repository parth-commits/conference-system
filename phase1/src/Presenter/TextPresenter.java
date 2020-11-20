package Presenter;

import Entities.Chat;
import Entities.Event;
import Entities.Message;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Entities.Chat;
import Entities.Event;
import Entities.Message;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.TimeZone;

/** TextPresenter prints out the String from this program to the user interface.
 *  @author Group_0112
 *  @version 1.0
 *  @since November 19th, 2020
 */

public class TextPresenter {

    /**
     * Print: Please enter what action from the menu you would like to perform.
     */
    public void PrintPrompt(){
        System.out.println("Please enter what action from the menu you would like to perform.");
    }

    /**
     * Print: Action Done!
     */
    public void ActionDone(){
        System.out.println("Action Done!");
    }

    /**
     * Print: Action Failed!
     */
    public void ActionFailed(){ System.out.println("Action Failed."); }

    //AttendeeSystem

    /**
     * Print: Menu 1.See Events 2.Sign Up or Cancel an Event Signed Up for 3.Check Schedule for an Signed Up Event
     * 4.Add or Remove Contact 5.Message Other Users 6.LOGOUT 7.SHUTDOWN
     */
    public void AttendeeMenu(){
        System.out.println("Menu:");
        System.out.println("\t1. See Events");
        System.out.println("\t2. Sign Up or Leave an Event Signed Up for");
        System.out.println("\t3. Check Schedule for an Signed Up Event");
        System.out.println("\t4. Add or Remove Contact");
        System.out.println("\t5. Message Other Users");
        System.out.println("\t6. LOGOUT");
        System.out.println("\t7. SHUTDOWN");
    }

    /**
     * Print: Please enter an integer ID for the new event:
     */
    public void enterCreatingEventID(){
        System.out.println("Please enter an integer ID for the new event:");
    }

    //SpeakerSystem
    /**
     * Print: Menu 1.See Events 2.See Assigned Events 3.Message 4.LOGOUT 5.SHUTDOWN
     */
    public void SpeakerMenu(){
        System.out.println("Menu:");
        System.out.println("\t1. See Events");
        System.out.println("\t2. See Assigned Events");
        System.out.println("\t3. Message");
        System.out.println("\t4. LOGOUT");
        System.out.println("\t5. SHUTDOWN");
    }

    /**
     * Print: Please enter your name:
     * Print: Please type 0 to go back.
     */
    public void enterName(){
        System.out.println("Please enter your name: ");
        System.out.println("Please type 0 to go back.");
    }

    /**
     * Print: Please enter your preferred ID: .
     * If the UserID is taken. Print: That UserID is taken, please enter another UserID:
     * Print: Please type 0 to go back.
     * @param checker Check if the UserId is taken
     */
    public void enterID(boolean checker){
        if (checker){
            System.out.println("Please enter your preferred UserID: ");
        }
        else{
            System.out.println("That UserID is taken, please enter another UserID:");
        }
        System.out.println("Please type 0 to go back.");
    }

    /**
     * Print: A password must be 8-14 characters long. Please enter a password:
     * If the password is invalid. Print: That is an invalid password. Please enter a valid password: .
     * Print: Please enter 0 to return to the previous menu
     * @param checker Check if the password is valid
     */
    public void enterPassword(boolean checker){
        System.out.println("A password must be 8-14 characters long.");
        if (checker) {
            System.out.println("Please enter a password: ");
        }
        else{
            System.out.println("That is an invalid password. Please enter a valid password: ");
        }
        System.out.println("Please enter 0 to return to the previous menu");
    }

    /**
     * Prints the AssignedEvents of this speaker
     * If there's no AssignedEvents, Print: You currently have no assigned talks.
     * @param listOfEventsSchedule The list of Events that assign to the speaker
     */
    public void eventsSpeaker(ArrayList<String> listOfEventsSchedule){
        if (listOfEventsSchedule.isEmpty()){
            System.out.println("You currently have no assigned talks. ");
        }
        else{
            for (String event: listOfEventsSchedule){
                System.out.println(event);
                System.out.println();
            }
        }
    }

    /**
     * Prints the SignedUpEvents of this user
     * If there's no SignedUpEvents. Print: You are currently not attending any events.
     * @param listOfEventsSchedule The list of Events that the user signed up
     */
    public void eventsAttendeeAndOrganizer(ArrayList<String> listOfEventsSchedule){
        if (listOfEventsSchedule.isEmpty()){
            System.out.println("You are currently not attending any events.");
        }
        else {
            for (String event: listOfEventsSchedule){
                System.out.println(event);
                System.out.println();
            }
        }
    }

    /**
     * If there's no Events. Print: There are currently no events being held in this conference.
     * Otherwise, prints all the Events
     * @param listOfEventsSchedule
     */
    public void eventsCheckAll(ArrayList<String> listOfEventsSchedule){
        if (listOfEventsSchedule.isEmpty()){
            System.out.println("There are currently no events being held in this conference.");
        }
        else {
            for (String event: listOfEventsSchedule){
                System.out.println(event);
                System.out.println();
            }
        }
    }

    /**
     * Print: Are you an Organizer or an Attendee?
     * If the input is invalid. Print: Invalid input. Are you an Organizer or an Attendee?
     * Print: Please select one of the following:
     * Print: 1. Organizer
     * Print: 2. Attendee
     * Print: Please type 0 to go back
     * @param checker Check of the input is valid
     */
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
        System.out.println("Please type 0 to go back.");
    }

    /**
     * Print: Please enter your UserID.
     * If the UserID is incorrect. Print: "Your UserID is incorrect. If you currently do not have an acocunt please make one!
     * You are being redirected to main menu where you can make an account."
     * @param checker Check if the userID is valid
     */
    public void loginEnterID(boolean checker){
        if (checker){
            System.out.println("Please enter your UserID.");
        }
        else {
            System.out.println("Your UserID is incorrect. If you currently do not have an acocunt please make one!");
            System.out.println("You are being redirected to main menu where you can make an account.");
        }
    }

    /**
     * Print: Please enter your password.
     * If the password is incorrect. Print: Your password is incorrect. If you currently do not have an acocunt please make one!
     * @param checker Check if the password is valid
     */
    public void loginPassword(boolean checker){
        if (checker){
            System.out.println("Please enter your password.");
        }
        else {
            System.out.println("Your password is incorrect. If you currently do not have an acocunt please make one!");
        }
    }

    /**
     * Print: Please select the action you'd like to do.
     * Print: 1. Register
     * Print: 2. Login
     * Print: 3. SHUTDOWN
     */
    public void loginOrRegister(){
        System.out.println("Please select the action you'd like to do.");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. SHUTDOWN");
    }

    /**
     * Print: You have no contacts yet! Please add a contact before sending a message.
     */
    public void youHaveNoContacts(){
        System.out.println("You have no contacts yet! Please add a contact before sending a message.");
    }

    /**
     * Print: Would you like to add or remove contact?
     * Print: 1.Add contact
     * Print: 2. Remove contact
     * Print: Please type 0 to return to the previous menu
     */
    public void addRemoveContact(){
        System.out.println("Would you like to add or remove contact?");
        System.out.println("\t1. Add contact");
        System.out.println("\t2. Remove contact");
        System.out.println("Please type 0 to return to the previous menu");
    }

    /**
     * If the userID entered is invalid. Print: Invalid userID. Enter a valid UserID of the contact:
     * Print: Enter the userID of the contact:
     * Print: Please type 0 to go back.
     * @param invalid
     */
    public void enterContactUserid(boolean invalid){
        if (invalid){
            System.out.println("Invalid userID. Enter a valid UserID of the contact:");
        }
        else {
            System.out.println("Enter the userID of the contact:");
        }
        System.out.println("Please type 0 to go back.");
    }

    /**
     * If the input is invalid. Print: Invalid input, please try again:
     * Otherwise, print: Please select a contact:
     * Prints all the user_ids from the list of contact
     * Print: Enter 0 if you would like to cancel.
     * @param contactList The contact list we want to print
     * @param invalid Check if the input is valid
     */
    public void promptRecipient(ArrayList<String> contactList, boolean invalid){
        if (invalid){
            System.out.println("Invalid input, please try again:");
        }
        System.out.println("Please select a contact:");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1)+". "+ contactList.get(i));
        }
        System.out.println("Enter 0 if you would like to cancel.");
    }

    /**
     * If the input is invalid. Print: Invalid input, please try again:
     * Otherwise, print: Please select an event to send an automatic message to all attendees of that event:
     * Prints all the events
     * Print: Enter the event ID of your choice:
     * @param eventList The event list we want to print
     * @param invalid Check if the input is valid
     */
    public void promptEvents(ArrayList<ArrayList<String>> eventList, boolean invalid){
        if (invalid){
            System.out.println("Invalid input, please try again:");
        }
        System.out.println("Please select an event to send an automatic message to all attendees of that event:");
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println((i+1)+". ID: "+ eventList.get(i).get(0) + " Name: " + eventList.get(i).get(0));
        }
        System.out.println("Enter the event ID of your choice:");
    }

    /**
     * Print: Please enter the message, or type 0 to cancel:
     */
    public void promptContext(){
        System.out.println("Please enter the message, or type 0 to cancel: ");
    }

    /**
     * Print: "Please enter the automated message for " + title + " event: "
     * @param title The title of the event
     */
    public void promptContextEvent(String title){
        System.out.println("Please enter the automated message for " + title + " event: ");
    }

    public void printChat(Chat chat){
        if (chat.getMessages()!= null) {
            ArrayList<Message> messages = chat.getMessages();
            for (Message m : messages) {
                System.out.println(m);
                //System.out.println(m.getSender()+": "+m.getContext());
            }
        }
    }

    //MessageSystem
    public void sendMsgOptions(int role){
        //Organizer
        if (role == 1){
            System.out.println("Who would you like to send a message to?");
            System.out.println("Note: to send a message to a single user, they must be on your contact list!");
            System.out.println("\t1. All Speakers");
            System.out.println("\t2. All Organizers");
            System.out.println("\t3. All Attendees");
            System.out.println("\t4. An Individual");
        }
        //Attendee
        else if (role == 2){
            System.out.println("Would you like to send to 1. another Attendee or 2. a Speaker? ");
        }
        //Speaker
        else if (role == 3){
            System.out.println("1. Select an event to send to all attendees 2. respond to an attendee ");
        }
    }
    public void msgOptionInvalid(){
        System.out.println("Invalid option, please enter again: ");
    }
    public void invalidRecipient(){
        System.out.println("Invalid user ID, please enter again: ");
    }
    public void addContactFailed(){
        System.out.println("Failed to add contact, please provide valid input ID.");
    }

    //OrganizerSystem
    public void organizationSystemStartOptions(){
        System.out.println("What would you like to do:\n");
        System.out.println("Organizer Specific Options:");
        System.out.println("\t1. Create a new Speaker");
        System.out.println("\t2. Schedule a Speaker");
        System.out.println("\t3. Message");
        System.out.println("\t4. Create/Delete event");
        System.out.println("\t5. Create Rooms\n");
        System.out.println("Regular Attendee Options:");
        System.out.println("\t6. Add/Remove contact");
        System.out.println("\t7. Join/Leave event");
        System.out.println("\t8. See all Events");
        System.out.println("\t9. See Scheduled Events");
        System.out.println("\t10. LOGOUT");
        System.out.println("\t11. SHUTDOWN");
    }
    public void enterSpeakerName(){
        System.out.println("Please enter the name of the speaker you wish to create: ");
        System.out.println("Please press 0 to return to the previous menu.");
    }
    public void enterSpeakerID(boolean checker){
        if (checker){
            System.out.println("Please enter the userid for the speaker: ");
        }
        else{
            System.out.println("That userid is taken, please enter another UserID:");
        }
        System.out.println("Please type 0 to return to the previous menu");
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
            System.out.println("\t" + tempstr + " " + events.get(i-1).getTitle());

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
        System.out.println("The event id you entered was invalid or is already in use, please try again");
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));
        System.out.println("Please select one of the following events\n");
        for (int i=1; i<listOfEvents.size()+1; i++){
            System.out.println("\t" + i + ". Name: " + listOfEvents.get(i-1).getTitle() +" | Time: " + formatter.format(listOfEvents.get(i-1).getTime()) + " | Location: " + listOfEvents.get(i-1).getLocation());

        }
        System.out.println("\nTo return to the previous menu please press 0");
    }

    public void createDeleteEvent(){
        System.out.println("Would you like to create or delete an event?");
        System.out.println("1. Create");
        System.out.println("2. Delete");
        System.out.println("If you want to return to the previous menu, please press 0");
    }

    public void createEnterTime(){
        System.out.println("Please enter a date and time for this new event. \nPlease note it must follow the following format:" +
                "dd-mm-yyyy hh:mm:ss." +
                "\nEnter the day then the hours, which must be in the 24 hour format and must be between 09 and 16, inclusive. \n This time should be the EST time. \nSince all events start on the hour, mm and ss must be 00." +
                " \nThe time you enter cannot be in the past. For example, to enter the date and time December 20th 2020 1P.M. you would type '20-12-2020 13:00:00.'");
        System.out.println("Please press 0 to return to the previous menu");
    }

    public void createEnterTimeInvalidTime(){
        System.out.println("The time you entered did not fit the formatting requirements. Please try again.");
    }
    public void createNoRoomAvailable(){
        System.out.println("Unfortunately there are no rooms available for you to schedule this room. Please select another date and/or time." );
    }
    public void createProvideEventTitle(){
        System.out.println("Please provide the event title");
    }

    public void deleteInvalidEventId(){
        System.out.println("The event id you entered was invalid, please try again.");
    }

    public void createRoom(){
        System.out.println("Please enter a name for this room. This will be known as the room's location.");
        System.out.println("Press 0 to return to the previous menu");
    }
    public void createRoomUnavailable(){
        System.out.println("The room name/location has already been taken by an existing room. Please try again.");
    }
    public void invalidInputSelection(){
        System.out.println("Your input was invalid. Please try again.");
    }
    public void userAlreadyInYourContacts(){
        System.out.println("This person is already in your contacts. You can add someone else or return.");
    }
    public void userNotInYourContacts(){
        System.out.println("This person is not in your contacts. You can remove someone else or return.");
    }
    public void replyOrAutomessage(){
        System.out.println("Would you like to reply to a specific user or send an automatic message?");
        System.out.println("\t1. Automatic message");
        System.out.println("\t2. Reply to a specific user");
        System.out.println("\nPress 0 to return to the previous menu");
    }
    public void pressAnyKeyToContinue(){
        System.out.println("Please press any key to continue.");
    }
    public void messageSentToEveryone(){
        System.out.println("Message was sent to all specified recipients!");
    }
    public void messageSent(){
        System.out.println("Message sent!");
    }
    public void noAssignedEvents(){
        System.out.println("You have no assigned events as of now.");
    }
    public void noSignedUpEvents(){
        System.out.println("You have not signed up for any events so far.");
    }

    public void invalidInput(){
        System.out.println("Invalid input, please try again!");
    }
}
