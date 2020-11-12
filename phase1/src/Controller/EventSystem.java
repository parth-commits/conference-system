package Controller;

import Entities.Attendee;
import Entities.Organizer;
import Entities.User;
import Entities.Event;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class EventSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private EventManager eventManager;

    //constructor
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

    //start





