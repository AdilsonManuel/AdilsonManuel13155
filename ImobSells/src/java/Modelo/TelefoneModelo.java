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
public class TelefoneModelo implements Serializable
{

    private int telefone_pk;
    private String numero;

    public TelefoneModelo ()
    {
    }

    public TelefoneModelo (int telefone_pk , String numero)
    {
        this.telefone_pk = telefone_pk;
        this.numero = numero;
    }

}
