package tp2.controller;

import java.util.ArrayList;
import tp2.model.Note;

/**
 * A class to manage notes on the database
 */
public class ManageNotes {

    private ArrayList<Note> notes;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageNotes() {
        notes = new ArrayList<Note>();
    }
}
