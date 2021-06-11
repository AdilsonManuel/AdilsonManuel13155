/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PessoaModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azm
 */
public class PessoaDAO
{

    private Connection conexaoBD;

    public PessoaDAO () throws ClassNotFoundException , SQLException
    {
        conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirPessoa (PessoaModelo pessoaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.pessoa(\n"
                + "	nome, data_nascimento, sexo_fk, estado_civil_fk, endereco_fk, telefone_fk, email_fk)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?);"))
        {
            pst.setString (1 , pessoaModelo.getNome ());
            pst.setString (2 , pessoaModelo.getData_nascimento ());
            pst.setInt (3 , pessoaModelo.getSexo_fk ());
            pst.setInt (4 , pessoaModelo.getEstado_civil_fk ());
            pst.setInt (5 , pessoaModelo.getEndereco_fk ());
            pst.setInt (6 , pessoaModelo.getTelefone_fk ());
            pst.setInt (7 , pessoaModelo.getEmail_fk ());

            pst.execute ();
            pst.close ();
            return true;
        }
    }

    public List<PessoaModelo> getListaPessoa () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pessoa"))
        {
            List<PessoaModelo> listaPessoaModelos = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                PessoaModelo pessoaModelo = new PessoaModelo ();
                pessoaModelo.setPessoa_pk (rs.getInt ("pessoa_pk"));
                pessoaModelo.setNome (rs.getString ("nome"));
                pessoaModelo.setData_nascimento (rs.getString ("data_nascimento"));
                pessoaModelo.setSexo_fk (rs.getInt ("sexo_fk"));
                pessoaModelo.setEstado_civil_fk (rs.getInt ("estado_civil_fk"));
                pessoaModelo.setEndereco_fk (rs.getInt ("endereco_fk"));
                pessoaModelo.setTelefone_fk (rs.getInt ("telefone_fk"));
                pessoaModelo.setEmail_fk (rs.getInt ("email_fk"));

                listaPessoaModelos.add (pessoaModelo);
            }
            pst.close ();
            rs.close ();
            return listaPessoaModelos;
        }
    }

    public List<PessoaModelo> getListaPessoaPK (int pessoaPK) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pessoa WHERE pessoa_pk=? ORDER BY pessoa_pk ASC"))
        {
            List<PessoaModelo> listaPessoaModelos = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();
            pst.setInt (1 , pessoaPK);
            while (rs.next ())
            {
                PessoaModelo pessoaModelo = new PessoaModelo ();
                pessoaModelo.setNome (rs.getString ("nome"));
                pessoaModelo.setData_nascimento (rs.getString ("data_nascimento"));
                pessoaModelo.setSexo_fk (rs.getInt ("sexo_fk"));
                pessoaModelo.setEstado_civil_fk (rs.getInt ("estado_civil_fk"));
                pessoaModelo.setEndereco_fk (rs.getInt ("endereco_fk"));
                pessoaModelo.setTelefone_fk (rs.getInt ("telefone_fk"));
                pessoaModelo.setEmail_fk (rs.getInt ("email_fk"));

                listaPessoaModelos.add (pessoaModelo);
            }
            pst.close ();
            rs.close ();
            return listaPessoaModelos;
        }
    }

    public boolean alterarPessoa (PessoaModelo pessoaModelo) throws SQLException
    {
        if (pessoaModelo != null)
        {
            try (PreparedStatement pst = this.conexaoBD.prepareStatement ("UPDATE public.pessoa SET nome=?, data_nascimento=?, sexo_fk=?, estado_civil_fk=?, "
                    + "                                                     endereco_fk=?, telefone_fk=?, email_fk=? WHERE pessoa_pk=?"))
            {
                pst.setString (1 , pessoaModelo.getNome ());
                pst.setString (2 , pessoaModelo.getData_nascimento ());
                pst.setInt (3 , pessoaModelo.getSexo_fk ());
                pst.setInt (4 , pessoaModelo.getEstado_civil_fk ());
                pst.setInt (5 , pessoaModelo.getEndereco_fk ());
                pst.setInt (6 , pessoaModelo.getTelefone_fk ());
                pst.setInt (7 , pessoaModelo.getEmail_fk ());
                pst.setInt (8 , pessoaModelo.getPessoa_pk ());

                pst.execute ();
                pst.close ();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPessoa (PessoaModelo pessoaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.pessoa SET WHERE pessoa_pk=?"))
        {
            pst.setInt (1 , pessoaModelo.getPessoa_pk ());

            pst.execute ();
            pst.close ();
            return true;
        }
    }
    
    public int getUltimaPessoa () throws SQLException
    {
        try ( PreparedStatement pst = conexaoBD.prepareStatement ("SELECT MAX(public.pessoa.pessoa_pk) FROM public.pessoa"))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();
            
            return rs.getInt (1);
        }
    }
}
