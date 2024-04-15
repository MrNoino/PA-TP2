package tp1.model;

/**
 * A class that represents a literacy style
 */
public class LiteraryStyle {
    private int id;
    private String literaryStyle;

    /**
     * Class constructor that assigns the attributes
     * @param aId the literacy style id
     * @param aLiteraryStyle the literacy style
     */
    public LiteraryStyle(int aId, String aLiteraryStyle) {
        this.id = aId;
        this.literaryStyle = aLiteraryStyle;
    }

    /**
     * Get the literacy style id
     * @return the literacy style id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the literacy style id
     * @param id the literacy style id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the literacy style
     * @return the literacy style
     */
    public String getLiteraryStyle() {
        return literaryStyle;
    }

    /**
     * Sets the literacy style
     * @param literaryStyle the literacy style
     */
    public void setLiteraryStyle(String literaryStyle) {
        this.literaryStyle = literaryStyle;
    }
}
