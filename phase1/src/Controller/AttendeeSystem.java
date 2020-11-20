package Controller;

import Entities.Event;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class AttendeeSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private ChatManager chatManager;
    private MessageSystem messageSystem;
    private EventSystem eventSystem;
    private RoomManager roomManager;
    private EventManager eventManager;

    public AttendeeSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, ChatManager chatManager,
                          AttendeeManager attendeeManager, MessageSystem messageSystem, EventSystem eventSystem, RoomManager roomManager, EventManager eventManager) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.attendeeManager = attendeeManager;
        this.chatManager = chatManager;
        this.messageSystem = messageSystem;
        this.eventSystem = eventSystem;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();

    }
    // Attendee is allowed to 1. see Events. 2. Sign up for Events
    // 3. Check Schedule for an Signed Up Event 4. Cancel an Event Signed Up for 5. Add or Remove Attendee in Contact
    //6. Message Other Users
    public boolean start(String userID) throws IOException {
        while (true) {
            String i;
            boolean validInput = false;
            output.AttendeeMenu();
            i = input.getKeyboardInput();
            //1. see Events
            if (i.equals("1")) {
                eventSystem.checkAllEvents();
            }
            //2. Sign up for Events / cancel an event
            else if (i.equals("2")) {
                joinLeaveEvent(userID);
            }
            //3. Check Schedule for an Signed Up Event
            else if (i.equals("3")){
                eventSystem.checkSignedUpEvent(userID);
            }
            //4. Add or Remove Attendee in Contact
            else if (i.equals("4")){
                addRemoveContact(userID);
            }
            //5. Message Other Users
            else if (i.equals("5")){
                messageSystem.sendMessage(userID);
            }
            //6. logout
            else if (i.equals("6")){
                return false;
            }
            //7. shutdown
            else if (i.equals("7")) {
                return true;
            }
        }
    }


    private void saveState() throws IOException {
        //save the state back in!!!
        speakerManager.saveState();
        //roomManager.saveState();
        organizerManager.saveState();
        //eventManager.saveState();
        chatManager.saveState();
        attendeeManager.saveState();

    }


    private boolean userExists(String userid){
        if (attendeeManager.userExist(userid)||organizerManager.userExist(userid)||speakerManager.userExist(userid)){
            return true;
        }
        return false;
    }






    private void addRemoveContact(String userID){
        boolean goBack = false;
        while (!goBack) {
            boolean validAddRemove = false;
            while (!validAddRemove) {
                output.addRemoveContact();
                String option = input.getKeyboardInput();
                if (option.equals("0")) {
                    validAddRemove = true;
                    goBack = true;
                }
                else if (option.equals("1")) {
                    boolean validUserID = false;
                    while (!validUserID) {
                        output.enterContactUserid(false);
                        String user = input.getKeyboardInput();
                        if (user.equals("0")) {
                            validUserID = true;
                        }
                        else if ((organizerManager.userExist(user) || attendeeManager.userExist(user) || speakerManager.userExist(user))) {
                            if (!attendeeManager.contactExists(userID, user)){
                                attendeeManager.addContact(userID, user);
                                if (organizerManager.userExist(user)) {
                                    organizerManager.addContact(user, userID);
                                } else if (attendeeManager.userExist(user)) {
                                    attendeeManager.addContact(user, userID);
                                } else {
                                    speakerManager.addContact(user, userID);
                                }
                                chatManager.createChat(user, userID);
                                validUserID = true;
                                validAddRemove = true;
                                goBack = true;
                            }
                            else{
                                output.userAlreadyInYourContacts();
                            }
                        } else {
                            output.enterContactUserid(true);
                        }
                    }
                } else if (option.equals("2")) {
                    boolean validUserID = false;
                    while (!validUserID) {
                        output.enterContactUserid(false);
                        String user = input.getKeyboardInput();
                        if (user.equals("0")) {
                            validUserID = true;
                        } else if ((organizerManager.userExist(user) || attendeeManager.userExist(user) || speakerManager.userExist(user))) {
                            if (attendeeManager.contactExists(userID,user)){
                                attendeeManager.removeContact(userID, user);
                                if (organizerManager.userExist(user)) {
                                    organizerManager.removeContact(user, userID);
                                } else if (attendeeManager.userExist(user)) {
                                    attendeeManager.removeContact(user, userID);
                                } else {
                                    speakerManager.removeContact(user, userID);
                                }
                                chatManager.deleteChat(user, userID);
                                validUserID = true;
                                validAddRemove = true;
                                goBack = true;
                            }
                            else{
                                output.userNotInYourContacts();
                            }
                        } else {
                            output.enterContactUserid(true);
                        }
                    }
                } else {
                    output.invalidInputSelection();
                }
            }
        }
        }




    private void joinLeaveEvent(String userID){
        boolean validInput = false;
        while (!validInput) {
            output.joinOrLeave();                                                     //checks whether attendee wants to join or leave an event, or wants to return back to menu
            String joinLeave = input.getKeyboardInput();
            int joinLeaveInt = Integer.parseInt(joinLeave);
            if (joinLeaveInt == 0) {                                                  //if attendee wants to return to menu, we exit this loop, having done nothing.
               validInput = true;
            }
            else if (joinLeaveInt == 1) {                                           //in this case, org wants to join an event
                boolean validEventSelected = false;
                while (!validEventSelected) {
                    ArrayList<Integer> listOfAllEventIDs = eventManager.getListOfEventIDs();                            //gets list of all events
                    ArrayList<Integer> listOfCurrentlyAttendingEventIds = attendeeManager.getSignedUpEvents(userID);    //gets list of all events this attendee is already attending
                    listOfAllEventIDs.removeAll(listOfCurrentlyAttendingEventIds);                                      //now listOfAllEvents contains the events this organizer is NOT attending already
                    for (Integer eventid : listOfAllEventIDs) {                                                            //goes through every event this organizer is not attending (list of events he can possible join)
                        Date newEventTime = eventManager.getTime(eventid);                                              //finds its time
                        for (Integer currenteventid : listOfCurrentlyAttendingEventIds) {
                            Date currentEventTime = eventManager.getTime(currenteventid);
                            if (newEventTime.equals(currentEventTime)) {                                                 //if this time is the same as any event the attendee is already attending,
                                listOfAllEventIDs.remove(eventid);                                                      //remove that event from the event from list of event he can possible join (listOfAllEventIDs)
                            }
                        }
                        //by now, listOfAllEventIDs contains the eventIDs of events that the attendee has not joined already and whose timings do not
                        //overlap/interfere with events he/she is already going to! Now from these events, we want to remove those that do not have
                        //room (sufficient capacity) to support one more attendee.
                        Event actualEvent = eventManager.getEvent(eventid);
                        int capacity = roomManager.getRoom(actualEvent.getLocation()).getCapacity();                    //gets the capacity of the room this event is held in
                        int numExistingAttendees = actualEvent.getAttendees().size();                                   //gets the number of attendees that are attending this event
                        if (capacity - numExistingAttendees == 0) {                                                           //if the number of attendees attending this event has reached the max capacity of the room,
                            listOfAllEventIDs.remove(eventid);                                                          //the organizer cannot join this room. Remove it from the list.
                        }
                    }
                    ArrayList<Event> listOfJoinableEvents = new ArrayList<>();                                          //list of all events this Attendee can join
                    for (Integer eventid : listOfAllEventIDs) {
                        listOfJoinableEvents.add(eventManager.getEvent(eventid));
                    }
                    output.joinDeleteEventSelector(listOfJoinableEvents);
                    String eventSelected = input.getKeyboardInput();
                    int eventSelectedInt = Integer.parseInt(eventSelected);
                    if (eventSelectedInt == 0) {
                        validEventSelected = true;
                    } else if (1 <= eventSelectedInt && eventSelectedInt <= listOfJoinableEvents.size()) {
                        attendeeManager.addEventToAttendee(listOfAllEventIDs.get(eventSelectedInt - 1), userID);         //add eventid to the attendees list of events.
                        eventManager.addAttendee(listOfAllEventIDs.get(eventSelectedInt - 1), userID);                     //add attendee to events list of attendees for this event.
                        output.ActionDone();
                        validEventSelected = true;
                        validInput = true;
                    } else {
                        output.joinLeaveInvalidResponse();
                    }
                }
            }
            else if(joinLeaveInt==2){                                                                                   //we need to leave an event here
                boolean validEventSelected = false;
                while(!validEventSelected){
                    ArrayList<Integer> listOfAttendingEventIds = attendeeManager.getSignedUpEvents(userID);            //get the list of signed up eventids
                    ArrayList<Event> listofAttendingEvents = new ArrayList<>();
                    for(Integer eventid: listOfAttendingEventIds){                                                      //get the list of events
                        listofAttendingEvents.add(eventManager.getEvent(eventid));
                    }
                    output.joinDeleteEventSelector(listofAttendingEvents);                                              //select which event they want to leave
                    String eventSelected = input.getKeyboardInput();
                    int eventSelectedInt = Integer.parseInt(eventSelected);
                    if(eventSelectedInt==0){
                        validEventSelected = true;
                    }
                    else if(1<=eventSelectedInt && eventSelectedInt<= listofAttendingEvents.size()){
                        attendeeManager.removeEvent(listOfAttendingEventIds.get(eventSelectedInt-1),userID);
                        eventManager.removeAttendee(listOfAttendingEventIds.get(eventSelectedInt-1),userID);
                        validEventSelected=true;
                        validInput=true;
                    }
                    else{
                        output.joinLeaveInvalidResponse();
                    }
                }
            }
        }

    }


}