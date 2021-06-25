/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EstadoCivilModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author azm
 */
public class EstadoCivilDAO
{

    private Connection conexaoBD;

    public EstadoCivilDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirEstadoCivil (EstadoCivilModelo estadoCivilModelo) throws SQLException
    {
        String sql = "INSERT INTO public.estado_civil( nome) VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , estadoCivilModelo.getNome ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public int getEstadoCivil_pk (int estado_civil_pk) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT estado_civil_pk FROM public.estado_civil WHERE estado_civil_pk= " + estado_civil_pk))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt (1) != 0)
            {
                return rs.getInt ("estado_civil_pk");
            }
        }
        return 0;
    }

}
