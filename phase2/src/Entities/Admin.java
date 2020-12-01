package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  The Admin program implements an application that simply records admins.
 *  * @author Group_0112
 *  * @version 1.0
 *  * @since November 16th, 2020
 */
public class Admin extends User implements Serializable{

    /**
     * Constructor
     * @param user_id the user id of this user. User id is an unique integer for each user.
     * @param name    the registered name of this user.
     * @param passwords the registered passwords of this user.
     */
    public Admin(String user_id, String name, String passwords){
        super(user_id, name, passwords);
    }

}

