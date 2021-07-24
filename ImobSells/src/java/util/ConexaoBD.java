/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azm
 */
public class ConexaoBD
{

    public Connection getConnection () throws ClassNotFoundException
    {
        try
        {
            Class.forName ("org.postgresql.Driver");
            return DriverManager.getConnection ("jdbc:postgresql://localhost:5432/imob_sells_db" , "postgres" , "postgres");
        }
        catch (SQLException e)
        {
            Logger.getLogger (ConexaoBD.class.getName ()).log (Level.SEVERE , null , e);
        }
        return null;
    }

}
