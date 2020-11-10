package UseCases;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;

import java.util.Hashtable;

public class UserManager {

    //A dictionary of all the attendees
    private Hashtable<String, Attendee> tableOfAttendees;

    //A dictionary of all the speakers
    private Hashtable<String, Speaker> tableOfSpeakers;

    //A dictionary of all the organizers
    private Hashtable<String, Organizer> tableOfOrganizers;

    public UserManager(){

    }

}
