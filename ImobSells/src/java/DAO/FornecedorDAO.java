/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.FornecedorModelo;
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
public class FornecedorDAO
{

    private Connection conexaoBD;

    public FornecedorDAO () throws ClassNotFoundException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public void inserirFornecedor (FornecedorModelo fornecedorModelo) throws SQLException
    {
        String sql = "INSERT INTO PUBLIC.fornecedor (nome,tipo_fornecedor_fk,pessoa_fk)";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, fornecedorModelo.getNome ());
            pst.setInt (2, fornecedorModelo.getTipo_fornecedor_fk ());
            pst.setInt (3, fornecedorModelo.getPessoa_fk ());

            pst.execute ();
            pst.close ();
        }
    }

    public List<FornecedorModelo> listarFornecedor () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.fornecedor"))
        {
            List<FornecedorModelo> listaForncedores = new ArrayList<> ();

            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                FornecedorModelo fornecedorModelo = new FornecedorModelo ();

                fornecedorModelo.setFornecedor_pk (rs.getInt ("fornecedor_pk"));
                fornecedorModelo.setNome (rs.getString ("nome"));
                fornecedorModelo.setTipo_fornecedor_fk (rs.getInt ("tipo_fornecedor_fk"));
                fornecedorModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));

                listaForncedores.add (fornecedorModelo);
            }
            pst.close ();
            rs.close ();
            return listaForncedores;

        }
    }

    public List<FornecedorModelo> listarFornecedorPorPk (int forncedor_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.fornecedor WHERE fornecedor_pk=" + forncedor_pk))
        {
            List<FornecedorModelo> listaForncedores = new ArrayList<> ();

            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                FornecedorModelo fornecedorModelo = new FornecedorModelo ();

                fornecedorModelo.setFornecedor_pk (rs.getInt ("fornecedor_pk"));
                fornecedorModelo.setNome (rs.getString ("nome"));
                fornecedorModelo.setTipo_fornecedor_fk (rs.getInt ("tipo_fornecedor_fk"));
                fornecedorModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));

                listaForncedores.add (fornecedorModelo);
            }
            pst.close ();
            rs.close ();
            return listaForncedores;

        }
    }

    public void alterarFornecedor (FornecedorModelo fornecedorModelo) throws SQLException
    {
        String sql = "ALTER PUBLIC.fornecedor nome=?,tipo_fornecedor_fk=?,pessoa_fk=? WHERE fornecedor_pk=?";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, fornecedorModelo.getNome ());
            pst.setInt (2, fornecedorModelo.getTipo_fornecedor_fk ());
            pst.setInt (3, fornecedorModelo.getPessoa_fk ());
            pst.setInt (4, fornecedorModelo.getFornecedor_pk ());

            pst.execute ();
            pst.close ();
        }
    }

    public void eliminarFornecedor (FornecedorModelo fornecedorModelo) throws SQLException
    {
        String sql = "DELETE FROM PUBLIC.fornecedor WHERE SET fornecedor_pk=?";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1, fornecedorModelo.getFornecedor_pk ());

            pst.execute ();
            pst.close ();
        }
    }

}
