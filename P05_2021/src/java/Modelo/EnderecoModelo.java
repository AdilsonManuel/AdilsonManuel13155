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
 * @author pc-accer
 */
@Data
public class EnderecoModelo implements Serializable
{
    
    private int endereco_pk;
    private String bairro;
    private int rua,numero_casa;
    private ComunaModelo comunaModelo;
    
    public EnderecoModelo ()
    {
    }

}
