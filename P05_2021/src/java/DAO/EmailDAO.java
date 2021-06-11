/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EmailModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author azm
 */
public class EmailDAO
{

    private Connection conexaoBD;

    public EmailDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirEmail (EmailModelo emailModelo) throws SQLException
    {
        String sql = "INSERT INTO public.email(dominio) VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , emailModelo.getDominio ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public int getEmail_pk (String email_pk) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT email_pk FROM public.email WHERE = " + email_pk))
        {
            ResultSet rs = pst.executeQuery ();

            if (rs.getInt (1) != 0)
            {
                return rs.getInt ("email_pk");
            }
        }
        return 0;
    }
}
