/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.ConexaoBD;

/**
 *
 * @author azm
 */
public class SurveyDAO
{

    private Connection conexao;

    public SurveyDAO () throws ClassNotFoundException , SQLException
    {
        this.conexao = new ConexaoBD ().getConnectio ();
    }

    public void updateTotalSurvey (int votes) throws SQLException
    {
        String sql = "UPDATE surveyResults SET votes = votes + 1 WHERE survey_pk " + votes;

        try (PreparedStatement pst = conexao.prepareStatement (sql))
        {
            pst.executeUpdate ();

            pst.close ();
        }
    }

    public int listTotalSurvey () throws SQLException
    {
        String sql = "SELECT SUM(votes) FROM surveyResults";
        int total = 0;
        try (PreparedStatement pst = conexao.prepareStatement (sql))
        {
            ResultSet rs = pst.executeQuery ();
            rs.next ();

            return total = rs.getInt (1);

        }
    }

    public int[] listResultsSurvey () throws SQLException
    {
        int totalSurvey = listTotalSurvey ();
        int votes = 0, count = 0;
        int resultSurvey[] = null;

        String sql = "SELECT surveyOption, votes, survey_pk FROM surveyResults ORDER BY survey_pk";

        try (PreparedStatement pst = conexao.prepareStatement (sql))
        {
            ResultSet rs = pst.executeQuery ();

            while (rs.next ())
            {
                rs.getString (1);
                votes = rs.getInt (2);

                resultSurvey[count] = votes / totalSurvey * 100;
                count++;
            }
            return resultSurvey;
        }
    }

}
