package tp1.model;

/**
 * A class that represents a License 
 */
public class License {
    private int id;
    private String designation;
    private String expireDate;
    private int quantity;

    /**
     * Class constructor that assigns the attributes
     * @param aId the license id
     * @param aDesignation the license designation
     * @param aExpireDate the license expire date
     * @param aQuantity the license uses left
     */
    public License(int aId, String aDesignation, String aExpireDate, int aQuantity) {
        this.id = aId;
        this.designation = aDesignation;
        this.expireDate = aExpireDate;
        this.quantity = aQuantity;
    }

    /**
     * Gets the license id
     * @return the license id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the license id
     * @param id the license id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the license designation
     * @return the license id
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the license designation
     * @param designation the license designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Get the license expire date
     * @return the license expire date
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the license expire date
     * @param expireDate the license expire date
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Gets the license uses left
     * @return the license uses left
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the license uses left
     * @param quantity the license uses left 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
