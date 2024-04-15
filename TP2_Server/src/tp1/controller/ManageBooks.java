package tp1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tp1.model.Book;
import tp1.model.DbWrapper;
import tp1.model.Log;
import tp1.view.Main;

/**
 * A class to manage books on the database
 */
public class ManageBooks {

    private ArrayList<Book> books;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageBooks() {
        books = new ArrayList<Book>();
    }

    /**
     * Gets the book from the database with the given id
     * @param authorId The author id
     * @param bookId The book id
     * @return Book
     */
    public Book getBookById(long authorId, long bookId) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_book_by_id(?, ?);", new Object[]{authorId, bookId});
        try {
            if (resultSet == null || !resultSet.next()) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        "Pesquisou Obra por ID: " + bookId));
            }
            return new Book(resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("subtitle"),
                    resultSet.getInt("pages"),
                    resultSet.getInt("words"),
                    resultSet.getString("isbn"),
                    resultSet.getString("edition"),
                    resultSet.getString("submission_date"),
                    resultSet.getString("approval_date"),
                    resultSet.getInt("literary_style_id"),
                    resultSet.getString("publication_type"),
                    resultSet.getInt("author_id"));

        } catch (SQLException e) {
            System.out.println("\nErro ao obter o livro\n");
        }
        return null;
    }

    /**
     * Gets the books from the database
     * @param authorId The author id
     * @param orderField The order field ["title", "submission_date"]
     * @param sortOrder The order for the sorting ["ASC", "DESC"]
     * @param page The listing page
     * @return A list of books
     */
    public ArrayList<Book> getBooks(long authorId, String orderField, String sortOrder, int page) {

        String sql = "CALL ";
        if (orderField.equals("title")) {
            sql += "get_books_ordered_title_paginated";
        } else if (orderField.equals("submission_date")) {
            sql += "get_books_ordered_submission_date_paginated";
        } else {
            return null;
        }
        sql += "(?, ?, ?);";

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query(sql, new Object[]{authorId, sortOrder, page});
        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        "Listou Obras"));
            }
            while (resultSet.next()) {
                this.books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("subtitle"),
                        resultSet.getInt("pages"),
                        resultSet.getInt("words"),
                        resultSet.getString("isbn"),
                        resultSet.getString("edition"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getInt("literary_style_id"),
                        resultSet.getString("publication_type"),
                        resultSet.getInt("author_id")));
            }
            return this.books;

        } catch (SQLException e) {
            System.out.println("\nErro ao obter as obras\n");
        }
        return null;
    }

    /**
     * Gets the books from the database given the submission date
     * @param authorId  The author id
     * @param submissionDate The submission date
     * @return A list of books
     */
    public ArrayList<Book> getBooksBySubmissionDate(long authorId, String submissionDate) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_books_by_submission_date(?, STR_TO_DATE(?, \"%d-%m-%Y\"));", new Object[]{authorId, submissionDate});
        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        "Pesquisou Obras por Data de Submissão: " + submissionDate));
            }
            while (resultSet.next()) {
                this.books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("subtitle"),
                        resultSet.getInt("pages"),
                        resultSet.getInt("words"),
                        resultSet.getString("isbn"),
                        resultSet.getString("edition"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getInt("literary_style_id"),
                        resultSet.getString("publication_type"),
                        resultSet.getInt("author_id")));
            }
            return this.books;

        } catch (SQLException e) {
            System.out.println("\nErro ao obter as obras");
        }
        return null;
    }

    /**
     * Gets the books from the database given the ISBN
     * @param authorId The author id
     * @param isbn The book ISBN
     * @return A list of books
     */
    public ArrayList<Book> getBooksByIsbn(long authorId, String isbn) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_books_by_isbn(?, ?);", new Object[]{authorId, isbn});
        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        "Pesquisou Obras por ISBN: " + isbn));
            }
            while (resultSet.next()) {
                this.books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("subtitle"),
                        resultSet.getInt("pages"),
                        resultSet.getInt("words"),
                        resultSet.getString("isbn"),
                        resultSet.getString("edition"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getInt("literary_style_id"),
                        resultSet.getString("publication_type"),
                        resultSet.getInt("author_id")));
            }
            return this.books;

        } catch (SQLException e) {
            System.out.println("\nErro ao obter as obras\n");
        }
        return null;
    }

    /**
     * Gets the author books from the database
     * @param authorId The author id
     * @return A list of books
     */
    public ArrayList<Book> getBooksByAuthor(long authorId){
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        
        ResultSet resultSet = dbWrapper.query("CALL get_books_by_author(?)", new Object[]{authorId});

        try{
            if (resultSet == null) {
                return null;
            }
            
            while (resultSet.next()) {
                this.books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("subtitle"),
                        resultSet.getInt("pages"),
                        resultSet.getInt("words"),
                        resultSet.getString("isbn"),
                        resultSet.getString("edition"),
                        resultSet.getString("submission_date"),
                        resultSet.getString("approval_date"),
                        resultSet.getInt("literary_style_id"),
                        resultSet.getString("publication_type"),
                        resultSet.getInt("author_id")));
            }
            return this.books;
        }catch(SQLException e){
            System.out.println("\nErro ao obter as obras\n");
        }
        
        return null;
    }
    
    /** 
     * Checks if any book in the database has the given title
     * @param title title to search
     * @return Confirms if a book exists with the same title
     */
    public boolean existsTitle(String title) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL exists_title(?);", new Object[]{title});
        try {
            if (resultSet == null || !resultSet.next()) {
                return false;
            }

            if (resultSet.getBoolean("exists")) {
                System.out.println("\nTítulo já em uso\n");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao verificar o título\n");
        } finally {
            dbWrapper.disconnect();
        }
        return false;
    }

       /**
        * Checks if any book in the database as the given ISBN
        * @param isbn The ISBN
        * @return Confirms if a book exists with the same ISBN
        */
    public boolean existsIsbn(String isbn) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL exists_isbn(?);", new Object[]{isbn});
        try {
            if (resultSet == null || !resultSet.next()) {
                return false;
            }

            if (resultSet.getBoolean("exists")) {
                System.out.println("\nISBN já em uso\n");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao verificar o ISBN\n");
        } finally {
            dbWrapper.disconnect();
        }
        return false;
    }

    /**
     * Insert a book in the database
     * @param book The book to insert in the database
     * @return Confirms if a book was inserted successfully
     */
    public boolean insertBook(Book book) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean inserted = dbWrapper.manipulate("CALL insert_book(?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{book.getTitle(),
            book.getSubtitle(),
            book.getPages(),
            book.getWords(),
            book.getIsbn(),
            book.getEdition(),
            book.getLiteraryStyleId(),
            book.getPublicationType(),
            book.getAuthorId()}) > 0;

        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                    new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                    "Inseriu Obra"));
        }
        return inserted;
    }

    /**
     * Updates a book in the database
     *
     * @param book The book to be updated
     * @return Confirms if a book was updated successfully
     */
    public boolean updateBook(Book book) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_book(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{book.getId(),
            book.getTitle(),
            book.getSubtitle(),
            book.getPages(),
            book.getWords(),
            book.getIsbn(),
            book.getEdition(),
            book.getLiteraryStyleId(),
            book.getPublicationType(),
            book.getAuthorId()}) > 0;
        
        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                    new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                    "Atualizou Obra (ID: " + book.getId() + ")"));
        }
        return updated;
    }
}
