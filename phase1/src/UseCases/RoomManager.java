package UseCases;
import Entities.Event;
import Entities.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class RoomManager implements Serializable{
    //dictionary of all rooms (key is the roomlocation)
    private Hashtable<String, Room> tableOfRooms;

    //constructor should create 5 rooms (different roomlocations)
    public RoomManager(){
        this.tableOfRooms = new Hashtable<>();
    }

    public void createRoom(String location){
        Room room = new Room(location);
        tableOfRooms.put(location, room);
    }
    public boolean locationIDAvailable(String location){
        return !tableOfRooms.containsKey(location);
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
        OutputStream file = new FileOutputStream("phase1/src/RoomManager.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(this);
        output.close();
    }


    public RoomManager importState() {
        try {
            InputStream file = new FileInputStream("phase1/src/RoomManager.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            RoomManager roomManager = (RoomManager) input.readObject();
            input.close();
            return roomManager;

        } catch (ClassNotFoundException | IOException e) {
            return new RoomManager();
        }
    }

    public ArrayList<String> getAllRoomLocs() {
        return new ArrayList<>(tableOfRooms.keySet());
    }
}
