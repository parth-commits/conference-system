package Controller;
import Entities.Chat;
import Gateway.KeyboardInput;
import Presenter.TextPresenter;
import UseCases.ChatManager;

import UseCases.*;

import java.util.ArrayList;

public class MessageSystem {
    private TextPresenter output;
    private KeyboardInput input;
    private ChatManager chatManager;

    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private AttendeeManager attendeeManager;


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
        //get the chat between two users
        return chatManager.findChat(id1, id2);
    }

    /*public void sendMessage(String sender, String recipient, String context){
        int role = userType(sender);
        output.sendMsgOptions(role);
        if (role == 1){
            // unfinished Ray
        }
        chatManager.addMessageToChat(sender, recipient, context);
    }*/

    public void sendMessage(String sender) {
        // simplify it by separating common methods?  send message
        String recipient;
        String context;
        int role = userType(sender);
        // Organizer
        if (role == 1) {
            output.sendMsgOptions(role);
            String action = input.getKeyboardInput();

            while (!(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4") || action.equals("5") || action.equals("6"))) {
                // may need to double check if user wants to quit
                output.msgOptionInvalid();
                action = input.getKeyboardInput();
            }
            // send to all speakers, organizers, attendees
            if (action.equals("1") || action.equals("3") || action.equals("5")) {
                // prompt context
                output.promptContext();
                context = input.getKeyboardInput();
                // add message
                ArrayList<String> ids;
                if (action.equals("1")) {
                    ids = speakerManager.getUserIDs();
                } else if (action.equals("3")) {
                    ids = organizerManager.getUserIDs();
                } else {
                    ids = attendeeManager.getUserIDs();
                }
                for (String id : ids) {
                    if (!(chatManager.chatExists(sender, id))) {
                        chatManager.createChat(sender, id);
                        organizerManager.addContact(sender, id);
                        int usertype = userType(id);
                        if (usertype == 1) {
                            organizerManager.addContact(id, sender);
                        } else if (usertype == 2) {
                            attendeeManager.addContact(id, sender);
                        } else {
                            speakerManager.addContact(id, sender);
                        }
                    }
                    chatManager.addMessageToChat(sender, id, context);
                }
            }
            // send to one user in contact list
            else {
                ArrayList<String> contactList = organizerManager.contactList(sender);
                if (contactList.size() == 0) {
                    output.youHaveNoContacts();
                    return;
                }
                //shows user their contact list
                output.promptRecipient(contactList, false);
                //tells them to choose 1 contact
                int personNumber;
                try {
                    personNumber = Integer.parseInt(input.getKeyboardInput());
                } catch (NumberFormatException e) {
                    personNumber = -1;
                }
                while (!(0 <= personNumber && personNumber <= contactList.size())) {
                    output.promptRecipient(contactList, true);
                    try {
                        personNumber = Integer.parseInt(input.getKeyboardInput());
                    } catch (NumberFormatException e) {
                        personNumber = -1;
                    }
                }
                if (personNumber == 0) {
                    return;
                }
                String contactID = contactList.get(personNumber - 1);
                Chat conversation = getChat(sender, contactID);
                //prints the chat of the user
                output.printChat(conversation);
                //ask user to type a message
                output.promptContext();
                context = input.getKeyboardInput();
                if (context.equals("return")) {
                    return;
                }
                // add message
                chatManager.addMessageToChat(sender, contactID, context);
            }
        }
        // Attendee
        else if (role == 2) {
            ArrayList<String> contactList = attendeeManager.contactList(sender);
            if (contactList.size() == 0) {
                output.youHaveNoContacts();
                return;
            }
            //shows user their contact list
            output.promptRecipient(contactList, false);
            //tells them to choose 1 contact
            int personNumber;
            try {
                personNumber = Integer.parseInt(input.getKeyboardInput());
            } catch (NumberFormatException e) {
                personNumber = -1;
            }
            while (!(0 <= personNumber && personNumber <= contactList.size())) {
                output.promptRecipient(contactList, true);
                try {
                    personNumber = Integer.parseInt(input.getKeyboardInput());
                } catch (NumberFormatException e) {
                    personNumber = -1;
                }
            }
            if (personNumber == 0) {
                return;
            }
            String contactID = contactList.get(personNumber - 1);
            Chat conversation = getChat(sender, contactID);
            //prints the chat of the user
            output.printChat(conversation);
            //ask user to type a message
            output.promptContext();
            context = input.getKeyboardInput();
            if (context.equals("return")) {
                return;
            }
            // add message
            chatManager.addMessageToChat(sender, contactID, context);
            /*while ( !(action.equals("1")||action.equals("2")) ){
                output.msgOptionInvalid();
                action = input.getKeyboardInput();
            }
            // one attendee
            if (action.equals("1")){
                // prompt user for recipient
                // check if chat exists
                output.promptRecipient();
                // maybe display all contacts???
                recipient = input.getKeyboardInput();
                // check recipient is attendee
                if (!(attendeeManager.userExist(recipient))){
                    output.invalidRecipient();
                    return;
                }
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
            // one speaker
            else if (action.equals("2")){
                // prompt user for recipient
                // check if chat exists
                output.promptRecipient();
                recipient = input.getKeyboardInput();
                // check recipient is speaker
                // check if attendee is signed up to the event???
                if (!(speakerManager.userExist(recipient))){
                    output.invalidRecipient();
                    return;
                }
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
            }*/
        }
        // Speaker
        else if (role == 3) {
            //speaker CURRENTLY can only send an automatic message to all the users in 1 of his talks

            // Select an event
            ArrayList<ArrayList<String>> eventIDsandTitle = eventManager.getListofEventsBySpeaker(sender);
            if (eventIDsandTitle.size() == 0) {
                output.youHaveNoEvents();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("couldnt not sleep thread");
                }
            }
            output.promptEvents(eventIDsandTitle, false);
            //String getInput = input.getKeyboardInput();

            String getInput = input.getKeyboardInput();
            boolean validInput = false;
            int eventIDChoosen = -1;// will guarantee change, -1 is a place holder
            for (ArrayList<String> stringArrayList : eventIDsandTitle) {
                if (stringArrayList.get(0).equals(getInput)) {
                    validInput = true;
                    eventIDChoosen = Integer.parseInt(getInput);
                    break;
                }
            }

            while (!validInput) {
                output.promptEvents(eventIDsandTitle, true);
                getInput = input.getKeyboardInput();
                for (ArrayList<String> strings : eventIDsandTitle) {
                    if (strings.get(0).equals(getInput)) {
                        validInput = true;
                        eventIDChoosen = Integer.parseInt(getInput);
                        break;
                    }
                }
            }
            output.promptContextEvent(eventManager.getEvent(eventIDChoosen).getTitle());
            context = input.getKeyboardInput();

            ArrayList<String> attendeesOfEvent = eventManager.getEventAttendees(eventIDChoosen);
            for (String id : attendeesOfEvent) {
                if (!(chatManager.chatExists(sender, id))) {
                    chatManager.createChat(sender, id);
                    speakerManager.addContact(sender, id);
                    int usertype = userType(id);
                    if (usertype == 1) {
                        organizerManager.addContact(id, sender);
                    } else if (usertype == 2) {
                        attendeeManager.addContact(id, sender);
                    } else {
                        speakerManager.addContact(id, sender);
                    }
                }
                chatManager.addMessageToChat(sender, id, context);
            }
        }
    }
    public void viewContacts(String id){
        //view all contacts of user
        chatManager.getContactsWithChat(id);
    }

    /* pretty sure we dont need the following function, but its here if we do in the future.
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
    }*/


    // Do we need delete chat?
    /*public boolean deleteChat(String id1, String id2){
        return chatManager.deleteChat(id1, id2);
    }*/

    public int userType(String id){
        // check current user status
        // 1 for attendee, 2 for speaker, 3 for organizer
        if (attendeeManager.userExist(id)){
            return 1;
        }
        else if(speakerManager.userExist(id)){
            return 2;
        }
        else if (organizerManager.userExist(id)){
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
