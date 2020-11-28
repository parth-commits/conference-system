package Entities;

import java.io.Serializable;

public class Request implements Serializable {

    //the id of the request
    private int id;

    //the userid of the attendee who made the req
    private String user_id;

    //the req string
    private String req;

    //status of the req
    private String status;

    public Request( int id, String user_id, String req){
        this.id = id;
        this.user_id = user_id;
        this.req = req;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request ID: " + id + " | Attendee that made request: " + user_id + " | Request: " + req + " | Status: " + status;
    }
}
