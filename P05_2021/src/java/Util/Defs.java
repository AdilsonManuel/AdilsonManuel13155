/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author benvindo
 */
public class Defs
{
    public static final String PATH = getUserHome() + "/Documents/tabelas/";

    // path excel
    public static String PORTFOLIO_PATH = PATH + "portifolio" + "/" + "portifolio_materias_escolares.xls";
    public static String LOCALIZACOES_PATH = (PATH + "localizacao" + "/" + "localizacoes.xls");

    // sheet names
    public static String PORTFOLIO_SHEETNAME = "portifolio";
    public static String PAIS_SHEETNAME = "paises";
    public static String PROVINCIA_SHEETNAME = "provincias";
    public static String MUNICIPIO_SHEETNAME = "municipios";
    public static String COMUNA_SHEETNAME = "comunas";
    public static String BAIRRO_SHEETNAME = "bairros";

    public static int START_LINE_ITERATOR = 2;

    public static int TWO_CELl = 2;
    public static int THREE_CELL = 3;
    public static int FOUR_CELL = 4;

    // obter a home do usuario
    private static String getUserHome()
    {
        return System.getProperty("user.home");
    }
}
