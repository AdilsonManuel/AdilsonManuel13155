/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.LocalizacaoModelo;
import Modelo.SexoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexaoBD;

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

    public SexoModelo getSexo_pk (int sexo_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.sexo WHERE sexo_pk="+sexo_pk))
        {
            SexoModelo sexoModelo = new SexoModelo ();
            
            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            if (rs.getInt ("sexo_pk") > 0)
            {
                sexoModelo.setSexo_pk (rs.getInt ("sexo_pk"));
                sexoModelo.setNome (rs.getString ("nome"));
                
                return sexoModelo;
            }
            
            
        }
        return null;
    }

}
