/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PaisModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azm
 */
public class PaisDAO
{

    private Connection conexaoBD;

    public PaisDAO () throws ClassNotFoundException , SQLException
    {
        conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirPais (PaisModelo paisModelo) throws SQLException
    {
        String sql = "INSERT INTO public.pais( nome) VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , paisModelo.getNome ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public List<PaisModelo> getListaPais () throws SQLException
    {
        String sql = "SELECT * FROM public.pais";
        List<PaisModelo> listaPaises = new ArrayList<> ();

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    PaisModelo paisModelo = new PaisModelo ();
                    paisModelo.setPaisPK (rs.getInt (1));
                    paisModelo.setNome (rs.getString ("nome"));

                    listaPaises.add (paisModelo);
                }
            }
            pst.close ();
        }
        return listaPaises;
    }

    public List<PaisModelo> pesquisaPaisPK (int pais_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pais WHERE pais_pk=? ORDER BY pais_pk ASC"))
        {
            List<PaisModelo> listaPais = new ArrayList<PaisModelo> ();

            pst.setInt (1 , pais_pk);
            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    PaisModelo paisModelo = new PaisModelo ();
                    paisModelo.setPaisPK (rs.getInt ("pais_pk"));
                    paisModelo.setNome (rs.getString ("nome"));

                    listaPais.add (paisModelo);
                }
            }
            pst.close ();
            return listaPais;
        }
    }

    public PaisModelo getDadosPais (int pais_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pais WHERE pais_pk="+pais_pk))
        {
            PaisModelo paisModelo = new PaisModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            if (rs.getInt (1) > 0)
            {
               paisModelo.setPaisPK (rs.getInt ("pais_pk"));
               paisModelo.setNome (rs.getString ("nome"));
               
               return paisModelo;
            }

        }
        return null;
    }

    public boolean alterarPais (PaisModelo paisModelo) throws SQLException
    {
        if (paisModelo != null)
        {
            String sql = "UPDATE public.pais\n"
                    + "SET nome=?\n"
                    + "WHERE pais_pk=?";

            try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
            {
                pst.setString (1 , paisModelo.getNome ());
                pst.setInt (2 , paisModelo.getPaisPK ());

                pst.execute ();
                pst.close ();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPais (PaisModelo paisModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.pais\n"
                + "	WHERE pais_pk=?"))
        {
            pst.setInt (1 , paisModelo.getPaisPK ());

            pst.execute ();
            pst.close ();
            return true;

        }
    }

}
