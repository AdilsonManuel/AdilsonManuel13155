/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EnderecoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexaoBD;

/**
 *
 * @author azm
 */
public class EnderecoDAO
{

    private final Connection conexaoBD;

    public EnderecoDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public void inserirEndereco (EnderecoModelo enderecoModelo) throws SQLException
    {
        if (enderecoModelo != null)
        {

            try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.endereco( rua, numero_casa, localizacao_fk)"
                    + " VALUES ( ?, ?, ?, ?)"))
            {
                pst.setString (1 , enderecoModelo.getRua ());
                pst.setInt (2 , enderecoModelo.getNumero_casa ());
                pst.setString (3 , enderecoModelo.getLocalizacaoModelo ().getLocalizacao_pk ());
//                System.out.println ("DAO.EnderecoDAO.inserirEndereco()" + enderecoModelo.toString ());
                pst.execute ();
                pst.close ();

            }
        }
    }

    public List<EnderecoModelo> getListaEndereco () throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.endereco ORDER BY endereco_pk ASC"))
        {
            List<EnderecoModelo> listaEnderecoModelos = new ArrayList<> ();
            try (ResultSet rs = pst.executeQuery ())
            {

                while (rs.next ())
                {
                    EnderecoModelo enderecoModelo = new EnderecoModelo ();
                    enderecoModelo.setEndereco_pk (rs.getInt ("endereco_pk"));
                    enderecoModelo.setNumero_casa (rs.getInt ("numero_casa"));
                    enderecoModelo.setRua (rs.getString ("rua"));
                    enderecoModelo.setLocalizacaoModelo (new LocalizacaoDAO ().findLocalidade (rs.getString ("localizacao_fk")));

                    listaEnderecoModelos.add (enderecoModelo);
                }
                pst.close ();
                return listaEnderecoModelos;
            }

        }
    }

    public List<EnderecoModelo> getListaEnderecoPK (int enderecoPK) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.endereco WHERE endereco_pk=? ORDER BY comuna_pk ASC"))
        {
            List<EnderecoModelo> listaEnderecoModelos = new ArrayList<> ();
            try (ResultSet rs = pst.executeQuery ())
            {
                pst.setInt (1 , enderecoPK);
                while (rs.next ())
                {
                    EnderecoModelo enderecoModelo = new EnderecoModelo ();
                    enderecoModelo.setEndereco_pk (rs.getInt ("endereco_pk"));
                    enderecoModelo.setNumero_casa (rs.getInt ("numero_casa"));
                    enderecoModelo.setRua (rs.getString ("rua"));
                    enderecoModelo.setLocalizacaoModelo (new LocalizacaoDAO ().findLocalidade (rs.getString ("localizacao_fk")));

                    listaEnderecoModelos.add (enderecoModelo);
                }
                pst.close ();
                return listaEnderecoModelos;
            }

        }
    }

    public EnderecoModelo getDadosMunicipio (int endereco_pk) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.endereco WHERE endereco_pk=" + endereco_pk))
        {
            EnderecoModelo enderecoModelo = new EnderecoModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("endereco_pk") > 0)
            {
                enderecoModelo.setEndereco_pk (rs.getInt ("endereco_pk"));
                enderecoModelo.setNumero_casa (rs.getInt ("numero_casa"));
                enderecoModelo.setRua (rs.getString ("rua"));
                enderecoModelo.setLocalizacaoModelo (new LocalizacaoDAO ().findLocalidade (rs.getString ("localizacao_fk")));

                return enderecoModelo;
            }

        }
        return null;
    }

    public boolean alterarEndereco (EnderecoModelo enderecoModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("UPDATE public.endereco SET  rua=?,"
                + " numero_casa=?, localização_fk=? WHERE endereco_pk=?"))
        {
            pst.setString (1 , enderecoModelo.getRua ());
            pst.setInt (2 , enderecoModelo.getNumero_casa ());
            pst.setString (3 , enderecoModelo.getLocalizacaoModelo ().getLocalizacao_pk ());

            pst.execute ();
            pst.close ();
            return true;
        }
    }

    public boolean eliminarEndereco (EnderecoModelo enderecoModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.endereco\n"
                + "	WHERE endereco_pk=?"))
        {
            pst.setInt (1 , enderecoModelo.getEndereco_pk ());

            pst.execute ();
            pst.close ();
            return true;
        }
    }

    public EnderecoModelo getEndereco_pk (int endereco_pk) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT * FROM public.endereco WHERE endereco_pk=" + endereco_pk))
        {
            ResultSet rs = pst.executeQuery ();

            if (rs.next ())
            {

                EnderecoModelo enderecoModelo = new EnderecoModelo ();
                enderecoModelo.setEndereco_pk (rs.getInt ("endereco_pk"));
                enderecoModelo.setRua (rs.getString ("rua"));
                enderecoModelo.setNumero_casa (rs.getInt ("numero_casa"));
                enderecoModelo.setLocalizacaoModelo (new LocalizacaoDAO ().findLocalidade (rs.getString ("localizacao_fk")));
                return enderecoModelo;
            }
        }
        return null;
    }

}
