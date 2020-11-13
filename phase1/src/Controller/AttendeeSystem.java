package Controller;

import java.util.ArrayList;

import Entities.Attendee;
import Entities.User;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class AttendeeSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private AttendeeManager attendeeManager;
    private ChatManager chatManager;
    private RoomManager roomManager;
    private EventManager eventManager;

    public AttendeeSystem (AttendeeManager attendeeManager, RoomManager roomManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();

    }

}
