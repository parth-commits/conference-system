package UseCases;
import Entities.Chat;

import java.util.ArrayList;

public class ChatManager {
    private ArrayList<Chat> chats;

    public ChatManager(){
        chats = new ArrayList<>();
    }

    public void addMessageToChat(int sender, int recipient, String context){
        Chat chat = findChat(sender, recipient);
        chat.addMessages(sender, recipient, context);
    }

    private void createChat(int id1, int id2){
        chats.add(new Chat(id1, id2));
    }


    public Chat findChat(int id1, int id2){
        int temp;
        if (id1 > id2){
            temp = id2;
            id1 = id2;
            id2 = temp;
        }
        for (Chat chat : chats) {
            if ((chat.getId1() == id1) && (chat.getId2() == id2)) {
                return chat;
            }
        }
        //adds chat if does not exists
        createChat(id1, id2);
        return chats.get(chats.size()-1);
    }
}