/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DAO.ClienteDAO;
import DAO.ContaDAO;
import DAO.EmailDAO;
import DAO.EnderecoDAO;
import DAO.EstadoCivilDAO;
import DAO.PessoaDAO;
import DAO.SexoDAO;
import DAO.TelefoneDAO;
import Modelo.ClienteModelo;
import Modelo.ContaModelo;
import Modelo.EmailModelo;
import Modelo.EnderecoModelo;
import Modelo.EstadoCivilModelo;
import Modelo.PessoaModelo;
import Modelo.SexoModelo;
import Modelo.TelefoneModelo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azm
 */
public class TesteProjecto
{

    public static void main (String[] args) throws ClassNotFoundException, SQLException
    {
        /*Teste conexão*/
//        Connection conexao = new ConexaoBD ().getConnection ();
//        System.out.println ("Conection looks good!!");
//        conexao.close ();

        /*Teste contas*/
        ContaModelo contaModelo = new ContaModelo ();
        ContaDAO contaDAO = new ContaDAO ();

//        List<ContaModelo> listaConta = contaDAO.ListarTodasContas ();
//        
//        for ( ContaModelo contaModelo1 : listaConta)
//        {
//            System.out.println ("Tipo de conta -> "+ contaModelo1.getTipo_conta_fk ());
//            System.out.println ("Nome do usuario -> "+contaModelo1.getNomeUsuario ());
//            System.out.println ("Senha do usuario -> " +contaModelo1.getSenha_usuario ());
//            System.out.println ("----------------------------------------------------");
//        }
//        contaModelo.setNomeUsuario ("root");
//        contaModelo.setSenha_usuario ("root");
//        System.out.println ("ID -> "+ contaDAO.getIDconta (contaModelo)  );

        /*Teste pessa*/
// /*Modelos*/
        PessoaModelo pessoaModelo = new PessoaModelo ();
//        PessoaDAO pessoaDAO = new PessoaDAO ();
//        SexoModelo sexoModelo = new SexoModelo ();
//        EstadoCivilModelo estadoCivilModelo = new EstadoCivilModelo ();
//        EnderecoModelo enderecoModelo = new EnderecoModelo ();
//        TelefoneModelo telefoneModelo = new TelefoneModelo ();
//        EmailModelo emailModelo = new EmailModelo ();
//        /*DAO*/
//        SexoDAO sexoDAO = new SexoDAO ();
//        EstadoCivilDAO estadoCivilDAO = new EstadoCivilDAO ();
//        EnderecoDAO enderecoDAO = new EnderecoDAO ();
//        TelefoneDAO telefoneDAO = new TelefoneDAO ();
//        EmailDAO emailDAO = new EmailDAO ();
//        /*Definição*/
//        sexoModelo.setSexo_pk (1);
//        estadoCivilModelo.setEstado_civili_pk (1);
//        telefoneModelo.setTelefone_pk (1);
//        enderecoModelo.setEndereco_pk (1);
//        emailModelo.setEmail_pk (1);
//
//        int sexoID = sexoModelo.getSexo_pk ();
//        int estado_civilID = estadoCivilModelo.getEstado_civili_pk ();
//        int telefoneID = telefoneModelo.getTelefone_pk ();
//        int enderecoID = enderecoModelo.getEndereco_pk ();
//        int emailID = emailModelo.getEmail_pk ();
//
//        pessoaModelo.setNome ("Aida Amaral");
//        pessoaModelo.setData_nascimento ("21-07-2021");
//        pessoaModelo.setSexo_fk (sexoDAO.getSexo_pk (sexoID));
//        pessoaModelo.setEstado_civil_fk (estadoCivilDAO.getEstadoCivil_pk (estado_civilID));
//        pessoaModelo.setEndereco_fk (enderecoDAO.getEndereco_pk (enderecoID));
//        pessoaModelo.setTelefone_fk (telefoneDAO.getTelefone (telefoneID));
//        pessoaModelo.setEmail_fk (emailDAO.getEmail_pk (emailID));
//        
//        System.out.println ("Pessoa modelo ->" + pessoaModelo.toString ());
//        System.out.println ("Last address ->" + telefoneDAO.pegarUltimoTelefone ());
//        System.out.println ("Last address ->" + emailDAO.pegarUltimoEmail ());
//        System.out.println ("Last address ->" + enderecoDAO.pegarUltimoEndereco ());
        ClienteDAO clienteDAO = new ClienteDAO ();
        PessoaDAO pessoaDAO = new PessoaDAO ();
        List<ClienteModelo> cliente = clienteDAO.getListaCliente ();
       
        for (ClienteModelo clienteModelo : cliente)
        {
            List<PessoaModelo> pessoaModelos = pessoaDAO.getDadosPessoa (clienteModelo.getPessoa_fk ());
            for (PessoaModelo pessoaModelo1 : pessoaModelos)
            {
                System.out.println ("ID -> " + pessoaModelo1.getPessoa_pk () + " - "
                        + "Nome -> " + pessoaModelo1.getNome () + " - "
                        + "Sexo -> " + pessoaModelo1.getSexo_fk ().getNome () + " - "
                        + "Data de Nascimento -> " + pessoaModelo1.getData_nascimento () + " - "
                        + "Telfeone -> " + pessoaModelo1.getTelefone_fk ().getNumero () + " - "
                        + "Estado civil -> " + pessoaModelo1.getEstado_civil_fk ().getNome () + " - "
                        + "Endereco ->" + pessoaModelo1.getEndereco_fk ().getNumero_casa () + "," + pessoaModelo1.getEndereco_fk ().getRua () + ","
                        + pessoaModelo1.getEndereco_fk ().getLocalizacaoModelo ().getDesignacao () + " - "
                        + "Email ->" + pessoaModelo1.getEmail_fk ().getNome ());

            }
        }

//        pessoaDAO.inserirPessoa (pessoaModelo);
    }

}
