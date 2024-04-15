package tp1.controller;

import java.util.ArrayList;
import tp1.model.DbWrapper;
import tp1.model.Reviewer;
import java.sql.*;
import java.text.SimpleDateFormat;
import tp1.model.Log;
import tp1.view.Main;

/**
 * A class to manage reviewers on the database
 */
public class ManageReviewers {

    private ArrayList<Reviewer> reviewers;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageReviewers() {
        reviewers = new ArrayList<Reviewer>();
    }
    
    /**
     * Gets the reviewer from the database with the given id
     * @param id The reviewer id
     * @return Reviewer
     */
    public Reviewer getReviewer(long id){
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_reviewer_by_id(?);", new Object[]{id});
        try {
            if (resultSet == null || !resultSet.next()) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Pesquisou Revisor por ID: " + id));
            }
            return new Reviewer(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("active"),
                    resultSet.getBoolean("deleted"),
                    resultSet.getInt("role_id"),
                    resultSet.getString("nif"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("graduation"),
                    resultSet.getString("specialization"));

        } catch (SQLException e) {
            System.out.println("\nErro ao obter o revisor\n");
        }
        return null;
    }

    /**
     * Insert a reviewer in the database
     *
     * @param reviewer The reviewer to insert
     * @return Confirms if a reviewer was inserted successfully
     */
    public boolean insertReviewer(Reviewer reviewer) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean inserted = dbWrapper.manipulate("CALL insert_reviewer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{reviewer.getName(),
            reviewer.getUsername(),
            reviewer.getPassword(),
            reviewer.getEmail(),
            reviewer.getRoleId(),
            reviewer.getNif(),
            reviewer.getPhone(),
            reviewer.getAddress(),
            reviewer.getGraduation(),
            reviewer.getSpecialization()}) > 0;
        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Inseriu Revisor"));
        }
        return inserted;
    }

    /**
     * Updates a reviewer in the database
     *
     * @param reviewer The reviewer to be updated
     * @return Confirms if a reviewer was updated successfully
     */
    public boolean updateReviewer(Reviewer reviewer) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_reviewer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", new Object[]{reviewer.getId(),
            reviewer.getName(),
            reviewer.getUsername(),
            reviewer.getPassword(),
            reviewer.getEmail(),
            reviewer.getRoleId(),
            reviewer.getNif(),
            reviewer.getPhone(),
            reviewer.getAddress(),
            reviewer.getGraduation(),
            reviewer.getSpecialization()}) > 0;
        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Atualizou Revisor (ID: " + reviewer.getId() + ")"));
        }
        return updated;
    }
}
