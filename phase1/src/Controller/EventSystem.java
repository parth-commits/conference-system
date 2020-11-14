package Controller;

import Entities.Event;
import Entities.Speaker;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

import java.util.ArrayList;

public class EventSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private ChatManager chatManager;
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
                    + "Time: " + event.getTime() + "\n"
                    + "Speaker: " + speakerManager.getSpeaker(event.getSpeakerID()).getName();
            listOfEventSchedule.add(schedule);
        }
        output.Events(listOfEventSchedule);
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
        ArrayList<Integer> listOfEventsId = attendeeManager.getSignedUpEvents(UserId);
        for(Integer i: listOfEventsId){
            listOfEvents.add(eventManager.getEvent(i));
        }
        for (Event event :listOfEvents){
            String schedule = event.getTitle() + "\n" + "Location: " + event.getLocation() + "\n"
                    + "Time: " + event.getTime() + "\n"
                    + "Speaker: " + speakerManager.getSpeaker(event.getSpeakerID()).getName();
            listOfEventSchedule.add(schedule);
        }
        output.Events(listOfEventSchedule);
    }

    public void checkAssignedEvent(String UserId){
        ArrayList<Event> listOfEvents = new ArrayList<>();
        ArrayList<String> listOfEventSchedule = new ArrayList<>();
        ArrayList<Integer> listOfEventsId = attendeeManager.getSignedUpEvents(UserId);
        for(Integer i: listOfEventsId){
            listOfEvents.add(eventManager.getEvent(i));
        }
        for (Event event :listOfEvents){
            String schedule = event.getTitle() + "\n" + "Location: " + event.getLocation() + "\n"
                    + "Time: " + event.getTime() + "\n"
                    + "Speaker: " + speakerManager.getSpeaker(event.getSpeakerID()).getName();
            listOfEventSchedule.add(schedule);
        }
        output.Events(listOfEventSchedule);
    }

    }

