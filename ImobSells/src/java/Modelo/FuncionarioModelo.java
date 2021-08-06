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
public class FuncionarioModelo extends PessoaModelo implements Serializable
{
    private int funcionario_pk,tipo_funcionario_fk,pessoa_fk;

    public FuncionarioModelo (int funcionario_pk, int tipo_funcionario_fk, int pessoa_fk)
    {
        this.funcionario_pk = funcionario_pk;
        this.tipo_funcionario_fk = tipo_funcionario_fk;
        this.pessoa_fk = pessoa_fk;
    }

    public FuncionarioModelo ()
    {
    }
    
    
}
