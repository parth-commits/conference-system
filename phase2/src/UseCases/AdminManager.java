package UseCases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * The AdminManager class stores all admins in an arraylist, and implements various actions that
 * can be done for an attendee, including verifying login/ if admin exists/ , and getting a list of signed-up event and contacts.
 * @author Group_0112
 * @version 1.0
 * @since November 19th, 2020
 */
public class AdminManager implements Serializable {

    private Hashtable<String, Admin> tableOfAdmin;
    /**
     * Constructor that initializes a hashtable to contains all attendees.
     */
    public AdminManager(){tableOfAdmin = new Hashtable<>();}

    /**
     * Adds an admin to the hashtable of all admins.
     * @param user_id the id of user that we wish to add
     * @param username the name of the user that we wish to add
     * @param passwords the passwords of the user that we wish to add
     */
    public void addAdmin(String user_id, String username, String passwords){
        Admin newAdmin = new Admin(user_id, username, passwords);
        tableOfAdmin.put(newAdmin.getUser_id(), newAdmin);
    }

    /**
     * Verifies admin's login based on the inputted credentials. The user will be logged in if the
     * inputted information is correct.
     * @param inputUserId the user id entered by the user
     * @param inputUserPassword the password entered by the user
     * @return boolean returns true when the inputted credential is correct,
     * returns false otherwise
     */
    public boolean verifyLogIn(String inputUserId, String inputUserPassword){
        if (userExist(inputUserId)) {
            return tableOfAdmin.get(inputUserId).getPasswords().equals(inputUserPassword);
        }
        return false;
    }

    /**
     * Gets the arraylist of all admin ids.
     * @return ArrayList </String> that contains all admins ids.
     */
    public List<String> getAdminIDs(){
        return new ArrayList<String>(tableOfAdmin.keySet());
    }

    /**
     * Checks if a particular admin exists in the arraylist of admin.
     * @param userId the id of admin that we'd like to look into
     * @return boolean returns true if the admin already registered in the system,
     * returns false otherwise
     */
    public boolean adminExist(String userId){
        return tableOfAdmin.containsKey(userId);
    }

    /**
     * Returns a particular admin.
     * @param userId the id of user that we'd like to return
     * @return Admin
     * @see Admin
     */
    public Admin getAdmin(String userId){
        return tableOfAdmin.get(userId);
    }
}
