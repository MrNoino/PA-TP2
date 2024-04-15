package tp1.model;

/**
 * A class that represents a Manager
 */
public class Manager extends User{

    /**
     * Class constructor that assigns the attributes
     * @param aId id of the manager
     * @param aName name of the manager
     * @param aUsername username of the manager
     * @param aPassword password of the manager
     * @param aEmail email of the manager
     * @param aActive if manager is active
     * @param aDeleted if manager was deleted
     * @param aRoleId role id of the manager
     */
    public Manager(long aId, String aName, String aUsername, String aPassword, String aEmail, boolean aActive, boolean aDeleted, int aRoleId) {
        super(aId, aName, aUsername, aPassword, aEmail, aActive, aDeleted, aRoleId);
    }
    
    /**
     * Class constructor that assigns the attributes except password
     * @param aId id of the manager
     * @param aName name of the manager
     * @param aUsername username of the manager
     * @param aEmail email of the manager
     * @param aActive if manager is active
     * @param aDeleted if manager was deleted
     * @param aRoleId role id of the manager
     */
    public Manager(long aId, String aName, String aUsername, String aEmail, boolean aActive, boolean aDeleted, int aRoleId) {
        this(aId, aName, aUsername, null, aEmail, aActive, aDeleted, aRoleId);
    }    
    
}
