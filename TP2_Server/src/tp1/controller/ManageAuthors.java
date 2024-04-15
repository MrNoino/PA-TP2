package tp1.controller;

import java.util.ArrayList;
import tp1.model.Author;
import tp1.model.DbWrapper;
import java.sql.*;
import java.text.SimpleDateFormat;
import tp1.model.Log;
import tp1.view.Main;

/**
 * A class to manage authors on the database
 */
public class ManageAuthors {

    private ArrayList<Author> authors;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageAuthors() {
        authors = new ArrayList<Author>();
    }
    
    /**
     * Gets the author from the database with the given id
     * @param id The author id
     * @return Author
     */
    public Author getAuthor(long id){
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_author_by_id(?);", new Object[]{id});
        try {
            if(resultSet == null || !resultSet.next())
                return null;
            
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Autor pelo ID: " + id));
            }
            return new Author(resultSet.getLong("id"), 
                    resultSet.getString("name"), 
                    resultSet.getString("username"), 
                    resultSet.getString("email"), 
                    resultSet.getBoolean("active"), 
                    resultSet.getBoolean("deleted"), 
                    resultSet.getInt("role_id"),
                    resultSet.getString("nif"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("activity_begin_date"),
                    resultSet.getInt("literary_style_id"));
            
        } catch (SQLException e) {
            System.out.println("\nErro ao obter o autor\n");
        }
        return null;
    }

    /**
     * Inserts an author in the database
     *
     * @param author The author to insert
     * @return Confirms if an author was inserted successfully
     */
    public boolean insertAuthor(Author author) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean inserted = dbWrapper.manipulate("CALL insert_author(?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{author.getName(),
            author.getUsername(),
            author.getPassword(),
            author.getEmail(),
            author.getRoleId(),
            author.getNif(),
            author.getPhone(),
            author.getAddress(),
            author.getLiteraryStyleId()}) > 0;
        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Inseriu Autor"));
        }
        return inserted;
    }

    /**
     * Updates an author in the database
     *
     * @param author The author to be updated
     * @return Confirms if an author was updated successfully
     */
    public boolean updateAuthor(Author author) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_author(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{author.getId(),
            author.getName(),
            author.getUsername(),
            author.getPassword(),
            author.getEmail(),
            author.getRoleId(),
            author.getNif(),
            author.getPhone(),
            author.getAddress(),
            author.getLiteraryStyleId()}) > 0;
        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Atualizou Autor (ID: " + author.getId() + ")"));
        }
        return updated;
    }

}
