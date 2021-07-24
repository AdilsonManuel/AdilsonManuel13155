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
public class EmailModelo implements Serializable
{

    private int email_pk;
    private String nome;

    public EmailModelo (int email_pk , String nome)
    {
        this.email_pk = email_pk;
        this.nome = nome;
    }

    public EmailModelo ()
    {
    }

}
