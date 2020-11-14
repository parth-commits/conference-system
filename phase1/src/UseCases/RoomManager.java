package UseCases;
import Entities.Event;
import Entities.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class RoomManager {
    //dictionary of all rooms (key is the roomlocation)
    private Hashtable<String, Room> tableOfRooms;

    //constructor should create 5 rooms (different roomlocations)
    public RoomManager(){
        Room r1 = new Room("room1");
        tableOfRooms.put("room1", r1);
        Room r2 = new Room("room2");
        tableOfRooms.put("room2", r2);
        Room r3 = new Room("room3");
        tableOfRooms.put("room3", r3);
        Room r4 = new Room("room4");
        tableOfRooms.put("room4", r4);
        Room r5 = new Room("room5");
        tableOfRooms.put("room5", r5);
    }

    //room manager should be able to pull up a list of rooms that are free at a given time
    public ArrayList<String> getAvailableRooms(Date date){
        ArrayList<String> availableRooms = new ArrayList<String>();
        for (Room roomValue: tableOfRooms.values()){
            if (!roomValue.isBooked(date)){
                availableRooms.add(roomValue.getRoomLocation());
            }
        }
        return availableRooms;
    }

    //add an event to a given room at a given time (takes in room location, eventid and date)
    public void addEventToRoom(String roomLocation, Integer eventID, Date date){
        tableOfRooms.get(roomLocation).addBookedTime(date, eventID);
    }

    //remove an event from a room at a given time (takes in room location, eventid and date)
    public void removeEventFromRoom(String roomLocation, Integer eventID, Date date){
        tableOfRooms.get(roomLocation).removeBookedTime(date, eventID);
    }

    public Room getRoom(String roomloc){
        for (String loc : tableOfRooms.keySet()){
            if (loc.equals(roomloc)){
                return tableOfRooms.get(loc);
            }
        }
        return null;
    }

    public int getSpots(String roomloc){
        Room r = getRoom(roomloc);
        return r.getCapacity();
    }

    public void saveState() throws IOException{
        OutputStream file = new FileOutputStream("src/RoomManager.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(this);
        output.close();
    }


    public RoomManager importState() {
        try {
            InputStream file = new FileInputStream("src/RoomManager.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            RoomManager roomManager = (RoomManager) input.readObject();
            input.close();
            return roomManager;

        } catch (ClassNotFoundException | IOException e) {
            return new RoomManager();
        }
    }
}
