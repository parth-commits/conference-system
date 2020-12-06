import Controller.*;
import Gateway.Serialization;
import UseCases.*;
import UseCases.AdminManager;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The TestExportSystem class tests the exportSystem
 * @author Group_0112
 * @version 2.0
 * @since December 6th, 2020
 */

public class TestExportSystem {

    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private AttendeeManager attendeeManager;
    private AdminManager adminManager;
    private RequestManager requestManager;

    private ExportSystem exportSystem;

    /**
     * Constructor
     */
    public TestExportSystem(){
        importState();// this function will bring back the managers and initialize them
        initializeControllers();// this function will initialize the controllers
    }

    /**
     * Initializes all controllers
     */
    private void initializeControllers() {
        exportSystem = new ExportSystem(speakerManager, organizerManager, eventManager, attendeeManager, roomManager);

    }

    /**
     * Serializes all managers (use cases)
     */
    private void importState() {
        Serialization io = new Serialization();
        this.adminManager = (AdminManager) io.importState("AdminManager");
        this.speakerManager = (SpeakerManager) io.importState("SpeakerManager");
        this.roomManager = (RoomManager) io.importState("RoomManager");
        this.organizerManager = (OrganizerManager) io.importState("OrganizerManager");
        this.eventManager = (EventManager) io.importState("EventManager");
        this.chatManager = (ChatManager) io.importState("ChatManager");
        this.attendeeManager = (AttendeeManager) io.importState("AttendeeManager");
        this.requestManager = (RequestManager) io.importState("RequestManager");

    }

    /**
     * Runs the program
     * @throws IOException throw IOException to avoid errors that might occur while running the program
     */
    public void run () throws IOException {
        boolean shutdown = false;
        Debugger debugger = new Debugger();
        debugger.printStateofSystem(organizerManager,speakerManager,attendeeManager,eventManager,roomManager, requestManager, adminManager, chatManager);
        while (!shutdown) {
            shutdown = exportSystem.run();
        }
    }

}