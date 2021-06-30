/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas_excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import Util.ConexaoBD;
import java.sql.SQLException;

public class ExcelFileReader
{

    private ConexaoBD conexao;

    public ExcelFileReader (ConexaoBD conexao) throws ClassNotFoundException, SQLException
    {
        this.conexao = (ConexaoBD) new ConexaoBD ().getConnection ();
    }

    public ExcelFileReader (String excelPath , String ExcelFileSheetName , int startLineIterator , int cellLineNumberIterator , String nomeTabela) throws ClassNotFoundException, SQLException, TabelaLinhaException
    {
        HSSFWorkbook workbook = null;

        try
        {
            //criar instância workbook contendo referência do arquivo .xls
            workbook = new HSSFWorkbook (new FileInputStream (excelPath));
        }
        catch (IOException ex)
        {
            System.err.println ("Falha ao tentar ler o ficheiro" + excelPath);
            System.exit (1);

        }
        // obter o nome da folha do aquivo .xls
        HSSFSheet sheet = workbook.getSheet (ExcelFileSheetName);

        TabelaLinha.setTableName (nomeTabela);

        // percorrer todas as linhas da folha, uma por uma
        Iterator rowIterator = sheet.rowIterator ();
        for (int linha = 0; rowIterator.hasNext (); linha++)
        {
            HSSFRow row = (HSSFRow) rowIterator.next ();

            if (linha < startLineIterator)
            {
                continue;
            }
            // obter as células da linha actual
            TabelaLinha tabelaLinha = null;
            tabelaLinha = lerCampos (row , cellLineNumberIterator);
            //System.out.println(tabelaLinha);
            // carregar TabelaLinha na bd
            tabelaLinha.carregarTabela (conexao);
        }
    }

    // retorna uma instancia de TabelaLinha(com todas as células contida nela)
    private TabelaLinha lerCampos (Row row , int cellLineNumberIterator) throws ClassNotFoundException, SQLException
    {
        Iterator cellIterator = row.cellIterator ();

        TabelaLinha tabelaLinha = new TabelaLinha ();
        for (int coluna = 0; coluna <= cellLineNumberIterator - 1; coluna++)
        {
            // verificar se a proxima posição do cellIterator está preenchida ou vazia
            if (cellIterator.hasNext ())
            {
                HSSFCell cells = (HSSFCell) cellIterator.next ();
                // formatar todos os valores da célula actual para string
                cells.setCellType (CellType.STRING);
// System.err.println(cell.getCellType())
                tabelaLinha.addCells (cells.getStringCellValue ());
            }
        }
        return tabelaLinha;
    }
}
