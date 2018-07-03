/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import weka.classifiers.trees.J48;

/**
 *
 * @author SEED
 */
public class C45 extends J48{
 
    private Tree CreateTree()
    {
        return new Tree();
    }

    private static class Tree {

        public Tree() {            
            Hashtable<Object, HashMap<Object, Object>> tree = new Hashtable<>();
        }
    }
    
}
