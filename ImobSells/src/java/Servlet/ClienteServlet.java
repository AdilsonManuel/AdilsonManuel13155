/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ClienteDAO;
import DAO.ContaDAO;
import DAO.EmailDAO;
import DAO.EnderecoDAO;
import DAO.EstadoCivilDAO;
import DAO.LocalizacaoDAO;
import DAO.PessoaDAO;
import DAO.SexoDAO;
import DAO.TelefoneDAO;
import Modelo.ClienteModelo;
import Modelo.ContaModelo;
import Modelo.EmailModelo;
import Modelo.EnderecoModelo;
import Modelo.EstadoCivilModelo;
import Modelo.LocalizacaoModelo;
import Modelo.PessoaModelo;
import Modelo.SexoModelo;
import Modelo.TelefoneModelo;
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
import util.ConstantesProjecto;

/**
 *
 * @author azm
 */
@WebServlet(name = "ContaServlet" , urlPatterns = "/ServLet/ContaServlet")
public class ClienteServlet extends HttpServlet
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
        response.setContentType ("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter ())
        {

        }
    }

    @Override
    protected void service (HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException
    {
        resp.setContentType ("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter ())
        {
            String operacao = req.getParameter ("operacao");
            String redirecionar = req.getParameter ("redirecionar");

            if (operacao.equals ("Cadastrar"))
            {

                /*DAO*/
                ClienteDAO clienteDAO = new ClienteDAO ();
                ContaDAO contaDAO = new ContaDAO ();
                EnderecoDAO enderecoDAO = new EnderecoDAO ();
                LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO ();
                SexoDAO sexoDAO = new SexoDAO ();
                EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO ();
                TelefoneDAO telefoneDAO = new TelefoneDAO ();
                EmailDAO emailDAO = new EmailDAO ();
                PessoaDAO pessoaDAO = new PessoaDAO ();


                /*Modelo*/
                ContaModelo contaModelo = new ContaModelo ();
                EnderecoModelo enderecoModelo = new EnderecoModelo ();
                SexoModelo sexoModelo = new SexoModelo ();
                EstadoCivilModelo estadoCivilModelo = new EstadoCivilModelo ();
                TelefoneModelo telefoneModelo = new TelefoneModelo ();
                EmailModelo emailModelo = new EmailModelo ();
                PessoaModelo pessoaModelo = new PessoaModelo ();
                ClienteModelo clienteModelo = new ClienteModelo ();
                LocalizacaoModelo localizacaoModelo = new LocalizacaoModelo ();

                String nome_usuario = req.getParameter ("nome_usuario");
                String senha_usuario = req.getParameter ("senha_usuario");
                int tipo_conta = ConstantesProjecto.CLIENTE_CADASTRADO;

                contaModelo.setNomeUsuario (nome_usuario);
                contaModelo.setSenha_usuario (senha_usuario);
                contaModelo.setTipo_conta_fk (tipo_conta);
                contaDAO.inserirConta (contaModelo);

                int conta_fk = contaDAO.getIDconta (contaModelo);

                sexoModelo.setSexo_pk (Integer.parseInt (req.getParameter ("comboSexo").trim ()));
                estadoCivilModelo.setEstado_civili_pk (Integer.parseInt (req.getParameter ("comboEstado_civil").trim ()));
                telefoneModelo.setNumero (req.getParameter ("txtTelefone"));
                emailModelo.setNome (req.getParameter ("txtEmail"));

                String nome = req.getParameter ("txtnome");
                String data_nascimento = req.getParameter ("txtData_nascimento");

                int sexoID = sexoModelo.getSexo_pk ();
                int estado_civilID = estadoCivilModelo.getEstado_civili_pk ();
                int telefoneID = telefoneModelo.getTelefone_pk ();
                int enderecoID = enderecoModelo.getEndereco_pk ();
                int emailID = emailModelo.getEmail_pk ();

                pessoaModelo.setNome (nome);
                pessoaModelo.setData_nascimento (data_nascimento);
                pessoaModelo.setSexo_fk (sexoDAO.getSexo_pk (sexoID));
                pessoaModelo.setEstado_civil_fk (estadoCivilDAO.getEstadoCivil_pk (estado_civilID));
                pessoaModelo.setEndereco_fk (enderecoDAO.getEndereco_pk (enderecoID));
                pessoaModelo.setTelefone_fk (telefoneDAO.getTelefone (telefoneID));
                pessoaModelo.setEmail_fk (emailDAO.getEmail_pk (emailID));

                pessoaDAO.inserirPessoa (pessoaModelo);
                int ultima_pessoa = pessoaDAO.getUltimaPessoa ();

                int cboTipoCLiente = Integer.parseInt (req.getParameter ("comboTipoCLiente"));
                clienteModelo.setTipo_cliente_fk (cboTipoCLiente);
                clienteModelo.setPessoa_fk (ultima_pessoa);
                clienteDAO.inserirCliente (clienteModelo);

                // imprime o nome do cliente que foi adicionado
                out.println ("<html>");
                out.println ("<body>");
                out.println ("Cliente " + pessoaModelo.getNome ()
                        + " adicionado com sucesso");
                out.println ("</body>");
                out.println ("</html>");

                resp.sendRedirect (redirecionar);

            }
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
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
