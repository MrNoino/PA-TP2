package tp1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tp1.model.DbWrapper;
import tp1.model.Log;
import tp1.model.Manager;
import tp1.view.Main;

/**
 * A class to manage managers on the database
 */
public class ManageManagers {

    private ArrayList<Manager> managers;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageManagers() {
        managers = new ArrayList<Manager>();
    }

    /**
     * Connects to the database and retrieve the number of managers
     *
     * @return the number of managers in the database
     */
    public int getTotalManagers() {

        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("SELECT * FROM total_managers;");

        try {
            if (resultSet == null || !resultSet.next()) {
                return -1;
            }

            return resultSet.getInt("total_managers");
        } catch (SQLException e) {
            System.out.println("\nErro ao obter o número total de gestores\n");
            return -1;
        } finally {
            dbWrapper.disconnect();
        }
    }

    /**
     * Gets the manager from the database with the given id
     * @param id The manager id
     * @return Manager
     */
    public Manager getManager(long id) {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("CALL get_manager_by_id(?);", new Object[]{id});
        try {
            if (resultSet == null || !resultSet.next()) {
                return null;
            }

            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        "Pesquisou Gestor (ID: " + id + ")"));
            }
            
            return new Manager(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("active"),
                    resultSet.getBoolean("deleted"),
                    resultSet.getInt("role_id"));

        } catch (SQLException e) {
            System.out.println("\nErro ao obter o gestor\n");
        }
        return null;
    }

    /**
     * Inserts a manager in the database
     *
     * @param manager The manager to insert
     * @return Confirms if a manager was inserted successfully
     */
    public boolean insertManager(Manager manager) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean inserted = dbWrapper.manipulate("CALL insert_manager(?, ?, ?, ?, ?);", new Object[]{manager.getName(),
            manager.getUsername(),
            manager.getPassword(),
            manager.getEmail(),
            manager.getRoleId()}) > 0;

        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new Date()),
                    "Inseriu Gestor"));
        }

        return inserted;
    }

    /**
     * Updates a manager
     *
     * @param manager The manager to be updated
     * @return Confirms if a manager was updated successfully
     */
    public boolean updateManager(Manager manager) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_user(?, ?, ?, ?, ?, ?);", new Object[]{manager.getId(),
            manager.getName(),
            manager.getUsername(),
            manager.getPassword(),
            manager.getEmail(),
            manager.getRoleId()}) > 0;

        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(), 
                    new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                    "Atualizou Gestor (ID: " + manager.getId() + ")"));
        }
        return updated;
    }

}
