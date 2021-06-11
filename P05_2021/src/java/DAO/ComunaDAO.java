/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ComunaModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Mensagens;

/**
 *
 * @author pc-accer
 */
public class ComunaDAO
{

    private final Connection conexaoBD;

    public ComunaDAO () throws ClassNotFoundException, SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirComuna (ComunaModelo comunaModelo) throws SQLException
    {
        String sql = "INSERT INTO public.comuna( nome, muncipio_fk) VALUES (?, ?)";

        if (comunaModelo != null)
        {
            try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
            {
                pst.setString (1 , comunaModelo.getNome ());
                pst.setInt (2 , comunaModelo.getMunicipioModelo ().getMuncipio_pk ());

                pst.execute ();
                Mensagens.sucessoGuardar (comunaModelo.getNome ());
                pst.close ();
                return true;
            }
        }
        return false;
    }

    public List<ComunaModelo> getListaComuna () throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.comuna ORDER BY comuna_pk ASC"))
        {
            List<ComunaModelo> listaComunaModelos = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                ComunaModelo comunaModelo = new ComunaModelo ();
                comunaModelo.setComuna_pk (rs.getInt ("comuna_pk"));
                comunaModelo.setNome (rs.getString ("nome"));
                comunaModelo.setMunicipioModelo (new MunicipioDAO ().getDadosMunicipio (rs.getInt ("muncipio_fk")));

                listaComunaModelos.add (comunaModelo);
            }
            pst.close ();
            rs.close ();
            return listaComunaModelos;

        }
    }

    public ComunaModelo getDadosComuna (int comuna_pk) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.comuna WHERE comuna_pk=" + comuna_pk))
        {
            ComunaModelo comunaModelo = new ComunaModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("comuna_pk") > 0)
            {
                comunaModelo.setComuna_pk (rs.getInt ("comuna_pk"));
                comunaModelo.setNome (rs.getString ("nome"));
                comunaModelo.setMunicipioModelo (new MunicipioDAO ().getDadosMunicipio (rs.getInt ("municipio_fk")));

                return comunaModelo;
            }

        }
        return null;
    }

    public List<ComunaModelo> getListaComunaPK (int comunaPK) throws SQLException, ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.comuna WHERE comuna_pk=? ORDER BY comuna_pk ASC"))
        {
            List<ComunaModelo> listaComunaModelos = new ArrayList<> ();
            try (ResultSet rs = pst.executeQuery ())
            {
                pst.setInt (1 , comunaPK);
                while (rs.next ())
                {
                    ComunaModelo comunaModelo = new ComunaModelo ();
                    comunaModelo.setComuna_pk (rs.getInt ("comuna_pk"));
                    comunaModelo.setNome (rs.getString ("nome"));
                    comunaModelo.setMunicipioModelo (new MunicipioDAO ().getDadosMunicipio (rs.getInt ("muncipio_fk")));

                    listaComunaModelos.add (comunaModelo);
                }
                pst.close ();
            }
            return listaComunaModelos;

        }
    }

    public boolean alterarComuna (ComunaModelo comunaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("UPDATE public.comuna SET nome=?,"
                + " muncipio_fk=? WHERE comuna_pk=?"))
        {
            pst.setString (1 , comunaModelo.getNome ());
            pst.setInt (2 , comunaModelo.getMunicipioModelo ().getMuncipio_pk ());
            pst.setInt (3 , comunaModelo.getComuna_pk ());

            pst.execute ();
            Mensagens.sucessoAlterar (comunaModelo.getNome ());
            pst.close ();
            return true;
        }
    }

    public boolean eliminarComuna (ComunaModelo comunaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.comuna\n"
                + "	WHERE comuna_pk=?"))
        {
            pst.setInt (1 , comunaModelo.getComuna_pk ());

            pst.execute ();
            Mensagens.sucessoEliminar (comunaModelo.getNome ());
            pst.close ();
            return true;
        }
    }
}
