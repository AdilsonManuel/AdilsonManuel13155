/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EmailModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexaoBD;

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
        String sql = "INSERT INTO public.email(\n"
                + "	 nome)\n"
                + "	VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , emailModelo.getNome ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }
    
    public int pegarUltimoEmail() throws SQLException{
        try(PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT MAX(PUBLIC.email.email_pk) FROM PUBLIC.email"))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            return rs.getInt (1);
        }
    }

    public EmailModelo getEmail_pk (int email_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.email WHERE email_pk="+email_pk))
        {
            EmailModelo emailModelo = new EmailModelo ();
            
            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            if (rs.getInt ("email_pk") > 0)
            {
                emailModelo.setEmail_pk (rs.getInt ("email_pk"));
                emailModelo.setNome (rs.getString ("nome"));
                
                return emailModelo;
            }
            
            
        }
        return null;
    }
}
