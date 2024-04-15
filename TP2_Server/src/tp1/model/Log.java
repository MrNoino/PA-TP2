package tp1.model;

/**
 * A class that represents a log
 */
public class Log {
    private long id;
    private long userId;
    private String datetime;
    private String action;

    /**
     * Class constructor that assigns the attributes
     * @param aId the log id
     * @param aUserId the log user id
     * @param aDatetime the log date
     * @param aAction the log action
     */
    public Log(long aId, long aUserId, String aDatetime, String aAction) {
        this.id = aId;
        this.userId = aUserId;
        this.datetime = aDatetime;
        this.action = aAction;
    }
    
    /**
     * Class constructor that assigns the attributes besides the id
     * @param aUserId the log user id
     * @param aDatetime the log date
     * @param aAction the log action
     */
    public Log(long aUserId, String aDatetime, String aAction) {
        this.id = -1;
        this.userId = aUserId;
        this.datetime = aDatetime;
        this.action = aAction;
    }

    /**
     * Get the log id 
     * @return the log id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the log id
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Get the log user id
     * @return the log user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the log user id
     * @param userId the log user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Get the log date
     * @return the log date
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the log date
     * @param datetime the log date
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the log action
     * @return the log action
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the log action
     * @param action the log action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
