/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServLet;

import DAO.ClienteDAO;
import DAO.ComunaDAO;
import DAO.ContaDAO;
import DAO.EmailDAO;
import DAO.EnderecoDAO;
import DAO.EstadoCivilDAO;
import DAO.MunicipioDAO;
import DAO.PaisDAO;
import DAO.PessoaDAO;
import DAO.ProvinciaDAO;
import DAO.SexoDAO;
import DAO.TelefoneDAO;
import Modelo.ClienteModelo;
import Modelo.ContaModelo;
import Modelo.EmailModelo;
import Modelo.EnderecoModelo;
import Modelo.EstadoCivilModelo;
import Modelo.MunicipioModelo;
import Modelo.PaisModelo;
import Modelo.PessoaModelo;
import Modelo.ProvinciaModelo;
import Modelo.SexoModelo;
import Modelo.TelefoneModelo;
import Util.ConstantesProjecto;
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
@WebServlet("/ClienteServlet")
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
            throws ServletException , IOException , SQLException , ClassNotFoundException
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
    protected void doGet (HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException
    {
        try
        {
            processRequest (request , response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
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
        catch (SQLException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (ClienteServlet.class.getName ()).log (Level.SEVERE , null , ex);
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

    @Override
    protected void service (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException
    {
        response.setContentType ("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter ())
        {
            String operacao = request.getParameter ("operacao");
            String redirecionar = request.getParameter ("redirecionar");

            if (operacao.equals ("Cadastrar"))
            {

                /*DAO*/
                ClienteDAO clienteDAO = new ClienteDAO ();
                ContaDAO contaDAO = new ContaDAO ();
                EnderecoDAO enderecoDAO = new EnderecoDAO ();
                ComunaDAO comunaDAO = new ComunaDAO ();
                SexoDAO sexoDAO = new SexoDAO ();
                EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO ();
                TelefoneDAO telefoneDAO = new TelefoneDAO ();
                EmailDAO emailDAO = new EmailDAO ();
                PessoaDAO pessoaDAO = new PessoaDAO ();
                MunicipioDAO municipioDAO = new MunicipioDAO ();
                ProvinciaDAO provinciaDAO = new ProvinciaDAO ();
                PaisDAO paisDAO = new PaisDAO ();

                /*Modelo*/
                ContaModelo contaModelo = new ContaModelo ();
                EnderecoModelo enderecoModelo = new EnderecoModelo ();
                SexoModelo sexoModelo = new SexoModelo ();
                EstadoCivilModelo estadoCivilModelo = new EstadoCivilModelo ();
                TelefoneModelo telefoneModelo = new TelefoneModelo ();
                EmailModelo emailModelo = new EmailModelo ();
                PessoaModelo pessoaModelo = new PessoaModelo ();
                ClienteModelo clienteModelo = new ClienteModelo ();
                PaisModelo paisModelo = new PaisModelo ();
                ProvinciaModelo provinciaModelo = new ProvinciaModelo ();
                MunicipioModelo municipioModelo = new MunicipioModelo ();

                String nome_usuario = request.getParameter ("nome_usuario");
                String senha_usuario = request.getParameter ("senha_usuario");
                int tipo_conta = ConstantesProjecto.CLIENTE_CADASTRADO;

                contaModelo.setNomeUsuario (nome_usuario);
                contaModelo.setSenha_usuario (senha_usuario);
                contaModelo.setTipo_conta_fk (tipo_conta);
                contaDAO.inserirConta (contaModelo);

                int conta_fk = contaDAO.getIDconta (nome_usuario , senha_usuario);

                int comuna_fk = Integer.parseInt (request.getParameter ("cboComuna").trim ());
                String bairro = request.getParameter ("Projecto Nando");
//                int rua = Integer.parseInt ( request.getParameter ("1"));
//                int numeroCasa = Integer.parseInt (request.getParameter ("246"));

                enderecoModelo.setBairro ("Projecto Nando");
                enderecoModelo.setRua (1);
                enderecoModelo.setNumero_casa (246);
                enderecoModelo.setComunaModelo (comunaDAO.getDadosComuna (comuna_fk));

                enderecoDAO.inserirEndereco (enderecoModelo);
                int endereco_fk = enderecoDAO.getEndereco_pk (246);
                int sexo = Integer.parseInt (request.getParameter ("comboSexo").trim ());
                int sexo_fk = sexoDAO.getSexo_pk (sexo);
                int estado_civil = Integer.parseInt (request.getParameter ("comboEstado_civil").trim ());
                int estado_civil_pk = estadoCivilDAO.getEstadoCivil_pk (estado_civil);
                int email = Integer.parseInt (request.getParameter ("comboEmail"));
                int operadora = Integer.parseInt (request.getParameter ("comboTelefone"));

                String numero = request.getParameter ("txtNumero");
                String nome = request.getParameter ("txtnome");
                String data_nascimento = request.getParameter ("txtData_nascimento");

                pessoaModelo.setData_nascimento (data_nascimento);
                pessoaModelo.setEmail_fk (email);
                pessoaModelo.setEndereco_fk (endereco_fk);
                pessoaModelo.setEstado_civil_fk (estado_civil_pk);
                pessoaModelo.setNome (nome);
                pessoaModelo.setSexo_fk (sexo_fk);
                pessoaModelo.setTelefone_fk (operadora);

                pessoaDAO.inserirPessoa (pessoaModelo);
                int pessoa_fk = pessoaDAO.getUltimaPessoa ();

                clienteModelo.setPessoa_fk (pessoa_fk);
                clienteDAO.inserirCliente (clienteModelo);

                // imprime o nome do cliente que foi adicionado
                out.println ("<html>");
                out.println ("<body>");
                out.println ("Cliente " + pessoaModelo.getNome ()
                        + " adicionado com sucesso");
                out.println ("</body>");
                out.println ("</html>");

                response.sendRedirect (redirecionar);

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

}
