/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author pc-accer
 */
public class Mensagens {
    
    public static void sucessoGuardar(String data)
    {
        if (data == null) 
        {
            JOptionPane.showMessageDialog(null, "Guardado (a) com sucesso");
        }
        else
        {
            JOptionPane.showMessageDialog(null,data + "Guardado (a) com sucesso"); 
        }
        
    }
    public static void sucessoAlterar(String data)
    {
        if (data == null) 
        {
            JOptionPane.showMessageDialog(null, "Alterado (a) com sucesso");
        }
        else
        {
            JOptionPane.showMessageDialog(null,data + "Alterado (a) com sucesso"); 
        }
        
    }
    public static void sucessoEliminar(String data)
    {
        if (data == null) 
        {
            JOptionPane.showMessageDialog(null, "Eliminado (a) com sucesso");
        }
        else
        {
            JOptionPane.showMessageDialog(null,data + "Eliminado (a) com sucesso"); 
        }
        
    }
}
