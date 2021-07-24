/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ContaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexaoBD;
import util.ConstantesProjecto;

/**
 *
 * @author azm
 */
public class ContaDAO
{

    private Connection conexaoBD;

    public ContaDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public ResultSet getRegisto (ContaModelo contaModelo) throws SQLException
    {
        String query = "SELECT * FROM PUBLIC.conta WHERE nome_usuario = '" + contaModelo.getNomeUsuario () + "'AND senha_usuario ='" + contaModelo.getSenha_usuario () + "'";
        try (PreparedStatement pst = this.conexaoBD.prepareStatement (query))
        {
            return pst.executeQuery ();
        }

    }

    public int getIDconta (ContaModelo contaModelo) throws ClassNotFoundException , SQLException
    {
        String query = "SELECT * FROM PUBLIC.conta WHERE nome_usuario='" + contaModelo.getNomeUsuario () + "' AND senha_usuario='" + contaModelo.getSenha_usuario () + "'";
        PreparedStatement pst = this.conexaoBD.prepareStatement (query);
        ResultSet rs = pst.executeQuery ();

        try
        {
            if (rs.next ())
            {
                return rs.getInt ("conta_pk");
            }
            return ConstantesProjecto.INVALID;
        }
        catch (SQLException e)
        {
            System.err.println (e);
        }
        return 0;

    }

    public int validarConta (ContaModelo contaModelo) throws ClassNotFoundException , SQLException
    {

        ResultSet rs = getRegisto (contaModelo);

        try
        {
            if (rs.next ())
            {
                return rs.getInt ("tipo_conta_fk");
            }
            return ConstantesProjecto.INVALID;
        }
        catch (SQLException e)
        {
            System.err.println (e);
        }
        return 0;
    }

    public List<ContaModelo> ListarTodasContas () throws SQLException
    {
        String query = "SELECT * FROM PUBLIC.conta";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (query))
        {
            List<ContaModelo> listConta = new ArrayList<ContaModelo> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                ContaModelo contaModelo = new ContaModelo ();
                contaModelo.setTipo_conta_fk (rs.getInt ("tipo_conta_fk"));
                contaModelo.setNomeUsuario (rs.getString ("nome_usuario"));
                contaModelo.setSenha_usuario (rs.getString ("senha_usuario"));

                listConta.add (contaModelo);
            }
            pst.close ();
            rs.close ();

            return listConta;
        }

    }

    public void inserirConta (ContaModelo contaModelo) throws SQLException
    {
        String query = "INSERT INTO public.conta(\n"
                + "tipo_conta_fk, nome_usuario, senha_usuario)\n"
                + "VALUES ( ?, ?, ?);";

        try (PreparedStatement pst = this.conexaoBD.prepareStatement (query))
        {
            pst.setInt (1 , contaModelo.getTipo_conta_fk ());
            pst.setString (2 , contaModelo.getNomeUsuario ());
            pst.setString (3 , contaModelo.getSenha_usuario ());

            pst.execute ();
            pst.close ();
        }
    }

}
