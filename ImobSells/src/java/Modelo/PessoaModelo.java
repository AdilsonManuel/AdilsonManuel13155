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

    private int pessoa_pk;
    private SexoModelo sexo_fk;
    private EstadoCivilModelo estado_civil_fk;
    private EnderecoModelo endereco_fk;
    private TelefoneModelo telefone_fk;
    private EmailModelo email_fk;
    private String nome, data_nascimento;

    public PessoaModelo (int pessoa_pk , SexoModelo sexo_fk , EstadoCivilModelo estado_civil_fk , EnderecoModelo endereco_fk , TelefoneModelo telefone_fk , EmailModelo email_fk , String nome , String data_nascimento)
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

    public PessoaModelo ()
    {
    }

}
