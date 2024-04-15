package tp1.model;

/**
 * A class that representes a role notification
 */
public class RoleNotification {

    private long id;
    private boolean read;
    private String content;
    private String datetime;
    private int roleId;

    /**
     * Class constructor that assigns attributes
     *
     * @param aId the role notification id
     * @param aRead the role notification read status
     * @param aContent the role notification content
     * @param aDatetime the role notification date
     * @param aRoleId the role notification role id
     */
    public RoleNotification(long aId, boolean aRead, String aContent, String aDatetime, int aRoleId) {
        this.id = aId;
        this.read = aRead;
        this.content = aContent;
        this.datetime = aDatetime;
        this.roleId = aRoleId;
    }

    /**
     * Get the role notification id
     *
     * @return the role notification id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the role notification id
     *
     * @param id the role notification id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the role notification read status
     *
     * @return the role notification read status
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets the role notification read status
     *
     * @param read the role notification read status
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Get the role notification content
     *
     * @return the role notification content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the role notification content
     *
     * @param content the role notification content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the role notification date
     *
     * @return the role notification date
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the role notification date
     *
     * @param datetime the role notification date
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the role notification role id
     *
     * @return the role notification role id
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role notification role role id
     *
     * @param roleId the notification role role id
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
