package ServLet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.PortfolioDAO;
import Modelo.PortfolioModelo;
import Util.ConexaoBD;
import Util.ListUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benvindo
 */
public class TabelaServlet extends HttpServlet
{
    private ConexaoBD conexao;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        conexao = new ConexaoBD ();

        try
        {

            String tabela = request.getParameter("tabela");
            String codigoPai = request.getParameter("codigoPai");

            System.out.println("tabela:" + tabela + " + codigoPai:" + codigoPai);

            if (tabela.equals("portifolio"))
            {
                List<PortfolioModelo> lista;

                if (codigoPai.isEmpty())
                {
                    lista = new PortfolioDAO().obterPortifolioPorFkPai(null);
                }
                else
                {
                    lista = new PortfolioDAO().obterPortifolioPorFkPai(codigoPai);
                }
                ListUtil.ordenarPorOrdemAlfabetica(lista);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lista.size(); i++)
                {
                    // System.out.println(lista.get(i).getPkPortifolio() + " " + lista.get(i).getDesignacao());
                    sb.append(lista.get(i).getPortfolio_pk ()+ "-" + lista.get(i).getDesignacao() + ";");
                }
                out.write(sb.toString());
            }

        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (TabelaServlet.class.getName()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (TabelaServlet.class.getName()).log (Level.SEVERE , null , ex);
        } finally
        {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
