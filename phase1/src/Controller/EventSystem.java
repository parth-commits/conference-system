package Controller;

import Entities.Event;
import Entities.Speaker;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private AttendeeManager attendeeManager;
    private RoomManager roomManager;

    public EventSystem(SpeakerManager speakerManager, RoomManager roomManager, OrganizerManager organizerManager,
                       EventManager eventManager, AttendeeManager attendeeManager) {
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.speakerManager = speakerManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
    }
    public void checkAllEvents(){
        ArrayList<String> listOfEventSchedule = new ArrayList<>();
        ArrayList<Event> listOfEvents = eventManager.getListOfEvents();
        for (Event event :listOfEvents){
            String schedule = event.getTitle() + "\n" + "Location: " + event.getLocation() + "\n"
                    + "Time: " + event.getTime() + "\n" + "Event ID: " + event.getID();
            if (eventManager.hasSpeaker(event.getID())){
                schedule +=  "\n" + "Speaker: " + speakerManager.getSpeaker(event.getSpeakerID()).getName();
            }
            listOfEventSchedule.add(schedule);
        }
        output.eventsCheckAll(listOfEventSchedule);
        boolean rtn = false;
        while(!rtn){
            output.pressAnyKeyToContinue();
            String in = input.getKeyboardInput();
            if (!in.equals("")){
                rtn = true;
            }
        }
    }

    private void saveState() throws IOException {
        //save the state back in!!!
        speakerManager.saveState();
        roomManager.saveState();
        organizerManager.saveState();
        eventManager.saveState();
        //chatManager.saveState();
        attendeeManager.saveState();

    }

    public void signUpEvent(String UserId, Integer EventId){
        if (attendeeManager.SignedUp(EventId, UserId)){
            output.ActionFailed();
        }
        else {
            attendeeManager.addEventToAttendee(EventId, UserId);
            eventManager.addAttendee(EventId, UserId);
            output.ActionDone();
        }
    }

    public void cancelSignedUpEvent(String UserId, Integer EventId) {
        if (attendeeManager.SignedUp(EventId, UserId)) {
            attendeeManager.removeEvent(EventId, UserId);
            output.ActionDone();
        } else {
            output.ActionFailed();
        }
    }

    public void checkSignedUpEvent(String UserId){
        ArrayList<Event> listOfEvents = new ArrayList<>();
        ArrayList<String> listOfEventSchedule = new ArrayList<>();
        ArrayList<Integer> listOfEventsId = new ArrayList<>();
        if (attendeeManager.userExist(UserId)) {
            listOfEventsId = attendeeManager.getSignedUpEvents(UserId);
        }
        else if (organizerManager.userExist(UserId)){
            listOfEventsId = organizerManager.getSignedUpEvents(UserId);
        }
        for(Integer i: listOfEventsId){
            listOfEvents.add(eventManager.getEvent(i));
        }
        if (!listOfEventsId.isEmpty()){
            for (Event event :listOfEvents){
                String schedule = event.getTitle() + "\n" + "Location: " + event.getLocation() + "\n"
                        + "Time: " + event.getTime() + "\n"
                        + "Speaker: " + speakerManager.getSpeaker(event.getSpeakerID()).getName();
                listOfEventSchedule.add(schedule);
            }
            output.eventsAttendeeAndOrganizer(listOfEventSchedule);
        }
        else{
            output.noSignedUpEvents();
        }
        boolean rtn = false;
        while(!rtn){
            output.pressAnyKeyToContinue();
            String in = input.getKeyboardInput();
            if (!in.equals("")){
                rtn = true;
            }
        }
    }

    public void checkAssignedEvent(String UserId){
        ArrayList<Event> listOfEvents = new ArrayList<>();
        ArrayList<String> listOfEventSchedule = new ArrayList<>();
        ArrayList<Integer> listOfEventsId = speakerManager.getAssignedEvent(UserId);
        if(!listOfEventsId.isEmpty()){
            for(Integer i: listOfEventsId){
                listOfEvents.add(eventManager.getEvent(i));
            }
            for (Event event :listOfEvents){
                String schedule = event.getTitle() + "\n" + "Location: " + event.getLocation() + "\n"
                        + "Time: " + event.getTime() + "\n"
                        + "Attendees: " + attendeeManager.getAttendee(event.getAttendees().toString());
                listOfEventSchedule.add(schedule);
            }
            output.eventsSpeaker(listOfEventSchedule);
        }
        else{
            output.noAssignedEvents();
        }

        boolean rtn = false;
        while(!rtn){
            output.pressAnyKeyToContinue();
            String in = input.getKeyboardInput();
            if (!in.equals("")){
                rtn = true;
            }
        }
    }


    //IS THIS METHOD EVERY GOING TO BE USED?
    public void checkEventTitleIDs(){
        ArrayList<String> entireEventList = new ArrayList<>();
        ArrayList<Event> listOfEvents = eventManager.getListOfEvents();
        int i = 0;
        for (Event event :listOfEvents){
            String s = String.valueOf(i);
            String schedule = s +". " + event.getTitle() + ".   EventID: " + event.getID();
            entireEventList.add(schedule);
        }
        //output.Events(entireEventList);
    }
    }

