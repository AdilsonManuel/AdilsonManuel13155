/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import lombok.Data;

/**
 *
 * @author pc-accer
 */
@Data
public class ProvinciaModelo
{

    private int provincia_pk;
    private String nome;
    private PaisModelo paisModelo;

    public ProvinciaModelo ()
    {
    }

}
