package tp2.controller;

import java.util.ArrayList;
import tp2.model.RoleNotification;

/**
 * A class to manage role notifications on the database
 */
public class ManageRoleNotifications {

    private ArrayList<RoleNotification> roleNotifications;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageRoleNotifications() {
        roleNotifications = new ArrayList<RoleNotification>();
    }
}
