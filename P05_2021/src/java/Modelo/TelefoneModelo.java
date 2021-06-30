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
public class TelefoneModelo
{
    private int telefone_pk,tipo_telefonefk;
    private String numero;
}
