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
    public static final int ID = 0;
    public static final int NOME = 1;
    public static final int TODOS = 2;
    
    /*
     * Operacoes.
     */
    int INICIAR = 0;
    int GUARDAR = 1;
    int EDITAR = 2;
    int ELIMINAR = 3;
    int ELIMINAR_TODOS = 4;
    int CARREGAR_DADOS_USUARIO = 5;
    int BLOQUEAR_CONTA_USUARIO = 6;
    int CARREGAR_PROVINCIAS_PAIS = 7;
    int CARREGAR_MUNICIPIOS_PROVINCIA = 8;
    int CLIENTE = 9;
    int CRIAR_PERFIL = 11;

    /*
     * Resultados.
     */
    int NULO = 0;
    int SUCESSO = 1;
    int DUPLICADO = 1062;
    int ERRO = -1;

}
