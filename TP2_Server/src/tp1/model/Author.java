package tp1.model;

/**
 * A class that represents an Author
 */
public class Author extends User{
    private String nif;
    private String phone;
    private String address;
    private String activityBeginDate;
    private int literaryStyleId;

    /**
     * Class constructor that assigns the attributes
     * @param aId id of the author
     * @param aName name of the author
     * @param aUsername username of the author
     * @param aPassword password of the author
     * @param aEmail email of the author
     * @param aActive if author is active
     * @param aDeleted if author was deleted
     * @param aRoleId role id of the author
     * @param aNif NIF of the author
     * @param aPhone phone number of the author
     * @param aAddress address of the author
     * @param aActivityBeginDate date of the begin activity of the author
     * @param aLiteraryStyleId literacy style id of the author
     */
    public Author(long aId, String aName, String aUsername, String aPassword, String aEmail, boolean aActive, boolean aDeleted, int aRoleId, String aNif, String aPhone, String aAddress, String aActivityBeginDate, int aLiteraryStyleId) {
        super(aId, aName, aUsername, aPassword, aEmail, aActive, aDeleted, aRoleId);
        this.nif = aNif;
        this.phone = aPhone;
        this.address = aAddress;
        this.activityBeginDate = aActivityBeginDate;
        this.literaryStyleId = aLiteraryStyleId;
    }
    
    /**
     * Class constructor that assigns the attributes except password
     * @param aId id of the author
     * @param aName name of the author
     * @param aUsername username of the author
     * @param aEmail email of the author
     * @param aActive if author is active
     * @param aDeleted if author was deleted
     * @param aRoleId role id of the author
     * @param aNif NIF of the author
     * @param aPhone phone number of the author
     * @param aAddress address of the author
     * @param aActivityBeginDate date of the begin activity of the author
     * @param aLiteraryStyleId literacy style id of the author
     */
    public Author(long aId, String aName, String aUsername, String aEmail, boolean aActive, boolean aDeleted, int aRoleId, String aNif, String aPhone, String aAddress, String aActivityBeginDate, int aLiteraryStyleId) {
        this(aId, aName, aUsername, null, aEmail, aActive, aDeleted, aRoleId, aNif, aPhone, aAddress, aActivityBeginDate, aLiteraryStyleId);
    }

    /**
     * Gets the NIF
     * @return The NIF
     */
    public String getNif() {
        return nif;
    }

    /**
     * Sets the the author NIF
     * @param nif the nif of the author
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Gets the author phone number
     * @return The phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number
     * @param phone The phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the address
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the activity begin date
     * @return The activity begin date
     */
    public String getActivityBeginDate() {
        return activityBeginDate;
    }

    /**
     * Sets the activity begin date
     * @param activityBeginDate The activity begin date
     */
    public void setActivityBeginDate(String activityBeginDate) {
        this.activityBeginDate = activityBeginDate;
    }

    /**
     * Gets the literacy style id
     * @return The literacy style id
     */
    public int getLiteraryStyleId() {
        return this.literaryStyleId;
    }

    /**
     * Sets the literacy style id
     * @param literaryStyleId The literacy style id
     */
    public void setLiteraryStyleId(int literaryStyleId) {
        this.literaryStyleId = literaryStyleId;
    }
}
