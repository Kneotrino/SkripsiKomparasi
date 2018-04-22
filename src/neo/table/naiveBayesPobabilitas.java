/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author SEED
 */
public class naiveBayesPobabilitas implements Serializable{

    @Override
    public String toString() {
        return getAtribut()
                + "\ncount = " + getCount()
                + "\tleft = " + getCountLeft()
                + "\tnotleft = " + getCountNotLeft()
                + "\tSumLeft = " + getSumLeft()
                + "\tSumNotLeft = " + getSumNotLeft()
                + "\tP(left) = " + getProbabilitasLeft()
                + "\tP(notleft) = " + getProbabilitasNotLeft();
    }

    
    public naiveBayesPobabilitas() {
    }

    public naiveBayesPobabilitas(String atribut) {
        this.atribut = atribut;
    }

    private int number;

    public static final String PROP_NUMBER = "number";
    public static final Double JUMALAH_KELAS = 2d;
    
    private double N = 2d;

    public static final String PROP_N = "N";

    /**
     * Get the value of N
     *
     * @return the value of N
     */
    public double getN() {
        return N;
    }

    /**
     * Set the value of N
     *
     * @param N new value of N
     */
    public void setN(double N) {
        double oldN = this.N;
        this.N = N;
        propertyChangeSupport.firePropertyChange(PROP_N, oldN, N);
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        int oldNumber = this.number;
        this.number = number;
        propertyChangeSupport.firePropertyChange(PROP_NUMBER, oldNumber, number);
    }

    private String atribut;

    private double countLeft;

    private double countNotLeft;

    private double sumLeft;

    public static final String PROP_SUMLEFT = "sumLeft";

    private double sumNotLeft;

    public static final String PROP_SUMNOTLEFT = "sumNotLeft";

    /**
     * Get the value of sumNotLeft
     *
     * @return the value of sumNotLeft
     */
    public double getSumNotLeft() {
        return sumNotLeft;
    }

    /**
     * Set the value of sumNotLeft
     *
     * @param sumNotLeft new value of sumNotLeft
     */
    public void setSumNotLeft(double sumNotLeft) {
        double oldSumNotLeft = this.sumNotLeft;
        this.sumNotLeft = sumNotLeft;
        propertyChangeSupport.firePropertyChange(PROP_SUMNOTLEFT, oldSumNotLeft, sumNotLeft);
    }

    /**
     * Get the value of sumLeft
     *
     * @return the value of sumLeft
     */
    public double getSumLeft() {
        return sumLeft;
    }

    /**
     * Set the value of sumLeft
     *
     * @param sumLeft new value of sumLeft
     */
    public void setSumLeft(double sumLeft) {
        double oldSumLeft = this.sumLeft;
        this.sumLeft = sumLeft;
        propertyChangeSupport.firePropertyChange(PROP_SUMLEFT, oldSumLeft, sumLeft);
    }

    private double count;

    private String info;

    private double probabilitasLeft;

    private double probabilitasNotLeft;

    public static final String PROP_PROBABILITASNOTLEFT = "probabilitasNotLeft";

    /**
     * Get the value of probabilitasNotLeft
     *
     * @return the value of probabilitasNotLeft
     */
    public double getProbabilitasNotLeft() {
        return probabilitasNotLeft;
    }

    /**
     * Set the value of probabilitasNotLeft
     *
     * @param probabilitasNotLeft new value of probabilitasNotLeft
     */
    public void setProbabilitasNotLeft(double probabilitasNotLeft) {
        double oldProbabilitasNotLeft = this.probabilitasNotLeft;
        this.probabilitasNotLeft = probabilitasNotLeft;
        propertyChangeSupport.firePropertyChange(PROP_PROBABILITASNOTLEFT, oldProbabilitasNotLeft, probabilitasNotLeft);
    }

    public static final String PROP_PROBABILITASLEFT = "probabilitasLeft";

    /**
     * Get the value of probabilitasLeft
     *
     * @return the value of probabilitasLeft
     */
    public double getProbabilitasLeft() {
        return probabilitasLeft;
    }

    /**
     * Set the value of probabilitasLeft
     *
     * @param probabilitasLeft new value of probabilitasLeft
     */
    public void setProbabilitasLeft(double probabilitasLeft) {
        double oldProbabilitasLeft = this.probabilitasLeft;
        this.probabilitasLeft = probabilitasLeft;
        propertyChangeSupport.firePropertyChange(PROP_PROBABILITASLEFT, oldProbabilitasLeft, probabilitasLeft);
    }

    public static final String PROP_INFO = "info";

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        String oldInfo = this.info;
        this.info = info;
        propertyChangeSupport.firePropertyChange(PROP_INFO, oldInfo, info);
    }

    public static final String PROP_COUNT = "count";

    /**
     * Get the value of count
     *
     * @return the value of count
     */
    public double getCount() {
        return countLeft+countNotLeft;
    }
    
    

    /**
     * Set the value of count
     *
     * @param count new value of count
     */
    public void setCount(double count) {
        double oldCount = this.count;
        this.count = count;
        propertyChangeSupport.firePropertyChange(PROP_COUNT, oldCount, count);
    }
    
    public void addCount()
    {
        setCount(count++);
    }

    public static final String PROP_COUNTNOTLEFT = "countNotLeft";

    /**
     * Get the value of countNotLeft
     *
     * @return the value of countNotLeft
     */
    public double getCountNotLeft() {
        return countNotLeft;
    }
    public void addCountNotLeft() {
        this.countNotLeft += 1d;
    }

    /**
     * Set the value of countNotLeft
     *
     * @param countNotLeft new value of countNotLeft
     */
    public void setCountNotLeft(double countNotLeft) {
        double oldCountNotLeft = this.countNotLeft;
        this.countNotLeft = countNotLeft;
        propertyChangeSupport.firePropertyChange(PROP_COUNTNOTLEFT, oldCountNotLeft, countNotLeft);
    }

    public static final String PROP_COUNTLEFT = "countLeft";

    /**
     * Get the value of countLeft
     *
     * @return the value of countLeft
     */
    public double getCountLeft() {
        return countLeft;
    }

    /**
     * Set the value of countLeft
     *
     * @param countLeft new value of countLeft
     */
    
    public void setCountLeft(double countLeft) {
        double oldCountLeft = this.countLeft;
        this.countLeft = countLeft;
        propertyChangeSupport.firePropertyChange(PROP_COUNTLEFT, oldCountLeft, countLeft);
    }
    public void addCountLeft() {
        this.countLeft += 1d;
    }
    public void addSumLeft() {
        this.sumLeft += 1d;
    }
    public void addSumNotLeft() {
        this.sumNotLeft += 1d;
    }

    public static final String PROP_ATRIBUT = "atribut";

    /**
     * Get the value of atribut
     *
     * @return the value of atribut
     */
    public String getAtribut() {
        return atribut;
    }

    /**
     * Set the value of atribut
     *
     * @param atribut new value of atribut
     */
    public void setAtribut(String atribut) {
        String oldAtribut = this.atribut;
        this.atribut = atribut;
        propertyChangeSupport.firePropertyChange(PROP_ATRIBUT, oldAtribut, atribut);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
}
