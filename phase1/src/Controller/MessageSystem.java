package Controller;

public class MessageSystem {
    private ChatManager chatManager;

    public MessageSystem (){
        this.chatManager = new ChatManager();
    }

    public Chat getChat(String id1, String id2){
        return chatManager.findChat(id1, id2);
    }

    public void sendMessage(String sender, String recipient, String context){
        chatManager.addMessageToChat(sender, recipient, context)
    }

    public boolean deleteChat(String id1, String id2){
        return chatManager.deleteChat(id1, id2);
    }

}
