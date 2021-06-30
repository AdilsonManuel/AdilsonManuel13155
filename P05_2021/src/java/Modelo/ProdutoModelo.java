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
public class ProdutoModelo implements Serializable
{

    private int produto_pk, fornecedor_fk, quantidade;
    private Double preco;
    private String designacao, imagem, portfolio_fk, data_registro;

    public ProdutoModelo ()
    {

    }
}
