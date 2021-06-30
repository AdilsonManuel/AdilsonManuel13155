/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServLet;

import DAO.ComunaDAO;
import DAO.ContaDAO;
import DAO.EmailDAO;
import DAO.EnderecoDAO;
import DAO.EstadoCivilDAO;
import DAO.FuncionarioDAO;
import DAO.MunicipioDAO;
import DAO.PaisDAO;
import DAO.PessoaDAO;
import DAO.ProvinciaDAO;
import DAO.SexoDAO;
import DAO.TelefoneDAO;
import Modelo.ContaModelo;
import Modelo.EmailModelo;
import Modelo.EnderecoModelo;
import Modelo.EstadoCivilModelo;
import Modelo.FuncionarioModelo;
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
@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet
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

            if (operacao.equals ("Cadastrar"))
            {

                /*DAO*/
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO ();
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
                FuncionarioModelo funcionarioModelo = new FuncionarioModelo ();
                PaisModelo paisModelo = new PaisModelo ();
                ProvinciaModelo provinciaModelo = new ProvinciaModelo ();
                MunicipioModelo municipioModelo = new MunicipioModelo ();

                String nome_usuario = req.getParameter ("nome_usuario");
                String senha_usuario = req.getParameter ("senha_usuario");
                int tipo_conta = ConstantesProjecto.FUNCIONARIO;

                contaModelo.setNomeUsuario (nome_usuario);
                contaModelo.setSenha_usuario (senha_usuario);
                contaModelo.setTipo_conta_fk (tipo_conta);
                contaDAO.inserirConta (contaModelo);

                int conta_fk = contaDAO.getIDconta (nome_usuario , senha_usuario);

                int comuna_fk = Integer.parseInt (req.getParameter ("cboComuna").trim ());
                String bairro = req.getParameter ("Projecto Nando");
//                int rua = Integer.parseInt ( req.getParameter ("1"));
//                int numeroCasa = Integer.parseInt (req.getParameter ("246"));

                enderecoModelo.setBairro ("Projecto Nando");
                enderecoModelo.setRua (1);
                enderecoModelo.setNumero_casa (246);
                enderecoModelo.setComunaModelo (comunaDAO.getDadosComuna (comuna_fk));

                enderecoDAO.inserirEndereco (enderecoModelo);
                int endereco_fk = enderecoDAO.getEndereco_pk (246);
                int sexo = Integer.parseInt (req.getParameter ("comboSexo").trim ());
                int sexo_fk = sexoDAO.getSexo_pk (sexo);
                int estado_civil = Integer.parseInt (req.getParameter ("comboEstado_civil").trim ());
                int estado_civil_pk = estadoCivilDAO.getEstadoCivil_pk (estado_civil);
                int tipo_email = Integer.parseInt (req.getParameter ("comboEmail"));
                int tipo_operadora = Integer.parseInt (req.getParameter ("comboOperadora"));

                String numero = req.getParameter ("txtTelefone");
                String email = req.getParameter ("txtEmail");

                telefoneModelo.setNumero (numero);
                telefoneModelo.setTipo_telefonefk (tipo_operadora);
                telefoneDAO.inserirTelefone (telefoneModelo);
                int operadora_pk = telefoneDAO.getTelefone_pk ();

                emailModelo.setEmail (email);
                emailModelo.setTipo_email_fk (tipo_email);
                emailDAO.inserirEmail (emailModelo);
                int email_pk = emailDAO.getEmail_pk ();

                String nome = req.getParameter ("txtnome");
                String data_nascimento = req.getParameter ("txtData_nascimento");

                pessoaModelo.setData_nascimento (data_nascimento);
                pessoaModelo.setEmail_fk (email_pk);
                pessoaModelo.setEndereco_fk (endereco_fk);
                pessoaModelo.setEstado_civil_fk (estado_civil_pk);
                pessoaModelo.setNome (nome);
                pessoaModelo.setSexo_fk (sexo_fk);
                pessoaModelo.setTelefone_fk (operadora_pk);

                pessoaDAO.inserirPessoa (pessoaModelo);
                int pessoa_fk = pessoaDAO.getUltimaPessoa ();

                int cboTipoFuncionario = Integer.parseInt (req.getParameter ("cboTipoFuncionario"));
                funcionarioModelo.setTipo_funcionario_fk (cboTipoFuncionario);
                funcionarioModelo.setPessoa_fk (pessoa_fk);
                funcionarioDAO.inserirFuncionario (funcionarioModelo);

                // imprime o nome do funcionario que foi adicionado
                out.println ("<html>");
                out.println ("<body>");
                out.println ("Funcionario " + pessoaModelo.getNome ()
                        + " adicionado com sucesso");
                out.println ("</body>");
                out.println ("</html>");

                resp.sendRedirect (redirecionar);

            }

        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger (FuncionarioServlet.class.getName ()).log (Level.SEVERE , null , ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger (FuncionarioServlet.class.getName ()).log (Level.SEVERE , null , ex);
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
