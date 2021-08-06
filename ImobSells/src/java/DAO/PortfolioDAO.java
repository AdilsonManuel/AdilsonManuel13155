/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PortfolioModelo;
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
public class PortfolioDAO
{

    private Connection conexaoBD;

    public PortfolioDAO () throws ClassNotFoundException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public void inserir (PortfolioModelo portfolioModelo) throws SQLException
    {
        String sql = "INSERT INTO PUBLIC.portfolio(designacao,portfolio_fk_pai) VALUES (?,?)";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
        {
            pst.setString (1, portfolioModelo.getDesignacao ());
            pst.setString (2, portfolioModelo.getPortfolio_fk_pai ());

            pst.execute ();
            pst.close ();
        }
    }

    public List<PortfolioModelo> listrPortfolio () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.portfolio"))
        {
            List<PortfolioModelo> listaPortfolio = new ArrayList<> ();

            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                PortfolioModelo portfolioModelo = new PortfolioModelo ();
                portfolioModelo.setPortfolio_pk (rs.getInt ("portfolio_pk"));
                portfolioModelo.setDesignacao (rs.getString ("designacao"));
                portfolioModelo.setPortfolio_fk_pai (rs.getString ("portfolio_fk_pai"));

                listaPortfolio.add (portfolioModelo);
            }
            pst.close ();
            rs.close ();
            return listaPortfolio;
        }
    }

    public List<PortfolioModelo> listrPortfolio_pk (int portfolio_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.portfolio"))
        {
            List<PortfolioModelo> listaPortfolio = new ArrayList<> ();

            pst.setInt (1, portfolio_pk);
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                PortfolioModelo portfolioModelo = new PortfolioModelo ();
                portfolioModelo.setPortfolio_pk (rs.getInt ("portfolio_pk"));
                portfolioModelo.setDesignacao (rs.getString ("designacao"));
                portfolioModelo.setPortfolio_fk_pai (rs.getString ("portfolio_fk_pai"));

                listaPortfolio.add (portfolioModelo);
            }
            pst.close ();
            rs.close ();
            return listaPortfolio;
        }
    }

    public PortfolioModelo retornarNivel (String designacao) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.portfolio WHERE designacao='" + designacao + "'"))
        {
            PortfolioModelo portfolioModelo = new PortfolioModelo ();

            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            if (rs.getInt ("portfolio_pk") > 0)
            {
                portfolioModelo.setPortfolio_pk (rs.getInt ("portfolio_pk"));
                portfolioModelo.setDesignacao (rs.getString ("designacao"));
                portfolioModelo.setPortfolio_fk_pai (rs.getString ("portfolio_fk_pai"));
                
                return portfolioModelo;
            }
        }
        return null;

    }

}
