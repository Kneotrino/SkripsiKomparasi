/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

import weka.classifiers.bayes.NaiveBayes;

/**
 *
 * @author SEED
 */
public class NB extends NaiveBayes {
    
    private int konstanta;

    /**
     * Get the value of konstanta
     *
     * @return the value of konstanta
     */
    public int getKonstanta() {
        return konstanta;
    }

    /**
     * Set the value of konstanta
     *
     * @param konstanta new value of konstanta
     */
    public void setKonstanta(int konstanta) {
        this.konstanta = konstanta;
    }

}
