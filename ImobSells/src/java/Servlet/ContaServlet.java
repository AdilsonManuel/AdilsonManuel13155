/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ContaDAO;
import Modelo.ContaModelo;
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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import util.ConstantesProjecto;

/**
 *
 * @author azm
 */
@WebServlet(name = "ContaServlet" , urlPatterns = "/ServLet/ContaServlet")
public class ContaServlet extends HttpServlet
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
            throws ServletException , IOException , ClassNotFoundException , SQLException
    {
        response.setContentType ("text/html;charset=UTF-8");
        HttpSession httpSession = request.getSession ();
        try (PrintWriter out = response.getWriter ())
        {
            ContaDAO contaDAO = new ContaDAO ();
            ContaModelo contaModelo = new ContaModelo ();

            String nome_usuario = request.getParameter ("nome_usuario");
            String senha_usuario = request.getParameter ("senha_usuario");

            contaModelo.setNomeUsuario (nome_usuario);
            contaModelo.setSenha_usuario (senha_usuario);

            int tipo_conta = contaDAO.getIDconta (contaModelo);

            if (tipo_conta != ConstantesProjecto.INVALID)
            {
                if (tipo_conta == ConstantesProjecto.ROOT)
                {
                 httpSession.setMaxInactiveInterval (3600 / 2);
                 response.sendRedirect ("homeRoot.jsp");
                }
                else if (tipo_conta == ConstantesProjecto.ADMIN)
                {
                 httpSession.setMaxInactiveInterval (3600 / 2);
                 response.sendRedirect ("homeAdmin.jsp");
                }
                else if (tipo_conta == ConstantesProjecto.FUNCIONARIO)
                {
                 httpSession.setMaxInactiveInterval (3600 / 2);
                 response.sendRedirect ("homeFuncionario.jsp");
                }
                else if (tipo_conta == ConstantesProjecto.CLIENTE_CADASTRADO)
                {
                 httpSession.setMaxInactiveInterval (3600 / 2);
                 response.sendRedirect ("homeClienteCadastrado.jsp");
                }
                else if (tipo_conta == ConstantesProjecto.CLIENTE_ANONIMO)
                {
                 httpSession.setMaxInactiveInterval (3600 / 2);
                 response.sendRedirect ("homeClienteAnonimo.jsp");
                }
            }
            else

            {
                JOptionPane.showMessageDialog (null , "Erro\nNome ou Senha incorrectos" , "Erro de login" , JOptionPane.ERROR_MESSAGE , null);
                response.sendRedirect ("index.jsp");
            }

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
        try
        {
            processRequest (request , response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ContaServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (ContaServlet.class.getName ()).log (Level.SEVERE , null , ex);
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
    protected void doPost (HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException
    {
        try
        {
            processRequest (request , response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ContaServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (ContaServlet.class.getName ()).log (Level.SEVERE , null , ex);
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
