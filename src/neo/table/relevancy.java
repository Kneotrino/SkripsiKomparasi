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
public class relevancy implements Serializable{

    public relevancy() {
    }

    private double TP;

    private double TN;

    private double FN;
    
    private double FP;

    private double jumlahData;

    private String info;
    
    private double jumlahDataLatih;

    public static final String PROP_JUMLAHDATALATIH = "jumlahDataLatih";

    /**
     * Get the value of jumlahDataLatih
     *
     * @return the value of jumlahDataLatih
     */
    public double getJumlahDataLatih() {
        return jumlahDataLatih;
    }

    /**
     * Set the value of jumlahDataLatih
     *
     * @param jumlahDataLatih new value of jumlahDataLatih
     */
    public void setJumlahDataLatih(double jumlahDataLatih) {
        double oldJumlahDataLatih = this.jumlahDataLatih;
        this.jumlahDataLatih = jumlahDataLatih;
        propertyChangeSupport.firePropertyChange(PROP_JUMLAHDATALATIH, oldJumlahDataLatih, jumlahDataLatih);
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


    public relevancy(String info) {
        this.info = info;
    }

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
        this.info = info;
    }

    /**
     * Get the value of jumlahData
     *
     * @return the value of jumlahData
     */
    public double getJumlahData() {
        return jumlahData;
    }

    /**
     * Set the value of jumlahData
     *
     * @param jumlahData new value of jumlahData
     */
    public void setJumlahData(double jumlahData) {
        this.jumlahData = jumlahData;
    }

    /**
     * Get the value of FP
     *
     * @return the value of FP
     */
    public double getFP() {
        return FP;
    }

    /**
     * Set the value of FP
     *
     * @param FP new value of FP
     */
    public void setFP(double FP) {
        this.FP = FP;
    }


    /**
     * Get the value of FN
     *
     * @return the value of FN
     */
    public double getFN() {
        return FN;
    }

    public double getPRE()
    {
        return (getTP() + getFP()) / getJumlahData();
    }
    public double getTPR()
    {
        return getTP()/ (getTP()+ getFN());
    }
    public double getPPV()
    {
        return getTP()/ (getTP()+ getFP());
    }
    public double getTNR()
    {
        return getTN()/ (getTN()+ getFP());
    }
    public double getNPV()
    {
        return getTN()/ (getTN()+ getFN());
    }
    public double getFNR()
    {
        return getFN()/ (getTN()+ getFN());
    }
    
    public double getFPR()
    {
        return getFP()/ (getFP()+ getTN());
    }
    public double getFDR()
    {
        return 1-getPPV();
    }
    
    public double getFOR()
    {
        return 1-getNPV();
    }
    
    public double getACC()
    {
        return (getTP()+getTN()) / getJumlahData();
    }
    
    public double getF1()
    {
        return (2*getTP()) / ((2*getTP())+getFP()+getFN());
    }
    
    public double  getBM()
    {
        return getTPR()+getTNR()-1;
    }
    public double  getLRplus()
    {
        return getTPR()/getFPR();
    }
    public double  getLRminus()
    {
        return getFNR()/getTNR();
    }
    public double  getDOR()
    {
        return getLRplus()/getLRminus();
    }
    public double  getMCC()
    {
        double akar = (TP+FP)*(TP+FN)*(TN+FP)*(TN+FN);
        akar = Math.sqrt(akar);
        
        return ((TP*TN)-(FP*FN))/(akar);
//        return akar;
    }
    
    
    
    /**
     * Set the value of FN
     *
     * @param FN new value of FN
     */
    public void setFN(double FN) {
        this.FN = FN;
    }

    /**
     * Get the value of TN
     *
     * @return the value of TN
     */
    public double getTN() {
        return TN;
    }

    /**
     * Set the value of TN
     *
     * @param TN new value of TN
     */
    public void setTN(double TN) {
        this.TN = TN;
    }

    /**
     * Get the value of TP
     *
     * @return the value of TP
     */
    public double getTP() {
        return TP;
    }

    /**
     * Set the value of TP
     *
     * @param TP new value of TP
     */
    public void setTP(double TP) {
        this.TP = TP;
    }
    
}
