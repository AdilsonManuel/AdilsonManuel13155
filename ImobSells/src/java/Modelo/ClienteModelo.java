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
@EqualsAndHashCode(callSuper = false)
public class ClienteModelo extends PessoaModelo
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

    public ClienteModelo (int pessoa_fk , int cliente_pk , int tipo_cliente_fk , int pessoa_pk , SexoModelo sexo_fk , EstadoCivilModelo estado_civil_fk , EnderecoModelo endereco_fk , TelefoneModelo telefone_fk , EmailModelo email_fk , String nome , String data_nascimento)
    {
        super (pessoa_pk , sexo_fk , estado_civil_fk , endereco_fk , telefone_fk , email_fk , nome , data_nascimento);
        this.pessoa_fk = pessoa_fk;
        this.cliente_pk = cliente_pk;
        this.tipo_cliente_fk = tipo_cliente_fk;
    }

    public ClienteModelo ()
    {
    }

}
