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
import javax.swing.JOptionPane;

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

//                int comuna_fk = Integer.parseInt (request.getParameter ("txtComuna"));
//                String bairro = request.getParameter ("txtBairro");
//                String rua = request.getParameter ("txtRua");
//                String numeroCasa = request.getParameter ("txtNumeroCasa");
//
//                enderecoModelo.setBairro (bairro);
//                enderecoModelo.setRua (rua);
//                enderecoModelo.setNumero_casa (numeroCasa);
//                enderecoModelo.setComunaModelo (comunaDAO.getDadosComuna (comuna_fk));
//
//                enderecoDAO.inserirEndereco (enderecoModelo);
//
//                int endereco_fk = enderecoDAO.getEndereco_pk (numeroCasa);
                int sexo = Integer.parseInt (request.getParameter ("txtSexo"));
                int estado_civil = Integer.parseInt (request.getParameter ("txtEstadoCivil"));
                int email = Integer.parseInt (request.getParameter ("txtEmail"));
                int operadora = Integer.parseInt (request.getParameter ("txtOperadora"));
                
                String numero = request.getParameter ("txtNumero");

                String nome = request.getParameter ("txtnome");
                String[] data = request.getParameter ("txtData_nascimento").split ("/");
                String data_nascimento = data[0] + data[1] + data[2];
                pessoaModelo.setData_nascimento (data_nascimento);
                pessoaModelo.setEmail_fk (email);
//                pessoaModelo.setEndereco_fk (endereco_fk);
                pessoaModelo.setEstado_civil_fk (estado_civil);
                pessoaModelo.setNome (nome);
                pessoaModelo.setSexo_fk (sexo);
                pessoaModelo.setTelefone_fk (operadora);

                int pessoa_fk = pessoaDAO.getUltimaPessoa ();

                clienteModelo.setPessoa_fk (pessoa_fk);
                clienteDAO.inserirCliente (clienteModelo);

                response.sendRedirect (redirecionar);

                // imprime o nome do contato que foi adicionado
                out.println ("<html>");
                out.println ("<body>");
                out.println ("Cliente " + pessoaModelo.getNome ()
                        + " adicionado com sucesso");
                out.println ("</body>");
                out.println ("</html>");

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
