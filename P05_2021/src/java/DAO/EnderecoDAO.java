/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.EnderecoModelo;
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
public class EnderecoDAO
{

    private final Connection conexaoBD;

    public EnderecoDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirEndereco (EnderecoModelo enderecoModelo) throws SQLException
    {
        if (enderecoModelo != null)
        {

            try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.endereco( bairro, rua, numero_casa, comuna_fk)"
                    + " VALUES ( ?, ?, ?, ?)"))
            {
                pst.setString (1 , enderecoModelo.getBairro ());
                pst.setString (2 , enderecoModelo.getRua ());
                pst.setString (3 , enderecoModelo.getNumero_casa ());
                pst.setInt (4 , enderecoModelo.getComunaModelo ().getComuna_pk ());

                pst.execute ();
                Mensagens.sucessoGuardar (enderecoModelo.getBairro ());
                pst.close ();
                return true;
            }
        }
        return false;
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
                    enderecoModelo.setBairro (rs.getString ("bairro"));
                    enderecoModelo.setRua (rs.getString ("rua"));
                    enderecoModelo.setNumero_casa (rs.getString ("numero_casa"));
                    enderecoModelo.setComunaModelo (new ComunaDAO ().getDadosComuna (rs.getInt ("comuna_fk")));

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
                    enderecoModelo.setBairro (rs.getString ("bairro"));
                    enderecoModelo.setRua (rs.getString ("rua"));
                    enderecoModelo.setNumero_casa (rs.getString ("numero_casa"));
                    enderecoModelo.setComunaModelo (new ComunaDAO ().getDadosComuna (rs.getInt ("comuna_fk")));

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
                enderecoModelo.setEndereco_pk (rs.getInt ("municipio_pk"));
                enderecoModelo.setBairro (rs.getString ("bairro"));
                enderecoModelo.setNumero_casa (rs.getString ("numero_casa"));
                enderecoModelo.setRua (rs.getString ("rua"));
                enderecoModelo.setComunaModelo (new ComunaDAO ().getDadosComuna (rs.getInt ("comuna_fk")));

                return enderecoModelo;
            }

        }
        return null;
    }

    public boolean alterarEndereco (EnderecoModelo enderecoModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("UPDATE public.endereco SET bairro=?, rua=?,"
                + " numero_casa=?, comuna_fk=? WHERE endereco_pk=?"))
        {
            pst.setString (1 , enderecoModelo.getBairro ());
            pst.setString (2 , enderecoModelo.getRua ());
            pst.setString (3 , enderecoModelo.getNumero_casa ());
            pst.setInt (4 , enderecoModelo.getComunaModelo ().getComuna_pk ());

            pst.execute ();
            Mensagens.sucessoAlterar (enderecoModelo.getBairro ());
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
            Mensagens.sucessoEliminar (enderecoModelo.getBairro ());
            pst.close ();
            return true;
        }
    }

    public int getEndereco_pk (String numero_casa) throws SQLException
    {
        try (PreparedStatement pst = conexaoBD.prepareStatement ("SELECT endereco_pk FROM public.endereco WHERE = " + numero_casa))
        {
            ResultSet rs = pst.executeQuery ();

            if (rs.getInt (1) != 0)
            {
                return rs.getInt ("endereco_pk");
            }
        }
        return 0;
    }

    public EnderecoModelo lerRegisto (ResultSet rs) throws SQLException , ClassNotFoundException
    {
        EnderecoModelo registo = new EnderecoModelo ();

        registo.setComunaModelo (new ComunaDAO ().getDadosComuna (rs.getInt ("comuna_fk")));

        return registo;
    }

}
