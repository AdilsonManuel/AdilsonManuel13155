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
@EqualsAndHashCode(callSuper=false)
public class FuncionarioModelo extends PessoaModelo implements Serializable
{
    private int pessoa_fk;
    private int funcionario_pk;
    private int tipo_funcionario_fk;
    
    public FuncionarioModelo ()
    {
    }

}
