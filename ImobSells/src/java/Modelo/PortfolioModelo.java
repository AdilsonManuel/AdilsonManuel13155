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
public class PortfolioModelo implements Serializable
{

    private int portfolio_pk;
    private String designacao, portfolio_fk_pai;

    public PortfolioModelo (int portfolio_pk, String designacao, String portfolio_fk_pai)
    {
        this.portfolio_pk = portfolio_pk;
        this.designacao = designacao;
        this.portfolio_fk_pai = portfolio_fk_pai;
    }

    public PortfolioModelo ()
    {
    }

}
