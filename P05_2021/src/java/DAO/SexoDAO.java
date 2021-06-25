/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PaisModelo;
import Modelo.SexoModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author azm
 */
public class SexoDAO
{

    private Connection conexaoBD;

    public SexoDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirSexo (SexoModelo sexoModelo) throws SQLException
    {
        String sql = "INSERT INTO public.sexo( nome) VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , sexoModelo.getNome ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public int getSexo_pk (int sexo) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT sexo_pk FROM public.sexo WHERE sexo_pk=" + sexo))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt (1) > 0)
            {
                return rs.getInt ("sexo_pk");
            }
        }
        return 0;
    }
}
