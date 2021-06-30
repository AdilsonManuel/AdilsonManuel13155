/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.FuncionarioModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author azm
 */
public class FuncionarioDAO
{

    private Connection conexaoBD;

    public FuncionarioDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirFuncionario (FuncionarioModelo funcionarioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.funcionario(\n"
                + "	pessoa_fk, tipo_funcionario_fk)\n"
                + "	VALUES ( ?, ?);"))
        {
            pst.setInt (1 , funcionarioModelo.getPessoa_fk ());
            pst.setInt (2 , funcionarioModelo.getTipo_funcionario_fk ());

            pst.execute ();
            pst.close ();

            return true;
        }
    }

    public List<FuncionarioModelo> getListaFuncionario () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.funcionario"))
        {
            List<FuncionarioModelo> listaFuncionarioModelos = new ArrayList<FuncionarioModelo> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                FuncionarioModelo funcionarioModelo = new FuncionarioModelo ();
                funcionarioModelo.setFuncionario_pk (rs.getInt ("funcionario_pk"));
                funcionarioModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));
                funcionarioModelo.setTipo_funcionario_fk (rs.getInt ("tipo_funcionario_fk"));

                listaFuncionarioModelos.add (funcionarioModelo);

            }
            pst.close ();
            rs.close ();
            return listaFuncionarioModelos;
        }
    }

    public List<FuncionarioModelo> getListaFuncionarioPK (int funconario_PK) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.funcionario"))
        {
            List<FuncionarioModelo> listaFuncionarioModelos = new ArrayList<FuncionarioModelo> ();
            ResultSet rs = pst.executeQuery ();
            pst.setInt (1 , funconario_PK);

            while (rs.next ())
            {
                FuncionarioModelo funcionarioModelo = new FuncionarioModelo ();
                funcionarioModelo.setFuncionario_pk (rs.getInt ("funcionario_pk"));
                funcionarioModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));
                funcionarioModelo.setTipo_funcionario_fk (rs.getInt ("tipo_funcionario_fk"));

                listaFuncionarioModelos.add (funcionarioModelo);

            }
            pst.close ();
            rs.close ();
            return listaFuncionarioModelos;
        }
    }

    public boolean eliminarPessoa (FuncionarioModelo funcionarioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.funcionario SET WHERE pessoa_fk=?"))
        {
            pst.setInt (1 , funcionarioModelo.getPessoa_fk ());

            pst.execute ();
            pst.close ();
            return true;
        }
    }

    public boolean redundanciaNome (String nome)
    {
        try
        {
            ResultSet rs;
            String query = "SELECT * FROM public.funcionario WHERE nome = " + nome;

            PreparedStatement pst = conexaoBD.prepareStatement (query);

            pst.setString (1 , nome);

            rs = pst.executeQuery ();
            if (rs.next ())
            {
                conexaoBD.close ();
                return true;
            }

        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog (null , ex);
        }

        return false;
    }
}
