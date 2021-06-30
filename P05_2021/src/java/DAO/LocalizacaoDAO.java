/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.LocalizacaoModelo;
import Util.ConexaoBD;
import Util.TabelaInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azm
 */
public class LocalizacaoDAO implements TabelaInterface<LocalizacaoModelo>
{

    private final Connection conexaoBD;

    public LocalizacaoDAO () throws ClassNotFoundException , SQLException
    {
        this.conexaoBD = new ConexaoBD ().getConnection ();

    }

    @Override
    public boolean create (LocalizacaoModelo localizacaoModelo)
    {
        String sql = "INSERT INTO public.localizacao(\n"
                + "	localizacao_pk, designacao, eh_distrito, localizacao_fk_pai, ehnivel)\n"
                + "	VALUES (?, ?, ?, ?, ?);";;

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1 , localizacaoModelo.getCodigo_localizacao ());
            pst.setString (2 , localizacaoModelo.getDesignacao ());

            if (!localizacaoModelo.getEhDistrito ())
            {
                pst.setNull (3 , 0);
            }
            else
            {
                pst.setBoolean (3 , localizacaoModelo.getEhDistrito ());
            }
            pst.setInt (4 , localizacaoModelo.getEh_nivel ());
            pst.setInt (5 , localizacaoModelo.getCodigo_localizacao_pai ());

            if (pst.executeUpdate () > 0)
            {
                System.err.println ("Cadastrado com sucesso o registo: " + pst);
                return true;
            }
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de criar um registo na tabela localizacao com a primária " + localizacaoModelo.getCodigo_localizacao ();
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return false;

    }

    @Override
    public LocalizacaoModelo find (LocalizacaoModelo localizacaoModelo)
    {
        String sql = "SELECT * \n"
                + "	FROM public.localizacao WHERE localizacao_pk=? AND ehnivel=?";

        LocalizacaoModelo localizacaoModelo1 = new LocalizacaoModelo ();

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1 , localizacaoModelo.getCodigo_localizacao ());
            pst.setInt (2 , localizacaoModelo.getEh_nivel ());

            ResultSet rs = pst.executeQuery ();

            if (!rs.next ())
            {
                return null;
            }

            localizacaoModelo1.setLocalizacao_pk (Integer.parseInt (rs.getString (1)));
            localizacaoModelo1.setDesignacao (rs.getString (3));
            return localizacaoModelo1;
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de localizar um registo na tabela localizacao "
                    + "com a chave primária " + localizacaoModelo1.getLocalizacao_pk () + ", " + localizacaoModelo1.getDesignacao ();
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return null;
    }

    @Override
    public boolean update (LocalizacaoModelo localizacaoModelo)
    {
        String sql = "UPDATE public.localizacao\n"
                + "	SET designacao=?\n"
                + "	WHERE pkseq=?";

        try (PreparedStatement pst = conexaoBD.prepareStatement (sql))
        {
            pst.setInt (1 , localizacaoModelo.getCodigo_localizacao ());
            pst.setString (2 , localizacaoModelo.getDesignacao ());

            if (pst.executeUpdate () > 0)
            {
                System.err.println ("Actualizado com sucesso o registo: " + localizacaoModelo.getLocalizacao_pk ());
                return true;
            }
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de actualizar um registo na tabela"
                    + " localizacao com a chave primária " + localizacaoModelo.getLocalizacao_pk ();
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE , msg , ex);
        }
        return false;

    }

    @Override
    public boolean insert (LocalizacaoModelo localizacaoModelo
    )
    {
        LocalizacaoModelo reg = find (localizacaoModelo);

        if (reg != null)
        {
            if (reg.getDesignacao ().equalsIgnoreCase (localizacaoModelo.getDesignacao ()))
            {
                System.err.println ("O registo: " + reg.getLocalizacao_pk ()
                        + " " + reg.getDesignacao () + "," + " já existe");
                return false;
            }
            localizacaoModelo.setLocalizacao_pk (reg.getLocalizacao_pk ());
            return update (localizacaoModelo);
        }
        return create (localizacaoModelo);
    }

}
