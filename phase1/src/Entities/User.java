package Entities;

import java.util.ArrayList;

public abstract class User {

    /* this is an abstract class as all types of people are users, and have the following basic properties*/

    //userid of the user
    protected String user_id;

    //password of the user
    protected String password;

    //name of the user
    protected String name;

    //list of contacts of the user
    private ArrayList<String> contacts;

    public User(String user_id, String name, String password) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.contacts = new ArrayList<>();
    }

    //returns the user id of the person
    public String getUser_id() {
        return user_id;
    }

    //returns the password of the person
    public String getPasswords() {
        return password;
    }

    //returns the name of the person
    public String getName() {
        return name;
    }

    //sets the name of the person
    public void setName(String name) {
        this.name = name;
    }

    //sets the password of the person
    public void setPasswords(String password) {
        this.password = password;
    }

    //gets the list of contacts of this user
    public ArrayList<String> getContacts() {
        return contacts;
    }

    //adds contact by id to the users list of contacts
    public void addContact(String user_id){
        this.contacts.add(user_id);
    }

    //checks if contact already exists
    public boolean checkContact(String user_id){
        return this.contacts.contains(user_id);
    }

    //removes the contact by id from the users list of contacts. returns true if removing was successful, false otherwise
    public boolean removeContact(String user_id){
        return this.contacts.remove(user_id);
    }
}