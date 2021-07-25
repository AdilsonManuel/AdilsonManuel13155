/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ProvinciaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Util.ConexaoBD;
import Util.HtmlComboBoxes;
import util.Mensagens;

/**
 *
 * @author pc-accer
 */
public class ProvinciaDAO
{

    private Connection conexsBD;

    public ProvinciaDAO () throws ClassNotFoundException , SQLException
    {
        this.conexsBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirProvincia (ProvinciaModelo provinciaModelo) throws SQLException
    {
        if (provinciaModelo != null)
        {
            String sql = "INSERT INTO public.provincia( nome, pais_fk) VALUES (?, ?)";

            try (PreparedStatement pst = this.conexsBD.prepareStatement (sql))
            {
                pst.setString (1 , provinciaModelo.getNome ());
                pst.setInt (2 , provinciaModelo.getPaisModelo ().getPaisPK ());

                pst.execute ();
                Mensagens.sucessoGuardar ("provincia " + provinciaModelo.getNome ());
                pst.close ();
                return true;

            }
        }
        return false;
    }

    public List<ProvinciaModelo> getListaProvincia () throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexsBD.prepareStatement ("SELECT * FROM public.provincia ORDER BY provincia_pk ASC"))
        {
            List<ProvinciaModelo> listProvincia = new ArrayList<> ();

            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    ProvinciaModelo provinciaModelo = new ProvinciaModelo ();
                    provinciaModelo.setProvincia_pk (rs.getInt ("provincia_pk"));
                    provinciaModelo.setNome (rs.getString ("nome"));
                    provinciaModelo.setPaisModelo (new PaisDAO ().getDadosPais (rs.getInt ("pais_pk")));

                    listProvincia.add (provinciaModelo);

                }
            }
            pst.close ();
            return listProvincia;
        }
    }

    public List<ProvinciaModelo> pesquisarProvinciaPK (int provinciaPK) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexsBD.prepareStatement ("SELECT * FROM public.provincia WHERE provincia_pk=? ORDER BY provincia_pk ASC"))
        {
            List<ProvinciaModelo> listaProvincia = new ArrayList<> ();
            pst.setInt (1 , provinciaPK);

            try (ResultSet rs = pst.executeQuery ())
            {
                while (rs.next ())
                {
                    ProvinciaModelo provinciaModelo = new ProvinciaModelo ();
                    provinciaModelo.setProvincia_pk (rs.getInt ("provincia_pk"));
                    provinciaModelo.setNome (rs.getString ("nome"));
                    provinciaModelo.setPaisModelo (new PaisDAO ().getDadosPais (rs.getInt ("pais_pk")));

                    listaProvincia.add (provinciaModelo);

                }
                pst.close ();
            }
            return listaProvincia;

        }
    }

    public ProvinciaModelo getDadosProvincia (int provincia_pk) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexsBD.prepareStatement ("SELECT * FROM public.provincia WHERE provincia_pk=" + provincia_pk))
        {
            ProvinciaModelo provinciaModelo = new ProvinciaModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("provincia_pk") > 0)
            {
                provinciaModelo.setProvincia_pk (rs.getInt ("provincia_pk"));
                provinciaModelo.setNome (rs.getString ("nome"));
                provinciaModelo.setPaisModelo (new PaisDAO ().getDadosPais (rs.getInt ("pais_fk")));

                return provinciaModelo;
            }

        }
        return null;
    }

    public boolean alterarProvincia (ProvinciaModelo provinciaModelo) throws SQLException
    {
        if (provinciaModelo != null)
        {

            try (PreparedStatement pst = this.conexsBD.prepareStatement ("UPDATE public.provincia SET nome=?, pais_fk=? WHERE provincia_pk=? ORDER BY provincia_pk ASC"))
            {
                pst.setString (1 , provinciaModelo.getNome ());
                pst.setInt (2 , provinciaModelo.getPaisModelo ().getPaisPK ());
                pst.setInt (3 , provinciaModelo.getProvincia_pk ());

                if (pst.execute ())
                {
                    Mensagens.sucessoAlterar ("Provincia " + provinciaModelo.getNome ());
                    pst.close ();
                    return true;
                }

            }
        }
        return false;
    }

    public boolean eliminarProvincia (ProvinciaModelo provinciaModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexsBD.prepareStatement ("DELETE FROM public.provincia\n"
                + "	WHERE provincia_pk=?"))
        {
            pst.setInt (1 , provinciaModelo.getProvincia_pk ());

            Mensagens.sucessoEliminar ("Provincia ");
            pst.close ();
            return true;

        }

    }

    /**
     *
     * @param nomeCombo nome da combobox no formulário
     * @param nomeForm nome do formulário onde a combobox está inserida
     * @param valorSelecionado
     * @return
     * @throws Exception
     */
    public String gerarComboProvincias (String nomeCombo , String nomeForm , String valorSelecionado) throws Exception
    {
        HtmlComboBoxes hcb = new HtmlComboBoxes ();
        //nome do arrayJavascript
        //ex: nomeCombo+Text
        //ex: nomeCombo+Relac
        //ex: nomeCombo+Value
        return hcb.selectDependente ("provincia" , nomeForm , nomeCombo ,
                "cboPais" , nomeCombo , "provincia_pk" , "nome" ,
                "pais_fk" , "" , valorSelecionado);
    }

    public String gerarComboProvinciascomEvento (String nomeCombo , String nomeForm , String nomeComboSeguinte , String valorSelecionado) throws Exception
    {
        HtmlComboBoxes hcb = new HtmlComboBoxes ();
        String ncs = nomeComboSeguinte;
        //nome do arrayJavascript
        //ex: nomeCombo+Text
        //ex: nomeCombo+Relac
        //ex: nomeCombo+Value
        return hcb.selectDependente ("provincia" , nomeForm , nomeCombo ,
                "comboPais" , nomeCombo , "provincia_pk" , "nome" ,
                "pais_fk" , "onChange=\"javascript: "
                + "selectChange('" + nomeCombo + "', '" + ncs
                + "', " + ncs + "Text, " + ncs
                + "Relac, " + ncs + "Value);\"" , valorSelecionado);

    }
}
