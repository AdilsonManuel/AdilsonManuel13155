/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import lombok.Data;

/**
 *
 * @author azm
 */
@Data
public class PessoaModelo
{

    private int pessoa_pk, sexo_fk, estado_civil_fk, endereco_fk, telefone_fk, email_fk;
    private String nome, data_nascimento;

    public PessoaModelo ()
    {
    }

    public PessoaModelo (int pessoa_pk , int sexo_fk , int estado_civil_fk , int endereco_fk , int telefone_fk , int email_fk , String nome , String data_nascimento)
    {
        this.pessoa_pk = pessoa_pk;
        this.sexo_fk = sexo_fk;
        this.estado_civil_fk = estado_civil_fk;
        this.endereco_fk = endereco_fk;
        this.telefone_fk = telefone_fk;
        this.email_fk = email_fk;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }

}
