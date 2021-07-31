/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author azm
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClienteModelo extends PessoaModelo implements Serializable
{

    private int pessoa_fk;
    private int cliente_pk;
    private int tipo_cliente_fk;

    public ClienteModelo (int pessoa_fk , int cliente_pk , int tipo_cliente_fk)
    {
        this.pessoa_fk = pessoa_fk;
        this.cliente_pk = cliente_pk;
        this.tipo_cliente_fk = tipo_cliente_fk;
    }

    public ClienteModelo ()
    {
    }

}
