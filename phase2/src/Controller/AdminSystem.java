package Controller;

import Entities.Event;
import Gateway.KeyboardInput;
import Gateway.Serialization;
import Presenter.TextPresenter;
import UseCases.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * AdminSystem controller implements 2 major functions that can be done by an admin,
 * which are delete empty events, and delete selected chat.
 * @author Group_0112
 * @version 2.0
 * @since December 1st, 2020
 */

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

    /**
     * Returns an arraylist of events that are empty (without any attendee).
     * @return ArrayList </Integer> list that contains all ids of empty events
     */
    private ArrayList<Integer> GetNoAttendeeEvent(){
        ArrayList<Integer> NoAttendee = new ArrayList<>();
        for(Event event : eventManager.getListOfEvents()){
            if(event.getAttendees().size() == 0){
                NoAttendee.add(event.getID());
            }
        }
        return NoAttendee;
    }

    /**
     * Removes the events that do not have any attendee.
     */
    public void RemoveEmptyEvent(){
        for(Integer id: GetNoAttendeeEvent()){
            eventManager.removeEvent(id);
        }
    }

    /**
     * Removes the chat between 2 users
     * @param userId1 one of those 2 users
     * @param userId2 one of those 2 users
     * @return boolean Returns true if the chat is deleted, false otherwise
     */
    public boolean RemoveChat(String userId1, String userId2){
        if(chatManager.chatExists(userId1, userId2)){
            chatManager.deleteChat(userId1, userId2);
            return true;
        }
        else{
            return false;
        }
        }

    /**
     * The start method of AdminSystem which can: 1.Delete Empty Event. 2.Delete Chat. 3.logout. 4.SHUTDOWN
     * @return object Returns different types based on the action taken
     * @throws IOException any errors that may occur while running
     */
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






    /**
     * Saves state for the system.
     * @throws IOException any errors that may occur while running
     */
    private void saveState() throws IOException {
        Serialization io = new Serialization();
        io.saveState(eventManager, "EventManager");
        io.saveState(chatManager, "ChatManager");
    }

}
