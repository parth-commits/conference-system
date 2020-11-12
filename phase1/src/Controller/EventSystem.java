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

        attendeeManager.addEventToAttendee(EventId, UserId);

    }
}


