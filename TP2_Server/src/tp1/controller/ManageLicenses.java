package tp1.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import tp1.model.DbWrapper;
import tp1.model.License;
import tp1.model.Log;
import tp1.view.Main;

/**
 * A class to manage licenses on the database
 */
public class ManageLicenses {

    private ArrayList<License> licenses;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageLicenses() {
        licenses = new ArrayList<License>();
    }

    /**
     * Gets the licenses from the database
     * @return A list of licenses
     */
    public ArrayList<License> getLicenses() {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("SELECT * FROM get_licenses;");
        try {
            if (resultSet == null) {
                return null;
            }
            if (Main.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Listou Licenças"));
            }
            while (resultSet.next()) {
                this.licenses.add(new License(resultSet.getInt("id"),
                        resultSet.getString("designation"),
                        resultSet.getString("expire_date"),
                        resultSet.getInt("quantity")));
            }

            return this.licenses;

        } catch (SQLException e) {
            System.out.println("\nErro ao obter as licensas");
        }
        return null;
    }

    /**
     * Insert a license in the database
     *
     * @param license The license to be inserted in the database
     * @return Confirms if a license was inserted successfully
     */
    public boolean insertLicense(License license) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean inserted = dbWrapper.manipulate("CALL insert_license(?, STR_TO_DATE(?, \"%d-%m-%Y\"), ?);", new Object[]{license.getDesignation(),
            license.getExpireDate(),
            license.getQuantity()}) > 0;
        if (inserted && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Inseriu Licença"));
        }
        return inserted;
    }

    /**
     * Updates a license quantity in the database
     *
     * @param licenseId the license id to be updated
     * @param quantity the amount to be updated
     * @return Confirms if the quantity was updated successfully
     */
    public boolean updateLicenseQuantity(int licenseId, int quantity) {
        DbWrapper dbWrapper = new DbWrapper();
        boolean updated = dbWrapper.manipulate("CALL update_license_quantity(?, ?);", new Object[]{licenseId, quantity}) > 0;
        if (updated && Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                    new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                    "Autalizou Licença (ID: " + licenseId + ")"));
        }
        return updated;
    }
}
