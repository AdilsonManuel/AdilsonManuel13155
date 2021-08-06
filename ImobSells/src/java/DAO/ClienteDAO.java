/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ClienteModelo;
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
public class ClienteDAO
{

    private Connection conexaoBD;

    public ClienteDAO () throws ClassNotFoundException, SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirCliente (ClienteModelo clienteModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.cliente(\n"
                + "	pessoa_fk, tipo_cliente_fk)\n"
                + "	VALUES ( ?, ?);"))
        {
            pst.setInt (1, clienteModelo.getPessoa_fk ());
            pst.setInt (2, clienteModelo.getTipo_cliente_fk ());

            pst.execute ();
            pst.close ();

            return true;
        }
    }

    public List<ClienteModelo> getListaCliente () throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.cliente"))
        {
            List<ClienteModelo> listaClienteModelos = new ArrayList<ClienteModelo> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                ClienteModelo clienteModelo = new ClienteModelo ();
//                clienteModelo.setCliente_pk (rs.getInt ("cliente_pk"));
                clienteModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));
//                clienteModelo.setTipo_cliente_fk (rs.getInt ("tipo_cliente_fk"));

                listaClienteModelos.add (clienteModelo);

            }
            pst.close ();
            rs.close ();
            return listaClienteModelos;
        }
    }

    public List<ClienteModelo> getListaClientePK (int clientePK) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.cliente"))
        {
            List<ClienteModelo> listaClienteModelos = new ArrayList<ClienteModelo> ();
            ResultSet rs = pst.executeQuery ();
            pst.setInt (1, clientePK);
            while (rs.next ())
            {
                ClienteModelo clienteModelo = new ClienteModelo ();
                clienteModelo.setCliente_pk (rs.getInt ("cliente_pk"));
                clienteModelo.setPessoa_fk (rs.getInt ("pessoa_fk"));
                clienteModelo.setTipo_cliente_fk (rs.getInt ("tipo_cliente_fk"));

                listaClienteModelos.add (clienteModelo);

            }
            pst.close ();
            rs.close ();
            return listaClienteModelos;
        }
    }

    public boolean eliminarCliente (ClienteModelo clienteModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.cliente SET WHERE pessoa_fk=?"))
        {
            pst.setInt (1, clienteModelo.getPessoa_fk ());

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
            String query = "SELECT * FROM public.cliente WHERE nome = " + nome;

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
        String query = "SELECT pessoa_fk FROM public.cliente";
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
