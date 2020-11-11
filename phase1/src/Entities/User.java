package Entities;

import java.util.ArrayList;

public abstract class User {

    protected String user_id;
    protected String passwords;
    protected String name;

    public User(String user_id, String name, String passwords) {
        this.user_id = user_id;
        this.name = name;
        this.passwords = passwords;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPasswords() {
        return passwords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public abstract void addContact(String currentUserId);

    public abstract boolean removeContact(String currentUserId);
}