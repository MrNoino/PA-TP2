package tp1.model;

/**
 * A class that represents a review license
 */
public class ReviewLicense {
    private int licenseId;
    private long reviewId;

    /**
     * Class constructor that assigns the attributes
     * @param aLicenseId the license id 
     * @param aReviewId  the review id
     */
    public ReviewLicense(int aLicenseId, long aReviewId) {
        this.licenseId = aLicenseId;
        this.reviewId = aReviewId;
    }

    /**
     * Get the license id
     * @return the license id
     */
    public int getLicenseId() {
        return licenseId;
    }

    /**
     * Sets the license id
     * @param licenseId the license id
     */
    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    /**
     * Get the review id
     * @return the review id
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * Sets the review id
     * @param reviewId the review id
     */
    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }
    
    
}
