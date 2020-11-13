package UseCases;
import Entities.Chat;

import java.util.ArrayList;

public class ChatManager {

    //list of chats. so this will be a 2 dimensional list
    private ArrayList<Chat> chats;

    //constructor
    public ChatManager(){
        chats = new ArrayList<>();
    }

    // add confirmation when creating new chat instances?
    public void addMessageToChat(String sender, String recipient, String context){
        if (chatExists(sender, recipient)) {
            Chat chat = findChat(sender, recipient);
            chat.addMessages(sender, recipient, context);
        }
    }

    //creates a new chat between 2 people
    public void createChat(String id1, String id2){
        if (!chatExists(id1, id2)) {
            chats.add(new Chat(id1, id2));
        }
    }

    //returns true if chat exists, false otherwise
    public boolean chatExists(String id1, String id2){
        for (Chat chat : chats) {
            if ((chat.getId1().equals(id1)) && (chat.getId2().equals(id2))) {
                return true;
            }
            else if ((chat.getId1().equals(id2)) && (chat.getId2().equals(id1))){
                return true;
            }
        }
        return false;
    }

    //given 2 user's ids, find the chat between them and returns them
    public Chat findChat(String id1, String id2){

        for (Chat chat : chats) {
            if ((chat.getId1().equals(id1)) && (chat.getId2().equals(id2))) {
                return chat;
            }
            else if ((chat.getId1().equals(id2)) && (chat.getId2().equals(id1))){
                return chat;
            }
        }
        return null;
    }

    //given a single userid, find all the people this person has a chat with and returns them in a list
    public ArrayList<String> getContactsWithChat(String id1){
        ArrayList<String> listOfChatsPeople = new ArrayList<>();
        for (Chat chat : chats) {
            if (chat.getId1().equals(id1)) {
                listOfChatsPeople.add(chat.getId2());
            }
            else if (chat.getId2().equals(id1)){
                listOfChatsPeople.add(chat.getId1());
            }
        }
        return listOfChatsPeople;
    }

    //given 2 userids, deletes the chat between them
    public void deleteChat(String id1, String id2){
        for (int i = 0; i<chats.size();i++){
            if (chats.get(i).getId1().equals(id1)&&chats.get(i).getId2().equals(id2)){
                chats.remove(i);
                return;
            }
            else if (chats.get(i).getId1().equals(id2)&&chats.get(i).getId2().equals(id1)){
                chats.remove(i);
                return;
            }
        }
    }
}