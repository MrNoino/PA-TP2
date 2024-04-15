package tp1.model;

/**
 * A class that represents a Review
 */
public class Review {
    private long id;
    private int randomCode;
    private String serialNumber;
    private String submissionDate;
    private String completionDate;
    private int elapsedTime;
    private String observations;
    private float cost;
    private Book book;
    private long authorId;
    private long managerId;
    private long reviewerId;
    private String status;

    /**
     * Class constructor that assigns the attributes
     * @param aId id of the review
     * @param aRandomCode random code
     * @param aSerialNumber serial number of the review
     * @param aSubmissionDate submission date of hte review
     * @param aCompletionDate completion date of the review
     * @param aElapsedTime elapsed time to complete the review
     * @param aObservations observations
     * @param aCost cost of the review
     * @param aBook the book associated to the review
     * @param aAuthorId the author id associated to the review
     * @param aManagerId the manager id associated to the review
     * @param aReviewerId the reviewer id associated to the review
     * @param aStatus status of the review
     */
    public Review(long aId, int aRandomCode, String aSerialNumber, String aSubmissionDate, String aCompletionDate, int aElapsedTime, String aObservations, float aCost, Book aBook, long aAuthorId, long aManagerId, long aReviewerId, String aStatus) {
        this.id = aId;
        this.randomCode = aRandomCode;
        this.serialNumber = aSerialNumber;
        this.submissionDate = aSubmissionDate;
        this.completionDate = aCompletionDate;
        this.elapsedTime = aElapsedTime;
        this.observations = aObservations;
        this.cost = aCost;
        this.book = aBook;
        this.authorId = aAuthorId;
        this.managerId = aManagerId;
        this.reviewerId = aReviewerId;
        this.status = aStatus;
    }

    /**
     * Get the review id
     * @return the id of the review
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the review id
     * @param id id to be assign
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the random code
     * @return the random code of the review
     */
    public int getRandomCode() {
        return randomCode;
    }

    /**
     * Sets the random code
     * @param randomCode random code to be assigned
     */
    public void setRandomCode(int randomCode) {
        this.randomCode = randomCode;
    }
    
    /**
     * Get the serial number of the review
     * @return the serial number of the review
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serial number
     * @param serialNumber serial number to be assigned
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Get the submission date of the review
     * @return the submission date of the review
     */
    public String getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Sets the submission date
     * @param submissionDate submission date to be assigned
     */
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * Get the completion date of the review
     * @return the completion date of the review
     */
    public String getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets the completion date
     * @param completionDate completion date to be assigned
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Get the elapsed time of the review
     * @return the elapsed time of the review
     */
    public int getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time
     * @param elapsedTime elapsed time to be assigned
     */
    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Get the observations of the review
     * @return the observations of the review
     */
    public String getObservations() {
        return observations;
    }

    /**
     * Sets the observations
     * @param observations observations to be assigned
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    /**
     * Get the cost of the review
     * @return the cost of the review
     */
    public float getCost() {
        return cost;
    }

    /**
     * Sets the cost
     * @param cost cost to be assigned
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Get the book of the review
     * @return the book of the review
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the books
     * @param book book to be assigned
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get the author id of the review
     * @return the author id of the review
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * Sets the author id
     * @param authorId author id to be assigned
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Get the manager id of the review
     * @return the manager id of the review
     */
    public long getManagerId() {
        return managerId;
    }

    /**
     * Sets the manager id
     * @param managerId manager id to be assigned
     */
    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    /**
     * Get the reviewer id of the review
     * @return the reviewer id of the review
     */
    public long getReviewerId() {
        return reviewerId;
    }

    /**
     * Sets the reviewer id
     * @param reviewerId reviewer id to be assigned
     */
    public void setReviewerId(long reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * Get the status of the review
     * @return the status of the review
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status
     * @param status status to be assigned
     */
    public void setStatus(String status) {
        this.status = status;
    } 
}