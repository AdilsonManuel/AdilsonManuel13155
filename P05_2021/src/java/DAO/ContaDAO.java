/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ContaModelo;
import Util.ConexaoBD;
import Util.ConstantesProjecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azm
 */
public class ContaDAO
{

    private Connection conexaoBD;
    private ContaModelo contaModelo;

    public ContaDAO () throws ClassNotFoundException, SQLException
    {
        conexaoBD = new ConexaoBD ().getConnection ();
    }

    public static int getIDconta (String userName , String password) throws ClassNotFoundException, SQLException
    {
        ContaDAO contaDAO = new ContaDAO ();
        ResultSet rs = contaDAO.getRegisto (userName , password);

        try
        {
            if (rs.next ())
            {
                return rs.getInt ("conta_pk");
            }
            else
            {
                return ConstantesProjecto.INVALID;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger (ContaDAO.class.getName ()).log (Level.SEVERE , null , e);
        }
        return 0;
    }

    public static int validarConta (String userName , String password) throws ClassNotFoundException, SQLException
    {
        ContaDAO contaDAO = new ContaDAO ();
        ResultSet rs = contaDAO.getRegisto (userName , password);

        try
        {
            if (rs.next ())
            {
                return rs.getInt ("tipo_conta_fk");
            }
            else
            {
                return ConstantesProjecto.INVALID;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger (ContaDAO.class.getName ()).log (Level.SEVERE , null , e);
        }
        return 0;
    }

    public ResultSet getRegisto (String userName , String password)
    {
        try
        {
            PreparedStatement pst = conexaoBD.prepareStatement ("SELECT * FROM public.conta WHERE nome_usuario = '" + userName + "'AND senha_usuario= '" + password + "'");
            return pst.executeQuery ();
        }
        catch (SQLException e)
        {
            Logger.getLogger (ContaDAO.class.getName ()).log (Level.SEVERE , null , e);
        }
        return null;
    }

    public ResultSet getTodosRegistos ()
    {
        try
        {
            PreparedStatement pst = conexaoBD.prepareStatement ("SELECT * FROM public.conta");
            return pst.executeQuery ();
        }
        catch (SQLException e)
        {
            Logger.getLogger (ContaDAO.class.getName ()).log (Level.SEVERE , null , e);
        }
        return null;
    }

    public void inserirConta (ContaModelo contaModelo) throws SQLException
    {
        String sql = "    INSERT INTO public.conta(\n"
                + "	tipo_conta_fk, nome_usuario, senha_usuario)\n"
                + "	VALUES ( ?, ?, ?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1 , contaModelo.getTipo_conta_fk ());
            pst.setString (2 , contaModelo.getNomeUsuario ());
            pst.setString (3 , contaModelo.getSenha_usuario ());

            pst.execute ();
            pst.close ();

        }
    }
}
