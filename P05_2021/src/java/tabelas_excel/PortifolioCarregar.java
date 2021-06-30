/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas_excel;

import DAO.PortfolioDAO;
import Modelo.PortfolioModelo;
import Util.ConexaoBD;
import java.sql.SQLException;
import Util.Defs;


public class PortifolioCarregar
{
    private static PortfolioModelo portifolioModelo1, portifolioModelo2,
            portifolioModelo3, portifolioModelo4;
    
    private static boolean sucesso;
    private ConexaoBD conexao;
    
    public PortifolioCarregar() throws ClassNotFoundException, SQLException, TabelaLinhaException
    { 
        sucesso = false;
        new ExcelFileReader(Defs.PORTFOLIO_PATH, Defs.PORTFOLIO_SHEETNAME, Defs.START_LINE_ITERATOR, 
                Defs.TWO_CELl, "portifolio");
    }
     
    public static boolean carregar(TabelaLinha tabelaLinha, ConexaoBD conexao) throws ClassNotFoundException, SQLException
    {
        String codigo, designacao;
        int nivel;

        codigo = tabelaLinha.getCellValueByIndex(0).toString();
        designacao = tabelaLinha.getCellValueByIndex(1).toString();

        nivel = tabelaLinha.getNivel();

        switch (nivel)
        {
            case 1:
                portifolioModelo1 = new PortfolioModelo (codigo, designacao, null);
//System.out.println(portifolioModelo1);
                if (new PortfolioDAO ().insert(portifolioModelo1))
                {
                    sucesso = true;
                }
                break;

            case 2:
                portifolioModelo2 = new PortfolioModelo (codigo, designacao, portifolioModelo1.getPortfolio_pk ());
//System.out.println(portifolioModelo2);
                if (new PortfolioDAO ().insert(portifolioModelo2))
                {
                    sucesso = true;
                }
                break;
                
            case 3:
                portifolioModelo3 = new PortfolioModelo (codigo, designacao, portifolioModelo2.getPortfolio_pk ());
//System.out.println(portifolioModelo3);
                if (new PortfolioDAO ().insert(portifolioModelo3))
                {
                    sucesso = true;
                }
                break;
            case 4:
                portifolioModelo4 = new PortfolioModelo (codigo, designacao, portifolioModelo3.getPortfolio_pk ());
//System.out.println(portifolioModelo4);
                if (new PortfolioDAO ().insert(portifolioModelo4))
                {
                    sucesso = true;
                }
                break;
        }
        return true;
    }      
}
