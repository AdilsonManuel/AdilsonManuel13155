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
public class EnderecoModelo implements Serializable
{

    private int endereco_pk;
    private int numero_casa;
    private String rua;
    private LocalizacaoModelo localizacaoModelo;

    public EnderecoModelo (int endereco_pk , int numero_casa , String rua , LocalizacaoModelo localizacaoModelo)
    {
        this.endereco_pk = endereco_pk;
        this.numero_casa = numero_casa;
        this.rua = rua;
        this.localizacaoModelo = localizacaoModelo;
    }

    public EnderecoModelo ()
    {
    }

}
