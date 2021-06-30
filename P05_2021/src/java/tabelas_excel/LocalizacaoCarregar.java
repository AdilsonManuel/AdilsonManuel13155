/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas_excel;

import DAO.LocalizacaoDAO;
import Modelo.LocalizacaoModelo;
import Util.ConexaoBD;
import java.sql.SQLException;
import Util.Defs;

/**
 *
 * @author benvindo
 */
public class LocalizacaoCarregar
{

    private static LocalizacaoModelo localizacaoModelo;
    public static boolean sucesso;

    public LocalizacaoCarregar (String localizacao) throws ClassNotFoundException , SQLException, TabelaLinhaException
    {
        this.readAll (localizacao);
    }

    public LocalizacaoCarregar (String ExcelFileSheetName , int cellLineNumberIterator ,
                                String nomeTabela) throws ClassNotFoundException , SQLException, TabelaLinhaException
    {
        sucesso = false;
        new ExcelFileReader (Defs.LOCALIZACOES_PATH ,
                ExcelFileSheetName , Defs.START_LINE_ITERATOR ,
                cellLineNumberIterator , nomeTabela);
    }

    public static void readAll (String localizacao) throws ClassNotFoundException , SQLException, TabelaLinhaException
    {
        switch (localizacao)
        {
            case "pais":
                new LocalizacaoCarregar (Defs.PAIS_SHEETNAME , Defs.TWO_CELl , "pais");
                break;
            case "provincia":
                new LocalizacaoCarregar (Defs.PROVINCIA_SHEETNAME , Defs.THREE_CELL , "provincia");
                break;
            case "municipio":
                new LocalizacaoCarregar (Defs.MUNICIPIO_SHEETNAME , Defs.THREE_CELL , "municipio");
            case "comuna":
                new LocalizacaoCarregar (Defs.COMUNA_SHEETNAME , Defs.FOUR_CELL , "comuna");
                break;
            case "bairro":
                new LocalizacaoCarregar (Defs.BAIRRO_SHEETNAME , Defs.THREE_CELL , "bairro");
                break;
        }

    }

    public static boolean carregarPais (TabelaLinha tabelaLinha , ConexaoBD conexao) throws ClassNotFoundException , SQLException
    {
        String designacao;
        int codigo, ehNivel = 1;
        codigo = Integer.parseInt (tabelaLinha.getCellValueByIndex (0).toString ());
        designacao = tabelaLinha.getCellValueByIndex (1).toString ();

        localizacaoModelo = new LocalizacaoModelo (codigo , 0 , ehNivel , designacao , false);

// System.out.println(localizacaoModelo);
        if (new LocalizacaoDAO ().insert (localizacaoModelo))
        {
            sucesso = true;
        }
        return true;
    }

    public static boolean carregarProvincia (TabelaLinha tabelaLinha , ConexaoBD conexao) throws ClassNotFoundException , SQLException
    {
        String designacao;
        int codigo, codigoPai, ehNivel = 2;
        codigo = Integer.parseInt (tabelaLinha.getCellValueByIndex (0).toString ());
        designacao = tabelaLinha.getCellValueByIndex (1).toString ();
        codigoPai = Integer.parseInt (tabelaLinha.getCellValueByIndex (2).toString ());

        localizacaoModelo = new LocalizacaoModelo (codigo , codigoPai , ehNivel , designacao , false);

//System.out.println(localizacaoModelo);
        if (new LocalizacaoDAO ().insert (localizacaoModelo))
        {
            sucesso = true;
        }
        return true;
    }

    public static boolean carregarMuncipio (TabelaLinha tabelaLinha , ConexaoBD conexao) throws ClassNotFoundException , SQLException
    {
        String designacao;
        int codigo, codigoPai, ehNivel = 3;
        codigo = Integer.parseInt (tabelaLinha.getCellValueByIndex (0).toString ());
        designacao = tabelaLinha.getCellValueByIndex (1).toString ();
        codigoPai = Integer.parseInt (tabelaLinha.getCellValueByIndex (2).toString ());

        localizacaoModelo = new LocalizacaoModelo (codigo , codigoPai , ehNivel , designacao , false);

        //System.out.println(localizacaoModelo);
        if (new LocalizacaoDAO ().insert (localizacaoModelo))
        {
            sucesso = true;
        }
        return true;
    }

    public static boolean carregarComunaOuDistrito (TabelaLinha tabelaLinha , ConexaoBD conexao) throws ClassNotFoundException , SQLException
    {
        String designacao, ehDistrito;
        int codigo, codigoPai, ehNivel = 4;

        // verifa se o tamanho da lista da instancia TabelaLinha possui 4 células
        if (tabelaLinha.getSize () == 4)
        {
            codigo = Integer.parseInt (tabelaLinha.getCellValueByIndex (0).toString ());
            designacao = tabelaLinha.getCellValueByIndex (1).toString ();
            codigoPai = Integer.parseInt (tabelaLinha.getCellValueByIndex (2).toString ());
            ehDistrito = tabelaLinha.getCellValueByIndex (3).toString ();

            if (ehDistrito.equalsIgnoreCase ("SIM"))
            {
                localizacaoModelo = new LocalizacaoModelo (codigo , codigoPai , ehNivel , designacao , true);

            }
            else
            {
                localizacaoModelo = new LocalizacaoModelo (codigo , codigoPai , ehNivel , designacao , false);

            }
// System.out.println(localizacaoModelo);
            if (new LocalizacaoDAO ().insert (localizacaoModelo))
            {
                sucesso = true;
            }
        }
        return true;
    }

    public static boolean carregarBairro (TabelaLinha tabelaLinha , ConexaoBD conexao) throws ClassNotFoundException , SQLException
    {
        String designacao;
        int codigo, codigoPai, ehNivel = 5;
        codigo = Integer.parseInt (tabelaLinha.getCellValueByIndex (0).toString ());
        designacao = tabelaLinha.getCellValueByIndex (1).toString ();
        codigoPai = Integer.parseInt (tabelaLinha.getCellValueByIndex (2).toString ());

        localizacaoModelo = new LocalizacaoModelo (codigo , codigoPai , ehNivel , designacao , false);

// System.out.println(localizacaoModelo);
        if (new LocalizacaoDAO ().insert (localizacaoModelo))
        {
            sucesso = true;
        }
        return true;
    }

}
