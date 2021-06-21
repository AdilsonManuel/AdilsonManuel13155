/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Bean;
import Modelo.PaisModelo;
import Util.ConexaoBD;
import Util.HtmlComboBoxes;
import Util.ConstantesProjecto;
import static Util.ConstantesProjecto.ID;
import static Util.ConstantesProjecto.NOME;
import static Util.ConstantesProjecto.TODOS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azm
 */
public class PaisDAO
{

    private Connection conexaoBD;

    public PaisDAO () throws ClassNotFoundException , SQLException
    {
        conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirPais (PaisModelo paisModelo) throws SQLException
    {
        String sql = "INSERT INTO public.pais( nome) VALUES (?);";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setString (1 , paisModelo.getNome ());
            pst.execute ();
            pst.close ();
        }
        return false;
    }

    public List<PaisModelo> getListaPais () throws SQLException
    {
        String sql = "SELECT * FROM public.pais";
        List<PaisModelo> listaPaises = new ArrayList<> ();

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    PaisModelo paisModelo = new PaisModelo ();
                    paisModelo.setPaisPK (rs.getInt (1));
                    paisModelo.setNome (rs.getString ("nome"));

                    listaPaises.add (paisModelo);
                }
            }
            pst.close ();
        }
        return listaPaises;
    }

    public List<PaisModelo> pesquisaPaisPK (int pais_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pais WHERE pais_pk=? ORDER BY pais_pk ASC"))
        {
            List<PaisModelo> listaPais = new ArrayList<PaisModelo> ();

            pst.setInt (1 , pais_pk);
            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    PaisModelo paisModelo = new PaisModelo ();
                    paisModelo.setPaisPK (rs.getInt ("pais_pk"));
                    paisModelo.setNome (rs.getString ("nome"));

                    listaPais.add (paisModelo);
                }
            }
            pst.close ();
            return listaPais;
        }
    }

    public PaisModelo getDadosPais (int pais_pk) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.pais WHERE pais_pk=" + pais_pk))
        {
            PaisModelo paisModelo = new PaisModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt (1) > 0)
            {
                paisModelo.setPaisPK (rs.getInt ("pais_pk"));
                paisModelo.setNome (rs.getString ("nome"));

                return paisModelo;
            }

        }
        return null;
    }

    public boolean alterarPais (PaisModelo paisModelo) throws SQLException
    {
        if (paisModelo != null)
        {
            String sql = "UPDATE public.pais\n"
                    + "SET nome=?\n"
                    + "WHERE pais_pk=?";

            try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
            {
                pst.setString (1 , paisModelo.getNome ());
                pst.setInt (2 , paisModelo.getPaisPK ());

                pst.execute ();
                pst.close ();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPais (PaisModelo paisModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.pais\n"
                + "	WHERE pais_pk=?"))
        {
            pst.setInt (1 , paisModelo.getPaisPK ());

            pst.execute ();
            pst.close ();
            return true;

        }
    }

    public String gerarComboPaises (String id , String form , String valorSeleccionado) throws Exception
    {

        return new HtmlComboBoxes ().select ("Pais" , form , "cboPais" + id , "pais_pk" ,
                "nome" , "" , valorSeleccionado);
    }

    public String gerarComboPaisesComEvento (String nomeCombo , String nomeForm , String nomeComboSeguinte , String valorSelecionado) throws Exception
    {

        HtmlComboBoxes hcb = new HtmlComboBoxes ();
        String ncs = nomeComboSeguinte;
        return hcb.select ("pais" , nomeForm , nomeCombo , "pais_pk" , "nome" ,
                "onChange=\"javascript: "
                + "selectChange('" + nomeCombo + "', '" + ncs
                + "', " + ncs + "Text, " + ncs
                + "Relac, " + ncs + "Value);\"" , valorSelecionado);
    }

    /**
     * Retorna uma lista de paises a partir de um determinado parametro.
     */
    public static List<Bean> get (int parametro , String ValorParametro)
            throws ClassNotFoundException , SQLException
    {

        List<Bean> lista = new ArrayList<Bean> ();

        String sql = "SELECT * FROM Pais where";

        switch (parametro)
        {
            case ID:
                sql += "idPais = " + Integer.parseInt (ValorParametro);
                break;
            case NOME:
                sql += "designacao = '" + ValorParametro + "'";
                break;
            case TODOS:
                sql = "SELECT * FROM Pais";
        }

        Connection conn = new ConexaoBD ().getConnection ();

        Statement stmt = conn.createStatement ();

        ResultSet rs = stmt.executeQuery (sql);

        PaisModelo pais;

        while (rs.next ())
        {
            pais = new PaisModelo ();
            pais.setPaisPK (rs.getInt ("pais_pk"));
            pais.setNome (rs.getString ("nome"));
            lista.add ((Bean) pais);
        }

        rs.close ();

        stmt.close ();

        conn.close ();

        return lista;

    }

    /**
     * Retorna um objecto "table" html contendo todos os paises.
     */
    public static String getTodosComoTabela () throws ClassNotFoundException ,
            SQLException
    {

        //Construindo o cabeçalho...
        String tabela = "<table>"
                + "<thead>"
                + "<tr>"
                + "<th class='titulo_coluna_esquerda'>Nº</th>"
                + "<th class='titulo_coluna_esquerda' width='100px'>País</th>"
                + "</tr>"
                + "</thead>";

        List lista = get (TODOS , null);

        PaisModelo pais = null;

        //Adicionando as linhas a tabela...
        for (int i = 0; i < lista.size (); ++i)
        {
            pais = (PaisModelo) lista.get (i);
            tabela += "<tbody>"
                    + "<tr>"
                    + "<td>" + (i + 1) + "</td>"
                    + "<td>" + inserirObjectoHTML ("text" , "txtPais" + pais.getPaisPK () , "txtPais" + pais.getPaisPK () , pais.getNome () , "") + "</td>"
                    + "<td>" + inserirObjectoHTML ("button" , "btnEditar" + pais.getPaisPK () , "btnEditar" + pais.getPaisPK () , "editar" , "editar(txtPais" + pais.getPaisPK () + "," + pais.getPaisPK () + "," + ConstantesProjecto.EDITAR + ")") + "</td>"
                    + "<td>" + inserirObjectoHTML ("button" , "btnEliminar" + pais.getPaisPK () , "btnEliminar" + pais.getPaisPK () , "eliminar" , "eliminar(" + pais.getPaisPK () + "," + ConstantesProjecto.ELIMINAR + ")") + "</td>"
                    + "</tr>";
        }

        tabela += "<tr>"
                + "<td></td>"
                + "<td>" + inserirObjectoHTML ("text" , "txtPaisNovo" , "txtPaisNovo" , "" , "") + "</td>"
                + "<td>" + inserirObjectoHTML ("button" , "btnNovo" , "btnNovo" , "inserir" , "guardar(txtPaisNovo," + ConstantesProjecto.GUARDAR + ")") + "</td>"
                + "<td></td>"
                + "</tr>"
                + "</tbody>"
                + "</table>";

        return tabela;

    }

    /**
     * Constroi um determinado objecto html.
     */
    private static String inserirObjectoHTML (String tipo , String nome , String id ,
                                              String valor , String onclick)
    {

        String objecto = "<input type='" + tipo + "' "
                + "name='" + nome + "' "
                + "id='" + id + "' "
                + "value='" + valor + "' "
                + "onclick='" + onclick + "' />";

        return objecto;

    }
}
