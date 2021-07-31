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
public class FornecedorModelo extends PessoaModelo implements Serializable 
{
    private int fornecedor_pk ;
    private String nome; 
    private int tipo_fornecedor_fk;
    private int pessoa_fk;

    public FornecedorModelo (int fornecedor_pk, String nome, int tipo_fornecedor_fk, int pessoa_fk)
    {
        this.fornecedor_pk = fornecedor_pk;
        this.nome = nome;
        this.tipo_fornecedor_fk = tipo_fornecedor_fk;
        this.pessoa_fk = pessoa_fk;
    }

    public FornecedorModelo ()
    {
    }   
    
}
