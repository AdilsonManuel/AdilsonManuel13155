package tabelas_excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author benvindo
 */
public class Test
{
    public static void main(String[] args) throws IOException, FileNotFoundException, TabelaLinhaException, SQLException, ClassNotFoundException
    {
        
        
        new LocalizacaoCarregar("pais");
        new LocalizacaoCarregar("provincia");
        new LocalizacaoCarregar("municipio");
        new LocalizacaoCarregar("comuna");
        new LocalizacaoCarregar("bairro");

        /*List<PortifolioModelo> lista1 = new ArrayList<>();
        //lista1 = new PortifolioDAO().obterPortifolioPorFkPai(null);
        lista1 = new PortifolioDAO().obterPortifolioPorFkPai("1.1.1");

        ListUtil.ordenarPorOrdemAlfabetica(lista1);
        
        for (PortifolioModelo model1 : lista1)
        {
            System.out.println("codigo: " + model1.getPkPortifolio()
                    + " designacao: " + model1.getDesignacao() + 
                    " codigoPai:" + model1.getFkPortifolioPai());
        }*/
 /*List<LocalizacaoModelo> lista = new ArrayList<>();
        //lista = new LocalizacaoDAO().obterLocaLocalizacaoPorEhNivelCodigoPai(1, 0);
        lista = new LocalizacaoDAO().obterLocaLocalizacaoPorEhNivelCodigoPai(2, 1);

        ListUtil.ordenarPorOrdemAlfabeticaLocalizacaoModelo(lista);

        for (LocalizacaoModelo model : lista)
        {
            System.out.println("codigo: " +model.getCodigoLocalizacao()
                    + " designacao: "+ model.getDesignacao() + " codigoPai:" + model.getCodigoLocalizacao_pai());
        }*/
    }
}
