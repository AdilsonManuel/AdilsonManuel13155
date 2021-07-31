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
    private String designacao, imagem, portfolio_fk, data_registro;
    private Double preco;

    public ProdutoModelo (int produto_pk, int fornecedor_fk, int quantidade, String designacao, String imagem, String portfolio_fk, String data_registro, Double preco)
    {
        this.produto_pk = produto_pk;
        this.fornecedor_fk = fornecedor_fk;
        this.quantidade = quantidade;
        this.designacao = designacao;
        this.imagem = imagem;
        this.portfolio_fk = portfolio_fk;
        this.data_registro = data_registro;
        this.preco = preco;
    }

    public ProdutoModelo ()
    {
    }

}
