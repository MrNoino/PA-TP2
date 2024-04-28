package tp2.controller;

import java.util.ArrayList;
import tp2.model.Notification;

/**
 * A class to manage notifications on the database
 */
public class ManageNotifications {

    private ArrayList<Notification> notifications;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageNotifications() {
        notifications = new ArrayList<Notification>();
    }
}
