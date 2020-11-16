package UseCases;
import Entities.Event;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


public class EventManager implements Serializable{
    private ArrayList<Event> listOfEvents;

    public EventManager(){
        listOfEvents = new ArrayList<>();

    }

    public ArrayList<Event> getListOfEvents(){
        return listOfEvents;
    }

    public ArrayList<Integer> getListOfEventIDs(){
        ArrayList<Integer> listOfEventIDs = new ArrayList<>();
        for(Event event: listOfEvents){
            listOfEventIDs.add(event.getID());
        }
        return listOfEventIDs;
    }

    public Event getEvent(int eventId){
        for (Event e:listOfEvents){
            if (eventId == e.getID()){
                return e;
            }
        }
        return null;
    }

    public Date getTime(int eventID){
        return getEvent(eventID).getTime();
    }

    public ArrayList<ArrayList<String>> getListofEventsBySpeaker(String speakerID){
        ArrayList<ArrayList<String>> listofEventsbySpeaker = new ArrayList<>();
        for (Event listOfEvent : listOfEvents) {
            if (listOfEvent.getSpeakerID().equals(speakerID)) {
                ArrayList<String> eventInfo = new ArrayList<>();
                eventInfo.add(String.valueOf(listOfEvent.getID()));
                eventInfo.add(listOfEvent.getTitle());
                listofEventsbySpeaker.add(eventInfo);
            }
        }
        return listofEventsbySpeaker;
    }

    public int addEvent(String title, Date time, String location, String organizerID) {
        // check time (speaker??? room???)
        Event event = new Event(title, time, location, organizerID);
        listOfEvents.add(event);
        return event.getID();
    }

    public void cancelEvent(int id){
        int index = -1;
        for (int i = 0; i < listOfEvents.size(); i++){
            if (listOfEvents.get(i).getID() == id){
                index = i;
            }
        }
        listOfEvents.remove(index);
    }

    public boolean hasSpeaker(int eventID){
        for (Event event: listOfEvents){
            if (event.getID()==eventID){
                return !event.noSpeaker();
            }
        }
        return false;
    }
    public String getSpeakerID(int eventID){
        for (Event event: listOfEvents){
            if (event.getID()==eventID){
                return event.getSpeakerID();
            }
        }
        return null;
    }

    public void addAttendee(int eventID,String userID){
        for (Event e:listOfEvents){
            if (eventID == e.getID()){
                e.addAttendee(userID);
            }
        }
    }

    public ArrayList<String> getEventAttendees(int eventID){
        return getEvent(eventID).getAttendees();
    }

    public void removeAttendee(int eventID,String userID){
        for (Event e:listOfEvents){
            if (eventID == e.getID()){
                e.removeAttendee(userID);
            }
        }
    }

    public String getLocation(int eventID){
        return getEvent(eventID).getLocation();
    }

    public void setListOfEvents(ArrayList<Event> listOfEvents){
        this.listOfEvents.addAll(listOfEvents);
    }

    public void saveState() throws IOException {
        OutputStream file = new FileOutputStream("phase1/src/EventManager.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(this);
        output.close();
    }


    public EventManager importState() {
        try {
            InputStream file = new FileInputStream("phase1/src/EventManager.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            EventManager eventManager = (EventManager) input.readObject();
            input.close();
            return eventManager;

        } catch (ClassNotFoundException | IOException e) {
            return new EventManager();
        }
    }

    public ArrayList<Event> listOfEventsWithoutSpeaker(){       //gets a list of events that do not have speakers
        ArrayList<Event> newList = new ArrayList<>();
        for(Event event: listOfEvents){
            if (event.noSpeaker()){
                newList.add(event);
            }
        }
        return newList;
    }

}
