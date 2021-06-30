/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServLet;

import DAO.ProdutoDAO;
import Modelo.ProdutoModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet("/ProdutoServlet")
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
    protected void processRequest (HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException
    {
    }

    @Override
    protected void service (HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException
    {
        resp.setContentType ("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter ())
        {
            String operacao = req.getParameter ("operacao");
            String redirecionar = req.getParameter ("redirecionar");

            ProdutoDAO produtoDAO = new ProdutoDAO ();
            ProdutoModelo produtoModelo = new ProdutoModelo ();

            String designacao = req.getParameter ("txt_descricao");
            String imagem = req.getParameter ("txt_imagem");
            String portfoli_codigo = req.getParameter ("txt_portfolio_codigo");
            Double preco = Double.parseDouble (req.getParameter ("txt_preco"));
            int fornecedor = Integer.parseInt (req.getParameter ("txtFornecedor"));
            int quantidate = Integer.parseInt (req.getParameter ("txt_quantidade"));
            String data_registo = req.getParameter ("txt_data_registo");

            produtoModelo.setDesignacao (designacao);
            produtoModelo.setImagem (imagem);
            produtoModelo.setPortfolio_fk (portfoli_codigo);
            produtoModelo.setPreco (preco);
            produtoModelo.setFornecedor_fk (fornecedor);
            produtoModelo.setQuantidade (quantidate);
            produtoModelo.setData_registro (data_registo);

            produtoDAO.inserirProduto (produtoModelo);

            // imprime o nome do produto que foi adicionado
            out.println ("<html>");
            out.println ("<body>");
            out.println ("Prduto " + produtoModelo.getDesignacao () + " adicionado com sucesso");
            out.println ("</body>");
            out.println ("</html>");

            resp.sendRedirect (redirecionar);

        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ProdutoServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (ProdutoServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
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
    protected void doGet (HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException
    {
        processRequest (request , response);
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
    protected void doPost (HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException
    {
        processRequest (request , response);
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
