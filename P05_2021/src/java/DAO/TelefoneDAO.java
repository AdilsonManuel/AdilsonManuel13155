/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TelefoneModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author azm
 */
public class TelefoneDAO
{

    private Connection conexaoBD;

    public TelefoneDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirTelefone (TelefoneModelo telefoneModelo) throws SQLException
    {
        String sql = "INSERT INTO public.telefone(operadora,numero) VALUES (?,?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , telefoneModelo.getOperadora ());
            pst.setString (2 , telefoneModelo.getNumero ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public int getTelefone_pk (String telefone_pk) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT telefone_pk FROM public.telefone WHERE = " + telefone_pk))
        {
            ResultSet rs = pst.executeQuery ();

            if (rs.getInt (1) != 0)
            {
                return rs.getInt ("telefone_pk");
            }
        }
        return 0;
    }

}
