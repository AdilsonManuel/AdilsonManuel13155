/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ContaModelo;
import Modelo.PessoaModelo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azm
 */
public class TesteDAO
{

    public static void main (String[] args) throws SQLException, ClassNotFoundException
    {
//        PessoaModelo pessoaModelo = new PessoaModelo ();
//        PessoaDAO pessoaDAO = new PessoaDAO ();

//        pessoaModelo.setNome ("João Pedro");
//        pessoaModelo.setData_nascimento ("13/02/2006");
//        pessoaModelo.setSexo_fk (1);
//        pessoaModelo.setEstado_civil_fk (1);
//        pessoaModelo.setEndereco_fk (1);
//        pessoaModelo.setTelefone_fk (3);
//        pessoaModelo.setEmail_fk (1);
//
//        pessoaDAO.inserirPessoa (pessoaModelo);
        
//        List<PessoaModelo> lista = pessoaDAO.getListaPessoa ();
//        
//        for (PessoaModelo pessoaModelo1 : lista)
//        {
//            System.out.println ("ID -> " + pessoaModelo1.getPessoa_pk ());
//            System.out.println ("Nome -> " + pessoaModelo1.getNome () );
//            System.out.println ("Data de nascimento -> " + pessoaModelo1.getData_nascimento ());
//            System.out.println ("Sexo ->" + pessoaModelo1.getSexo_fk ());
//            System.out.println ("Estado civil -> " + pessoaModelo1.getEstado_civil_fk ());
//            System.out.println ("Endereco ->" + pessoaModelo1.getEndereco_fk ());
//            System.out.println ("Telefone ->" + pessoaModelo1.getTelefone_fk () );
//            System.out.println ("Email ->" + pessoaModelo1.getEmail_fk ());
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
        contaModelo.setTipo_conta_fk (4);
        contaModelo.setNomeUsuario ("clienteAnonimo");
        contaModelo.setSenha_usuario ("clienteAnonimo");
        
        ContaDAO contaDAO = new ContaDAO ();
        
//        contaDAO.inserirConta (contaModelo);
//        System.out.println (contaModelo.toString ());
        System.out.println (contaDAO.getIDconta ("clienteAnonimo", "clienteAnonimo"));
                
    }
}
