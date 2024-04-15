package tp1.model;
/**
 * A class that represents a book
 */
public class Book {
    private long id;
    private String title;
    private String subtitle;
    private int pages;
    private int words;
    private String isbn;
    private String edition;
    private String submissionDate;
    private String approvalDate;
    private int literaryStyleId;
    private String publicationType;
    private long authorId;

    /**
     * Class constructor that assigns the attributes
     * @param aId the book name
     * @param aTitle the book title
     * @param aSubtitle the book subtitle
     * @param aPages the book page count
     * @param aWords the book word count
     * @param aIsbn the book ISBN
     * @param aEdition the book edition number
     * @param aSubmissionDate the book submission date
     * @param aApprovalDate the book approval date
     * @param aLiteraryStyleId the book literacy style id
     * @param aPublicationType the book publication type
     * @param aAuthorId the book author id
     */
    public Book(long aId, String aTitle, String aSubtitle, int aPages, int aWords, String aIsbn, String aEdition, String aSubmissionDate, String aApprovalDate, int aLiteraryStyleId, String aPublicationType, long aAuthorId) {
        this.id = aId;
        this.title = aTitle;
        this.subtitle = aSubtitle;
        this.pages = aPages;
        this.words = aWords;
        this.isbn = aIsbn;
        this.edition = aEdition;
        this.submissionDate = aSubmissionDate;
        this.approvalDate = aApprovalDate;
        this.literaryStyleId = aLiteraryStyleId;
        this.publicationType = aPublicationType;
        this.authorId = aAuthorId;
    }
    
    /**
     * Class constructor that assigns the attributes
     * @param aId the book name
     * @param aTitle the book title
     * @param aSubtitle the book subtitle
     * @param aPages the book page count
     * @param aWords the book word count
     * @param aIsbn the book ISBN
     * @param aEdition the book edition number
     * @param aSubmissionDate the book submission date
     * @param aPublicationType the book publication type
     * @param aAuthorId the book author id
     */
    public Book(long aId, String aTitle, String aSubtitle, int aPages, int aWords, String aIsbn, String aEdition, String aSubmissionDate, int aLiteracyStyleId, String aPublicationType, long aAuthorId){
        this(aId, aTitle, aSubtitle, aPages, aWords, aIsbn, aEdition, aSubmissionDate, null, aLiteracyStyleId, aPublicationType, aAuthorId);
    }

    /**
     * Gets the book id
     * @return the book id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the book title
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the book subtitle
     * @return the book subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Gets the book number of pages
     * @return the book number of pages
     */
    public int getPages() {
        return pages;
    }
 /**
     * Gets the book number of words
     * @return the book number of words
     */
    public int getWords() {
        return words;
    }

    /**
     * Gets the book isbn
     * @return the book isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Gets the book edtion
     * @return the book edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Gets the book edition
     * @return the book edition
     */
    public String getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Gets the book approval date
     * @return the book approval date
     */
    public String getApprovalDate() {
        return approvalDate;
    }

    /**
     * Gets the book literary style id
     * @return the book literary style id
     */
    public int getLiteraryStyleId() {
        return literaryStyleId;
    }

    /**
     * Gets the book publication type
     * @return the book publication type
     */
    public String getPublicationType() {
        return publicationType;
    }

    /**
     * Gets the book author id
     * @return the book author id
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * Sets the book id
     * @param id id of the book
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the book title
     * @param title title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the book subtitle
     * @param subtitle subtitle of the book
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Sets the number of pages of the book
     * @param pages number of pages of the book
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Sets the number of words of the book
     * @param words number of words of the book
     */
    public void setWords(int words) {
        this.words = words;
    }

    /**
     * Sets the book isbn
     * @param isbn isbn of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the book edtion
     * @param edition edition of the book
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Sets the book submission date
     * @param submissionDate submission date of the book
     */
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * Sets the book approval date
     * @param approvalDate approval date of the book
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Sets the book literary style id
     * @param literaryStyleId literary style id of the book
     */
    public void setLiteraryStyleId(int literaryStyleId) {
        this.literaryStyleId = literaryStyleId;
    }

    /**
     * Sets the book publication type
     * @param publicationType publication type of the book
     */
    public void setPublicationType(String publicationType) {
        this.publicationType = publicationType;
    }

    /**
     * Sets the book author id
     * @param authorId author id of the book
     */
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
