package Controller;

import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.ChatManager;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import UseCases.*;
import Entities.*;

import java.util.ArrayList;

public class ExportSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private ChatManager chatManager;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private AttendeeManager attendeeManager;
    private RoomManager roomManager;

    public ExportSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, EventManager eventManager, ChatManager chatManager, AttendeeManager attendeeManager, RoomManager roomManager) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.chatManager = chatManager;
        this.attendeeManager = attendeeManager;
        this.output = new TextPresenter();
        this.input = new KeyboardInput();
        this.roomManager = roomManager;
    }

    public StringBuilder getInfo(){
        StringBuilder info = new StringBuilder("Full Schedule:\n");
        ArrayList<String> roomLocList = roomManager.getAllRoomLocs();
        for (String roomLoc : roomLocList){
            info.append(getRoomInfo(roomLoc));

        }
        return info;
    }

    public String getRoomInfo(String roomLocation){
        ArrayList<Event> eventList = eventManager.getListOfEvents();

        StringBuilder roomInfo = new StringBuilder("Room "+roomLocation+":\n");
        for (Event event : eventList){
            if (event.getLocation().equals(roomLocation)){
                roomInfo.append(getEventInfo(event)).append("\n");
            }
        }
        return roomInfo.toString();
    }

// CONCERN: Can I pass event in like this? or should I access it as eventID
    public String getEventInfo(Event event){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(event.getTime());

        StringBuilder eventInfo = new StringBuilder(strDate + "\n");

        eventInfo.append("\tEvent:").append(event.getTitle()).append("\n");
        // get speaker info string
        ArrayList<String> speakers = event.getSpeakerID();
        eventInfo.append("\t\tSpeaker: " + "\n");

        for (String speaker : speakers){
            eventInfo.append("\t\t\t").append(speakerManager.getSpeaker(speaker).getName()).append("\n");
        }
        // get attendee info string
        eventInfo.append("\t\tAttendees: " + "\n");
        ArrayList<String> attendees = event.getAttendees();
        for (String attendee : attendees){
            eventInfo.append("\t\t\t").append(attendeeManager.getAttendee(attendee).getName()).append("\n");
        }
        // get organizer info string
        String organizer = organizerManager.getOrganizer(event.getOrganizerID()).getName();
        eventInfo.append("\t\tOrganizer: " + "\n");
        eventInfo.append("\t\t\t").append(organizer).append("\n");

        return eventInfo.toString();
    }

}
