// import neccessary libraries
import java.util.ArrayList;
import java.util.Objects;


public class Event {

    public static int id_counter;
    private int id;
    private String title;
    private String time;
    private String location;
    // private String locationID;
    private Speaker speaker;
    private ArrayList<Attendee> attendees = new ArrayList<Attendee>();
    private ArrayList<Organizer> organizers = new ArrayList<Organizer>();


    public Event(String title, String time, String location, Speaker speaker, Organizer organizer) {
        this.id = id_counter + 1;
        id_counter++;
        this.title = title;
        this.time = time;
        this.location = location;
        this.speaker = speaker;
        this.organizers.add(organizer);
    }

    // Getters

    public int getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getTime(){
        return this.time;
    }

    public String getLocation(){
        return this.location;
    }

    public Speaker getSpeaker(){
        return this.speaker;
    }

    public ArrayList<Attendee> getAttendees(){
        return this.attendees;
    }

    public ArrayList<Organizer> getOrganizers(){
        return this.organizers;
    }

    // add get Attendee using ID, index, name?

    // Setters

    public void setID(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setSpeaker(Speaker speaker){
        this.speaker = speaker;
    }

    public void setAttendees(ArrayList<Attendee> attendees){
        this.attendees = attendees;
    }

    public void setOrganizers(ArrayList<Organizer> organizers){
        this.organizers = organizers;
    }

    // Adding methods for attendees and organizers

    public void addAttendee(Attendee attendee){
        this.attendees.add(attendee);
    }


    public void addOrganizer(Organizer organizer){
        this.organizers.add(organizer);
    }

    // Removing methods for attendees

    public boolean removeAttendeeID(int index){
        // add to check if index within length
        if (index >= this.attendees.size()){
            return false;
        }
        Attendee x = this.attendees.remove(index);
        if (x != null){
            return true;
        }
        return false;
    }

    // shadows if an attendee object is passed in
    public boolean removeAttendee(Attendee attendee){
        for (int i = 0; i < this.attendees.size(); i++){
            if (Attendee.equals(this.attendees.get(i), attendee)){
                return this.removeAttendeeID(i);
            }
        }
        return false;
    }
    // shadows if an attendee id is passed in
    public boolean removeAttendee(int id){
        for (int i = 0; i < this.attendees.size(); i++){
            if (this.attendees.get(i).getId.equals(id)){
                return this.removeAttendeeID(i);
            }
        }
        return false;
    }

    // Removing methods for organizers

    public boolean removeOrganizerID(int index){
        // add to check if index within length
        if (index >= this.organizers.size()){
            return false;
        }
        Organizer x = this.organizers.remove(index);
        if (x != null){
            return true;
        }
        return false;
    }

    // shadows if an Organizer object is passed in
    public boolean removeorganizer(Organizer organizer){
        for (int i = 0; i < this.organizers.size(); i++){
            if (Organizer.equals(this.organizers.get(i), organizer)){
                return this.removeOrganizerID(i);
            }
        }
        return false;
    }
    // shadows if an Organizer id is passed in
    public boolean removeorganizer(int id){
        for (int i = 0; i < this.organizers.size(); i++){
            if (this.organizers.get(i).getId.equals(id)){
                return this.removeOrganizerID(i);
            }
        }
        return false;
    }
}

