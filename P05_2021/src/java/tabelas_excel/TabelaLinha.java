/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas_excel;

import java.util.ArrayList;
import java.util.List;
import Util.ConexaoBD;
import java.sql.SQLException;

/**
 *
 * @author benvindo
 */
public class TabelaLinha
{
    private static String tableName;
    private List<Object> cells = new ArrayList();
    private ConexaoBD conexao;

    public TabelaLinha() throws ClassNotFoundException, SQLException
    {
        this.conexao = (ConexaoBD) new ConexaoBD ().getConnection (); 
    }

    public int getSize()
    {
        return cells.size();
    }

    public void addCells(Object object)
    {
        cells.add(object);
    }

    public Object getCellValueByIndex(int index)
    {
        return cells.get(index);
    }

    public static String getTableName()
    {
        return tableName;
    }

    public static void setTableName(String tableName)
    {
        TabelaLinha.tableName = tableName;
    }

    public int getNivel()
    {
        int nivel = 0;
        String codigo = cells.get(0).toString();
        int l = codigo.length();
        for (int i = 0; i < l; i++)
        {
            if (codigo.charAt(i) == '.')
            {
                nivel++;
            }
        }

        if (codigo.charAt(l - 1) == '.')
        {
            return nivel;
        }
        if (codigo.charAt(l - 1) != '.' && nivel != 0)
        {
            return ++nivel;
        }

        return nivel;
    }

    public void carregarTabela(ConexaoBD conexao) throws TabelaLinhaException, ClassNotFoundException, SQLException
    {
        switch (TabelaLinha.getTableName())
        {   // accão quando uma instância de TabelaLinha é criada
            case "portfolio":
                PortifolioCarregar.carregar(this, conexao);
                break;
            // carregar localizações
            case "pais":
                LocalizacaoCarregar.carregarPais(this, conexao);
                break;
            case "provincia":
                LocalizacaoCarregar.carregarProvincia(this, conexao);
                break;
            case "municipio":
                LocalizacaoCarregar.carregarMuncipio(this, conexao);
                break;
            case "comuna":
                LocalizacaoCarregar.carregarComunaOuDistrito(this, conexao);
                break;
            case "bairro":
                LocalizacaoCarregar.carregarBairro(this, conexao);
                break;
            default:
                throw new TabelaLinhaException();
        }

    }

    public String toString()
    {
        return cells.toString();
    }

}
