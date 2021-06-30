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

    private int localizacao_pk, codigo_localizacao, codigo_localizacao_pai, eh_nivel;
    private String designacao;
    private Boolean ehDistrito;

    public LocalizacaoModelo ()
    {

    }

    public LocalizacaoModelo ( int codigo_localizacao , int codigo_localizacao_pai , int eh_nivel , String designacao , boolean ehDistrito)
    {
        this.codigo_localizacao = codigo_localizacao;
        this.codigo_localizacao_pai = codigo_localizacao_pai;
        this.eh_nivel = eh_nivel;
        this.designacao = designacao;
        this.ehDistrito = ehDistrito;
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
