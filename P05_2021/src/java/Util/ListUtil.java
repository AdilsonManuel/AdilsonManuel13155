/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Modelo.LocalizacaoModelo;


public class ListUtil
{

    public static <T> List<T> ordenarPorOrdemAlfabetica(List<T> lista)
    {
        Collections.sort(lista, null);
        return lista;
    }

    public static List<Modelo.LocalizacaoModelo> ordenarPorOrdemAlfabeticaLocalizacaoModelo(List<LocalizacaoModelo> lista)
    {
        List<LocalizacaoModelo> listaOrdenada, listaComIndefinidosNoFim;
        
        // ordenar a lista e fazer o append dos indefinidos
        listaOrdenada = ordenarPorOrdemAlfabetica(lista);
        listaComIndefinidosNoFim = appendIndefinidos(listaOrdenada);

        return listaComIndefinidosNoFim;
    }

    private static List<LocalizacaoModelo> appendIndefinidos(List<LocalizacaoModelo> lista)
    {
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getDesignacao ().equals ("Indefinido")
                    || lista.get(i).getDesignacao().equals ("Indefinida"))
            {
                lista.add(lista.get(i));
                lista.remove(lista.get(i));
            }
        }
        return lista;
    }
}
