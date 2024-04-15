package tp1.controller;

import java.util.ArrayList;
import tp1.model.Log;
import tp1.model.DbWrapper;
import java.sql.*;

/**
 * A class to manage logs on the database
 */
public class ManageLogs {

    private ArrayList<Log> logs;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageLogs() {
        logs = new ArrayList<Log>();
    }

    /**
     * Gets the logs from the database
     * @param page The list page
     * @return A list of logs
     */
    public ArrayList<Log> getLogs(int page) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_logs_paginated(?)", new Object[]{page});

        try {
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                this.logs.add(new Log(resultSet.getInt("user_id"),
                        resultSet.getString("datetime"),
                        resultSet.getString("action")));
            }
            return this.logs;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter os logs\n");
        }

        return null;
    }

    /**
     * Gets the user logs from the database given the id
     * @param userId id of the user
     * @return A list of logs
     */
    public ArrayList<Log> getLogsByUser(long userId) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_logs_by_user(?)", new Object[]{userId});

        try {
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                this.logs.add(new Log(resultSet.getInt("user_id"),
                        resultSet.getString("datetime"),
                        resultSet.getString("action")));
            }
            return this.logs;
        } catch (SQLException e) {
            System.out.println("\nErro ao obter os logs\n");
        }

        return null;
    }

    /**
     * Inserts a log in the database
     *
     * @param log The log to insert to the database
     * @return Confirms if a log was inserted successfully
     */
    public boolean insertLog(Log log) {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();

        return dbWrapper.manipulate("CALL insert_log(?,?);", new Object[]{log.getUserId(), log.getAction()}) > 0;
    }
    
}
