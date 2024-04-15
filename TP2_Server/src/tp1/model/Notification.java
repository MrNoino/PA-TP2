package tp1.model;

/**
 * A class that represents a notification 
 */
public class Notification {

    private long id;
    private long userId;
    private boolean read;
    private String content;
    private String datetime;

    /**
     * Class constructor that assigns the attributes
     * 
     * @param aId id of the notification
     * @param aUserId target user id of the notification
     * @param aRead if hte notification was read
     * @param aContent content of the notification
     * @param aDatetime datetime of the notification
     */
    public Notification(long aId, long aUserId, boolean aRead, String aContent, String aDatetime) {
        this.id = aId;
        this.userId = aUserId;
        this.read = aRead;
        this.content = aContent;
        this.datetime = aDatetime;
    }

    /**
     * Gets the id of the notification
     * @return the id of the notification
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the notification
     * @param id id to be assigned
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the target user id of the notification
     * @return the id of the user
     */
    public long getUserId() {
        return userId;
    }
    
    /**
     * Sets the target user id of the notification
     * @param userId user id to be assigned
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Check if the notification was read
     * @return confirms if it was read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets if the notification was read
     * @param read assigns if it was read
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Gets the content of the notification
     * @return the content of the notification
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the notification
     * @param content content to be assigned
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the datetime of the notification
     * @return the datetime of the notification
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the datetime of the notification
     * @param datetime datetime to be assigned
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
