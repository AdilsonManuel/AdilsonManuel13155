/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.MunicipioModelo;
import Util.ConexaoBD;
import Util.HtmlComboBoxes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Mensagens;

/**
 *
 * @author pc-accer
 */
public class MunicipioDAO
{

    private Connection conexaoBD;

    public MunicipioDAO () throws ClassNotFoundException , SQLException
    {

        this.conexaoBD = new ConexaoBD ().getConnection ();
    }

    public boolean inserirMunicipio (MunicipioModelo municipioModelo) throws SQLException
    {
        if (municipioModelo != null)
        {
            String sql = "INSERT INTO public.municipio(nome, provincia_fk) VALUES (?, ?);";
            try (PreparedStatement pst = this.conexaoBD.prepareStatement (sql))
            {
                pst.setString (1 , municipioModelo.getNome ());
                pst.setInt (2 , municipioModelo.getProvinciaModelo ().getProvincia_pk ());

                pst.execute ();
                Mensagens.sucessoGuardar (municipioModelo.getNome ());
                pst.close ();
                return true;
            }
        }
        return false;
    }

    public List<MunicipioModelo> getListaMunicipio () throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.municipio ORDER BY municipio_pk ASC"))
        {
            List<MunicipioModelo> listaModelos = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                MunicipioModelo municipioModelo = new MunicipioModelo ();
                municipioModelo.setMuncipio_pk (rs.getInt ("municipio_pk"));
                municipioModelo.setNome (rs.getString ("nome"));
                municipioModelo.setProvinciaModelo (new ProvinciaDAO ().getDadosProvincia (rs.getInt (" provincia_fk")));

                listaModelos.add (municipioModelo);
            }
            pst.close ();
            rs.close ();
            return listaModelos;
        }

    }

    public List<MunicipioModelo> getMunicipioPK (int municipioPK) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.municipio WHERE municipio_pk=? ORDER BY municipio_pk ASC"))
        {
            List<MunicipioModelo> listaModelos = new ArrayList<> ();
            ResultSet rs = pst.executeQuery ();

            pst.setInt (1 , municipioPK);
            while (rs.next ())
            {
                MunicipioModelo municipioModelo = new MunicipioModelo ();
                municipioModelo.setMuncipio_pk (rs.getInt ("municipio_pk"));
                municipioModelo.setNome (rs.getString ("nome"));
                municipioModelo.setProvinciaModelo (new ProvinciaDAO ().getDadosProvincia (rs.getInt (" provincia_fk")));

                listaModelos.add (municipioModelo);
            }
            pst.close ();
            rs.close ();
            return listaModelos;
        }

    }

    public MunicipioModelo getDadosMunicipio (int municipio_pk) throws SQLException , ClassNotFoundException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM public.municipio WHERE municipio_pk=" + municipio_pk))
        {
            MunicipioModelo municipioModelo = new MunicipioModelo ();
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("municipio_pk") > 0)
            {
                municipioModelo.setMuncipio_pk (rs.getInt ("municipio_pk"));
                municipioModelo.setNome (rs.getString ("nome"));
                municipioModelo.setProvinciaModelo (new ProvinciaDAO ().getDadosProvincia (rs.getInt ("provincia_fk")));

                return municipioModelo;
            }

        }
        return null;
    }

    public boolean alterarMunicipio (MunicipioModelo municipioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("UPDATE public.municipio SET nome=?, provincia_fk=? WHERE municipio_pk=?"))
        {
            pst.setString (1 , municipioModelo.getNome ());
            pst.setInt (1 , municipioModelo.getProvinciaModelo ().getProvincia_pk ());
            pst.setInt (3 , municipioModelo.getMuncipio_pk ());

            pst.execute ();
            Mensagens.sucessoAlterar (municipioModelo.getNome ());
            pst.close ();
            return true;
        }
    }

    public boolean eliminarMunicipio (MunicipioModelo municipioModelo) throws SQLException
    {
        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("DELETE FROM public.municipio\n"
                + "	WHERE municipio_pk=?"))
        {
            pst.setInt (1 , municipioModelo.getMuncipio_pk ());

            pst.execute ();
            Mensagens.sucessoEliminar (municipioModelo.getNome ());
            pst.close ();
            return true;
        }
    }

    /**
     *
     * @param nomeCombo nome da combobox no formul??rio
     * @param nomeForm nome do formul??rio onde a combobox est?? inserida
     * @param valorSelecionado
     * @return
     * @throws Exception
     */
    public String gerarComboBox (String nomeCombo , String nomeForm , String valorSelecionado) throws Exception
    {
        HtmlComboBoxes hcb = new HtmlComboBoxes ();
        //nome do arrayJavascript
        //ex: nomeCombo+Text
        //ex: nomeCombo+Relac
        //ex: nomeCombo+Value
        return hcb.selectDependente ("municipio" , nomeForm , nomeCombo ,
                "cboprovincia" , nomeCombo , "municipio_pk" , "nome" ,
                "provincia_fk" , "" , valorSelecionado);
    }

    /**
     *
     * @param nomeCombo nome da combobox no formul??rio
     * @param nomeForm nome do formul??rio onde a combobox est?? inserida
     * @param nomeComboSeguinte nome da combobox seguinte que ser?? afectada
     * @param valorSelecionado
     * @return
     * @throws Exception
     */
    public String gerarComboBoxComEvento (String nomeCombo , String nomeForm , String nomeComboSeguinte , String valorSelecionado) throws Exception
    {
        HtmlComboBoxes hcb = new HtmlComboBoxes ();
        String ncs = nomeComboSeguinte;
        //nome do arrayJavascript
        //ex: nomeCombo+Text
        //ex: nomeCombo+Relac
        //ex: nomeCombo+Value
        return hcb.selectDependente ("cboMunicipio" , nomeForm , nomeCombo ,
                "cboProvincia" , nomeCombo , "municipio_pk" , "nome" ,
                "provincia_fk" , "onChange=\"javascript: "
                + "selectChange('" + nomeCombo + "', '" + ncs
                + "', " + ncs + "Text, " + ncs
                + "Relac, " + ncs + "Value);\"" , valorSelecionado);
    }
}
