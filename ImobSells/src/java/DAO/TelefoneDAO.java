/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TelefoneModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexaoBD;

/**
 *
 * @author azm
 */
public class TelefoneDAO
{

    private Connection conexaoBD;

    public TelefoneDAO () throws ClassNotFoundException, SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirTelefone (TelefoneModelo telefoneModelo) throws SQLException
    {
        String sql = "INSERT INTO public.telefone(\n"
                + "	numero)\n"
                + "	VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, telefoneModelo.getNumero ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public int pegarUltimoTelefone () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT MAX(PUBLIC.telefone.telefone_pk) FROM PUBLIC.telefone"))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            return rs.getInt (1);
        }
    }

    public TelefoneModelo getTelefone (int telefone_pk) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT * FROM public.telefone WHERE telefone_pk=" + telefone_pk))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("telefone_pk") > 0)
            {

                TelefoneModelo telefoneModelo = new TelefoneModelo ();
                telefoneModelo.setTelefone_pk (rs.getInt ("telefone_pk"));
                telefoneModelo.setNumero (rs.getString ("numero"));

                return telefoneModelo;
            }
        }
        return null;
    }
}
