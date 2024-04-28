package tp2.controller;

import java.util.ArrayList;
import tp2.model.DbWrapper;
import tp2.model.LiteraryStyle;
import java.sql.*;
import java.text.SimpleDateFormat;
import tp2.model.Log;
import tp2.view.Server;

/**
 * A class to manage literary styles on the database
 */
public class ManageLiteraryStyles {

    private ArrayList<LiteraryStyle> literacyStyles;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageLiteraryStyles() {
        literacyStyles = new ArrayList<LiteraryStyle>();
    }

    /**
     * Gets the literary styles from the database
     * @return A list of literacy styles
     */
    public ArrayList<LiteraryStyle> getLiteraryStyles() {
        DbWrapper dbWrapper = new DbWrapper();
        dbWrapper.connect();
        ResultSet resultSet = dbWrapper.query("SELECT * FROM get_literary_styles;");
        try {
            if(resultSet == null)
                return null;
            if (Server.getLoggedUser() != null) {
                new ManageLogs().insertLog(new Log(Server.getLoggedUser().getId(),
                        new SimpleDateFormat("yyyy-mm-dd").format(new java.util.Date()),
                        "Listou Estilos Literários"));
            }
            while(resultSet.next())
                this.literacyStyles.add(new LiteraryStyle(resultSet.getInt("id"), resultSet.getString("literary_style")));
            
        } catch (SQLException e) {
            System.out.println("\nErro ao obter os estilos literários\n");
            return null;
        }finally{
            dbWrapper.disconnect();
        }
        
        return this.literacyStyles;
    }
}
