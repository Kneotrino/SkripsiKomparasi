/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

/**
 *
 * @author SEED
 */
public class gain {

    private Object data;

    public gain(Object object, long size, Long frek) {
        this.data = object;
        this.totalData = size;
        this.frekuensi = frek;
    }

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public Object getData() {
        return data;
    }

    private double totalData;

    /**
     * Get the value of totalData
     *
     * @return the value of totalData
     */
    public double getTotalData() {
        return totalData;
    }
    public  double log(double n, int base) {
        return Math.log(n) / Math.log(base);
    }  
    public double getEntropy()
    {        
        return -getProporsi() * log(getProporsi(), 2);
    }

    public gain(Object data, double totalData, String fitur) {
        this.data = data;
        this.totalData = totalData;
        this.fitur = fitur;
    }

    public gain(Object data, double totalData, long frekuensi, String fitur) {
        this.data = data;
        this.totalData = totalData;
        this.frekuensi = frekuensi;
        this.fitur = fitur;
    }

    @Override
    public String toString() {
        return "Fitur =" +fitur
                + "\tData =" + getData()
                + "\tFrek =" + frekuensi
                + "\tProposi="+ getProporsi()
                + "\tEntropy=" + getEntropy();
//To change body of generated methods, choose Tools | Templates.
    }
    
    public double getProporsi()
    {
        return (getFrekuensi() / (getTotalData() * 1d));
    }
    /**
     * Set the value of totalData
     *
     * @param totalData new value of totalData
     */
    public void setTotalData(double totalData) {
        this.totalData = totalData;
    }

    private long frekuensi;

    /**
     * Get the value of frekuensi
     *
     * @return the value of frekuensi
     */
    public long getFrekuensi() {
        return frekuensi;
    }

    /**
     * Set the value of frekuensi
     *
     * @param frekuensi new value of frekuensi
     */
    public void setFrekuensi(long frekuensi) {
        this.frekuensi = frekuensi;
    }

    private String dataString;

    /**
     * Get the value of dataString
     *
     * @return the value of dataString
     */
    public String getDataString() {
        return dataString;
    }

    /**
     * Set the value of dataString
     *
     * @param dataString new value of dataString
     */
    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    /**
     * Set the value of data
     *
     * @param data new value of data
     */
    public void setData(Object data) {
        this.data = data;
    }

    private String fitur;

    /**
     * Get the value of fitur
     *
     * @return the value of fitur
     */
    public String getFitur() {
        return fitur;
    }

    /**
     * Set the value of fitur
     *
     * @param fitur new value of fitur
     */
    public void setFitur(String fitur) {
        this.fitur = fitur;
    }
    
}
