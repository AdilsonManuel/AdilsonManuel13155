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
public class ContaModelo implements Serializable
{

    private Integer tipo_conta_fk;
    private String nomeUsuario, senha_usuario;

    public ContaModelo ()
    {
    }

}
