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

    
    public naiveBayesPobabilitas() {
    }

    private int number;

    public static final String PROP_NUMBER = "number";

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
        return count;
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

    public static final String PROP_COUNTNOTLEFT = "countNotLeft";

    /**
     * Get the value of countNotLeft
     *
     * @return the value of countNotLeft
     */
    public double getCountNotLeft() {
        return countNotLeft;
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
