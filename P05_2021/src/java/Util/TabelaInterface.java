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
import DAO.*;

public interface TabelaInterface<T>
{

    public boolean create (T t);

    public T find (T t);

    public boolean update (T t);

    public boolean insert (T t);
}
