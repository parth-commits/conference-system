package UseCases;
import Entities.Chat;

import java.util.ArrayList;

public class ChatManager {
    private ArrayList<Chat> chats;

    public ChatManager(){
        chats = new ArrayList<>();
    }

    // add confirmation when creating new chat instances?
    public void addMessageToChat(String sender, String recipient, String context){
        Chat chat = findChat(sender, recipient);
        chat.addMessages(sender, recipient, context);
    }

    private void createChat(String id1, String id2){
        chats.add(new Chat(id1, id2));
    }


    public Chat findChat(String id1, String id2){
//        int temp;
//        if (id1 > id2){
//            temp = id2;
//            id1 = id2;
//            id2 = temp;
//        }
        for (Chat chat : chats) {
            if ((chat.getId1().equals(id1)) && (chat.getId2().equals(id2))) {
                return chat;
            }
            else if ((chat.getId1().equals(id2)) && (chat.getId2().equals(id1))){
                return chat;
            }
        }
        //adds chat if does not exists
        createChat(id1, id2);
        return chats.get(chats.size()-1);
    }

    public boolean deleteChat(String id1, String id2){
        for (int i = 0; i < chats.size(); i++){
            if ((chats.get(i).getId1().equals(id1)) && (chats.get(i).getId2().equals(id2))) {
                chats.remove(i);
                return true;
            }
            else if ((chats.get(i).getId1().equals(id2)) && (chats.get(i).getId2().equals(id1))){
                chats.remove(i);
                return true;
            }
        }
        return false;
    }
}