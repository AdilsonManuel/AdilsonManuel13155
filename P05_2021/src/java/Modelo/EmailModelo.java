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
public class EmailModelo
{
    private int email_pk,tipo_email_fk;
    private String email;
}
