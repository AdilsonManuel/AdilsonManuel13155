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
public class LocalizacaoModelo implements Comparable<LocalizacaoModelo>
{

    private int pkSeq;
    private String designacao, localizacao_fk_pai, localizacao_pk, ehNivel;
    private Boolean eh_distrito;

    public LocalizacaoModelo (int pkSeq , String designacao , String localizacao_fk_pai , String localizacao_pk , String ehNivel , Boolean eh_distrito)
    {
        this.pkSeq = pkSeq;
        this.designacao = designacao;
        this.localizacao_fk_pai = localizacao_fk_pai;
        this.localizacao_pk = localizacao_pk;
        this.ehNivel = ehNivel;
        this.eh_distrito = eh_distrito;
    }

    public LocalizacaoModelo ()
    {
    }

    @Override
    public int compareTo (LocalizacaoModelo o)
    {
        Collator instance = Collator.getInstance ();

        // esta estratégia irá ignorar os acentos
        instance.setStrength (Collator.NO_DECOMPOSITION);

        return instance.compare (this.designacao , o.getDesignacao ());
    }
}
