package tp1.model;

/**
 * A class that represents a Role
 */
public class Role {

    private int id;
    private String role;

    /**
     * Class constructor that assigns attributes
     *
     * @param aId the role id
     * @param aRole the role role
     */
    public Role(int aId, String aRole) {
        this.id = aId;
        this.role = aRole;
    }

    /**
     * Get the role id
     *
     * @return the role id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the role id
     *
     * @param id the role id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the role role
     *
     * @return the role role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role role
     *
     * @param role the role role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
