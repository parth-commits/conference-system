package Controller;

import Entities.Event;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;
import jdk.internal.net.http.common.Log;
import jdk.jfr.internal.Logger;

import java.util.ArrayList;
import java.util.Date;

public class OrganizerSystem {

    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;


    public OrganizerSystem(SpeakerManager speakerManager, RoomManager roomManager, OrganizerManager organizerManager, EventManager eventManager, ChatManager chatManager, AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem) {
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.speakerManager = speakerManager;
        this.chatManager = chatManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
    }

    public boolean start(String userID) {
        while(true){
            String o;
            boolean validInput = false;
            output.organizationSystemStartOptions();
            o = input.getKeyboardInput();
            if (o.equals("1")){
                createSpeaker(userID);
            }
            else if (o.equals("2")){
                scheduleASpeaker();
            }
            else if (o.equals("3")){
                message(userID);
            }
            else if (o.equals("4")){
                createDeleteEvent();
            }
            else if (o.equals("5")){
                addRemoveContact(userID);
            }
            else if (o.equals("6")){
                joinLeaveEvent();
            }
            else if (o.equals("7")){
                return false;
            }
            else if (o.equals("8")){
                return true;
            }
        }

    }

    //creates a new speaker
    private void createSpeaker(String userID) {
        output.enterSpeakerName();
        String inputName = input.getKeyboardInput();
        boolean untilCorrect = true;
        boolean correct = true;
        String inputID = "";
        while (untilCorrect) {
            output.enterSpeakerID(correct);
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
        speakerManager.addSpeaker(inputID,inputPass,inputName, userID);
        organizerManager.setAddSpeakerCreated(userID,inputID);
        output.ActionDone();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){
            System.out.println("Was not able to sleep");
        }

    }

    //WE WANT THE ORGANIZER TO BE ABLE TO RETURN TO THE PREVIOUS STEP AT ANY TIME. TO DO THIS, EVERY TIME THE ORGANIZER HAS TO INPUT
    //SOMETHING, IF THEY INPUT 0, IT WILL BREAK OUT OF THE LOOP, AND THEREBY RETURN TO THE PREVIOUS STEP.
    private void scheduleASpeaker() {
        ArrayList<Event> listOfEvents = eventManager.listOfEventsWithoutSpeaker();

        if (listOfEvents.isEmpty()) {                                               //if all events already have speakers.
            output.scheduleSpeakerNoSpeakerlessEvents();                            //there's no choice but to return to the previous menu
        }
        else {                                                                      //there is at least one event which does not have an assigned speaker
            boolean loopVariable = true;
            boolean invalidEvent = false;
            while (loopVariable){
                if (invalidEvent){
                    output.scheduleSpeakerInvalidEventID();                         //WE NEED TO COME UP WITH A WAY FOR THIS MESSAGE TO STAY FOR AT LEAST 5-10 SECONDS
                }
                output.scheduleSpeakerSelectEvent(listOfEvents);                    //Gives list of events without a speaker
                String eventID = input.getKeyboardInput();
                int eventIDint = Integer.parseInt(eventID);
                if (eventIDint==0){                                                 //If the user wishes to return to the previous menu, they press 0. Exit loop.
                    loopVariable=false;
                }
                else if (0< eventIDint && eventIDint<=listOfEvents.size()){         //if they entered a valid int to select an event
                    boolean validSpeakerID = false;
                    while(!validSpeakerID){                                         //loop repeats until user inputs a valid speakerID
                        output.scheduleSpeaker();
                        String speakerID = input.getKeyboardInput();                                     // gets speakerid
                        if (speakerID.equals("0")){                                                      // if organizer wants to select a different event, they press 0. Exit loop.
                            validSpeakerID=true;
                        }
                        else if (speakerManager.userExist(speakerID)){                                   //if speaker exists
                            ArrayList<Integer> listOfEnrolledEventIDs = speakerManager.getSpeaker(speakerID).getAssignEvents(); //gets list of eventid's this speaker is talking at
                            Date newEventDateTime = eventManager.getEvent(eventIDint).getTime();         //gets the time of this new event
                            boolean speakerBusy = false;
                            for (Integer event: listOfEnrolledEventIDs){                                 // gets time of every event this speaker is talking at.
                                Date existingEventTime= eventManager.getEvent(event).getTime();
                                if(existingEventTime.equals(newEventDateTime)){                          //if the speaker is talking at the same time at another existing event,
                                    output.scheduleSpeakerSpeakerBusy();
                                    speakerBusy = true;                                                  // then speakerBusy = true
                                    break;
                                }
                            }
                            if (speakerBusy) {                                       //if the speaker is busy, then we want the organizer to be able to select a different event
                                validSpeakerID=true;                                 //breaks out of the inner while loop that checks for speaker, but not the outer while loop
                            }                                                        //that checks for event.
                            else {                                                                      //if speaker is not busy, then
                                Event event = listOfEvents.get(eventIDint-1);
                                event.setSpeaker(speakerID);                                            //gets the event and sets the speaker.
                                speakerManager.addEventToSpeaker(event.getID(), speakerID);             //gets the speaker and adds this new event to its list of assigned events.
                                validSpeakerID=true;                                 //Now we want to exit both loops, since our job is done successfully.
                                loopVariable=false;
                            }
                        }
                        else{                                                        //if speakerid does not exist.
                            output.scheduleSpeakerInvalidSpeakerID();                // validSpeakerID stays false, hence this inner loop will repeat and ask for a speakerID again
                        }
                    }
                }
                else{                                                                //if the event id (int) they inputted was not valid, we loop back
                    invalidEvent=true;
                }
            }
        }
    }

