/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author azm
 */
public class HtmlComboBoxes
{

    public HtmlComboBoxes ()
    {
    }

    /**
     * Constrói uma combo HTML (SELECT) a partir de uma tabela da BD.
     *
     * @param tabela Nome da tabela da BD.
     * @param formulario Nome do formulário HTML.
     * @param nome Nome da combo box.
     * @param value Campo da tabela para o atributo 'value' das OPTION.
     * @param label Campo da tabela para o 'label' das OPTION.
     * @param evento Evento Javascript da combo box (opcional). Se não houver
     * eventos, este parâmetro deve conter uma string vazia.
     * @param valorSeleccionado Valor seleccionado por defeito na combo (pode
     * ser utilizado em formulários de UPDATE). Se não houver valor seleccionado
     * por defeito, este parâmetro deve estar nulo.
     * @throws Exception Caso ocorra algum erro de SQL.
     * @return String com código HMTL da combo box.
     */
    public String select (String tabela ,
                          String formulario ,
                          String nome ,
                          String value ,
                          String label ,
                          String evento ,
                          String valorSeleccionado) throws Exception
    {
//         conectarBD();
        Connection conexao = new ConexaoBD ().getConnection ();
        //abrir a tag select
        StringBuffer html = new StringBuffer ("<select name='" + nome + "' "
                + "id='" + nome + "' " + evento + ">\n");

        //adicionar uma option vazia
        html.append ("<option value=''>-- escolha --</option>\n");

        //efectuar a consulta SQL para seleccionar todos os registos da tabela
        PreparedStatement stm = conexao.prepareStatement ("SELECT * FROM " + tabela + " ORDER BY " + label);

        ResultSet resultSet = stm.executeQuery ();

        //para cada registo seleccionado, gerar um OPTION
        while (resultSet.next ())
        {
            String option = "<option value='" + resultSet.getString (value) + "'>"
                    + resultSet.getString (label) + "</option>\n";

            html.append (option);
        }

        //fechar o cursor
        resultSet.close ();
        stm.close ();
        conexao.close ();

        //fechar a tag select
        html.append ("</select>\n\n");
        //se o houver um valor seleccionado por defeito
        if (valorSeleccionado != null && !(valorSeleccionado.equals ("")))
        {
            html.append ("<script language='javascript'>" + "document."
                    + formulario + "." + nome + ".value='" + valorSeleccionado
                    + "'</script>\n");
        }

//        fecharConexao();
        return html.toString ();
    }

    public String selectMany (String tabela ,
                                     String formulario ,
                                     String nome ,
                                     String value ,
                                     String label ,
                                     String evento ,
                                     String valorSeleccionado ,
                                     String ehnivel) throws Exception
    {
//         conectarBD();
        Connection conexao = new ConexaoBD ().getConnection ();
        //abrir a tag select
        StringBuffer html = new StringBuffer ("<select name='" + nome + "' "
                + "id='" + nome + "' " + evento + ">\n");

        //adicionar uma option vazia
        html.append ("<option value=''>-- escolha --</option>\n");
//        System.out.println ("Nivel -> " + ehnivel);
        //efectuar a consulta SQL para seleccionar todos os registos da tabela
        PreparedStatement stm = conexao.prepareStatement ("SELECT * FROM " + tabela + " " + "WHERE ehNivel='" + ehnivel + "'" + " ORDER BY " + label);
//        System.out.println ("Pst -> " + stm.toString ());
        ResultSet resultSet = stm.executeQuery ();

        //para cada registo seleccionado, gerar um OPTION
        while (resultSet.next ())
        {
            String option = "<option value='" + resultSet.getString (value) + "'>"
                    + resultSet.getString (label) + "</option>\n";

            html.append (option);
        }

        //fechar o cursor
        resultSet.close ();
        stm.close ();
        conexao.close ();

        //fechar a tag select
        html.append ("</select>\n\n");
        //se o houver um valor seleccionado por defeito
        if (valorSeleccionado != null && !(valorSeleccionado.equals ("")))
        {
            html.append ("<script language='javascript'>" + "document."
                    + formulario + "." + nome + ".value='" + valorSeleccionado
                    + "'</script>\n");
        }

//        fecharConexao();
        return html.toString ();
    }

