/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author azm
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ClienteModelo extends PessoaModelo
{
    private int pessoa_fk;
    private int cliente_pk;
    

    public ClienteModelo ()
    {
    }

}
