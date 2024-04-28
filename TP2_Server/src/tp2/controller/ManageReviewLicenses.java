package tp2.controller;

import java.util.ArrayList;
import tp2.model.ReviewLicense;

/**
 * A class to manage review license on the database
 */
public class ManageReviewLicenses {

    private ArrayList<ReviewLicense> reviewLicenses;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageReviewLicenses() {
        reviewLicenses = new ArrayList<ReviewLicense>();
    }
}
