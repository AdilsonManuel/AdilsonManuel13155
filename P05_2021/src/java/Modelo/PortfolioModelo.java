/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.Collator;
import lombok.Data;

/**
 *
 * @author azm
 */
@Data
public class PortfolioModelo implements Comparable<PortfolioModelo>
{

    private String Portfolio_pk, designacao, portofolio_fk_pai;

    public PortfolioModelo ()
    {

    }

    public PortfolioModelo (String Portfolio_pk , String designacao , String portofolio_fk_pai)
    {
        this.Portfolio_pk = Portfolio_pk;
        this.designacao = designacao;
        this.portofolio_fk_pai = portofolio_fk_pai;
    }

    @Override
    public int compareTo (PortfolioModelo o)
    {
        Collator instance = Collator.getInstance ();

        // esta estratégia irá ignorar os acentos
        instance.setStrength (Collator.NO_DECOMPOSITION);

        return instance.compare (this.designacao , o.getDesignacao ());
    }
}
