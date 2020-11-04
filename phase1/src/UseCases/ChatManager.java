package UseCases;
import java.util.ArrayList;

public class ChatManager {
    private List<Chat> chats;

    public ChatManager(){
        chats = new ArrayList<Chat>();
    }

    public void addMessageToChat(int sender, int recipient, String context){
        chat = findChat(sender, recipient);
        chat.addMessages(sender, recipient, context);
    }

    private void createChat(int id1, int id2){
        chats.add(Chat(id1, id2));
    }


    public Chat findChat(int id1, int id2){
        int temp = 0;
        if (id1 > id2){
            temp = id2;
            id1 = id2;
            id2 = temp;
        }
        for(int i = 0; i<len(chats); i++){
            if (chats[i].getId1() == id1) && (chats[i].getId2() == id2))
                    return chats.get(i);
        }
        //adds chat if does not exists
        createChat(id1, id2);
        return chats.get(chats.size()-1);
    }
}