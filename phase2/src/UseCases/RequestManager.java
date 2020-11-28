package UseCases;

import Entities.Event;
import Entities.Request;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestManager implements Serializable {


    private ArrayList<Request> listOfRequests;

    public RequestManager(){
        listOfRequests = new ArrayList<>();
    }


    public void addRequest(int id, String user_id, String req){
        Request request = new Request( id,  user_id,  req);
        listOfRequests.add(request);
    }

    public void removeRequest(int id){
        listOfRequests.removeIf(request -> request.getId() == id);
    }

    /**
     *
     * @param id the id of the request
     * @param status the status of the request, will be 2 options: "Addressed" or "Pending"
     */
    public void changeStatus(int id, String status){
        for (Request request: listOfRequests){
            if (request.getId() == id){
                request.setStatus(status);
            }
        }
    }

    public ArrayList<Request> getListOfRequests() {
        return listOfRequests;
    }

    public ArrayList<Request> getListOfRequestsofAttendee(String userid) {
        ArrayList<Request> requestArrayList = new ArrayList<>();
        for (Request request: listOfRequests){
            if (request.getUser_id().equals(userid)){
                requestArrayList.add(request);
            }
        }
        return requestArrayList;
    }

    public boolean idIsAvailable(int id){
        for (Request request: listOfRequests){
            if (request.getId() == id){
                return false;
            }
        }
        return true;
    }
}
