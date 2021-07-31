/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ProdutoModelo;
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
public class ProdutoDAO
{

    private Connection conexaoBD;

    public ProdutoDAO () throws ClassNotFoundException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public void inserirProduto (ProdutoModelo produtoModelo) throws SQLException
    {
        String sql = "INSERT INTO PUBLIC.produto (designacao,imagem,portfolio_fk,preco,fornecedor_fk,quantidade,data_registro)";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, produtoModelo.getDesignacao ());
            pst.setString (2, produtoModelo.getImagem ());
            pst.setString (3, produtoModelo.getPortfolio_fk ());
            pst.setDouble (4, produtoModelo.getPreco ());
            pst.setInt (5, produtoModelo.getFornecedor_fk ());
            pst.setInt (6, produtoModelo.getQuantidade ());
            pst.setString (7, produtoModelo.getData_registro ());

            pst.execute ();
            pst.close ();
        }
    }

    public List<ProdutoModelo> listarProduto () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.produto"))
        {
            List<ProdutoModelo> listaProduto = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                ProdutoModelo produtoModelo = new ProdutoModelo ();
                produtoModelo.setProduto_pk (rs.getInt ("produto_pk"));
                produtoModelo.setDesignacao (rs.getString ("designacao"));
                produtoModelo.setImagem (rs.getString ("imagem"));
                produtoModelo.setPortfolio_fk (rs.getString ("portfolio_fk"));
                produtoModelo.setPreco (rs.getDouble ("preco"));
                produtoModelo.setFornecedor_fk (rs.getInt ("fornecedor_fk"));
                produtoModelo.setQuantidade (rs.getInt ("quantidade"));
                produtoModelo.setData_registro (rs.getString ("data_registro"));

                listaProduto.add (produtoModelo);
            }
            pst.close ();
            rs.close ();
            return listaProduto;
        }
    }

    public List<ProdutoModelo> listarProdutoPK (int produto_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.produto"))
        {
            List<ProdutoModelo> listaProduto = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();
            pst.setInt (1, produto_pk);
            while (rs.next ())
            {
                ProdutoModelo produtoModelo = new ProdutoModelo ();
                produtoModelo.setProduto_pk (rs.getInt ("produto_pk"));
                produtoModelo.setDesignacao (rs.getString ("designacao"));
                produtoModelo.setImagem (rs.getString ("imagem"));
                produtoModelo.setPortfolio_fk (rs.getString ("portfolio_fk"));
                produtoModelo.setPreco (rs.getDouble ("preco"));
                produtoModelo.setFornecedor_fk (rs.getInt ("fornecedor_fk"));
                produtoModelo.setQuantidade (rs.getInt ("quantidade"));
                produtoModelo.setData_registro (rs.getString ("data_registro"));

                listaProduto.add (produtoModelo);
            }
            pst.close ();
            rs.close ();
            return listaProduto;
        }
    }

    public void alterarProduto (ProdutoModelo produtoModelo) throws SQLException
    {
        String sql = "ALTER PUBLIC.produto designacao=?,imagem=?,portfolio_fk=?,preco=?,fornecedor_fk=?,quantidade=?,data_registro=? WHERE produto_pk=?";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, produtoModelo.getDesignacao ());
            pst.setString (2, produtoModelo.getImagem ());
            pst.setString (3, produtoModelo.getPortfolio_fk ());
            pst.setDouble (4, produtoModelo.getPreco ());
            pst.setInt (5, produtoModelo.getFornecedor_fk ());
            pst.setInt (6, produtoModelo.getQuantidade ());
            pst.setString (7, produtoModelo.getData_registro ());
            pst.setInt (8, produtoModelo.getProduto_pk ());

            pst.execute ();
            pst.close ();
        }
    }

    public void eliminarProduto (ProdutoModelo produtoModelo) throws SQLException
    {
        String sql = "DELETE FROM PUBLIC.produto WHERE SET produto_pk=?";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1, produtoModelo.getProduto_pk ());

            pst.execute ();
            pst.close ();
        }
    }

}
