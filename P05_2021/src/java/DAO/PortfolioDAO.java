/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PortfolioModelo;
import Util.ConexaoBD;
import Util.TabelaInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azm
 */
public class PortfolioDAO implements TabelaInterface<PortfolioModelo>
{

    private final Connection connection;

    public PortfolioDAO () throws ClassNotFoundException , SQLException
    {
        this.connection = new ConexaoBD ().getConnection ();
    }

    @Override
    public boolean create (PortfolioModelo portfolioModelo)
    {
        String sql = "INSERT INTO public.portfolio(\n"
                + "	portofolio_pk, designacao, portofolio_fk_pai)\n"
                + "	VALUES (?, ?, ?);";

        try (PreparedStatement pst = connection.prepareStatement (sql))
        {
            pst.setString (1 , portfolioModelo.getPortfolio_pk ());
            pst.setString (2 , portfolioModelo.getDesignacao ());
            pst.setString (3 , portfolioModelo.getPortofolio_fk_pai ());

            if (pst.executeUpdate () > 0)
            {
                System.err.println ("Cadastrado com sucesso o registo: " + pst);
                return true;
            }
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de criar um registo na tabela porifolio com a chave primária " + portfolioModelo.getPortfolio_pk ();
            Logger.getLogger (PortfolioDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return false;
    }

    @Override
    public PortfolioModelo find (PortfolioModelo portfolioModelo)
    {
        String sql = "SELECT * \n"
                + "	FROM public.portfolio WHERE portofolio_pk = ?";

        PortfolioModelo reg = new PortfolioModelo ();

        try (PreparedStatement pst = connection.prepareStatement (sql))
        {
            pst.setString (1 , portfolioModelo.getPortfolio_pk ());

            ResultSet rs = pst.executeQuery ();

            if (!rs.next ())
            {
                return null;
            }
            reg.setPortfolio_pk (rs.getString (1));
            reg.setDesignacao (rs.getString (2));
            return reg;
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de localizar um registo na tabela portifoio "
                    + "com a chave primária " + reg.getPortfolio_pk ();

            Logger.getLogger (PortfolioDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return null;
    }

    @Override
    public boolean update (PortfolioModelo portfolioModelo)
    {
        String sql = "UPDATE public.portfolio\n"
                + "	SET designacao=?\n"
                + "	WHERE  portofolio_pk=?";

        try (PreparedStatement pst = connection.prepareStatement (sql))
        {
            pst.setString (2 , portfolioModelo.getPortfolio_pk ());
            pst.setString (1 , portfolioModelo.getDesignacao ());
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de actualizar um registo na tabela"
                    + " portifolio com a chave primária " + portfolioModelo.getPortfolio_pk ();
            Logger.getLogger (PortfolioDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return false;
    }

    @Override
    public boolean insert (PortfolioModelo portfolioModelo)
    {
        PortfolioModelo reg = find (portfolioModelo);

        if (reg == null)
        {
            return create (portfolioModelo);
        }

        if (portfolioModelo.getDesignacao ().equalsIgnoreCase (reg.getDesignacao ()))
        {
            System.out.println ("O registo: " + portfolioModelo.getPortofolio_fk_pai () + "" + portfolioModelo.getDesignacao () + "," + "Já existe");
            return false;
        }
        return update (portfolioModelo);
    }

    public List<PortfolioModelo> getListaPortfolioPK (int portfolioPK) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.connection.prepareStatement ("SELECT * FROM public.portfolio WHERE portofolio_pk=?"))
        {
            List<PortfolioModelo> listaPortfolioModelos = new ArrayList<> ();
            try (ResultSet rs = pst.executeQuery ())
            {
                pst.setInt (1 , portfolioPK);
                while (rs.next ())
                {
                    PortfolioModelo portfolioModelo = new PortfolioModelo ();
                    portfolioModelo.setPortfolio_pk ("Portfolio_pk");
                    portfolioModelo.setDesignacao ("designacao");
                    portfolioModelo.setPortofolio_fk_pai ("portofolio_fk_pai");

                    listaPortfolioModelos.add (portfolioModelo);

                    pst.close ();
                }
                return listaPortfolioModelos;
            }
        }
    }

    public List<PortfolioModelo> obterPortifolioPorFkPai (String fkPorifolioPai)
    {
        List<PortfolioModelo> lista = new ArrayList<> ();
        String sqlQuery = "SELECT * FROM portifolio";

        try
        {
            PreparedStatement pst = connection.prepareStatement (sqlQuery);
            ResultSet rs = pst.executeQuery (sqlQuery);
// System.out.println("Tabelas com fkPorifolioPai: " + fkPorifolioPai);
            while (rs.next ())
            {
                if ((rs.getString (3) == null
                        && rs.getString (3) == fkPorifolioPai)
                        || (rs.getString (3) != null && rs.getString (3).equals (fkPorifolioPai)))
                {
                    /*System.out.println("pkPortifolio: " + rs.getString(1) + ", "
                            + "designacao: " + rs.getString(2) + ", "
                            + "fkPortifolioPai: " + rs.getString(3));*/

                    PortfolioModelo portifolioModelo = new PortfolioModelo ();

                    portifolioModelo.setPortfolio_pk (rs.getString (1));
                    portifolioModelo.setDesignacao (rs.getString (2));
                    portifolioModelo.setPortofolio_fk_pai (rs.getString (3));

                    lista.add (portifolioModelo);
                }
            }

        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de listar os registos na tabela "
                    + "potifolio com a chave estrangeira " + fkPorifolioPai;
            System.err.println (msg);
        }
        return lista;

    }

}
