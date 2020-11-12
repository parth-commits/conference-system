import Controller.EventSystem;
import Controller.LogInAndRegistrationSystem;
import Controller.MessageSystem;
import Controller.OrganizerSystem;
import Entities.Event;
import Entities.User;
import UseCases.*;

public class ConferenceSystem {

    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private ChatManager chatManager;
    private AttendeeManager attendeeManager;

    private OrganizerSystem organizerSystem;
    private MessageSystem messageSystem;
    private LogInAndRegistrationSystem logInAndRegistrationSystem;
    private EventSystem eventSystem;

    //constructor
    public ConferenceSystem(){
        serialize();// this function will bring back the managers and initialize them
        organizerSystem = new OrganizerSystem(speakerManager, roomManager,organizerManager, eventManager, chatManager, attendeeManager);
        logInAndRegistrationSystem = new LogInAndRegistrationSystem(attendeeManager, organizerManager, speakerManager);
        messageSystem = new MessageSystem(speakerManager,organizerManager, eventManager, chatManager, attendeeManager);
    }

    private void serialize() {
        //this method will serialize the managers - the usecases
    }

    //UserManager userManager = new UserManager;
    //Eventmanager ....

    public void run (){
        /*serialize();
        /*create an instance of all use cases
        LogInSystem login = new LogInSystem(Usermanager, eventmananger, etc);



        User userInContext =  login.loginuser();

        if organziermanager.userExists(friendsid):
            att

        or speakermaanger.userExists(friends)
        attendeemanager.addContact(userInContext.getUser_id(), friendsuserid, organizermanager.getListofIDS(),  speakermanager.getlistofIDS());

        organziermanager.addContact(friendsUserid, userInContext.getUser_id(), attendeemanager.getListofIDS(), speakermanager.getListofIDS());*/

        // do u want to login, sign up

        /* loop until gets a valid id pass
        {
            // get user input of id and pass
            userInContext = login.verifyIDPass(id, pass, userManager?!)
        }*/

        /*if userInContext is a attendee:
        attendeemanager = //we need an attendee manager!! instead of user manager
        else if its an organizer:
        make it an organzier manager!!!!
            else if its a speaker, make it a speaker*/
    }
}

getinput = () from gateway
while getinput != LOGOUT :
    if getinput is x:
        do x
    else if getinput is y:
        do y
    ...
    else:
        getinput = () from gateway


