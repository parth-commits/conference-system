package Controller;

import java.util.ArrayList;

import Entities.Attendee;
import Entities.Speaker;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;


public class SpeakerSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private ChatManager chatManager;
    private RoomManager roomManager;
    private EventManager eventManager;
    private SpeakerManager speakerManager;

    public AttendeeSystem (AttendeeManager attendeeManager, RoomManager roomManager, EventManager eventManager,
                           SpeakerManager speakerManager){
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.speakerManager = SpeakerManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
}
