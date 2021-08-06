/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.PortfolioDAO;
import Modelo.PortfolioModelo;
import Modelo.ProdutoModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author azm
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = "/ServLet/ProdutoServlet")
public class ProdutoServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException
    {
        response.setContentType ("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter ())
////        {
//            String operacao = request.getParameter ("operacao");
//            String redirecionar = request.getParameter ("redirecionar");
//
//            if (operacao.equalsIgnoreCase ("cadastrar"))
//            {
//                String nomeDoProduto = request.getParameter ("txtNomeProduto");
//                String imagem = request.getParameter ("txtImagem");
//                Double preco = Double.parseDouble (request.getParameter ("txtPreco"));
//                String dataCadastro = request.getParameter ("txtData_cadastro");
//                int quantidade = Integer.parseInt (request.getParameter ("txtQuantidade"));
//                int fornecedor_pk = Integer.parseInt (request.getParameter ("comboFornecdor"));
//                String categoria1 = request.getParameter ("comboNivel_1");
//                String categoria2 = request.getParameter ("comboNivel_2");
//                String categoria3 = request.getParameter ("comboNivel_3");
//
//                ProdutoModelo produtoModelo = new ProdutoModelo ();
//                PortfolioModelo portfolioModelo = new PortfolioModelo ();
//                PortfolioDAO portfolioDAO = new PortfolioDAO ();
////                PortfolioModelo portfolioModelo1 = portfolioDAO.retornarNivel (categoria1);
//                produtoModelo.setDesignacao (nomeDoProduto);
//                produtoModelo.setPreco (preco);
//                produtoModelo.setImagem (imagem);
//                produtoModelo.setData_registro (dataCadastro);
//                produtoModelo.setQuantidade (quantidade);
//                produtoModelo.setFornecedor_fk (fornecedor_pk);
//                portfolioModelo.setDesignacao (categoria1);
//
//            }
//            else if (operacao.equalsIgnoreCase ("eliminar"))
//            {
//
//            }
//            else if (operacao.equalsIgnoreCase ("alterar"))
//            {
//
//            }
//        }
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
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest (request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ProdutoServlet.class.getName ()).log (Level.SEVERE, null, ex);
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
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest (request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ProdutoServlet.class.getName ()).log (Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

}