    public String selectMultiple (String tabela ,
                                  String formulario ,
                                  String nome ,
                                  String value ,
                                  String label ,
                                  String evento ,
                                  String valorSeleccionado ,
                                  int size) throws Exception
    {
//         conectarBD();
        Connection conexao = new ConexaoBD ().getConnection ();

        //abrir a tag select
        StringBuffer html = new StringBuffer ("<select name='" + nome + "' "
                + "id='" + nome + "' " + evento + " size=\"" + size + "\" multiple=\"multiple\" >\n");

        //adicionar uma option vazia
//        html.append("<option value=''>-- escolha --</option>\n");
        //efectuar a consulta SQL para seleccionar todos os registos da tabela
        PreparedStatement stm = conexao.prepareStatement ("SELECT * FROM " + tabela + " ORDER BY " + label);

        ResultSet resultSet = stm.executeQuery ();

        //para cada registo seleccionado, gerar um OPTION
        while (resultSet.next ())
        {
            String option = "<option value='" + resultSet.getString (value) + "'>"
                    + resultSet.getString (label) + "</option>\n";

            html.append (option);
        }

        //fechar o cursor
        resultSet.close ();
        stm.close ();
        conexao.close ();

        //fechar a tag select
        html.append ("</select>\n\n");

        //se o houver um valor seleccionado por defeito
        if (valorSeleccionado != null)
        {
            html.append ("<script language='javascript'>" + "document."
                    + formulario + "." + nome + ".value=" + valorSeleccionado
                    + "</script>\n");
        }

//        fecharConexao();
        return html.toString ();
    }

    /**
     * Constrói uma combo HTML (SELECT) a partir de uma tabela da BD, que
     * depende de outra combo.
     *
     * @param tabela Nome da tabela da BD.
     * @param formulario Nome do formulário HTML.
     * @param arrayJavascript Nome do array javascript que será criado para
     * armazenar os itens da tabela.
     * @param comboAnterior Nome da combo anterior, da qual esta depende.
     * @param nome Nome da combo box.
     * @param value Campo da tabela para o atributo 'value' das OPTION.
     * @param label Campo da tabela para o 'label' das OPTION.
     * @param campoRelacionado Nome do campo relacionado, ou seja, chave
     * estrangeira proveniente da tabela pai.
     * @param evento Evento Javascript da combo box (opcional).
     * @param valorSeleccionado Valor seleccionado por defeito na combo (pode
     * ser utilizado em formulários de UPDATE).
     * @throws Exception Caso ocorra algum erro de SQL.
     * @return String com código HMTL da combo box.
     */
    public String selectDependente (String tabela ,
                                    String formulario ,
                                    String arrayJavascript ,
                                    String comboAnterior ,
                                    String nome ,
                                    String value ,
                                    String label ,
                                    String campoRelacionado ,
                                    String evento ,
                                    String valorSeleccionado ,
                                    String ehnivel) throws Exception
    {
//        conectarBD();
        Connection conexao = new ConexaoBD ().getConnection ();

        //abrir a tag select
        StringBuffer html = new StringBuffer ("<select name='" + nome + "' "
                + "id='" + nome + "' " + evento + ">\n");

        //adicionar uma opção vazia e fechar a tag select
        html.append ("<option value=''>-- escolha --</option></select>\n");

        //efectuar a consulta SQL para seleccionar todos os registos da tabela
        PreparedStatement stm = conexao.prepareStatement ("SELECT * FROM " + tabela + " " +"WHERE ehNivel='" + ehnivel + "'"  + " ORDER BY " + label);
        System.out.println ("pst ->"+stm.toString ());
        ResultSet resultSet = stm.executeQuery ();

        //declarar os arrays javascript
        html.append ("<script>\n");

        html.append ("var " + arrayJavascript + "Text" + " = new Array();\n");
        html.append ("var " + arrayJavascript + "Relac" + " = new Array();\n");
        html.append ("var " + arrayJavascript + "Value" + " = new Array();\n");
        html.append ("\n");

        //Código do item pai seleccionado. Utilizado apenas se o valor seleccionado não for nulo.
        String codigoPaiSeleccionado = null;

        //índice dos arrays javascript
        int i = 0;

        //para cada registo seleccionado, gerar um OPTION
        while (resultSet.next ())
        {

            String designacao = resultSet.getString (label);
            String codigoPai = resultSet.getString (campoRelacionado);
            String codigo = resultSet.getString (value);

            //se este for o item seleccionado, fixa o código do item pai para seleccionar na combo anterior
            if (codigo.equals (valorSeleccionado))
            {
                codigoPaiSeleccionado = codigoPai;

                //adicionar um elemento a cada um dos arrays javascript
            }
            html.append ("" + arrayJavascript + "Text" + "[" + i + "] = \""
                    + designacao + "\";\n");
            html.append ("" + arrayJavascript + "Relac" + "[" + i + "] = "
                    + codigoPai + ";\n");
            html.append ("" + arrayJavascript + "Value" + "[" + i + "] = "
                    + codigo + ";\n");

            i++;
        }
        //se houver um valor seleccionado por defeito
        if (valorSeleccionado != null)
        {
            //seleccionar o valor da combo anterior com o codigo pai seleccionado
            html.append (formulario + "." + comboAnterior
                    + ".value = " + codigoPaiSeleccionado + ";\n");

            //chamar a função JS que altera os itens da combo em função ao valor seleccionado na combo anterior
            html.append ("selectChange('" + comboAnterior
                    + "', '" + nome + "', " + arrayJavascript
                    + "Text" + ", " + arrayJavascript + "Relac" + ", " + arrayJavascript
                    + "Value" + ");\n");

            //seleccionar o valor por defeito na combo
            html.append ("document." + formulario + "." + nome
                    + ".value = " + valorSeleccionado + ";\n");

        }

        //fechar a tag <script>
        html.append ("</script>\n");

        //fechar o cursor
        resultSet.close ();
        stm.close ();
//        fecharConexao();
        conexao.close ();

        return html.toString ();
    }

