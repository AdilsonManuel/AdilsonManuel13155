/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.LocalizacaoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConexaoBD;
import util.TabelaInterface;

/**
 *
 * @author azm
 */
public class LocalizacaoDAO implements TabelaInterface<LocalizacaoModelo>
{

    private final Connection conexaoBD;

    public LocalizacaoDAO () throws ClassNotFoundException
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
            pst.setString (1, localizacaoModelo.getLocalizacao_pk ());
            pst.setString (2, localizacaoModelo.getDesignacao ());
            pst.setBoolean (3, localizacaoModelo.getEh_distrito ());
            pst.setString (4, localizacaoModelo.getLocalizacao_fk_pai ());
            pst.setString (5, localizacaoModelo.getEhNivel ());

            if (!localizacaoModelo.getEh_distrito ())
            {
                pst.setNull (3, 0);
            }
            else
            {
                pst.setBoolean (3, localizacaoModelo.getEh_distrito ());
            }
            pst.setString (4, localizacaoModelo.getEhNivel ());
            pst.setString (5, localizacaoModelo.getLocalizacao_fk_pai ());

            if (pst.executeUpdate () > 0)
            {
                System.err.println ("Cadastrado com sucesso o registo: " + pst);
                return true;
            }
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de criar um registo na tabela localizacao com a primária " + localizacaoModelo.getLocalizacao_pk ();
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE, msg, ex);
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
            pst.setString (1, localizacaoModelo.getLocalizacao_pk ());
            pst.setString (2, localizacaoModelo.getEhNivel ());

            ResultSet rs = pst.executeQuery ();

            if (!rs.next ())
            {
                return null;
            }

            localizacaoModelo1.setLocalizacao_pk (rs.getString (1));
            localizacaoModelo1.setDesignacao (rs.getString (3));
            return localizacaoModelo1;
        }
        catch (SQLException ex)
        {
            String msg = "Falha na tentativa de localizar um registo na tabela localizacao "
                    + "com a chave primária " + localizacaoModelo1.getLocalizacao_pk () + ", " + localizacaoModelo1.getDesignacao ();
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE, msg, ex);
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
            pst.setInt (1, localizacaoModelo.getPkSeq ());
            pst.setString (2, localizacaoModelo.getDesignacao ());

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
            Logger.getLogger (LocalizacaoDAO.class.getName ()).log (Level.SEVERE, msg, ex);
        }
        return false;

    }

    @Override
    public boolean insert (LocalizacaoModelo localizacaoModelo)
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

    public LocalizacaoModelo findLocalidade (String nomeDoCampo) throws SQLException
    {
//        System.out.println ("DAO.LocalizacaoDAO.findLocalidade() ->"+nomeDoCampo);

        try (PreparedStatement pst = this.conexaoBD.prepareStatement ("SELECT * FROM PUBLIC.localizacao WHERE localizacao_pk='" + nomeDoCampo + "'"))
        {
            LocalizacaoModelo localizacaoModelo = new LocalizacaoModelo ();

            ResultSet rs = pst.executeQuery ();
            rs.next ();

            if (rs.getInt ("pkSeq") > 0)
            {

                localizacaoModelo.setPkSeq (rs.getInt ("pkSeq"));
                localizacaoModelo.setLocalizacao_pk (rs.getString ("localizacao_pk"));
                localizacaoModelo.setDesignacao (rs.getString ("designacao"));
                localizacaoModelo.setEh_distrito (rs.getBoolean ("eh_distrito"));
                localizacaoModelo.setLocalizacao_fk_pai (rs.getString ("localizacao_fk_pai"));
                localizacaoModelo.setEhNivel (rs.getString ("ehNivel"));

                return localizacaoModelo;
            }

        }
        return null;
    }
}
