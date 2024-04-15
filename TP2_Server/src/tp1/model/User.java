package tp1.model;

/**
 * A class that represents an User
 */
public class User {

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean active;
    private boolean deleted;
    private int roleId;

    /**
     * Class constutor that assigns the attributes
     *
     * @param aId id of the user
     * @param aName name of the user
     * @param aUsername username of the user
     * @param aPassword password of the user
     * @param aEmail email of the user
     * @param aActive if user is active
     * @param aDeleted if user was deleted
     * @param aRoleId role id of the user
     */
    public User(long aId, String aName, String aUsername, String aPassword, String aEmail, boolean aActive, boolean aDeleted, int aRoleId) {
        this.id = aId;
        this.name = aName;
        this.username = aUsername;
        this.password = aPassword;
        this.email = aEmail;
        this.active = aActive;
        this.deleted = aDeleted;
        this.roleId = aRoleId;
    }

    /**
     * Class constutor that assigns the attributes except password
     *
     * @param aId id of the user
     * @param aName name of the user
     * @param aUsername username of the user
     * @param aEmail email of the user
     * @param aActive if user is active
     * @param aDeleted if user was deleted
     * @param aRoleId role id of the user
     */
    public User(long aId, String aName, String aUsername, String aEmail, boolean aActive, boolean aDeleted, int aRoleId) {
        this.id = aId;
        this.name = aName;
        this.username = aUsername;
        this.password = null;
        this.email = aEmail;
        this.active = aActive;
        this.deleted = aDeleted;
        this.roleId = aRoleId;
    }

    /**
     * Get the user id
     *
     * @return the user id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user id
     *
     * @param id the user id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the user name
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user name
     *
     * @param name the user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user username
     *
     * @return the user username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user username
     *
     * @param username username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user email
     *
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email
     *
     * @param email the user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get if the user is active
     *
     * @return confirms if the user is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets if the user is active
     *
     * @param active the active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get the user deleted status
     *
     * @return the user deleted status
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the user deleted status
     *
     * @param deleted the user deleted status
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Get the user role id
     *
     * @return the user role id
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the user role id
     *
     * @param roleId the user role id
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the user password
     *
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user password
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
