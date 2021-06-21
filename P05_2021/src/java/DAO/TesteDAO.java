/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ClienteModelo;
import Modelo.PessoaModelo;
import Modelo.ContaModelo;
import Modelo.PessoaModelo;
import Modelo.SexoModelo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azm
 */
public class TesteDAO
{

    public static void main (String[] args) throws SQLException , ClassNotFoundException
    {
        
        SexoDAO sexoDAO = new SexoDAO ();
        SexoModelo sexoModelo = new SexoModelo ();
        
        sexoModelo.setNome ("Masculino");
        System.out.println ("Genero -> "  + sexoDAO.getSexo_pk (sexoModelo.getNome ()));
        
        PessoaModelo PessoaModelo = new PessoaModelo ();
        PessoaDAO pessoaDAO = new PessoaDAO ();
//        System.out.println ("DAO.TesteDAO.main()"+pessoaDAO.getUltimaPessoa ());

//        PessoaModelo.setNome ("João Pedro");
//        PessoaModelo.setData_nascimento ("13/02/2006");
//        PessoaModelo.setSexo_fk (1);
//        PessoaModelo.setEstado_civil_fk (1);
//        PessoaModelo.setEndereco_fk (1);
//        PessoaModelo.setTelefone_fk (3);
//        PessoaModelo.setEmail_fk (1);
//
//        pessoaDAO.inserirPessoa (PessoaModelo);
//        List<PessoaModelo> lista = pessoaDAO.getListaPessoa ();
//        
//        for (PessoaModelo PessoaModelo1 : lista)
//        {
//            System.out.println ("ID -> " + PessoaModelo1.getPessoa_pk ());
//            System.out.println ("Nome -> " + PessoaModelo1.getNome () );
//            System.out.println ("Data de nascimento -> " + PessoaModelo1.getData_nascimento ());
//            System.out.println ("Sexo ->" + PessoaModelo1.getSexo_fk ());
//            System.out.println ("Estado civil -> " + PessoaModelo1.getEstado_civil_fk ());
//            System.out.println ("Endereco ->" + PessoaModelo1.getEndereco_fk ());
//            System.out.println ("Telefone ->" + PessoaModelo1.getTelefone_fk () );
//            System.out.println ("Email ->" + PessoaModelo1.getEmail_fk ());
//                    
//        }
//        
        //        PaisModelo paisModelo = new PaisModelo ();
//        PaisDAO paisDAO = new PaisDAO ();
//
////        paisModelo.setNome ("Brazil");
//        paisModelo.setPaisPK (2);
////        
////        paisDAO.inserirPais (paisModelo);
//
//        paisDAO.eliminarPais (paisModelo);
//        List<PaisModelo> pais = paisDAO.getListaPais ();
//
//        for (PaisModelo pai : pais)
//        {
//            
//            System.out.println ("ID -> " +pai.getPaisPK () + " - "+ " Nome do pais -> " + pai.getNome ());
//        }
        ContaModelo contaModelo = new ContaModelo ();
//        contaModelo.setTipo_conta_fk (4);
//        contaModelo.setNomeUsuario ("clienteAnonimo");
//        contaModelo.setSenha_usuario ("clienteAnonimo");
//        
        ContaDAO contaDAO = new ContaDAO ();
//        
//        contaDAO.getIDconta ("root","root");
//        System.out.println (contaDAO.getIDconta ("root" , "root");
//        System.out.println (contaDAO.getIDconta ("root", "root"));
//        PessoaModelo PessoaModelo = new PessoaModelo ();
//        PessoaModelo.setNome ("Aida Amaral");
//        PessoaModelo.setData_nascimento ("05/10/2000");
//        PessoaModelo.setSexo_fk (2);
//        PessoaModelo.setEstado_civil_fk (2);
//        PessoaModelo.setEndereco_fk (2);
//        PessoaModelo.setTelefone_fk (1);
//        PessoaModelo.setEmail_fk (3);
//        pessoaDAO.inserirPessoa (PessoaModelo);
//        System.out.println ("Pessoa modelo ->" + PessoaModelo.toString ());
        ClienteModelo clienteModelo = new ClienteModelo ();
        ClienteDAO clienteDAO = new ClienteDAO ();
//        clienteDAO.inserirCliente (PessoaModelo);

//        System.out.println ("DAO.TesteDAO.main()"+PessoaModelo.toString ());
//        List<ClienteModelo> cliente = clienteDAO.getListaCliente ();
////
//        for (ClienteModelo PessoaModelo1 : cliente)
//        {
//            System.out.println ("ID cliente-> " + PessoaModelo1.getCliente_pk ());
//            System.out.println ("ID pessoa -> " + PessoaModelo1.getPessoa_fk ());
//
//
//        }
    }
}
