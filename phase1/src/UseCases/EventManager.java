package UseCases;
import Entities.Event;
import java.util.ArrayList;

public class EventManager {
    private ArrayList<Event> listOfEvents;

    public EventManager(){
        listOfEvents = new ArrayList<>();
    }

    public ArrayList<Event> getListOfEvents(){
        return listOfEvents;
    }

    public void addEvent(String title, String time, String location, String speakerID, String organizerID) {
        // check time (speaker??? room???)
        for (Event e:listOfEvents){
            if (e.getTime().equals(time)){
                return;
            }
        }
        Event event;
        event = new Event(title, time, location, speakerID, organizerID);
        listOfEvents.add(event);
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

    public void addAttendee(int eventID,String userID){
        for (Event e:listOfEvents){
            if (eventID == e.getID()){
                e.addAttendee(userID);
            }
        }
    }

    public void removeAttendee(int eventID,String userID){
        for (Event e:listOfEvents){
            if (eventID == e.getID()){
                e.removeAttendee(userID);
            }
        }
    }

    public void setListOfEvents(ArrayList<Event> listOfEvents){
        this.listOfEvents.addAll(listOfEvents);
    }
}
