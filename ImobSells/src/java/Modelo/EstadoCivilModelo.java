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
public class EstadoCivilModelo implements  Serializable
{

    private int estado_civili_pk;
    private String nome;

    public EstadoCivilModelo (int estado_civili_pk , String nome)
    {
        this.estado_civili_pk = estado_civili_pk;
        this.nome = nome;
    }

    public EstadoCivilModelo ()
    {
    }

}
