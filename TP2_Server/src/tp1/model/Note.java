package tp1.model;

/**
 * A class that represents a note
 */
public class Note {
    private long id;
    private String description;
    private int page;
    private int paragraph;
    private String date;
    private long reviewId;

    /**
     * Class constructor that assigns the attributes
     * @param aId the note id
     * @param aDescription the note description
     * @param aPage the note page
     * @param aParagraph the note paragraph
     * @param aDate the note date
     * @param aReviewId the note review id
     */
    public Note(long aId, String aDescription, int aPage, int aParagraph, String aDate, long aReviewId) {
        this.id = aId;
        this.description = aDescription;
        this.page = aPage;
        this.paragraph = aParagraph;
        this.date = aDate;
        this.reviewId = aReviewId;
    }

    /**
     * Get the note id
     * @return the note id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the note id
     * @param id the note id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the note description
     * @return the note description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the note description
     * @param description the note description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the note page
     * @return the note page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the note page
     * @param page the note page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets the note paragraph
     * @return the note paragraph
     */
    public int getParagraph() {
        return paragraph;
    }

    /**
     * Sets the note paragraph
     * @param paragraph the note paragraph
     */
    public void setParagraph(int paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * Get the note local date
     * @return the note local date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the note date
     * @param date the note date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the note review id
     * @return the note review id
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * Sets the note review id
     * @param reviewId the note review id
     */
    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }
    
    
}