    /**
     * Constrói uma combo HTML (SELECT) a partir de uma tabela da BD, que
     * depende de outra combo.
     *
     * @param tabela Nome da tabela da BD.
     * @param formulario Nome do formulário HTML.
     * @param arrayJavascript Nome do array javascript que será criado para
     * armazenar os itens da tabela.
     * @param comboAnterior Nome da combo anterior, da qual esta depende.
     * @param nome Nome da combo box.
     * @param value Campo da tabela para o atributo 'value' das OPTION.
     * @param label Campo da tabela para o 'label' das OPTION.
     * @param campoRelacionado Nome do campo relacionado, ou seja, chave
     * estrangeira proveniente da tabela pai.
     * @param evento Evento Javascript da combo box (opcional).
     * @param valorSeleccionado Valor seleccionado por defeito na combo (pode
     * ser utilizado em formulários de UPDATE).
     * @throws Exception Caso ocorra algum erro de SQL.
     * @return String com código HMTL da combo box.
     */
    public String selectDependenteMultiple (String tabela ,
                                            String formulario ,
                                            String arrayJavascript ,
                                            String comboAnterior ,
                                            String nome ,
                                            String value ,
                                            String label ,
                                            String campoRelacionado ,
                                            String evento ,
                                            String valorSeleccionado ,
                                            int size) throws Exception
    {
//        conectarBD();
        Connection conexao = new ConexaoBD ().getConnection ();

        //abrir a tag select
        StringBuffer html = new StringBuffer ("<select name='" + nome + "' "
                + "id='" + nome + "' " + evento + " size=\"" + size + "\" multiple=\"multiple\" >\n");

        //adicionar uma opção vazia e fechar a tag select
        html.append ("<option value=''>-- escolha --</option></select>\n");

        //efectuar a consulta SQL para seleccionar todos os registos da tabela
        PreparedStatement stm = conexao.prepareStatement ("SELECT * FROM " + tabela + " ORDER BY " + label);

        ResultSet resultSet = stm.executeQuery ();

        //declarar os arrays javascript
        html.append ("<script>\n");

        html.append ("var " + arrayJavascript + "Text" + " = new Array();\n");
        html.append ("var " + arrayJavascript + "Relac" + " = new Array();\n");
        html.append ("var " + arrayJavascript + "Value" + " = new Array();\n");
        html.append ("\n");

        //Código do item pai seleccionado. Utilizado apenas se o valor seleccionado não for nulo.
        String codigoPaiSeleccionado = null;

        //índice dos arrays javascript
        int i = 0;

        //para cada registo seleccionado, gerar um OPTION
        while (resultSet.next ())
        {

            String designacao = resultSet.getString (label);
            String codigoPai = resultSet.getString (campoRelacionado);
            String codigo = resultSet.getString (value);

            //se este for o item seleccionado, fixa o código do item pai para seleccionar na combo anterior
            if (codigo.equals (valorSeleccionado))
            {
                codigoPaiSeleccionado = codigoPai;

                //adicionar um elemento a cada um dos arrays javascript
            }
            html.append ("" + arrayJavascript + "Text" + "[" + i + "] = \""
                    + designacao + "\";\n");
            html.append ("" + arrayJavascript + "Relac" + "[" + i + "] = "
                    + codigoPai + ";\n");
            html.append ("" + arrayJavascript + "Value" + "[" + i + "] = "
                    + codigo + ";\n");

            i++;
        }

        //se houver um valor seleccionado por defeito
        if (valorSeleccionado != null)
        {
            //seleccionar o valor da combo anterior com o codigo pai seleccionado
            html.append (formulario + "." + comboAnterior
                    + ".value = " + codigoPaiSeleccionado + ";\n");

            //chamar a função JS que altera os itens da combo em função ao valor seleccionado na combo anterior
            html.append ("selectChange(" + formulario + "." + comboAnterior
                    + ", " + formulario + "." + nome + ", " + arrayJavascript
                    + "Text" + ", " + arrayJavascript + "Relac" + ", " + arrayJavascript
                    + "Value" + ");\n");

            //seleccionar o valor por defeito na combo
            html.append (formulario + "." + nome
                    + ".value = " + valorSeleccionado + ";\n");

        }

        //fechar a tag <script>
        html.append ("</script>\n");

        //fechar o cursor
        resultSet.close ();
        stm.close ();
//        fecharConexao();
        conexao.close ();

        return html.toString ();
    }
}
