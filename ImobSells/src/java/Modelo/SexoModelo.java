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
public class SexoModelo implements Serializable
{

    private int sexo_pk;
    private String nome;

    public SexoModelo (int sexo_pk , String nome)
    {
        this.sexo_pk = sexo_pk;
        this.nome = nome;
    }

    public SexoModelo ()
    {
    }

}
