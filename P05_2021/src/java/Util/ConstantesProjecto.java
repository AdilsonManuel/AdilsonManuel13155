/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author azm
 */
public interface ConstantesProjecto
{

    public static final int INVALID = 0;
    public static final int ROOT = 1;
    public static final int ADMIN = ROOT + 1;
    public static final int FUNCIONARIO = ADMIN + 1;
    public static final int CLIENTE_CADASTRADO = FUNCIONARIO + 1;
    public static final int CLIENTE_ANONIMO = CLIENTE_CADASTRADO + 1;
    public static String CADASTRAR_CLIENTE = "CADASTRAR_CLIENTE";
}
