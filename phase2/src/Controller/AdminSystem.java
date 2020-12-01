package Controller;

import Entities.Event;
import Gateway.KeyboardInput;
import Gateway.Serialization;
import Presenter.TextPresenter;
import UseCases.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminSystem {

    private TextPresenter output;
    private KeyboardInput input;
    private ChatManager chatManager;
    private EventManager eventManager;


    /**
     * Constructor
     * @param chatManager      The chat manager implements by ChatManager use case
     * @param eventManager     The event manager implements by EventManager use case
     */


    public AdminSystem( ChatManager chatManager,  EventManager eventManager) {
        this.chatManager = chatManager;
        this.eventManager = eventManager;
        this.input = new KeyboardInput();
        this.output = new TextPresenter();
    }

    private ArrayList<Integer> GetNoAttendeeEvent(){
        ArrayList<Integer> NoAttendee = new ArrayList<>();
        for(Event event : eventManager.getListOfEvents()){
            if(event.getAttendees().size() == 0){
                NoAttendee.add(event.getID());
            }
        }
        return NoAttendee;
    }


    public void RemoveEmptyEvent(){
        for(Integer id: GetNoAttendeeEvent()){
            eventManager.removeEvent(id);
        }
    }

    public boolean RemoveChat(String userId1, String userId2){
        if(chatManager.chatExists(userId1, userId2)){
            chatManager.deleteChat(userId1, userId2);
            return true;
        }
        else{
            return false;
        }
        }



    public boolean start() throws IOException {
            while (true) {
                String i;
                boolean validInput = false;
                output.AdminMenu();
                i = input.getKeyboardInput();
                //1. Delete Empty Event
                switch (i) {
                    case "1":
                        RemoveEmptyEvent();
                        output.RemoveEmptyEvent();
                        break;
                    //2. Delete Chat
                    case "2":
                        output.deleteChat(true);
                        String Prompt1 = input.getKeyboardInput();
                        String Prompt2 = input.getKeyboardInput();
                        boolean removeChat = RemoveChat(Prompt1, Prompt2);
                        output.deleteChat(removeChat);
                        break;
                    //3. logout
                    case "3":
                        return false;

                    //4. shutdown
                    case "4":
                        return true;
                }
                saveState();
            }
        }

    private void saveState() throws IOException {
        Serialization io = new Serialization();
        io.saveState(eventManager, "EventManager");
        io.saveState(chatManager, "ChatManager");
    }

}
