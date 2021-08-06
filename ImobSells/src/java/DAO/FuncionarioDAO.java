/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.FuncionarioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.ConexaoBD;
import util.ConstantesProjecto;

/**
 *
 * @author azm
 */
public class FuncionarioDAO
{
    private Connection conexaoBD;

    public FuncionarioDAO () throws ClassNotFoundException, SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirFuncionario (FuncionarioModelo funcionarioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.funcionario(\n"
                + "	pessoa_fk, tipo_funcionario_fk)\n"
                + "	VALUES ( ?, ?);"))
        {
            pst.setInt (1, funcionarioModelo.getPessoa_fk ());
            pst.setInt (2, funcionarioModelo.getTipo_funcionario_fk ());

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
//                funcionarioModelo.setFuncionario_pk (rs.getInt ("cliente_pk"));
                funcionarioModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));
//                funcionarioModelo.setTipo_cliente_fk (rs.getInt ("tipo_cliente_fk"));

                listaFuncionarioModelos.add (funcionarioModelo);

            }
            pst.close ();
            rs.close ();
            return listaFuncionarioModelos;
        }
    }

    public List<FuncionarioModelo> getListaFuncionarioPK (int clientePK) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.funcionario"))
        {
            List<FuncionarioModelo> listaFuncionarioModelos = new ArrayList<FuncionarioModelo> ();
            ResultSet rs = pst.executeQuery ();
            pst.setInt (1, clientePK);
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

    public boolean eliminarFuncionario (FuncionarioModelo funcionarioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.funcionario SET WHERE pessoa_fk=?"))
        {
            pst.setInt (1, funcionarioModelo.getPessoa_fk ());

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

            pst.setString (1, nome);

            rs = pst.executeQuery ();
            if (rs.next ())
            {
                conexaoBD.close ();
                return true;
            }

        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog (null, ex);
        }

        return false;
    }

    public int getIDpessoa () throws ClassNotFoundException, SQLException
    {
        String query = "SELECT pessoa_fk FROM public.funcionario";
        PreparedStatement pst = this.conexaoBD.prepareStatement (query);
        ResultSet rs = pst.executeQuery ();

        try
        {
            if (rs.next ())
            {
                return rs.getInt ("pessoa_fk");
            }
            return ConstantesProjecto.INVALID;
        }
        catch (SQLException e)
        {
            System.err.println (e);
        }
        return 0;

    }

}
