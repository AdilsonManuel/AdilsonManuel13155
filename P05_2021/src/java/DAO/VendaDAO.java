/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.VendaModelo;
import Util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author azm
 */
public class VendaDAO
{

    private Connection conexaoBD;

    public VendaDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirVendas (VendaModelo vendaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("INSERT INTO public.venda(\n"
                + "	 data_venda, total, cliente_fk, funcionario_fk, forma_pagamento_fk)\n"
                + "	VALUES ( ?, ?, ?, ?, ?);"))
        {
            pst.setString (1 , vendaModelo .getData_venda ());
            pst.setDouble (2 , vendaModelo.getTotal ());
            pst.setInt (3, vendaModelo.getCliente_fk ());
            pst.setInt (4, vendaModelo.getFuncionario_fk ());
            pst.setInt (5, vendaModelo.getForma_pagamento_fk ());

            pst.execute ();
            pst.close ();
 
            return true;
        }
    }

}
