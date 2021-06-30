/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author azm
 */
@Data
public class VendaModelo implements Serializable
{
    private int venda_pk, cliente_fk ,funcionario_fk,forma_pagamento_fk;
    private String data_venda;
    private double total ;

    public VendaModelo ()
    {
    }
    
    
}
