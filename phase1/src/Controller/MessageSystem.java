package Controller;

import Entities.Chat;
import UseCases.*;

public class MessageSystem {
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
    }

    public Chat getChat(String id1, String id2){
        return chatManager.findChat(id1, id2);
    }

    public void sendMessage(String sender, String recipient, String context){
        chatManager.addMessageToChat(sender, recipient, context);
    }

    public boolean deleteChat(String id1, String id2){
        return chatManager.deleteChat(id1, id2);
    }

}
