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
import DAO.PessoaDAO;
import DAO.SexoDAO;
import DAO.TelefoneDAO;
import Modelo.ClienteModelo;
import Modelo.ComunaModelo;
import Modelo.ContaModelo;
import Modelo.EmailModelo;
import Modelo.EnderecoModelo;
import Modelo.EstadoCivilModelo;
import Modelo.PessoaModelo;
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
@WebServlet(name = "ClienteServlet" , urlPatterns = "/ServLet/ClienteServlet")
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
        response.setContentType ("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter ())
        {
            String operacao = request.getParameter ("operacao");

            if (operacao == ConstantesProjecto.CADASTRAR_CLIENTE)
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

                /*Modelo*/
                ContaModelo contaModelo = new ContaModelo ();
                EnderecoModelo enderecoModelo = new EnderecoModelo ();
                SexoModelo sexoModelo = new SexoModelo ();
                EstadoCivilModelo estadoCivilModelo = new EstadoCivilModelo ();
                TelefoneModelo telefoneModelo = new TelefoneModelo ();
                EmailModelo emailModelo = new EmailModelo ();
                PessoaModelo pessoaModelo = new PessoaModelo ();
                ClienteModelo clienteModelo = new ClienteModelo ();

                String nome_usuario = request.getParameter ("nome_usuario");
                String senha_usuario = request.getParameter ("senha_usuario");
                int tipo_conta = ConstantesProjecto.CLIENTE_CADASTRADO;

                contaModelo.setNomeUsuario (nome_usuario);
                contaModelo.setSenha_usuario (senha_usuario);
                contaModelo.setTipo_conta_fk (tipo_conta);
                contaDAO.inserirConta (contaModelo);

                int conta_fk = contaDAO.getIDconta (nome_usuario , senha_usuario);

                int comuna_fk = Integer.parseInt (request.getParameter ("txtComuna"));
                String bairro = request.getParameter ("txtBairro");
                String rua = request.getParameter ("txtRua");
                String numeroCasa = request.getParameter ("txtNumeroCasa");

                enderecoModelo.setBairro (bairro);
                enderecoModelo.setRua (rua);
                enderecoModelo.setNumero_casa (numeroCasa);
                enderecoModelo.setComunaModelo (comunaDAO.getDadosComuna (comuna_fk));

                enderecoDAO.inserirEndereco (enderecoModelo);

                int endereco_fk = enderecoDAO.getEndereco_pk (numeroCasa);

                String sexo = request.getParameter ("txtSexo");
                sexoModelo.setNome (sexo);
                sexoDAO.inserirSexo (sexoModelo);

                int sexo_fk = sexoDAO.getSexo_pk (sexo);

                String estado_civil = request.getParameter ("txtEstadoCivil");
                estadoCivilModelo.setNome (estado_civil);
                estadoCivilDAO.inserirEstadoCivil (estadoCivilModelo);

                int estado_civil_fk = estadoCivilDAO.getEstadoCivil_pk (estado_civil);

                String numero = request.getParameter ("txtNumero");
                String operadora = request.getParameter ("txtOperadora");
                telefoneModelo.setOperadora (operadora);
                telefoneModelo.setNumero (numero);
                telefoneDAO.inserirTelefone (telefoneModelo);

                int telefone_fk = telefoneDAO.getTelefone_pk (numero);

                String email = request.getParameter ("txtEmail");
                emailModelo.setDominio (email);
                emailDAO.inserirEmail (emailModelo);

                int email_fk = emailDAO.getEmail_pk (email);

                String nome = request.getParameter ("nome");
                String[] data = request.getParameter ("txtData_nascimento").split ("/");
                String data_nascimento = data[2] + data[1] + data[0];
                pessoaModelo.setData_nascimento (data_nascimento);
                pessoaModelo.setEmail_fk (email_fk);
                pessoaModelo.setEndereco_fk (endereco_fk);
                pessoaModelo.setEstado_civil_fk (estado_civil_fk);
                pessoaModelo.setNome (nome);
                pessoaModelo.setSexo_fk (sexo_fk);
                pessoaModelo.setTelefone_fk (telefone_fk);

                pessoaDAO.inserirPessoa (pessoaModelo);

                int pessoa_fk = pessoaDAO.getUltimaPessoa ();

                clienteModelo.setPessoa_fk (pessoa_fk);
                clienteDAO.inserirCliente (clienteModelo);

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

}
