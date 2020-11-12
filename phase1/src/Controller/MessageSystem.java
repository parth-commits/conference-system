package Controller;

import Entities.Chat;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.*;

public class MessageSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private ChatManager chatManager;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private AttendeeManager attendeeManager;

    public MessageSystem (){
        this.chatManager = new ChatManager();
    }

    public MessageSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, EventManager eventManager, ChatManager chatManager, AttendeeManager attendeeManager) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.chatManager = chatManager;
        this.attendeeManager = attendeeManager;
        this.output = new TextPresenter();
        this.input = new KeyboardInput();
    }

    public Chat getChat(String id1, String id2){
        return chatManager.findChat(id1, id2);
    }

    public void sendMessage(String sender, String recipient, String context){
        int role = userType(sender);
        output.sendMsgOptions(role);
        if (role == 1){
            // unfinished Ray
        }
        chatManager.addMessageToChat(sender, recipient, context);
    }

    public boolean deleteChat(String id1, String id2){
        return chatManager.deleteChat(id1, id2);
    }

    public int userType(String id){
        // check current user status
        // 1 for attendee, 2 for speaker, 3 for organizer
        if (attendeeManager.userExist(current)){
            return 1;
        }
        else if(speakerManager.userExist(current)){
            return 2;
        }
        else if (organizerManager.userExist(current)){
            return 3;
        }
        else {
            System.out.println("Current User DNE Error");
            return -1;
        }
    }

    public void addContact (String current, String id){
        int current_role = userType(current);
        int id_role = userType(id);
        // adding contact based on their authority
        if (current_role == 1){
            if (id_role < 3){

            }
        }
        else if (current_role == 2){
            if (id_role == 1){

            }
        }
        else if (current_role == 3) {
            if (id_role < 3){

            }
        }
    }

}
