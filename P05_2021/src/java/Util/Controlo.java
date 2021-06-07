/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Modelo.ClienteModelo;
import java.util.ArrayList;

/**
 *
 * @author azm
 */
public class Controlo
{

    public static int idClienteComprador = 0;
    public static int tipoDeConta = ConstantesProjecto.ROOT;
    public static String operacao = null;
    public static ArrayList<ClienteModelo> clientesPesquisa = new ArrayList<ClienteModelo> ();
    public static ArrayList<Integer> carrinho = new ArrayList<Integer> ();
//    public static ArrayList<ProdutoModelo> produtos = new ArrayList<ProdutoModelo> ();

    public static Double totalApagar = 0.0;

    public static void inicializarCarrinho ()
    {
        carrinho = new ArrayList<Integer> ();
//        produtos = new ArrayList<ProdutoModelo> ();
        totalApagar = 0.0;
    }

    public static void adicionarAoCarrinho (int idProduto)
    {
        if (!jaExiste (idProduto))
        {
            carrinho.add (idProduto);
        }
    }

    public static boolean jaExiste (int idProduto)
    {
        for (int i = 0; i < carrinho.size (); i++)
        {
            if (carrinho.get (i) == idProduto)
            {
                return true;
            }
        }
        return false;

    }

    public static String getElmentos ()
    {
        String elementos = "";
        for (int i = 0; i < carrinho.size (); i++)
        {
            elementos = elementos + " - " + carrinho.get (i);
        }
        return elementos;
    }

    public static ArrayList<Integer> getCarrinho ()
    {
        return carrinho;
    }

}
