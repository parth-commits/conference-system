package Controller;
import Entities.Chat;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.ChatManager;

public class MessageSystem {
    private ChatManager chatManager;
    private TextPresenter output;
    private KeyboardInput input;


    public MessageSystem (){
        this.chatManager = new ChatManager();
        this.output = new TextPresenter();
        this.input = new KeyboardInput();
    }

    public Chat getChat(String id1, String id2){
        //get the chat between two users
        return chatManager.findChat(id1, id2);
    }

    public void viewContacts(String id){
        //view all contacts of user
        chatManager.getContactsWithChat(id);
    }

    public void viewChat(String id1){
        String id2;
        // prompt user for recipient
        output.promptRecipient();
        id2 = input.getKeyboardInput();
        // check if chat exists
        // if not -> break
        if (chatManager.findChat(id1, id2) == null){
            output.chatDNE();
        }
        // exists -> print
        // print chat
        else{
            Chat conversation = getChat(id1, id2);
            output.printChat(conversation);
        }
    }

    public void sendMessage(String sender){
        String recipient;
        String context;
        // prompt user for recipient
        // check if chat exists
        output.promptRecipient();
        recipient = input.getKeyboardInput();
        if (chatManager.findChat(sender, recipient) == null){
            //prompt create chat confirmation
            output.confirmCreateChat(recipient);
            if (input.getKeyboardInput().equals("y")){
                chatManager.createChat(sender, recipient);
            }
            else{return; }
        }
        // prompt context
        output.promptContext();
        context = input.getKeyboardInput();
        // add message
        chatManager.addMessageToChat(sender, recipient, context);
    }

    // Do we need delete chat?
    public boolean deleteChat(String id1, String id2){
        return chatManager.deleteChat(id1, id2);
    }
}