    private void message(String userID) {
        messageSystem.sendMessage(userID);
    }
    //to do
    private void createDeleteEvent() {

        }


    //when you add a contact, a new empty chat object gets created with them. And it follows that when you remove a contact,the chat with them is deleted.
    private void addRemoveContact(String userID) {
        output.addRemoveContact();
        String option = input.getKeyboardInput();
        while (!(option.equals("1")||option.equals("2"))){
            output.addRemoveContact();
            option = input.getKeyboardInput();
        }
        if (option.equals("1")){
            output.enterContactUserid(false);
            String input = this.input.getKeyboardInput();
            while (!(organizerManager.userExist(input)||attendeeManager.userExist(input)||speakerManager.userExist(input))){
                output.enterContactUserid(true);
                input = this.input.getKeyboardInput();
            }
            organizerManager.addContact(userID, input);
            if (organizerManager.userExist(input)){
                organizerManager.addContact(input, userID);
            }
            else if (attendeeManager.userExist(input)){
                attendeeManager.addContact(input, userID);
            }
            else{
                speakerManager.addContact(input, userID);
            }
            chatManager.createChat(input, userID);
        }
        else {
            output.enterContactUserid(false);
            String input = this.input.getKeyboardInput();
            while (!(organizerManager.userExist(input)||attendeeManager.userExist(input)||speakerManager.userExist(input))){
                output.enterContactUserid(true);
                input = this.input.getKeyboardInput();
            }
            organizerManager.removeContact(userID, input);
            if (organizerManager.userExist(input)){
                organizerManager.removeContact(input, userID);
            }
            else if (attendeeManager.userExist(input)){
                attendeeManager.removeContact(input, userID);
            }
            else{
                speakerManager.removeContact(input, userID);
            }
            chatManager.deleteChat(input,userID);
        }

    }
    //to do
    private void joinLeaveEvent() {
        output.joinLeaveEvent();
        String eventid = input.getKeyboardInput();
        boolean checker = false;
        int eventidint = Integer.parseInt(eventid);
        for (Event event: eventManager.getListOfEvents()){
            if (event.getID() == eventidint) {
                checker = true;
                break;
            }
        }
        if (checker) {
            output.joinOrLeave();
            String joinLeave = input.getKeyboardInput();
            int joinLeaveInt = Integer.parseInt(joinLeave);
            if (joinLeaveInt == 1) {

            }
            else if (joinLeaveInt == 2){

            }
            else {
                output.joinLeaveInvalidResponse();
            }
        }
        else{
            output.invalidEventID();
        }


    //returns true if the given userid exists in any 3 people's managers
    private boolean userExists(String userid){
        if (attendeeManager.userExist(userid)||organizerManager.userExist(userid)||speakerManager.userExist(userid)){
            return true;
        }
        return false;
    }

}
